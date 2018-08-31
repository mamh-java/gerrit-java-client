package com.mage.gerrit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.DocResult;
import com.mage.gerrit.model.ProjectAccessInfo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;
import java.util.Map;


public class GerritServerTest {

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

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getAccount(String username)
     */
    @Test
    public void testGetAccount() {
        AccountInfo account = server.getAccount("bright.ma");
        Assert.assertEquals(1000000, account.getId());
        pprint(account);
    }

    /**
     * Method: getAccount(String username, boolean withDetail)
     */
    @Test
    public void testGetAccountDetail() {
        AccountInfo account = server.getAccount("bright.ma", true);
        Assert.assertEquals(1000000, account.getId());
        Assert.assertNotNull(account.getRegOn());
        pprint(account);
    }

    /**
     * Method: listAccess(String project)
     */
    @Test
    public void testListAccess() throws Exception {
        String project1 = "git/shared/test";
        Map<String, ProjectAccessInfo> info = server.listAccess(project1);
        pprint(info);
    }

    /**
     * Method: listAccess(String project)
     */
    @Test
    public void testListAccess1() throws Exception {
        String project1 = "All-Projects";
        Map<String, ProjectAccessInfo> info = server.listAccess(project1);
        pprint(info);
    }

    /**
     * Method: listAccess(String project)
     */
    @Test
    public void testListAccess2() throws Exception {
        String project1 = "git/shared/test";
        String project2 = "git/shared/hibernate";
        Map<String, ProjectAccessInfo> info = server.listAccess(project1, project2);
        pprint(info);
    }

    @Test
    public void searchDocumentation() {
        List<DocResult> l = server.searchDocumentation("test");
        pprint(l);
    }
}
