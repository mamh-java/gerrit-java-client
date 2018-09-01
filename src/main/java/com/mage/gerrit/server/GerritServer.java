package com.mage.gerrit.server;

import com.mage.gerrit.client.AccountClient;
import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.model.DocResult;
import com.mage.gerrit.model.ProjectAccessInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.joinPath;
import static com.mage.gerrit.utils.UrlUtils.joinParam;

public class GerritServer {
    private GerritHttpClient client;
    private AccountClient account;

    public GerritServer(URI serverUri) {
        this(new GerritHttpClient(serverUri));
    }

    public GerritServer(URI serverUri, String username, String passwordOrToken) {
        this(new GerritHttpClient(serverUri, username, passwordOrToken));
    }

    public GerritServer(String serverUri, String username, String passwordOrToken) throws URISyntaxException {
        this(new GerritHttpClient(serverUri, username, passwordOrToken));
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

    public AccountClient getAccount() {
        return account;
    }

    public void setAccount(AccountClient account) {
        this.account = account;
    }

    public Map<String, ProjectAccessInfo> listAccess(List<String> projects) {
        try {
            String endpoint = joinParam("a/access/", "project", projects);
            return client.get(endpoint, ProjectAccessInfo.class, HashMap.class, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, ProjectAccessInfo> listAccess(String... project) {
        List<String> projects = Arrays.asList(project);
        return listAccess(projects);
    }


    public List<DocResult> searchDocumentation(String pattern) {
        String endpoint = "/Documentation/";
        endpoint = joinPath(endpoint, "?q=" + pattern);
        try {
            return client.get(endpoint, DocResult.class, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
