package com.mage.gerrit.client;

import com.mage.gerrit.model.BaseModel;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GerritHttpConnection extends Closeable {

    /**
     * @param path       Endpoints,也就是url地址
     * @param elementCls list中元素类型的
     * @param listCls    返回的list的类型，一般可以是ArrayList
     * @param <E>        元素类型
     * @param <C>        集合类型
     * @return
     * @throws IOException
     */
    <E extends BaseModel, C extends List>
    List<E> get(String path, Class<E> elementCls, Class<C> listCls) throws IOException;

    <E extends BaseModel, K, M extends Map<K, E>>
    Map<K, E> get(String path, Class<E> elementCls, Class<M> mapCls, Class<K> keyCls) throws IOException;

    /**
     * @param path Endpoints,也就是url地址
     * @param cls
     * @param <E>
     * @return
     * @throws IOException
     */
    <E extends BaseModel>
    E get(String path, Class<E> cls) throws IOException;

    /**
     * 获取单个值的
     *
     * @param path
     * @return
     * @throws IOException
     */
    String get(String path) throws IOException;

    String get(String path, boolean raw) throws IOException;
}
