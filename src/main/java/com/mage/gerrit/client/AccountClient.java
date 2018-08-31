package com.mage.gerrit.client;

import com.mage.gerrit.client.GerritHttpClient;
import com.mage.gerrit.api.AccountApi;
import com.mage.gerrit.model.AccountDetailInfo;
import com.mage.gerrit.model.AccountInfo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.mage.gerrit.utils.UrlUtils.join;

public class AccountClient implements AccountApi {
    GerritHttpClient client;

    public AccountClient(GerritHttpClient client) {
        this.client = client;
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
            String endpoint = join("a/accounts/", username);
            if (withDetail) {
                endpoint = join(endpoint, "detail");
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
}
