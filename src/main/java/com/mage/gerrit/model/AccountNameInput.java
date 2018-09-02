package com.mage.gerrit.model;

public class AccountNameInput extends BaseModel {
    private String name;

    public AccountNameInput() {
    }

    public AccountNameInput(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
