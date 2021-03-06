package com.mage.gerrit.client;

import com.mage.gerrit.api.AccountApi;
import com.mage.gerrit.model.AccountDetailInfo;
import com.mage.gerrit.model.AccountExternalIdInfo;
import com.mage.gerrit.model.AccountInfo;
import com.mage.gerrit.model.AccountNameInput;
import com.mage.gerrit.model.CapabilityInfo;
import com.mage.gerrit.model.ChangeInfo;
import com.mage.gerrit.model.ContributorAgreementInfo;
import com.mage.gerrit.model.DiffPreferencesInfo;
import com.mage.gerrit.model.EditPreferencesInfo;
import com.mage.gerrit.model.EmailInfo;
import com.mage.gerrit.model.GpgKeyInfo;
import com.mage.gerrit.model.GroupInfo;
import com.mage.gerrit.model.ListAccountsOption;
import com.mage.gerrit.model.PreferencesInfo;
import com.mage.gerrit.model.ProjectWatchInfo;
import com.mage.gerrit.model.SshKeyInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Override
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
        String endpoint = joinPath(ROOT_ENDPOINT, username);
        if (withDetail) {
            endpoint = joinPath(endpoint, "detail");
            return client.get(endpoint, AccountDetailInfo.class);
        } else {
            return client.get(endpoint, AccountInfo.class);
        }
    }

    @Override
    public AccountDetailInfo detail(String username) {
        return (AccountDetailInfo) get(username, true);
    }

    private String getString(String id, String name) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, name);
        return client.getString(endpoint);
    }

    /**
     * Get Account Full Name
     * 'GET /accounts/{account-id}/name'
     *
     * @return
     */
    @Override
    public String getFullName(String id) {
        return getString(id, "name");
    }


    @Override
    public String getFullName() {
        return getFullName("self");
    }

    @Override
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
    @Override
    public String getUserName(String id) {
        return getString(id, "username");
    }

    /**
     * Get Active
     * 'GET /accounts/{account-id}/active'
     *
     * @param id
     * @return
     */
    @Override
    public String getActive(String id) {
        return getString(id, "active");
    }

    @Override
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
    @Override
    public String getHttpPasswd(String id) {
        return getString(id, "password.http");
    }

    @Override
    public String getHttpPasswd() {
        return getHttpPasswd("self");
    }

    @Override
    public List<EmailInfo> getEmails(String id, String emailId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "emails");
        if (StringUtils.isNotEmpty(emailId))
            endpoint = joinPath(endpoint, emailId);
        return client.get(endpoint, EmailInfo.class, List.class);

    }

    @Override
    public List<EmailInfo> getEmails(String id) {
        return getEmails(id, null);
    }

    @Override
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
    @Override
    public SshKeyInfo getSshKey(String id, String keyId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "sshkeys");
        endpoint = joinPath(endpoint, keyId);
        return client.get(endpoint, SshKeyInfo.class);

    }

    /**
     * Delete SSH Key
     * 'DELETE /accounts/{account-id}/sshkeys/{ssh-key-id}'
     * <p>
     * Deletes an SSH key of a user.
     * <p>
     * Request
     * DELETE /accounts/self/sshkeys/2 HTTP/1.0
     */
    @Override
    public void deleteSshkey(String id, String keyId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "sshkeys");
        endpoint = joinPath(endpoint, keyId);
        client.delete(endpoint);
    }

    /**
     * List SSH Keys
     * 'GET /accounts/{account-id}/sshkeys'
     *
     * @param id
     * @return
     */
    @Override
    public List<SshKeyInfo> getSshKeys(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "sshkeys");
        return client.get(endpoint, SshKeyInfo.class, List.class);
    }

    @Override
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
    @Override
    public Map<String, GpgKeyInfo> getGpgkeys(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gpgkeys");
        return client.get(endpoint, GpgKeyInfo.class, Map.class, String.class);
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
    @Override
    public GpgKeyInfo getGpgkey(String id, String keyId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gpgkeys");
        endpoint = joinPath(endpoint, keyId);
        return client.get(endpoint, GpgKeyInfo.class);
    }

    /**
     * Delete GPG Key
     * 'DELETE /accounts/{account-id}/gpgkeys/{gpg-key-id}'
     * <p>
     * Deletes a GPG key of a user.
     * <p>
     * Request
     * DELETE /accounts/self/gpgkeys/AFC8A49B HTTP/1.0
     */
    @Override
    public void deleteGpgkey(String id, String keyId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gpgkeys");
        endpoint = joinPath(endpoint, keyId);
        client.delete(endpoint);
    }


    /**
     * List Account Capabilities
     * 'GET /accounts/{account-id}/capabilities'
     * Returns the global capabilities that are enabled for the specified user.
     * <p>
     * If the global capabilities for the calling user should be listed, self can be used as account-id.
     * This can be used by UI tools to discover if administrative features are available to the caller,
     * so they can hide (or show) relevant UI actions.
     */
    @Override
    public CapabilityInfo getCapabilities(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "capabilities");
        return client.get(endpoint, CapabilityInfo.class);
    }

    @Override
    public CapabilityInfo getCapabilities() {
        return getCapabilities("self");
    }

    @Override
    public CapabilityInfo getCapabilities(String id, List<String> filters) {
        List<NameValuePair> list = filters.stream().map(
                p -> new BasicNameValuePair("q", p)).collect(Collectors.toList());

        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "capabilities");
        endpoint = joinParam(endpoint, list);
        return client.get(endpoint, CapabilityInfo.class);
    }


    /**
     * Check if you can create groups
     * GET /accounts/self/capabilities/createGroup HTTP/1.0
     * 这个在2.14版本中 返回的 不是 application/json 格式了(而是text/plain;charset=utf-8),shit
     *
     * @param id
     * @param capabilityId
     * @return
     */
    @Override
    public String getCapability(String id, String capabilityId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "capabilities");
        endpoint = joinPath(endpoint, capabilityId);
        return client.getString(endpoint);
    }

    /**
     * List Groups
     * 'GET /accounts/{account-id}/groups/'
     * <p>
     * Lists all groups that contain the specified user as a member.
     */
    @Override
    public List<GroupInfo> getGroups(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "groups");
        return client.get(endpoint, GroupInfo.class, List.class);
    }

    /**
     * Get Avatar
     * 'GET /accounts/{account-id}/avatar'
     * <p>
     * Retrieves the avatar image of the user.
     * <p>
     * With the size option (alias s) you can specify the preferred size in pixels (height and width).
     * 这个测试中是不能获取到的。
     */
    @Override
    public String getAvatar(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "avatar");
        return client.getString(endpoint);
    }

    /**
     * Get Avatar Change URL
     * 'GET /accounts/{account-id}/avatar.change.url'
     * <p>
     * Retrieves the URL where the user can change the avatar image.
     * 这个测试中是不能获取到的。
     */
    @Override
    public String getAvatarUrl(String id) {
        return getString(id, "avatar.change.url");
    }

    /**
     * Get User Preferences
     * 'GET /accounts/{account-id}/preferences'
     * <p>
     * Retrieves the user’s preferences.
     * As result the account preferences of the user are returned as a PreferencesInfo entity.
     * As result the account preferences of the user are returned as a PreferencesInfo entity.
     * <p>
     * Users may only retrieve the preferences for their own account,
     * unless they are an Administrator or a member of a group that is
     * granted the ModifyAccount capability, in which case they can retrieve
     * the preferences for any account.
     */
    @Override
    public PreferencesInfo getPreferences(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "preferences");
        return client.get(endpoint, PreferencesInfo.class);
    }

    /**
     * Get Diff Preferences
     * 'GET /accounts/{account-id}/preferences.diff'
     * <p>
     * Retrieves the diff preferences of a user.
     * <p>
     * Request
     * GET /a/accounts/self/preferences.diff HTTP/1.0
     * As result the diff preferences of the user are returned as a DiffPreferencesInfo entity.
     *
     * @param id
     * @return
     */
    @Override
    public DiffPreferencesInfo getDiffPreferences(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "preferences.diff");
        return client.get(endpoint, DiffPreferencesInfo.class);
    }

    /**
     * Get Edit Preferences
     * 'GET /accounts/{account-id}/preferences.edit'
     * <p>
     * Retrieves the edit preferences of a user.
     * <p>
     * Request
     * GET /a/accounts/self/preferences.edit HTTP/1.0
     * As result the edit preferences of the user are returned as a EditPreferencesInfo entity.
     */
    @Override
    public EditPreferencesInfo getEditPreferences(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "preferences.edit");
        return client.get(endpoint, EditPreferencesInfo.class);
    }

    /**
     * Get Watched Projects
     * 'GET /accounts/{account-id}/watched.projects'
     * <p>
     * Retrieves all projects a user is watching.
     * <p>
     * Request
     * GET /a/accounts/self/watched.projects HTTP/1.0
     * As result the watched projects of the user are returned as a list of
     * ProjectWatchInfo entities. The result is sorted by project name in ascending order.
     */
    @Override
    public List<ProjectWatchInfo> getWatchedProjects(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "watched.projects");
        return client.get(endpoint, ProjectWatchInfo.class, List.class);
    }


    /**
     * Get Account External IDs
     * 'GET /accounts/{account-id}/external.ids'
     * <p>
     * Retrieves the external ids of a user account.
     * <p>
     * Request
     * GET /a/accounts/self/external.ids HTTP/1.0
     * As result the external ids of the user are returned as a list of AccountExternalIdInfo entities.
     */
    @Override
    public List<AccountExternalIdInfo> getExternal(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "external.ids");
        return client.get(endpoint, AccountExternalIdInfo.class, List.class);
    }

    /**
     * Get Changes With Default Star
     * 'GET /accounts/{account-id}/starred.changes'
     * <p>
     * Gets the changes that were starred with the default star by the identified user account.
     * This URL endpoint is functionally identical to the changes query
     * GET /changes/?q=is:starred. The result is a list of ChangeInfo entities.
     * <p>
     * Request
     * GET /a/accounts/self/starred.changes
     */
    @Override
    public List<ChangeInfo> getStarredChanges(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "starred.changes");
        return client.get(endpoint, ChangeInfo.class, List.class);
    }

    /**
     * Get Starred Changes
     * 'GET /accounts/{account-id}/stars.changes'
     * <p>
     * Gets the changes that were starred with any label by the identified user account.
     * This URL endpoint is functionally identical to the changes query GET /changes/?q=has:stars.
     * The result is a list of ChangeInfo entities.
     * <p>
     * Request
     * GET /a/accounts/self/stars.changes
     *
     * @param id
     * @return
     */
    @Override
    public List<ChangeInfo> getStarsChanges(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "stars.changes");
        return client.get(endpoint, ChangeInfo.class, List.class);
    }

    /**
     * Get Star Labels From Change
     * 'GET /accounts/{account-id}/stars.changes/{change-id}'
     * <p>
     * Get star labels from a change.
     * <p>
     * Request
     * GET /a/accounts/self/stars.changes/myProject~master~I8473b95934b5732ac55d26311a706c9c2bde9940 HTTP/1.0
     * As response the star labels that the user applied on the change are returned.
     * The labels are lexicographically sorted.
     */
    @Override
    public List<String> getStarLabels(String id, String changeId) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "stars.changes");
        endpoint = joinPath(endpoint, changeId);
        return client.getList(endpoint);
    }

    /**
     * List Contributor Agreements
     * 'GET /accounts/{account-id}/agreements'
     * <p>
     * Gets a list of the user’s signed contributor agreements.
     * <p>
     * Request
     * GET /a/accounts/self/agreements HTTP/1.0
     * As response the user’s signed agreements are returned as
     * a list of ContributorAgreementInfo entities.
     * <p>
     * Method Not Allowed
     */
    @Override
    public List<ContributorAgreementInfo> getAgreements(String id) {
        id = StringUtils.isEmpty(id) ? "self" : id;

        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "agreements");
        return client.get(endpoint, ContributorAgreementInfo.class, List.class);
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
    @Override
    public String setFullName(String id, AccountNameInput account) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "name");
        return client.put(endpoint, account);
    }

    @Override
    public String setFullName(AccountNameInput account) {
        return setFullName("self", account);
    }

    /**
     * Query Account  'GET /accounts/'
     *
     * @param query
     * @return
     */
    @Override
    public List<AccountInfo> query(String query) {
        return query(query, null, false, null);
    }

    /**
     * GET /accounts/?q=name:John+email:example.com&n=2 HTTP/1.0
     *
     * @param query
     * @param limit
     * @return
     */
    @Override
    public List<AccountInfo> query(String query, String limit) {
        return query(query, limit, false, null);
    }

    /**
     * 专门用了 模糊查询的。也就是 suggest 查询模式
     *
     * @param query
     * @param limit
     * @return
     */
    @Override
    public List<AccountInfo> suggest(String query, String limit) {
        return query(query, limit, true, null);
    }

    /**
     * GET /accounts/?suggest&q=John HTTP/1.0
     *
     * @param query
     * @return
     */
    @Override
    public List<AccountInfo> query(String query, String limit, boolean isSuggest, ListAccountsOption option) {
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
    }


}
