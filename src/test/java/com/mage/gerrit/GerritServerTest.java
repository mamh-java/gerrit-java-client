package com.mage.gerrit;

import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.ProjectAccessInfo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.Map;


public class GerritServerTest {

    private GerritHttpClient client;
    private GerritServer server;

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
        System.out.println(account);
    }

    /**
     * Method: getAccount(String username, boolean withDetail)
     */
    @Test
    public void testGetAccountDetail() {
        AccountInfo account = server.getAccount("bright.ma", true);
        Assert.assertEquals(1000000, account.getId());
        Assert.assertNotNull(account.getRegOn());
        System.out.println(account);
    }

    /**
     * Method: listAccess(String project)
     */
    @Test
    public void testListAccess() throws Exception {
        String project1 = "git/shared/test";
        String project2 = "git/shared/tools/aais-java";
        Map<String, ProjectAccessInfo> info = server.listAccess(project1);
        Map<String, ProjectAccessInfo> info1 = server.listAccess(project1, project2, project1);

        System.out.println(info);
        System.out.println(info1);

    }

}
