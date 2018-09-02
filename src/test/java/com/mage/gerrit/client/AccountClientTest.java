package com.mage.gerrit.client;

import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.AccountNameInput;
import com.mage.gerrit.model.ListAccountsOption;
import com.mage.gerrit.server.GerritServer;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.List;

import static com.mage.gerrit.utils.Utils.pprint;
import static org.junit.Assert.assertEquals;

public class AccountClientTest {
    private GerritHttpClient client;
    private GerritServer server;


    @Before
    public void before() throws Exception {
        URI uri = new URI("http://10.0.12.62:8080");
        client = new GerritHttpClient(uri, "bright.ma", "vc7WPNtrB3AxteP8IoW5UBU4iyVYXcnr8JakYosw7A");
        server = new GerritServer(client);
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
    public void testName() {
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
    public void testSetName() {
        AccountNameInput input = new AccountNameInput("new getFullName");
        String accountInfo = server.getAccount().setFullName(input);
        pprint(accountInfo);
    }


}