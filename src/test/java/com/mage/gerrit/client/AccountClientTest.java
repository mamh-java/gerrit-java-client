package com.mage.gerrit.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.server.GerritServer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;

public class AccountClientTest {
    private GerritHttpClient client;
    private GerritServer server;

    public static void pprint(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(o));
    }

    @Before
    public void before() throws Exception {
        URI uri = new URI("http://10.0.63.21:8081");
        client = new GerritHttpClient(uri, "bright.ma", "MteqipPO3d+kBW2PiFbaNIk8nfY2S6WDLyB81CThfg");
        server = new GerritServer(client);
    }

    @Test
    public void testGet() {
        AccountInfo account = server.getAccount().get();
        Assert.assertEquals(1000000, account.getId());
        pprint(account);
    }

    @Test
    public void testGet1() {
        AccountInfo account = server.getAccount().get("bright.ma");
        Assert.assertEquals(1000000, account.getId());
        pprint(account);
    }

    @Test
    public void testGet2() {
        AccountInfo account = server.getAccount().get("bright.ma", true);
        Assert.assertEquals(1000000, account.getId());

        pprint(account);
    }

    @Test
    public void testDetail() {
        AccountInfo account = server.getAccount().detail("bright.ma");
        Assert.assertEquals(1000000, account.getId());

        pprint(account);
    }


    @Test
    public void testQuery() {
        List<AccountInfo> accountInfos = server.getAccount().query("name:bright", "2");
        pprint(accountInfos);
    }


    @Test
    public void testQuery1() {
        List<AccountInfo> accountInfos = server.getAccount().query("ma");
        pprint(accountInfos);
    }
}