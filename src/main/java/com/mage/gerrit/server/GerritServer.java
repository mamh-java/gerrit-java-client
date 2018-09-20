package com.mage.gerrit.server;

import com.mage.gerrit.api.AccountApi;
import com.mage.gerrit.api.PluginApi;
import com.mage.gerrit.client.AccountClient;
import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.client.PluginClient;
import com.mage.gerrit.model.DocResult;
import com.mage.gerrit.model.ProjectAccessInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.joinParam;
import static com.mage.gerrit.utils.UrlUtils.joinPath;

public class GerritServer {
    private PluginApi plugin;
    private GerritHttpClient client;
    private AccountApi account;

    public GerritServer(URI serverUri) {
        NetRC netRC = new NetRC();
        String username = netRC.getEntry(serverUri.getHost()).login;
        String password = netRC.getEntry(serverUri.getHost()).password;
        this.client = new GerritHttpClient(serverUri, username, password);
        this.account = new AccountClient(client);
        this.plugin = new PluginClient(client);
    }

    public GerritServer(URI serverUri, String username, String password) {
        this(new GerritHttpClient(serverUri, username, password));
    }

    public GerritServer(String serverUri, String username, String password) throws URISyntaxException {
        this(new GerritHttpClient(serverUri, username, password));
    }

    public GerritServer(GerritHttpClient client) {
        this.client = client;
        this.account = new AccountClient(client);
    }

    public GerritHttpClient getClient() {
        return client;
    }

    public void setClient(GerritHttpClient client) {
        this.client = client;
    }

    public AccountApi getAccount() {
        return account;
    }

    public void setAccount(AccountApi account) {
        this.account = account;
    }


    public PluginApi getPlugin() {
        return plugin;
    }

    public void setPlugin(PluginApi plugin) {
        this.plugin = plugin;
    }

    public Map<String, ProjectAccessInfo> listAccess(List<String> projects) {
        String endpoint = joinParam("a/access/", "project", projects);
        return client.get(endpoint, ProjectAccessInfo.class, HashMap.class, String.class);
    }

    public Map<String, ProjectAccessInfo> listAccess(String... project) {
        List<String> projects = Arrays.asList(project);
        return listAccess(projects);
    }

    public String getVersion() {
        String endpoint = "/config/server/version";
        return client.getString(endpoint);
    }

    public List<DocResult> searchDocumentation(String pattern) {
        String endpoint = "/Documentation/";
        endpoint = joinPath(endpoint, "?q=" + pattern);

        return client.get(endpoint, DocResult.class, List.class);
    }

}
