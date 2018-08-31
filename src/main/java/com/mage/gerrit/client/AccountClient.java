package com.mage.gerrit.client;

import com.mage.gerrit.api.AccountApi;
import com.mage.gerrit.model.AccountDetailInfo;
import com.mage.gerrit.model.AccountInfo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.join;
import static com.mage.gerrit.utils.UrlUtils.joinParam;

public class AccountClient implements AccountApi {
    GerritHttpClient client;

    public AccountClient(GerritHttpClient client) {
        this.client = client;
    }


    /**
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

    /**
     * GET /accounts/?q=name:John+email:example.com&n=2 HTTP/1.0
     *
     * @param query
     * @param limit
     * @return
     */
    List<AccountInfo> query(String query, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("n", limit);
        try {
            String endpoint = join("a/accounts/", joinParam(map));
            return client.get(endpoint, AccountInfo.class, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GET /accounts/?suggest&q=John HTTP/1.0
     *
     * @param query
     * @return
     */
    List<AccountInfo> query(String query) {
        try {
            String endpoint = join("a/accounts/", joinParam("suggest", "q", Arrays.asList(query)));
            return client.get(endpoint, AccountInfo.class, List.class);
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
