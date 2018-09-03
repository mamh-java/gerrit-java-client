package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupOptionsInfo extends BaseModel {
    /**
     * Whether the group is visible to all registered users.
     */
    @JsonProperty("visible_to_all")
    private boolean visibleToAll;

    public boolean isVisibleToAll() {
        return visibleToAll;
    }

    public void setVisibleToAll(boolean visibleToAll) {
        this.visibleToAll = visibleToAll;
    }
}
