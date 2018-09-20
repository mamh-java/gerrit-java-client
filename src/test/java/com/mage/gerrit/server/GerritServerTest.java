package com.mage.gerrit.server;

import com.mage.gerrit.model.DocResult;
import com.mage.gerrit.model.PluginInfo;
import com.mage.gerrit.model.ProjectAccessInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.Utils.pprint;
import static org.junit.Assert.assertEquals;


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
        assertEquals("2.15.3", version);
    }

    @Test
    public void testGetPlugin() {
        Map<String, PluginInfo> map = server.getPlugin().get();
        assertEquals("replication", map.get("replication").getId());
        assertEquals("singleusergroup", map.get("singleusergroup").getId());
        assertEquals("commit-message-length-validator", map.get("commit-message-length-validator").getId());
        assertEquals("download-commands", map.get("download-commands").getId());
        assertEquals("reviewnotes", map.get("reviewnotes").getId());
    }


    @Test
    public void testGetPlugin1() {
        Map<String, PluginInfo> all = server.getPlugin().getAll();
        assertEquals(5, all.size());
    }

    @Test
    public void testGetPlugin2() {
        Map<String, PluginInfo> all = server.getPlugin().get(false, null, null, null, 0, 0);
        assertEquals(5, all.size());
    }

    @Test
    public void testGetPlugin3() {
        Map<String, PluginInfo> all = server.getPlugin().get(false, "rep", null, null, 0, 0);
        assertEquals("replication", all.get("replication").getId());
    }

    @Test
    public void testGetPlugin4() {
        Map<String, PluginInfo> all = server.getPlugin().get(false, null, "rep.*", null, 0, 0);
        assertEquals("replication", all.get("replication").getId());
    }

    @Test
    public void testGetPlugin5() {
        Map<String, PluginInfo> all = server.getPlugin().get(false, null, null, "t", 0, 0);
        assertEquals(3, all.size());
        assertEquals("replication", all.get("replication").getId());
    }

    @Test
    public void testGetStatus(){
        PluginInfo status = server.getPlugin().getStatus("replication");
        pprint(status);
    }

    @Test
    public void testEnable(){
        PluginInfo replication = server.getPlugin().enable("replication");
        pprint(replication);
    }

    @Test
    public void testDisable(){
        PluginInfo replication = server.getPlugin().disable("replication");
        pprint(replication);
    }

    @Test
    public void testReload(){
        //reload会 出现 [2018-09-20 15:23:11,114] [] Cancelling replication events 这种日志的。所有后台任务会被取消掉
        PluginInfo replication = server.getPlugin().reload("replication");
        pprint(replication);
    }
}
