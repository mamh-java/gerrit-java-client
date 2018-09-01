package com.mage.gerrit.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class UrlUtilsTest {
    private static final String ROOT_ENDPOINT = "a/accounts/";

    @Test
    public void testURIBuilder() throws URISyntaxException {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("suggest", null));
        list.add(new BasicNameValuePair("aaa", "bbb"));
        URI uri = new URIBuilder(ROOT_ENDPOINT).addParameters(list).build();
        System.out.println(uri.toString());
    }

    @Test
    public void testURIBuilder1() throws URISyntaxException {
        String url = "http://www.google.com/search/?suggest&hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=";
        URIBuilder uriBuilder = new URIBuilder(url);
        System.out.println(uriBuilder.getQueryParams());
    }

    /**
     * public static String joinParam(String root, List<NameValuePair> list)
     */
    @Test
    public void joinParam() {
        List<NameValuePair> list = Stream.of(
                new BasicNameValuePair("suggest", null),
                new BasicNameValuePair("username", "mage"),
                new BasicNameValuePair("password", "123"),
                new BasicNameValuePair("age", "22"))
                .collect(Collectors.toList());
        String expected = "/a/accounts/?suggest&username=mage&password=123&age=22";
        String path = UrlUtils.joinParam(ROOT_ENDPOINT, list);
        assertEquals(expected, path);
    }

    /**
     * public static String joinParam(String root, String name, List<String> values)
     */
    @Test
    public void joinParam1() {
        String expected = "/a/accounts/?suggest&username=mage&password=123&age=22";
        List<String> list = Stream.of(
                "git/android/kernel/msm-3.14",
                "git/android/kernel/lk"
        ).collect(Collectors.toList());
        String path = UrlUtils.joinParam(ROOT_ENDPOINT, "project", list);
        System.out.println(path);
    }


    /**
     * public static String joinPath(final String path1, final String path2)
     */
    @Test
    public void joinPath() {
        String expected = "a/accounts/mage/suggest";
        String mage = UrlUtils.joinPath("a/accounts", "mage/suggest");
        assertEquals(expected, mage);
        mage = UrlUtils.joinPath("a/accounts/", "mage/suggest");
        assertEquals(expected, mage);
        mage = UrlUtils.joinPath("a/accounts", "/mage/suggest");
        assertEquals(expected, mage);
        mage = UrlUtils.joinPath("a/accounts/", "/mage/suggest");
        assertEquals(expected, mage);

        expected = "a/accounts/mage/suggest/";
        mage = UrlUtils.joinPath("a/accounts/", "/mage/suggest/");
        assertEquals(expected, mage);
    }

    /**
     * public static URI joinPath(final URI uri, final String context, final String path)
     */
    @Test
    public void joinPath1() throws Exception {

        URI uri = new URI("http://10.0.63.21:8080");
        String context = "hibernate_01";
        String path = "a/accounts/suggest.jsp?username=mamge&password=123";
        URI uri1 = UrlUtils.joinPath(uri, context, path);

        System.out.println(uri1);

    }

    public class URIBuilder extends org.apache.http.client.utils.URIBuilder {
        public URIBuilder() {
        }

        public URIBuilder(String string) throws URISyntaxException {
            super(string);
        }

        public URIBuilder(URI uri) {
            super(uri);
        }

        public org.apache.http.client.utils.URIBuilder addPath(String subPath) {
            if (subPath == null || subPath.isEmpty() || "/".equals(subPath)) {
                return this;
            }
            return setPath(appendSegmentToPath(getPath(), subPath));
        }

        private String appendSegmentToPath(String path, String segment) {
            if (path == null || path.isEmpty()) {
                path = "/";
            }

            if (path.charAt(path.length() - 1) == '/' || segment.startsWith("/")) {
                return path + segment;
            }

            return path + "/" + segment;
        }
    }

    @Test
    public void test2() throws URISyntaxException {
        String url = "http://example.com/test";
        String expected = "http://example.com/test/example";

        URIBuilder builder = new URIBuilder(url);
        builder.addPath("/example");
        assertEquals(expected, builder.toString());

        builder = new URIBuilder(url);
        builder.addPath("example");
        assertEquals(expected, builder.toString());

        builder.addPath("");
        builder.addPath(null);
        assertEquals(expected, builder.toString());

        url = "http://example.com";
        expected = "http://example.com/example";

        builder = new URIBuilder(url);
        builder.addPath("/");
        assertEquals(url, builder.toString());
        builder.addPath("/example");
        assertEquals(expected, builder.toString());

    }


}