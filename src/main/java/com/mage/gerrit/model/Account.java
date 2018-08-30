package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Account extends BaseModel {
    //_account_id=1000000, name=Minghui Ma, email=bright.ma@blackshark.com, username=bright.ma

    @JsonProperty("_account_id")
    private int id;
    private String name;
    private String email;
    private String username;

    @JsonProperty("registered_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regOn;

    public Date getRegOn() {
        return regOn;
    }

    public void setRegOn(Date regOn) {
        this.regOn = regOn;
    }

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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", regOn='" + regOn + '\'' +
                '}';
    }
}
