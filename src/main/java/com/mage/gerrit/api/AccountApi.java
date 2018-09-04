package com.mage.gerrit.api;

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

import java.util.List;
import java.util.Map;

public interface AccountApi {
    AccountInfo get();

    AccountInfo get(String username);

    AccountInfo get(String username, boolean withDetail);

    AccountDetailInfo detail(String username);

    String getFullName(String id);

    String getFullName();

    String getUserName();

    String getUserName(String id);

    String getActive(String id);

    String getActive();

    String getHttpPasswd(String id);

    String getHttpPasswd();

    List<EmailInfo> getEmails(String id, String emailId);

    List<EmailInfo> getEmails(String id);

    List<EmailInfo> getEmails();

    SshKeyInfo getSshKey(String id, String keyId);

    void deleteSshkey(String id, String keyId);

    List<SshKeyInfo> getSshKeys(String id);

    List<SshKeyInfo> getSshKeys();

    Map<String, GpgKeyInfo> getGpgkeys(String id);

    GpgKeyInfo getGpgkey(String id, String keyId);

    void deleteGpgkey(String id, String keyId);

    CapabilityInfo getCapabilities(String id);

    CapabilityInfo getCapabilities();

    CapabilityInfo getCapabilities(String id, List<String> filters);

    String getCapability(String id, String capabilityId);

    List<GroupInfo> getGroups(String id);

    String getAvatar(String id);

    String getAvatarUrl(String id);

    PreferencesInfo getPreferences(String id);

    DiffPreferencesInfo getDiffPreferences(String id);

    EditPreferencesInfo getEditPreferences(String id);

    List<ProjectWatchInfo> getWatchedProjects(String id);

    List<AccountExternalIdInfo> getExternal(String id);

    List<ChangeInfo> getStarredChanges(String id);

    List<ChangeInfo> getStarsChanges(String id);

    List<String> getStarLabels(String id, String changeId);

    List<ContributorAgreementInfo> getAgreements(String id);

    String setFullName(String id, AccountNameInput account);

    String setFullName(AccountNameInput account);

    List<AccountInfo> query(String query);

    List<AccountInfo> query(String query, String limit);

    List<AccountInfo> suggest(String query, String limit);

    List<AccountInfo> query(String query, String limit, boolean isSuggest, ListAccountsOption option);
}
