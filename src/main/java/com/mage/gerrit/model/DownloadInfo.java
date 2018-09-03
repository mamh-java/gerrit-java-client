package com.mage.gerrit.model;

import java.util.List;
import java.util.Map;

public class DownloadInfo extends BaseModel {

    /**
     * The supported download schemes as a map which maps the scheme name to a of DownloadSchemeInfo entity.
     */
    private Map<String, DownloadSchemeInfo> schemes;


    /**
     * List of supported archive formats. Possible values are tgz, tar, tbz2 and txz.
     */
    private List archives;


    public Map<String, DownloadSchemeInfo> getSchemes() {
        return schemes;
    }

    public void setSchemes(Map<String, DownloadSchemeInfo> schemes) {
        this.schemes = schemes;
    }

    public List getArchives() {
        return archives;
    }

    public void setArchives(List archives) {
        this.archives = archives;
    }
}
