package com.mage.gerrit.server;

import com.mage.gerrit.model.DocResult;
import com.mage.gerrit.model.ProjectAccessInfo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.Utils.pprint;


public class GerritServerTest {

    private GerritServer server;

    @Before
    public void before() throws Exception {
        URI uri = new URI("http://10.0.12.62:8080");
        server = new GerritServer(uri);
    }

    @After
    public void after() throws Exception {
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

    @Test
    public void testGetVersion() {
        String version = server.getVersion();
        Assert.assertEquals("2.15.3", version);
    }
}
