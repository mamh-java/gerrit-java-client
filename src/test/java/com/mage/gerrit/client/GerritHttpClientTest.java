package com.mage.gerrit.client;

import com.mage.gerrit.model.Account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;

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
    public void testGetAccount() throws Exception {
        Account o = client.get("a/accounts/bright.ma", Account.class);
        System.out.println(o);
    }

    /**
     * Method: get(String path, Class<T> cls)
     */
    @Test
    public void testGetAccountDetail() throws Exception {
        Account o = client.get("a/accounts/bright.ma/detail", Account.class);
        System.out.println(o);
    }

    /**
     * Method: get(String path, Class<E> eCls, Class<C> cCls)
     */
    @Test
    public void testGetAccounts() throws Exception {
        List o = client.get("a/accounts/?q=ma", Account.class, List.class);
        System.out.println(o);
    }

    /**
     * Method: get(String path)
     */
    @Test
    public void testGetAccountName() throws Exception {
        String o = client.get("a/accounts/bright.ma/name");
        System.out.println(o);
        Assert.assertEquals("Minghui Ma", o);
    }

    /**
     * Method: get(String path)
     */
    @Test
    public void testGetAccountUserName() throws Exception {
        String o = client.get("a/accounts/bright.ma/username");
        System.out.println(o);
        Assert.assertEquals("bright.ma", o);
    }
}
