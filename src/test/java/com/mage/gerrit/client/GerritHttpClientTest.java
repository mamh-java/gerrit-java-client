package com.mage.gerrit.client;

import com.mage.gerrit.model.AccountInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;

import static com.mage.gerrit.utils.Utils.pprint;

public class GerritHttpClientTest {

    private GerritHttpClient client;

    @Before
    public void before() throws Exception {
        URI uri = new URI("http://10.0.63.21:8081");
        client = new GerritHttpClient(uri, "bright.ma", "MteqipPO3d+kBW2PiFbaNIk8nfY2S6WDLyB81CThfg");
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: get(String path, Class<T> cls)
     */
    @Test
    public void testGet() throws Exception {
        AccountInfo o = client.get("a/accounts/bright.ma", AccountInfo.class);
        pprint(o);
    }


    /**
     * Method: get(String path, Class<E> eCls, Class<C> cCls)
     */
    @Test
    public void testGet1() throws Exception {
        List o = client.get("a/accounts/?q=b", AccountInfo.class, List.class);
        pprint(o);
    }


    /**
     * Method: get(String path)
     */
    @Test
    public void testGet2() throws Exception {
        String o = client.get("a/accounts/bright.ma/name");
        pprint(o);
    }

    /**
     * Method: get(String path, boolean raw)
     */
    @Test
    public void testGet3() throws Exception {
        String o = client.get("a/accounts/bright.ma/username", true);
        pprint(o);
    }

}
