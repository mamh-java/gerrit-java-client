package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountInfo extends BaseModel {
    //_account_id=1000000, name=Minghui Ma, email=bright.ma@blackshark.com, username=bright.ma

    /**
     * The numeric ID of the account.
     */
    @JsonProperty("_account_id")
    private int id;

    /**
     * optional
     * <p>
     * The full name of the user.
     * Only set if detailed account information is requested.
     * See option DETAILED_ACCOUNTS for change queries
     * and option DETAILS for account queries.
     */
    private String name;

    /**
     * optional
     * <p>
     * The email address the user prefers to be contacted through.
     * Only set if detailed account information is requested.
     * See option DETAILED_ACCOUNTS for change queries
     * and options DETAILS and ALL_EMAILS for account queries.
     */
    private String email;

    /**
     * optional
     * <p>
     * A list of the secondary email addresses of the user.
     * Only set for account queries when the ALL_EMAILS option is set.
     */
    @JsonProperty("secondary_emails")
    private List<String> secondaryEmails;

    /**
     * optional
     * <p>
     * The username of the user.
     * Only set if detailed account information is requested.
     * See option DETAILED_ACCOUNTS for change queries
     * and option DETAILS for account queries.
     */
    private String username;


    /**
     * optional, not set if false
     * Whether the query would deliver more results if not limited.
     * Only set on the last account that is returned.
     */
    @JsonProperty("_more_accounts")
    private boolean moreAccounts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
