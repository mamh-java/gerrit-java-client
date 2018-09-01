package com.mage.gerrit.utils;


import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


public final class UrlUtils {

    private static final int DEFAULT_BUFFER_SIZE = 64;

    private UrlUtils() {
        //do nothing
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param root 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String joinParam(String root, List<NameValuePair> list) {
        try {
            URI uri = new URIBuilder(root).addParameters(list).build();
            return uri.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static String joinParam(String root, String name, List<String> values) {
        List<NameValuePair> list = values.stream().map(p ->
                new BasicNameValuePair(name, p)).collect(Collectors.toList());
        return joinParam(root, list);
    }

    /**
     * Join two paths together taking into account leading/trailing slashes.
     *
     * @param path1 the first path
     * @param path2 the second path
     * @return the joins path
     */
    public static String joinPath(final String path1, final String path2) {
        if (path1.isEmpty() && path2.isEmpty()) return "";
        if (path1.isEmpty()) return path2;
        if (path2.isEmpty()) return path1;

        final StringBuilder sb = new StringBuilder(DEFAULT_BUFFER_SIZE);
        sb.append(path1);
        if (sb.charAt(sb.length() - 1) == '/') sb.setLength(sb.length() - 1);
        if (path2.charAt(0) != '/') sb.append('/');
        sb.append(path2);
        return sb.toString();
    }


    public static URI joinPath(final URI uri, final String context, final String path) {
        URIBuilder uriBuilder = new URIBuilder(uri);
        uriBuilder = addPath(uriBuilder, context);
        try {
            return uriBuilder.build().normalize().resolve(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String p = path;
        if (!p.matches("(?i)https?://.*")) {
            p = joinPath(context, p);
        }
        return uri.resolve("/").resolve(p.replace(" ", "%20"));
    }

    private static URIBuilder addPath(URIBuilder uriBuilder, String subPath) {
        if (subPath == null || subPath.isEmpty() || "/".equals(subPath)) {
            return uriBuilder;
        }
        return uriBuilder.setPath(appendSegmentToPath(uriBuilder.getPath(), subPath));
    }

    private static String appendSegmentToPath(String path, String segment) {
        if (path == null || path.isEmpty()) {
            path = "/";
        }

        if (path.charAt(path.length() - 1) == '/' || segment.startsWith("/")) {
            return path + segment;
        }

        return path + "/" + segment;
    }


}

