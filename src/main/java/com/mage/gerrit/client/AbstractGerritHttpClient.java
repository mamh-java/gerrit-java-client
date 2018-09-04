package com.mage.gerrit.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mage.gerrit.api.GerritHttpConnection;
import com.mage.gerrit.utils.UrlUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

public abstract class AbstractGerritHttpClient implements
        GerritHttpConnection {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    protected static final String MAGIC_PREFIX = ")]}'\n";
    protected static final String JSON_MIME_TYPE = ContentType.APPLICATION_JSON.getMimeType();

    protected String context;
    protected HttpContext localContext;


    protected URI uri;
    protected CloseableHttpClient client;
    protected ObjectMapper mapper;


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


    protected void setRequestBody(String requestBody, HttpRequestBase method) {
        if (requestBody != null) {
            ((HttpEntityEnclosingRequestBase) method).setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
        }
    }

    protected HttpResponse request(String path, String verb, String requestBody, Header... headers) throws IOException {
        HttpRequestBase method;
        URI uri = UrlUtils.joinPath(this.uri, context, path);
        switch (verb) {
            case HttpPost.METHOD_NAME:
                method = new HttpPost(uri);
                setRequestBody(requestBody, method);
                break;
            case HttpGet.METHOD_NAME:
                method = new HttpGet(uri);
                break;
            case HttpDelete.METHOD_NAME:
                method = new HttpDelete(uri);
                break;
            case HttpPut.METHOD_NAME:
                method = new HttpPut(uri);
                setRequestBody(requestBody, method);
                break;
            default:
                throw new IllegalStateException("Unknown or unsupported Http method: " + verb);
        }

        for (Header header : headers) {
            method.addHeader(header);
        }

        HttpResponse response = client.execute(method, localContext);

        checkResponse(response);


        return response;
    }

    //从response 中获取 一个字节数组，去除掉json开头的魔术字符串
    protected byte[] getResponseBytes(HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        byte[] bytes = IOUtils.toByteArray(content);
        bytes = Arrays.copyOfRange(bytes, MAGIC_PREFIX.length(), bytes.length);
        return bytes;
    }

    protected void checkResponse(HttpResponse response) throws HttpResponseException {
        int status = response.getStatusLine().getStatusCode();

        if (status < 200 || status >= 400) {
            throw new HttpResponseException(status, response.getStatusLine().getReasonPhrase());
        }

        if (status == 204) {
            return;
        }
        HttpEntity entity = response.getEntity();

        if (entity == null) {
            throw new HttpResponseException(status, response.getStatusLine().getReasonPhrase());
        }

        Header contentType = entity.getContentType();
        if (contentType != null && !contentType.getValue().contains(JSON_MIME_TYPE)) {
            throw new HttpResponseException(status, String.format("Expected JSON but got '%s'.", contentType.getValue()));
        }
    }


    protected ObjectMapper getDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }


    @Override
    public void close() throws IOException {
        try {
            client.close();
        } catch (final IOException ex) {
            LOGGER.debug("I/O exception closing client", ex);
        }
    }

    protected static HttpClientBuilder addAuth(final HttpClientBuilder builder, final URI uri, final String username, String password) {
        if (StringUtils.isNotBlank(username)) {
            CredentialsProvider provider = new BasicCredentialsProvider();
            AuthScope scope = new AuthScope(uri.getHost(), uri.getPort());//, "realm"
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            provider.setCredentials(scope, credentials);
            builder.setDefaultCredentialsProvider(provider);

            builder.addInterceptorFirst(new PreemptiveAuth());//1.1抢先认证(Preemptive Authentication)

            builder.addInterceptorLast(new UserAgentHttpRequestInterceptor());
        }
        return builder;
    }

    static class UserAgentHttpRequestInterceptor implements HttpRequestInterceptor {

        @Override
        public void process(final HttpRequest request, final HttpContext context) {
            Header existingUserAgent = request.getFirstHeader(HttpHeaders.USER_AGENT);
            String userAgent = String.format("gerrit-java-client/%s using %s", "1.0", existingUserAgent.getValue());
            request.setHeader(HttpHeaders.USER_AGENT, userAgent);
        }
    }

    protected static class PreemptiveAuth implements HttpRequestInterceptor {
        static final String PREEMPTIVE_AUTH = "preemptive-auth";

        @Override
        public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
            AuthState authState = (AuthState) context.getAttribute(HttpClientContext.TARGET_AUTH_STATE);

            if (authState.getAuthScheme() == null) {
                AuthScheme authScheme = (AuthScheme) context.getAttribute(PREEMPTIVE_AUTH);
                CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(HttpClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
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

}
