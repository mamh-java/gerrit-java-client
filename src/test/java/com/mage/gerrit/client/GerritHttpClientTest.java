package com.mage.gerrit.client;

import com.mage.gerrit.model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

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

    @Test
    public void testGetAccounts() throws Exception {
        Object o = client.get("a/accounts/?q=ma", Object.class);
        System.out.println(o);
    }

    @Test
    public void testGetAccountDetail() throws Exception {
        Object o = client.get("a/accounts/bright.ma/detail", Account.class);
        System.out.println(o);
    }


    @Test
    public void testGetAccountName() throws Exception {
        String o = client.get("a/accounts/bright.ma/name", String.class);
        System.out.println(o);
    }


    @Test
    public void testGetAccountUserName() throws Exception {
        String o = client.get("a/accounts/bright.ma/username", String.class);
        System.out.println(o);
    }
}
