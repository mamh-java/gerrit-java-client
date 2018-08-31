package com.mage.gerrit.model;

public class PermissionRuleInfo extends BaseModel {
    public enum Action {
        ALLOW,
        DENY,
        BLOCK,
        INTERACTIVE,
        BATCH
    }

    private Action action;

    private boolean force;

    private int min;
    private int max;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
