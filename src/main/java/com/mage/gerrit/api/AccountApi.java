package com.mage.gerrit.api;

import com.mage.gerrit.model.AccountDetailInfo;
import com.mage.gerrit.model.AccountInfo;

public interface AccountApi {
    AccountInfo get(String username);

    AccountInfo get(String username, boolean withDetail);

    AccountDetailInfo detail(String username);
}
