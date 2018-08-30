package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectAccessInfo extends BaseModel {
    private String revision;

    @JsonProperty("inherits_from")
    private ProjectInfo inheritsFrom;

    @JsonIgnore
    private String local;

    @JsonProperty("is_owner")
    private boolean isOwner;

    @JsonProperty("owner_of")
    private List ownerOf;

    @JsonProperty("can_upload")
    private boolean canUpload;

    @JsonProperty("can_add")
    private boolean canAdd;

    @JsonProperty("config_visible")
    private boolean configVisible;

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public ProjectInfo getInheritsFrom() {
        return inheritsFrom;
    }

    public void setInheritsFrom(ProjectInfo inheritsFrom) {
        this.inheritsFrom = inheritsFrom;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public List getOwnerOf() {
        return ownerOf;
    }

    public void setOwnerOf(List ownerOf) {
        this.ownerOf = ownerOf;
    }

    public boolean isCanUpload() {
        return canUpload;
    }

    public void setCanUpload(boolean canUpload) {
        this.canUpload = canUpload;
    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public boolean isConfigVisible() {
        return configVisible;
    }

    public void setConfigVisible(boolean configVisible) {
        this.configVisible = configVisible;
    }

    @Override
    public String toString() {
        return "\nProjectAccessInfo{" +
                "revision='" + revision + '\'' +
                ", inheritsFrom=" + inheritsFrom +
                ", local='" + local + '\'' +
                ", isOwner=" + isOwner +
                ", ownerOf=" + ownerOf +
                ", canUpload=" + canUpload +
                ", canAdd=" + canAdd +
                ", configVisible=" + configVisible +
                '}';
    }
}
