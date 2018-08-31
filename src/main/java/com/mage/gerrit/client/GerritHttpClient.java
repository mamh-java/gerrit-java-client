package com.mage.gerrit.client;

import com.fasterxml.jackson.databind.JavaType;
import com.mage.gerrit.api.GerritHttpConnection;
import com.mage.gerrit.model.BaseModel;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class GerritHttpClient extends AbstractGerritHttpClient implements GerritHttpConnection {

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

    /**
     * 获取一个list
     *
     * @param path       Endpoints,也就是url地址
     * @param elementCls list中元素类型的
     * @param listCls    返回的list的类型，一般可以是ArrayList
     * @param <E>
     * @param <C>
     * @return
     * @throws IOException
     */
    @Override
    public <E extends BaseModel, C extends List>
    List<E> get(String path, Class<E> elementCls, Class<C> listCls) throws IOException {
        HttpResponse response = request(path, HttpGet.METHOD_NAME, null);
        byte[] bytes = getResponseBytes(response);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(listCls, elementCls);
        return mapper.readValue(bytes, javaType);

    }

    /**
     * 获取为一个map对象
     *
     * @param path
     * @param elementCls
     * @param mapCls
     * @param keyCls
     * @param <E>
     * @param <K>
     * @param <M>
     * @return
     * @throws IOException
     */
    @Override
    public <E extends BaseModel, K, M extends Map<K, E>>
    Map<K, E> get(String path, Class<E> elementCls, Class<M> mapCls, Class<K> keyCls) throws IOException {
        HttpResponse response = request(path, "GET", null);
        checkResponse(response);
        byte[] bytes = getResponseBytes(response);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(mapCls, keyCls, elementCls);
        return mapper.readValue(bytes, javaType);
    }


    /**
     * 获取为一个单个的对象
     *
     * @param path Endpoints,也就是url地址
     * @param cls
     * @param <E>
     * @return
     * @throws IOException
     */
    @Override
    public <E extends BaseModel>
    E get(String path, Class<E> cls) throws IOException {
        HttpResponse response = request(path, "GET", null);
        checkResponse(response);
        byte[] bytes = getResponseBytes(response);
        return mapper.readValue(bytes, cls);
    }

    @Override
    public String get(String path) throws IOException {
        HttpResponse response = request(path, "GET", null);
        checkResponse(response);
        byte[] bytes = getResponseBytes(response);
        return mapper.readValue(bytes, String.class);
    }

    @Override
    public String get(String path, boolean raw) throws IOException {
        HttpResponse response = request(path, "GET", null);
        checkResponse(response);
        byte[] bytes = getResponseBytes(response);
        if (raw) {
            return new String(bytes);
        } else {
            return mapper.readValue(bytes, String.class);//解析响应体内容,这个是获取字符串内容的。一般json体是一个单个值
        }
    }


}
