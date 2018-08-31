package com.mage.gerrit.utils;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public final class UrlUtils {

    /**
     * The default size to use for string buffers.
     */
    private static final int DEFAULT_BUFFER_SIZE = 64;

    /**
     * Utility Class.
     */
    private UrlUtils() {
        //do nothing
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String joinParam(Map<String, String> params) {
        return joinParam(null, params);
    }

    public static String joinParam(String first, Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder("?");
        if (StringUtils.isNotEmpty(first)) {
            prestr.append(first);
        }
        if (params.size() > 0) {
            prestr.append("&");
        }
        for (int idx = 0, len = keys.size(); idx < len; idx++) {
            String key = keys.get(idx);
            String value = params.get(key);
            if (idx == len - 1) {//拼接时，不包括最后一个&字符
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }

        return prestr.toString();
    }

    /**
     * 把list所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param list 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String joinParam(String key, List<String> list) {
        return joinParam(null, key, list);
    }

    public static String joinParam(String first, String key, List<String> list) {
        StringBuilder prestr = new StringBuilder("?");
        if (StringUtils.isNotEmpty(first)) {
            prestr.append(first);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            prestr.append("&");
        }
        for (int idx = 0, len = list.size(); idx < len; idx++) {
            String value = list.get(idx);
            if (idx == len - 1) {//拼接时，不包括最后一个&字符
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.toString();
    }

    /**
     * Join two paths together taking into account leading/trailing slashes.
     *
     * @param path1 the first path
     * @param path2 the second path
     * @return the joins path
     */
    public static String join(final String path1, final String path2) {
        if (path1.isEmpty() && path2.isEmpty()) return "";
        if (path1.isEmpty() && !path2.isEmpty()) return path2;
        if (path2.isEmpty() && !path1.isEmpty()) return path1;
        final StringBuilder sb = new StringBuilder(DEFAULT_BUFFER_SIZE);
        sb.append(path1);
        if (sb.charAt(sb.length() - 1) == '/') sb.setLength(sb.length() - 1);
        if (path2.charAt(0) != '/') sb.append('/');
        sb.append(path2);
        return sb.toString();
    }


    /**
     * Create a JSON URI from the supplied parameters.
     *
     * @param uri     the server URI
     * @param context the server context if any
     * @param path    the specific API path
     * @return new full URI instance
     */
    public static URI toJsonApiUri(final URI uri, final String context, final String path) {
        String p = path;
        if (!p.matches("(?i)https?://.*")) {
            p = join(context, p);
        }
        return uri.resolve("/").resolve(p.replace(" ", "%20"));
    }


    /**
     * Create a URI from the supplied parameters.
     *
     * @param uri     the server URI
     * @param context the server context if any
     * @param path    the specific API path
     * @return new full URI instance
     */
    public static URI toNoApiUri(final URI uri, final String context, final String path) {
        final String p = path.matches("(?i)https?://.*") ? path : join(context, path);
        return uri.resolve("/").resolve(p);
    }


}

