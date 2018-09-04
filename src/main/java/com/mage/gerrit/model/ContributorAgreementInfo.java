package com.mage.gerrit.model;

public class ContributorAgreementInfo extends BaseModel {
    /**
     * name
     * <p>
     * The name of the agreement.
     */
    private String name;

    /**
     * description
     * <p>
     * The description of the agreement.
     */
    private String description;

    /**
     * url
     * <p>
     * The URL of the agreement.
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
