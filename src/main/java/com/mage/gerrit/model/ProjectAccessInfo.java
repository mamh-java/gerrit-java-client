package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ProjectAccessInfo extends BaseModel {
    //The revision of the refs/meta/config branch from which the access rights were loaded.
    private String revision;

    //The parent project from which permissions are inherited as a ProjectInfo entity.
    @JsonProperty("inherits_from")
    private ProjectInfo inheritsFrom;


    //The local access rights of the project as a map that maps the refs to AccessSectionInfo entities.
    private Map<String, AccessSectionInfo> local;

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

    private Map<String, GroupInfo> groups;

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

    public Map<String, AccessSectionInfo> getLocal() {
        return local;
    }

    public void setLocal(Map<String, AccessSectionInfo> local) {
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

    public Map<String, GroupInfo> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupInfo> groups) {
        this.groups = groups;
    }


}
