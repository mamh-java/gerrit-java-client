package com.mage.gerrit.client;

import com.mage.gerrit.api.AccountApi;
import com.mage.gerrit.model.AccountDetailInfo;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.AccountNameInput;
import com.mage.gerrit.model.EmailInfo;
import com.mage.gerrit.model.GpgKeyInfo;
import com.mage.gerrit.model.ListAccountsOption;
import com.mage.gerrit.model.SshKeyInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.joinParam;
import static com.mage.gerrit.utils.UrlUtils.joinPath;

public class AccountClient implements AccountApi {
    private static final String ROOT_ENDPOINT = "a/accounts/";
    private GerritHttpClient client;

    public AccountClient(GerritHttpClient client) {
        this.client = client;
    }


    /**
     * Get Account  'GET /accounts/{account-id}'
     * 什么参数都没有就获取自己本身账号信息
     *
     * @return
     */
    public AccountInfo get() {
        return get("self", false);
    }

    @Override
    public AccountInfo get(String username) {
        return get(username, false);
    }

    @Override
    public AccountInfo get(String username, boolean withDetail) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, username);
            if (withDetail) {
                endpoint = joinPath(endpoint, "detail");
                return client.get(endpoint, AccountDetailInfo.class);
            } else {
                return client.get(endpoint, AccountInfo.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountDetailInfo detail(String username) {
        return (AccountDetailInfo) get(username, true);
    }

    /**
     * Get Account Full Name
     * 'GET /accounts/{account-id}/name'
     *
     * @return
     */
    public String getFullName(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }

        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "name");
            return client.get(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFullName() {
        return getFullName("self");
    }

    public String getUserName() {
        return getUserName("self");
    }

    /**
     * Get Username
     * 'GET /accounts/{account-id}/getUserName'
     *
     * @param id
     * @return
     */
    public String getUserName(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "username");
            return client.get(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get Active
     * 'GET /accounts/{account-id}/active'
     *
     * @param id
     * @return
     */
    public String getActive(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "active");
            return client.get(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getActive() {
        return getActive("self");
    }

    /**
     * Get HTTP Password
     * 'GET /accounts/{account-id}/password.http'
     * 这个方法好像不能用,应该是取消了
     *
     * @param id
     * @return
     */
    public String getHttpPasswd(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "password.http");
            return client.get(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getHttpPasswd() {
        return getHttpPasswd("self");
    }

    public List<EmailInfo> getEmails(String id, String emailId) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "emails");
            if (StringUtils.isNotEmpty(emailId))
                endpoint = joinPath(endpoint, emailId);
            return client.get(endpoint, EmailInfo.class, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<EmailInfo> getEmails(String id) {
        return getEmails(id, null);
    }

    public List<EmailInfo> getEmails() {
        return getEmails("self", null);
    }

    /**
     * Get SSH Key
     * 'GET /accounts/{account-id}/sshkeys/{ssh-key-id}'
     *
     * @param id
     * @param keyId
     * @return
     */
    public SshKeyInfo getSshKey(String id, String keyId) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "sshkeys");
            endpoint = joinPath(endpoint, keyId);
            return client.get(endpoint, SshKeyInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * List SSH Keys
     * 'GET /accounts/{account-id}/sshkeys'
     *
     * @param id
     * @return
     */
    public List<SshKeyInfo> getSshKeys(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "sshkeys");
            return client.get(endpoint, SshKeyInfo.class, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SshKeyInfo> getSshKeys() {
        return getSshKeys("self");
    }

    /**
     * List GPG Keys
     * 'GET /accounts/{account-id}/gpgkeys'
     * Returns the GPG keys of an account.
     *
     * @param id
     * @return
     */
    public Map<String, GpgKeyInfo> getGpgkeys(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "gpgkeys");
            return client.get(endpoint, GpgKeyInfo.class, Map.class, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get GPG Key
     * 'GET /accounts/{account-id}/gpgkeys/{gpg-key-id}'
     * Retrieves a GPG key of a user.
     *
     * @param id
     * @param keyId
     * @return
     */
    public GpgKeyInfo getGpgkey(String id, String keyId) {
        if (StringUtils.isEmpty(id)) {
            id = "self";
        }
        try {
            String endpoint = joinPath(ROOT_ENDPOINT, id);
            endpoint = joinPath(endpoint, "gpgkeys");
            endpoint = joinPath(endpoint, keyId);
            return client.get(endpoint, GpgKeyInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Set Account Name
     * 'PUT /accounts/{account-id}/getFullName'
     * <p>
     * Sets the full getFullName of an account.
     * <p>
     * The new account getFullName must be provided in the request body inside an AccountNameInput entity.
     * <p>
     * Some realms may not allow to modify the account getFullName.
     * In this case the request is rejected with “405 Method Not Allowed”.
     *
     * @param id
     * @return
     */
    public String setFullName(String id, AccountNameInput account) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "name");
        try {
            return client.put(endpoint, account);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String setFullName(AccountNameInput account) {
        return setFullName("self", account);
    }

    /**
     * Query Account  'GET /accounts/'
     *
     * @param query
     * @return
     */
    List<AccountInfo> query(String query) {
        return query(query, null, false, null);
    }

    /**
     * GET /accounts/?q=name:John+email:example.com&n=2 HTTP/1.0
     *
     * @param query
     * @param limit
     * @return
     */
    List<AccountInfo> query(String query, String limit) {
        return query(query, limit, false, null);
    }

    /**
     * 专门用了 模糊查询的。也就是 suggest 查询模式
     *
     * @param query
     * @param limit
     * @return
     */
    List<AccountInfo> suggest(String query, String limit) {
        return query(query, limit, true, null);
    }

    /**
     * GET /accounts/?suggest&q=John HTTP/1.0
     *
     * @param query
     * @return
     */
    List<AccountInfo> query(String query, String limit, boolean isSuggest, ListAccountsOption option) {
        try {
            List<NameValuePair> list = new ArrayList<>();
            if (isSuggest) {//如果是suggest搜索就加上这个 suggest 参数
                list.add(new BasicNameValuePair("suggest", null));
            }

            list.add(new BasicNameValuePair("q", query));

            if (StringUtils.isNotEmpty(limit) && StringUtils.isNumeric(limit)) {
                list.add(new BasicNameValuePair("n", limit));//默认是10个
            }
            if (option != null) {
                list.add(new BasicNameValuePair("o", option.toString()));
            }
            String endpoint = joinParam(ROOT_ENDPOINT, list);
            return client.get(endpoint, AccountInfo.class, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
