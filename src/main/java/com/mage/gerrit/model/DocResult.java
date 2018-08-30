package com.mage.gerrit.model;

public class DocResult extends BaseModel {
    private String title;    //The title of the document.

    private String url;      //The URL of the document.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "\nDocResult{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
