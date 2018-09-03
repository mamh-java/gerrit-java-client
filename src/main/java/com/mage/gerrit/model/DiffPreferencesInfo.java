package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiffPreferencesInfo extends BaseModel {

    /**
     * context
     * The number of lines of context when viewing a patch.
     */
    private int context;


    /**
     * theme
     * The CodeMirror theme name in upper case, for example DEFAULT.
     * All the themes from the CodeMirror release that Gerrit is using are available.
     */
    private String theme;


    /**
     * expand_all_comments
     * not set if false
     * Whether all inline comments should be automatically expanded.
     */
    @JsonProperty("expand_all_comments")
    private boolean expandAllComments;

    /**
     * ignore_whitespace
     * Whether whitespace changes should be ignored and if yes,
     * which whitespace changes should be ignored.Allowed values
     * are IGNORE_NONE, IGNORE_TRAILING,IGNORE_LEADING_AND_TRAILING, IGNORE_ALL.
     */
    @JsonProperty("ignore_whitespace")
    private String ignoreWhitespace;


    /**
     * intraline_difference
     * not set if false	Whether intraline differences should be highlighted.
     */
    @JsonProperty("intraline_difference")
    private boolean intralineDifference;


    /**
     * line_length
     * Number of characters that should be displayed in one line.
     */
    @JsonProperty("line_length")
    private int lineLength;


    /***
     * cursor_blink_rate
     * Half-period in milliseconds used for cursor blinking. Setting it to 0 disables cursor blinking.
     */
    @JsonProperty("cursor_blink_rate")
    private int cursorBlinkRate;


    /**
     * manual_review
     * not set if false
     * Whether the 'Reviewed' flag should not be set automatically on a patch when it is viewed.
     */
    @JsonProperty("manual_review")
    private boolean manualReview;

    /**
     * retain_header
     * not set if false
     * Whether the header that is displayed
     * above the patch (that either shows the commit message, the diff preferences,
     * the patch sets or the files) should be retained on file switch.
     */
    @JsonProperty("retain_header")
    private boolean retainHeader;

    /**
     * show_line_endings
     * not set if false
     * Whether Windows EOL/Cr-Lf should be displayed as '\r' in a dotted-line box.
     */
    @JsonProperty("show_line_endings")
    private boolean showLineEndings;


    /**
     * show_tabs
     * not set if false
     * Whether tabs should be shown.
     */
    @JsonProperty("show_tabs")
    private boolean showTabs;

    /**
     * show_whitespace_errors
     * not set if false
     * Whether whitespace errors should be shown.
     */
    @JsonProperty("show_whitespace_errors")
    private boolean showWhitespaceErrors;


    /**
     * skip_deleted
     * not set if false
     * Whether deleted files should be skipped on file switch.
     */
    @JsonProperty("skip_deleted")
    private boolean skipDeleted;


    /**
     * skip_uncommented
     * not set if false
     * Whether uncommented files should be skipped on file switch.
     */
    @JsonProperty("skip_uncommented")
    private boolean skipUncommented;

    /**
     * syntax_highlighting
     * not set if false
     * Whether syntax highlighting should be enabled.
     */
    @JsonProperty("syntax_highlighting")
    private boolean syntaxHighlighting;


    /**
     * hide_top_menu
     * not set if false
     * If true the top menu header and site header are hidden.
     */
    @JsonProperty("hide_top_menu")
    private boolean hideTopMenu;

    /**
     * auto_hide_diff_table_header
     * not set if false
     * If true the diff table header is automatically hidden when scrolling down more than half of a page.
     */
    @JsonProperty("auto_hide_diff_table_header")
    private boolean autoHideDiffTableHeader;

    /**
     * hide_line_numbers
     * not set if false
     * If true the line numbers are hidden.
     */
    @JsonProperty("hide_line_numbers")
    private boolean hideLineNumbers;

    /**
     * tab_size
     * Number of spaces that should be used to display one tab.
     */
    @JsonProperty("tab_size")
    private int tabSize;

    /**
     * font_size
     * Default font size in pixels for change to be displayed in the diff view.
     */
    @JsonProperty("font_size")
    private int fontSize;

    /**
     * 'hide_empty_pane'
     * not set if false
     * Whether empty panes should be hidden.
     * The left pane is empty when a file was added;
     * the right pane is empty when a file was deleted.
     */
    @JsonProperty("hide_empty_ane")
    private boolean hideEmptyPane;


    /**
     * match_brackets
     * not set if false	Whether matching brackets should be highlighted.
     */
    @JsonProperty("match_brackets")
    private boolean matchBrackets;


    /**
     * line_wrapping
     * not set if false	Whether to enable line wrapping or not.
     */
    @JsonProperty("line_wrapping")
    private boolean lineWrapping;

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isExpandAllComments() {
        return expandAllComments;
    }

    public void setExpandAllComments(boolean expandAllComments) {
        this.expandAllComments = expandAllComments;
    }

    public String getIgnoreWhitespace() {
        return ignoreWhitespace;
    }

    public void setIgnoreWhitespace(String ignoreWhitespace) {
        this.ignoreWhitespace = ignoreWhitespace;
    }

    public boolean isIntralineDifference() {
        return intralineDifference;
    }

    public void setIntralineDifference(boolean intralineDifference) {
        this.intralineDifference = intralineDifference;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getCursorBlinkRate() {
        return cursorBlinkRate;
    }

    public void setCursorBlinkRate(int cursorBlinkRate) {
        this.cursorBlinkRate = cursorBlinkRate;
    }

    public boolean isManualReview() {
        return manualReview;
    }

    public void setManualReview(boolean manualReview) {
        this.manualReview = manualReview;
    }

    public boolean isRetainHeader() {
        return retainHeader;
    }

    public void setRetainHeader(boolean retainHeader) {
        this.retainHeader = retainHeader;
    }

    public boolean isShowLineEndings() {
        return showLineEndings;
    }

    public void setShowLineEndings(boolean showLineEndings) {
        this.showLineEndings = showLineEndings;
    }

    public boolean isShowTabs() {
        return showTabs;
    }

    public void setShowTabs(boolean showTabs) {
        this.showTabs = showTabs;
    }

    public boolean isShowWhitespaceErrors() {
        return showWhitespaceErrors;
    }

    public void setShowWhitespaceErrors(boolean showWhitespaceErrors) {
        this.showWhitespaceErrors = showWhitespaceErrors;
    }

    public boolean isSkipDeleted() {
        return skipDeleted;
    }

    public void setSkipDeleted(boolean skipDeleted) {
        this.skipDeleted = skipDeleted;
    }

    public boolean isSkipUncommented() {
        return skipUncommented;
    }

    public void setSkipUncommented(boolean skipUncommented) {
        this.skipUncommented = skipUncommented;
    }

    public boolean isSyntaxHighlighting() {
        return syntaxHighlighting;
    }

    public void setSyntaxHighlighting(boolean syntaxHighlighting) {
        this.syntaxHighlighting = syntaxHighlighting;
    }

    public boolean isHideTopMenu() {
        return hideTopMenu;
    }

    public void setHideTopMenu(boolean hideTopMenu) {
        this.hideTopMenu = hideTopMenu;
    }

    public boolean isAutoHideDiffTableHeader() {
        return autoHideDiffTableHeader;
    }

    public void setAutoHideDiffTableHeader(boolean autoHideDiffTableHeader) {
        this.autoHideDiffTableHeader = autoHideDiffTableHeader;
    }

    public boolean isHideLineNumbers() {
        return hideLineNumbers;
    }

    public void setHideLineNumbers(boolean hideLineNumbers) {
        this.hideLineNumbers = hideLineNumbers;
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isHideEmptyPane() {
        return hideEmptyPane;
    }

    public void setHideEmptyPane(boolean hideEmptyPane) {
        this.hideEmptyPane = hideEmptyPane;
    }

    public boolean isMatchBrackets() {
        return matchBrackets;
    }

    public void setMatchBrackets(boolean matchBrackets) {
        this.matchBrackets = matchBrackets;
    }

    public boolean isLineWrapping() {
        return lineWrapping;
    }

    public void setLineWrapping(boolean lineWrapping) {
        this.lineWrapping = lineWrapping;
    }
}
