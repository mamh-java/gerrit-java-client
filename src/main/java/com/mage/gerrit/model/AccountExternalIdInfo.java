package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountExternalIdInfo extends BaseModel {
    /**
     * identity
     * <p>
     * The account external id.
     */
    private String identity;

    /**
     * email
     * <p>
     * optional
     * <p>
     * The email address for the external id.
     */
    private String email;

    /**
     * trusted
     * <p>
     * not set if false
     * <p>
     * Whether the external id is trusted.
     */
    private boolean trusted;

    /**
     * can_delete
     * <p>
     * not set if false
     * <p>
     * Whether the external id can be deleted by the calling user.
     */
    @JsonProperty("can_delete")
    private boolean canDelete;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTrusted() {
        return trusted;
    }

    public void setTrusted(boolean trusted) {
        this.trusted = trusted;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
