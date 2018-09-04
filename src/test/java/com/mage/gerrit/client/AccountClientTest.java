package com.mage.gerrit.client;

import com.mage.gerrit.model.AccountExternalIdInfo;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.AccountNameInput;
import com.mage.gerrit.model.CapabilityInfo;
import com.mage.gerrit.model.ChangeInfo;
import com.mage.gerrit.model.ContributorAgreementInfo;
import com.mage.gerrit.model.DiffPreferencesInfo;
import com.mage.gerrit.model.EditPreferencesInfo;
import com.mage.gerrit.model.EmailInfo;
import com.mage.gerrit.model.GroupInfo;
import com.mage.gerrit.model.ListAccountsOption;
import com.mage.gerrit.model.PreferencesInfo;
import com.mage.gerrit.model.ProjectWatchInfo;
import com.mage.gerrit.model.QueueType;
import com.mage.gerrit.model.SshKeyInfo;
import com.mage.gerrit.server.GerritServer;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mage.gerrit.utils.Utils.pprint;
import static org.junit.Assert.assertEquals;

public class AccountClientTest {
    private GerritServer server;


    @Before
    public void before() throws Exception {
        URI uri = new URI("http://10.0.12.62:8080");
        server = new GerritServer(uri);
    }

    /**
     * test method     public AccountInfo get()
     */
    @Test
    public void testGet() {
        AccountInfo account = server.getAccount().get();
        assertEquals(1000000, account.getId());
    }

    /**
     * test method  public AccountInfo get(String username)
     */
    @Test
    public void testGet1() {
        AccountInfo account = server.getAccount().get("bright.ma");
        assertEquals(1000000, account.getId());
    }

    /**
     * test method  public AccountInfo get(String username, boolean withDetail)
     */
    @Test
    public void testGet2() {
        AccountInfo account = server.getAccount().get("bright.ma", true);
        assertEquals(1000000, account.getId());
    }

    /**
     * test method     public AccountDetailInfo detail(String username)
     */
    @Test
    public void testDetail() {
        AccountInfo account = server.getAccount().detail("bright.ma");
        assertEquals(1000000, account.getId());
    }

    /**
     * test method  List<AccountInfo> query(String query)
     */
    @Test
    public void testQuery0() {
        List<AccountInfo> accountInfos = server.getAccount().query("name:bright");
        assertEquals(1000000, accountInfos.get(0).getId());
    }

    /**
     * test method List<AccountInfo> query(String query, String limit)
     */
    @Test
    public void testQuery1() {
        List<AccountInfo> accountInfos = server.getAccount().query("name:b", "5");
        assertEquals(5, accountInfos.size());
    }

    /**
     * test method List<AccountInfo> query(String query, String limit)
     */
    @Test
    public void testQuery3() {
        List<AccountInfo> accountInfos = server.getAccount().query("bri", "1");
        assertEquals(1, accountInfos.size());
    }

    /**
     * test method List<AccountInfo> query(String query)
     */
    @Test
    public void testQuery4() {
        List<AccountInfo> accountInfos = server.getAccount().query("bri");
        assertEquals(2, accountInfos.size());
    }

    /**
     * test method List<AccountInfo> query(String query, String limit, boolean isSuggest, ListAccountsOption option)
     */
    @Test
    public void testQuery5() {
        List<AccountInfo> accountInfos = server.getAccount().query("bri", "1", true, ListAccountsOption.DETAILS);
        assertEquals(1, accountInfos.size());
    }

    @Test
    public void testSuggest() {
        List<AccountInfo> accountInfos = server.getAccount().suggest("Ma", "5");
        assertEquals(5, accountInfos.size());
    }

    @Test
    public void testGetFullName() {
        String name = server.getAccount().getFullName();
        assertEquals("Minghui Ma", name);
        name = server.getAccount().getFullName("1000001");
        assertEquals("Buildfarm", name);
    }

    @Test
    public void testUsername() {
        String username = server.getAccount().getUserName();
        System.out.println(username);
    }

    @Test
    public void testGetActive() {
        String active = server.getAccount().getActive();
        assertEquals("ok", active);

        active = server.getAccount().getActive("bright.ma");
        assertEquals("ok", active);

        active = server.getAccount().getActive("1000001");
        assertEquals("ok", active);

        active = server.getAccount().getActive("buildfarm@blackshark.com");
        assertEquals("ok", active);

    }

    @Test
    public void testGetActive1() {
        String active = server.getAccount().getActive("jira@blackshark.com");
        System.out.println(active);
        assertEquals(null, active);
    }


    @Test
    public void testGetHttpPasswd() {
        String pw = server.getAccount().getHttpPasswd("");
        System.out.println(pw);
        server.getAccount().getHttpPasswd("bright.ma");
        System.out.println(pw);
    }

    @Test
    public void testGetEmails() {
        List<EmailInfo> emails = server.getAccount().getEmails();
        pprint(emails);

        emails = server.getAccount().getEmails("jenkins");
        pprint(emails);
    }

    @Test
    public void testGetSshKeys() {
        SshKeyInfo sshKeys = server.getAccount().getSshKey("self", "8");
        pprint(sshKeys);
    }

    @Test
    public void testGetSshKeys1() {
        List<SshKeyInfo> sshKeys = server.getAccount().getSshKeys();
        pprint(sshKeys);
    }

    @Test
    public void testGetSshKeys2() {
        List<SshKeyInfo> sshKeys = server.getAccount().getSshKeys("test");
        pprint(sshKeys);
    }

    @Test
    public void testGetCapabilities() {
        CapabilityInfo capabilityInfo = server.getAccount().getCapabilities();
        pprint(capabilityInfo.getPriority());

        capabilityInfo = server.getAccount().getCapabilities("test");
        pprint(capabilityInfo);
    }

    @Test
    public void testGetCapabilities1() {
        List<String> list = Stream.of("createAccount", "createGroup", "priority")
                .collect(Collectors.toList());
        CapabilityInfo capabilityInfo = server.getAccount().getCapabilities("self", list);
        assertEquals(true, capabilityInfo.isCreateAccount());
        assertEquals(true, capabilityInfo.isCreateGroup());
        assertEquals(QueueType.INTERACTIVE, capabilityInfo.getPriority());
    }

    @Test
    public void testGetCapability() {
        String s = server.getAccount().getCapability("self", "createGroup");
        System.out.println(s);
    }

    @Test
    public void testSetName() {
        AccountNameInput input = new AccountNameInput("new getFullName");
        String accountInfo = server.getAccount().setFullName(input);
        pprint(accountInfo);
    }


    @Test
    public void testGetGroups() {
        List<GroupInfo> groups = server.getAccount().getGroups(null);
        assertEquals(16, groups.size());

        groups = server.getAccount().getGroups("");
        assertEquals(16, groups.size());

    }

    @Test
    public void testGetAvatar() {
        String avatar = server.getAccount().getAvatar("self");
        System.out.println(avatar);
    }

    @Test
    public void testGetAvatarUrl() {
        String avatar = server.getAccount().getAvatar("self");
        System.out.println(avatar);
    }

    @Test
    public void testGetPreferences() {
        PreferencesInfo info = server.getAccount().getPreferences("");
        pprint(info);

        info = server.getAccount().getPreferences("self");
        pprint(info);
    }

    @Test
    public void testGetDiffPreferences() {
        DiffPreferencesInfo info = server.getAccount().getDiffPreferences("");
        assertEquals(10, info.getContext());
        assertEquals("DAY_3024", info.getTheme());
    }

    @Test
    public void testGetEditPreferences() {
        EditPreferencesInfo info = server.getAccount().getEditPreferences("");
        assertEquals("BASE16_LIGHT", info.getTheme());
        assertEquals("DEFAULT", info.getKeyMapType());
    }

    @Test
    public void testGetWatchedProjects() {
        List<ProjectWatchInfo> list = server.getAccount().getWatchedProjects("");
        assertEquals(2, list.size());

        list = server.getAccount().getWatchedProjects("bright.ma");
        assertEquals(2, list.size());
    }

    @Test
    public void testGetExternal() {
        List<AccountExternalIdInfo> external = server.getAccount().getExternal("");
        assertEquals("gerrit:bright.ma", external.get(0).getIdentity());

        external = server.getAccount().getExternal("jenkins");
        assertEquals("mailto:jenkins@zeusis.com", external.get(0).getIdentity());

    }

    @Test
    public void testGetStarredChanges() {
        List<ChangeInfo> l = server.getAccount().getStarredChanges("");
        pprint(l);
    }

    @Test
    public void testGetStarsChanges() {
        List<ChangeInfo> l = server.getAccount().getStarsChanges("");
        pprint(l);
    }

    @Test
    public void testGetStarLabels() {
        List<ChangeInfo> l = server.getAccount().getStarsChanges("");
        String changeId = l.get(0).getId();
        List<String> list = server.getAccount().getStarLabels("", changeId);
        assertEquals("star", list.get(0));
    }

    @Test
    public void testGetAgreements() {
        List<ContributorAgreementInfo> list = server.getAccount().getAgreements("");
        pprint(list);
    }

    @Test
    public void testDeleteSshkey() {
        server.getAccount().deleteSshkey("self", "9");
    }
}