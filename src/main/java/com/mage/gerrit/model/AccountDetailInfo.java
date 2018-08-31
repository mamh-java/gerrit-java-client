package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class AccountDetailInfo extends AccountInfo {
    /**
     * The timestamp of when the account was registered.
     */
    @JsonProperty("registered_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp regOn;


    /**
     * not set if false
     * <p>
     * Whether the account is inactive.
     */
    private boolean inactive;

    public Timestamp getRegOn() {
        return regOn;
    }

    public void setRegOn(Timestamp regOn) {
        this.regOn = regOn;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
}
