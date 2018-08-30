package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectInfo {
    private String id;
    private String name;
    private String parent;
    private String description;
    private String state;
    private String branches;
    @JsonProperty("web_links")
    private String webLinks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    public String getWebLinks() {
        return webLinks;
    }

    public void setWebLinks(String webLinks) {
        this.webLinks = webLinks;
    }

    @Override
    public String toString() {
        return "\nProjectInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", branches='" + branches + '\'' +
                ", webLinks='" + webLinks + '\'' +
                '}';
    }
}
