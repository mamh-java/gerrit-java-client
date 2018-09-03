package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PreferencesInfo extends BaseModel {

    /**
     * The number of changes to show on each page. Allowed values are 10, 25, 50, 100.
     */
    @JsonProperty("changes_per_page")
    private int changesPerPage;

    /**
     * Whether the site header should be shown.
     */
    @JsonProperty("show_site_header")
    private boolean showSiteHeader;


    /**
     * Whether to use the flash clipboard widget.
     */
    @JsonProperty("use_flash_clipboard")
    private boolean useFlashClipboard;


    /**
     * Whether to expand diffs inline instead of opening as separate page (PolyGerrit only).
     */
    @JsonProperty("expand_inline_diffs")
    private boolean expandInlineDiffs;

    /**
     * The type of download URL the user prefers to use.
     * May be any key from the schemes map in DownloadInfo.
     * 这个取值可能是 DownloadInfo 中 schemes 中的key值
     */
    @JsonProperty("download_scheme")
    private String downloadScheme;


    /**
     * The type of download command the user prefers to use.
     */
    @JsonProperty("download_command")
    private String downloadCommand;

    /**
     * The format to display the date in. Allowed values are STD, US, ISO, EURO, UK.
     */
    @JsonProperty("date_format")
    private String dateFormat;

    /**
     * The format to display the time in. Allowed values are HHMM_12, HHMM_24.
     */
    @JsonProperty("time_format")
    private String timeFormat;


    /**
     * Whether to show relative dates in the changes table.
     */
    @JsonProperty("relative_date_in_change_table")
    private boolean relativeDateInChangeTable;


    /**
     * The type of diff view to show. Allowed values are SIDE_BY_SIDE, UNIFIED_DIFF.
     */
    @JsonProperty("diff_view")
    private String diffView;

    /**
     * Whether to show the change sizes as colored bars in the change table.
     */
    @JsonProperty("size_bar_in_change_table")
    private boolean sizeBarInChangeTable;

    /**
     * Whether to show change number in the change table.
     */
    @JsonProperty("legacycid_in_change_table")
    private boolean legacycidInChangeTable;

    /**
     * The strategy used to displayed info in the review category column.
     * Allowed values are NONE, NAME, EMAIL, USERNAME, ABBREV.
     */
    @JsonProperty("review_category_strategy")
    private String reviewCategoryStrategy;

    /**
     * Whether to mute common path prefixes in file names in the file table.
     */
    @JsonProperty("mute_common_path_prefixes")
    private boolean muteCommonPathPrefixes;

    /**
     * Whether to insert Signed-off-by footer in changes created with the inline edit feature.
     */
    @JsonProperty("signed_off_by")
    private boolean signedOffBy;


    /**
     * The menu items of the MY top menu as a list of TopMenuItemInfo entities.
     */
    private List<TopMenuItemInfo> my;

    /**
     * The columns to display in the change table (PolyGerrit only).
     * The default is empty, which will default columns as determined by the frontend.
     */
    @JsonProperty("change_table")
    private List<String> changeTable;

    /**
     * A map of URL path pairs, where the first URL path is an alias for the second URL path.
     */
    @JsonProperty("url_aliases")
    private String urlAliases;

    /**
     * The type of email strategy to use. On ENABLED, the user will receive emails from Gerrit.
     * On CC_ON_OWN_COMMENTS the user will also receive emails for their own comments.
     * On DISABLED the user will not receive any email notifications from Gerrit.
     * Allowed values are ENABLED, CC_ON_OWN_COMMENTS, DISABLED.
     */
    @JsonProperty("email_strategy")
    private String emailStrategy;

    /**
     * The base which should be pre-selected in the 'Diff Against'
     * drop-down list when the change screen is opened for a merge commit.
     * Allowed values are AUTO_MERGE and FIRST_PARENT.
     */
    @JsonProperty("default_base_for_merges")
    private String defaultBaseDorMerges;

    /**
     * Whether to publish draft comments on push by default.
     */
    @JsonProperty("publish_comments_on_push")
    private boolean publishCommentsOnPush;


    public int getChangesPerPage() {
        return changesPerPage;
    }

    public void setChangesPerPage(int changesPerPage) {
        this.changesPerPage = changesPerPage;
    }

    public boolean isShowSiteHeader() {
        return showSiteHeader;
    }

    public void setShowSiteHeader(boolean showSiteHeader) {
        this.showSiteHeader = showSiteHeader;
    }

    public boolean isUseFlashClipboard() {
        return useFlashClipboard;
    }

    public void setUseFlashClipboard(boolean useFlashClipboard) {
        this.useFlashClipboard = useFlashClipboard;
    }

    public boolean isExpandInlineDiffs() {
        return expandInlineDiffs;
    }

    public void setExpandInlineDiffs(boolean expandInlineDiffs) {
        this.expandInlineDiffs = expandInlineDiffs;
    }

    public String getDownloadScheme() {
        return downloadScheme;
    }

    public void setDownloadScheme(String downloadScheme) {
        this.downloadScheme = downloadScheme;
    }

    public String getDownloadCommand() {
        return downloadCommand;
    }

    public void setDownloadCommand(String downloadCommand) {
        this.downloadCommand = downloadCommand;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isRelativeDateInChangeTable() {
        return relativeDateInChangeTable;
    }

    public void setRelativeDateInChangeTable(boolean relativeDateInChangeTable) {
        this.relativeDateInChangeTable = relativeDateInChangeTable;
    }

    public String getDiffView() {
        return diffView;
    }

    public void setDiffView(String diffView) {
        this.diffView = diffView;
    }

    public boolean isSizeBarInChangeTable() {
        return sizeBarInChangeTable;
    }

    public void setSizeBarInChangeTable(boolean sizeBarInChangeTable) {
        this.sizeBarInChangeTable = sizeBarInChangeTable;
    }

    public boolean isLegacycidInChangeTable() {
        return legacycidInChangeTable;
    }

    public void setLegacycidInChangeTable(boolean legacycidInChangeTable) {
        this.legacycidInChangeTable = legacycidInChangeTable;
    }

    public String getReviewCategoryStrategy() {
        return reviewCategoryStrategy;
    }

    public void setReviewCategoryStrategy(String reviewCategoryStrategy) {
        this.reviewCategoryStrategy = reviewCategoryStrategy;
    }

    public boolean isMuteCommonPathPrefixes() {
        return muteCommonPathPrefixes;
    }

    public void setMuteCommonPathPrefixes(boolean muteCommonPathPrefixes) {
        this.muteCommonPathPrefixes = muteCommonPathPrefixes;
    }

    public boolean isSignedOffBy() {
        return signedOffBy;
    }

    public void setSignedOffBy(boolean signedOffBy) {
        this.signedOffBy = signedOffBy;
    }

    public List<TopMenuItemInfo> getMy() {
        return my;
    }

    public void setMy(List<TopMenuItemInfo> my) {
        this.my = my;
    }

    public List<String> getChangeTable() {
        return changeTable;
    }

    public void setChangeTable(List<String> changeTable) {
        this.changeTable = changeTable;
    }

    public String getUrlAliases() {
        return urlAliases;
    }

    public void setUrlAliases(String urlAliases) {
        this.urlAliases = urlAliases;
    }

    public String getEmailStrategy() {
        return emailStrategy;
    }

    public void setEmailStrategy(String emailStrategy) {
        this.emailStrategy = emailStrategy;
    }

    public String getDefaultBaseDorMerges() {
        return defaultBaseDorMerges;
    }

    public void setDefaultBaseDorMerges(String defaultBaseDorMerges) {
        this.defaultBaseDorMerges = defaultBaseDorMerges;
    }

    public boolean isPublishCommentsOnPush() {
        return publishCommentsOnPush;
    }

    public void setPublishCommentsOnPush(boolean publishCommentsOnPush) {
        this.publishCommentsOnPush = publishCommentsOnPush;
    }
}
