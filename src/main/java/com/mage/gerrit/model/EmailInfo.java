package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailInfo extends BaseModel {
    private String email;

    private boolean preferred;

    @JsonProperty("pending_confirmation")
    private boolean pendingConfirmation;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public boolean isPendingConfirmation() {
        return pendingConfirmation;
    }

    public void setPendingConfirmation(boolean pendingConfirmation) {
        this.pendingConfirmation = pendingConfirmation;
    }
}
