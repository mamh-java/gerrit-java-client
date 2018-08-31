package com.mage.gerrit.model;

import java.util.Map;

public class AccessSectionInfo extends BaseModel {
    private Map<String, PermissionInfo> permissions;


    public Map<String, PermissionInfo> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, PermissionInfo> permissions) {
        this.permissions = permissions;
    }
}
