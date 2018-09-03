package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditPreferencesInfo extends BaseModel {
    /**
     * theme
     * <p>
     * The CodeMirror theme name in upper case, for example DEFAULT.
     * All the themes from the CodeMirror release that Gerrit is using are available.
     */
    private String theme;


    /**
     * key_map_type
     * <p>
     * The CodeMirror key map. Currently only a subset of key maps are
     * supported: DEFAULT, EMACS, SUBLIME, VIM.
     */
    @JsonProperty("key_map_type")
    private String keyMapType;


    /**
     * tab_size
     * <p>
     * Number of spaces that should be used to display one tab.
     */
    @JsonProperty("tab_size")
    private int tabSize;


    /**
     * line_length
     * <p>
     * Number of characters that should be displayed per line.
     */
    @JsonProperty("line_length")
    private int lineLength;

    /**
     * indent_unit
     * <p>
     * Number of spaces that should be used for auto-indent.
     */
    @JsonProperty("indent_unit")
    private int indentUnit;


    /**
     * cursor_blink_rate
     * <p>
     * Half-period in milliseconds used for cursor blinking. Setting it to 0 disables cursor blinking.
     */
    @JsonProperty("cursor_blink_rate")
    private int cursorBlinkRate;

    /**
     * hide_top_menu
     * <p>
     * not set if false
     * <p>
     * If true the top menu header and site header is hidden.
     */
    @JsonProperty("hide_top_menu")
    private boolean hideTopMrenu;


    /**
     * show_tabs
     * <p>
     * not set if false
     * <p>
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
     * syntax_highlighting
     * not set if false
     * Whether syntax highlighting should be enabled.
     */
    @JsonProperty("syntax_highlighting")
    private boolean syntaxHighlighting;

    /**
     * hide_line_numbers
     * not set if false
     * If true the line numbers are hidden.
     */
    @JsonProperty("hide_line_numbers")
    private boolean hideLineNumbers;

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


    /**
     * indent_with_tabs
     * <p>
     * not set if false
     * <p>
     * Whether to indent with tabs or not.
     */
    @JsonProperty("indent_with_tabs")
    private boolean indentWithTabs;

    /**
     * auto_close_brackets
     * <p>
     * not set if false
     * <p>
     * Whether brackets and quotes should be auto-closed during typing.
     */
    @JsonProperty("auto_close_brackets")
    private boolean autoCloseBrackets;

    /**
     * show_base
     * <p>
     * not set if false
     * <p>
     * Whether to show the inline edit base version or not.
     */
    @JsonProperty("show_base")
    private boolean showBase;


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getKeyMapType() {
        return keyMapType;
    }

    public void setKeyMapType(String keyMapType) {
        this.keyMapType = keyMapType;
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getIndentUnit() {
        return indentUnit;
    }

    public void setIndentUnit(int indentUnit) {
        this.indentUnit = indentUnit;
    }

    public int getCursorBlinkRate() {
        return cursorBlinkRate;
    }

    public void setCursorBlinkRate(int cursorBlinkRate) {
        this.cursorBlinkRate = cursorBlinkRate;
    }

    public boolean isHideTopMrenu() {
        return hideTopMrenu;
    }

    public void setHideTopMrenu(boolean hideTopMrenu) {
        this.hideTopMrenu = hideTopMrenu;
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

    public boolean isSyntaxHighlighting() {
        return syntaxHighlighting;
    }

    public void setSyntaxHighlighting(boolean syntaxHighlighting) {
        this.syntaxHighlighting = syntaxHighlighting;
    }

    public boolean isHideLineNumbers() {
        return hideLineNumbers;
    }

    public void setHideLineNumbers(boolean hideLineNumbers) {
        this.hideLineNumbers = hideLineNumbers;
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

    public boolean isIndentWithTabs() {
        return indentWithTabs;
    }

    public void setIndentWithTabs(boolean indentWithTabs) {
        this.indentWithTabs = indentWithTabs;
    }

    public boolean isAutoCloseBrackets() {
        return autoCloseBrackets;
    }

    public void setAutoCloseBrackets(boolean autoCloseBrackets) {
        this.autoCloseBrackets = autoCloseBrackets;
    }

    public boolean isShowBase() {
        return showBase;
    }

    public void setShowBase(boolean showBase) {
        this.showBase = showBase;
    }
}
