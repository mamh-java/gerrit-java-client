package com.mage.gerrit.model;

import java.util.Map;

public class PermissionInfo extends BaseModel {

    private String label;

    private boolean exclusive;

    //The rules assigned for this permission as a map that maps the UUIDs of the groups for which the permission are assigned to PermissionRuleInfo entities.
    private Map<String, PermissionRuleInfo> rules;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Map<String, PermissionRuleInfo> getRules() {
        return rules;
    }

    public void setRules(Map<String, PermissionRuleInfo> rules) {
        this.rules = rules;
    }
}
