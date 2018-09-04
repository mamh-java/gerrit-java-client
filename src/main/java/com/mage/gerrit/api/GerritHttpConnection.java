package com.mage.gerrit.api;

import com.mage.gerrit.model.BaseModel;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

public interface GerritHttpConnection extends Closeable {

    /**
     * 获取为一个list对象，里面存放的元素类型是E
     *
     * @param path       Endpoints,也就是url地址
     * @param elementCls list中元素类型的
     * @param listCls    返回的list的类型，一般可以是ArrayList
     * @param <E>        元素类型
     * @param <C>        集合类型
     * @return list
     */
    <E extends BaseModel, C extends List>
    List<E> get(String path, Class<E> elementCls, Class<C> listCls);

    /**
     * 获取为一个map对象，map的key类似是K，value类型是E
     *
     * @param path   Endpoints,也就是url地址
     * @param eCls   元素类型
     * @param mapCls map类型
     * @param keyCls key的类型
     * @param <E>    元素类型
     * @param <K>    map类型
     * @param <M>    key的类型
     * @return 返回一个mapr
     */
    <E extends BaseModel, K, M extends Map<K, E>>
    Map<K, E> get(String path, Class<E> eCls, Class<M> mapCls, Class<K> keyCls);

    /**
     * 获取为一个单个的对象,类型是E
     *
     * @param path Endpoints,也就是url地址
     * @param cls  元素类型
     * @param <E>  元素类型
     * @return 单个元素
     */
    <E extends BaseModel>
    E get(String path, Class<E> cls);


    /**
     * @param path Endpoints,也就是url地址
     * @return 返回字符串list
     */
    List<String> getList(String path);

    /**
     * @param path Endpoints,也就是url地址
     * @return 返回一个字节数组
     */
    byte[] getRaw(String path);


    /**
     * 获取单个字符串值的
     *
     * @param path Endpoints,也就是url地址
     * @return 返回字符串
     */
    String getString(String path);

}
