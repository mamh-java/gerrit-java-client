package com.mage.gerrit.model;

public class TopMenuItemInfo extends BaseModel {
    /**
     * The URL of the menu item link.
     */
    private String url;
    /**
     * The name of the menu item.
     */
    private String name;


    /**
     * Target attribute of the menu item link.
     */
    private String target;

    /**
     * The id attribute of the menu item link.
     */
    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
