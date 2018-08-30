package com.mage.gerrit;

import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.model.Account;
import com.mage.gerrit.model.ProjectAccessInfo;
import com.mage.gerrit.utils.UrlUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.join;

public class GerritServer {
    private final GerritHttpClient client;

    public GerritServer(URI serverUri) {
        this(new GerritHttpClient(serverUri));
    }

    public GerritServer(URI serverUri, String username, String passwordOrToken) {
        this(new GerritHttpClient(serverUri, username, passwordOrToken));
    }

    public GerritServer(String serverUri, String username, String passwordOrToken) throws URISyntaxException {
        this(new GerritHttpClient(serverUri, username, passwordOrToken));
    }

    public GerritServer(final GerritHttpClient client) {
        this.client = client;
    }

    public Account getAccount(String username) {
        return getAccount(username, false);
    }

    public Account getAccount(String username, boolean withDetail) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        String endpoint = join("a/accounts/", username);
        if (withDetail) {
            endpoint = join(endpoint, "detail");
        }
        try {
            return client.get(endpoint, Account.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, ProjectAccessInfo> listAccess(String... project) {
        List<String> list = Arrays.asList(project);

        try {
            String s = UrlUtils.joinParam("project", list);
            String endpoint = join("a/access/", s);
            System.out.println(client.get(endpoint, true));
            return client.getMap(endpoint, ProjectAccessInfo.class, HashMap.class, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
