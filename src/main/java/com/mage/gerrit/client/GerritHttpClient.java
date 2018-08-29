package com.mage.gerrit.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mage.gerrit.utils.UrlUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class GerritHttpClient implements GerritHttpConnection {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private String context;
    private static final String JSON_MIME_TYPE = ContentType.APPLICATION_JSON.getMimeType();

    private URI uri;
    private CloseableHttpClient client;
    private HttpContext localContext;
    private ObjectMapper mapper;

    private static final String MAGIC_PREFIX = ")]}'\n";

    public GerritHttpClient(URI uri) {
        this(uri, HttpClientBuilder.create());
    }

    /**
     * Create an unauthenticated Gerrit HTTP client
     *
     * @param uri    Location of the Gerrit server (ex. http://localhost:8080)
     * @param client Configured CloseableHttpClient to be used
     */
    public GerritHttpClient(URI uri, CloseableHttpClient client) {
        this.context = uri.getPath();
        if (!context.endsWith("/")) {
            context += "/";
        }
        this.uri = uri;
        this.client = client;
        this.mapper = getDefaultMapper();
    }

    public GerritHttpClient(URI uri, HttpClientBuilder builder) {
        this(uri, builder.build());
    }

    public GerritHttpClient(String url, String username, String password) throws URISyntaxException {
        this(new URI(url), username, password);
    }

    public GerritHttpClient(URI uri, String username, String password) {
        this(uri, HttpClientBuilder.create(), username, password);
    }

    public GerritHttpClient(URI uri, HttpClientBuilder builder, String username, String password) {
        this(uri, addAuth(builder, uri, username, password));
        if (StringUtils.isNotBlank(username)) {
            localContext = new BasicHttpContext();
            localContext.setAttribute("preemptive-auth", new BasicScheme());
        }
    }


    protected static HttpClientBuilder addAuth(final HttpClientBuilder builder, final URI uri, final String username, String password) {
        if (StringUtils.isNotBlank(username)) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            AuthScope scope = new AuthScope(uri.getHost(), uri.getPort(), "realm");
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            provider.setCredentials(scope, credentials);
            builder.setDefaultCredentialsProvider(provider);

            builder.addInterceptorFirst(new PreemptiveAuth());
        }
        return builder;
    }

    public static class PreemptiveAuth implements HttpRequestInterceptor {

        @Override
        public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
            AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);

            if (authState.getAuthScheme() == null) {
                AuthScheme authScheme = (AuthScheme) context.getAttribute("preemptive-auth");
                CredentialsProvider credsProvider = (CredentialsProvider) context
                        .getAttribute(ClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                if (authScheme != null) {
                    Credentials creds = credsProvider
                            .getCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()));
                    if (creds == null) {
                        throw new HttpException("No credentials for preemptive authentication");
                    }
                    authState.update(authScheme, creds);
                }
            }
        }
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public void setClient(CloseableHttpClient client) {
        this.client = client;
    }

    /**
     * Get the local context.
     *
     * @return context
     */
    protected HttpContext getLocalContext() {
        return localContext;
    }


    /**
     * Set the local context.
     *
     * @param localContext the context
     */
    protected void setLocalContext(final HttpContext localContext) {
        this.localContext = localContext;
    }


    @Override
    public <T extends Object> T get(String path, Class<T> cls) throws IOException {
        HttpGet getMethod = new HttpGet(UrlUtils.toJsonApiUri(uri, context, path));
        HttpResponse response = client.execute(getMethod, localContext);
        try {
            checkResponse(response);

            return parseResponse(cls, response);//解析响应体内容
        } finally {
            EntityUtils.consume(response.getEntity());
            releaseConnection(getMethod);
        }
    }


    private void checkResponse(HttpResponse response) throws HttpResponseException {
        int status = response.getStatusLine().getStatusCode();
        if (status < 200 || status >= 400) {
            throw new HttpResponseException(status, response.getStatusLine().getReasonPhrase());
        }
        HttpEntity entity = response.getEntity();

        Header contentType = entity.getContentType();
        if (contentType != null && !contentType.getValue().contains(JSON_MIME_TYPE)) {
            throw new HttpResponseException(status, String.format("Expected JSON but got '%s'.", contentType.getValue()));
        }
    }


    private <T extends Object> T parseResponse(Class<T> cls, HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        byte[] bytes = IOUtils.toByteArray(content);
        LOGGER.debug(new String(bytes));
        bytes = Arrays.copyOfRange(bytes, MAGIC_PREFIX.length(), bytes.length);
        return mapper.readValue(bytes, cls);
    }

    private ObjectMapper getDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private void releaseConnection(HttpRequestBase httpRequestBase) {
        httpRequestBase.releaseConnection();
    }

    @Override
    public void close() throws IOException {
        try {
            client.close();
        } catch (final IOException ex) {
            LOGGER.debug("I/O exception closing client", ex);
        }
    }
}
