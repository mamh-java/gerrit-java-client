package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectWatchInfo extends BaseModel {
    /**
     * project
     * <p>
     * The name of the project.
     */
    private String project;

    /**
     * filter
     * <p>
     * optional
     * <p>
     * A filter string to be applied to the project.
     */
    private String filter;

    /**
     * notify_new_changes
     * <p>
     * optional
     * <p>
     * Notify on new changes.
     */
    @JsonProperty("notify_new_changes")
    private boolean notifyNewChanges;

    /**
     * notify_new_patch_sets
     * <p>
     * optional
     * <p>
     * Notify on new patch sets.
     */
    @JsonProperty("notify_new_patch_sets")
    private boolean notifyNewPatchSets;

    /**
     * notify_all_comments
     * <p>
     * optional
     * <p>
     * Notify on comments.
     */
    @JsonProperty("notify_all_comments")
    private boolean notifyAllComments;


    /**
     * notify_submitted_changes
     * <p>
     * optional
     * <p>
     * Notify on submitted changes.
     */
    @JsonProperty("notify_submitted_changes")
    private boolean notifySubmittedChanges;

    /**
     * notify_abandoned_changes
     *
     * optional
     *
     * Notify on abandoned changes.
     */
    @JsonProperty("notify_abandoned_changes")
    private boolean notifyAbandonedChanges;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isNotifyNewChanges() {
        return notifyNewChanges;
    }

    public void setNotifyNewChanges(boolean notifyNewChanges) {
        this.notifyNewChanges = notifyNewChanges;
    }

    public boolean isNotifyNewPatchSets() {
        return notifyNewPatchSets;
    }

    public void setNotifyNewPatchSets(boolean notifyNewPatchSets) {
        this.notifyNewPatchSets = notifyNewPatchSets;
    }

    public boolean isNotifyAllComments() {
        return notifyAllComments;
    }

    public void setNotifyAllComments(boolean notifyAllComments) {
        this.notifyAllComments = notifyAllComments;
    }

    public boolean isNotifySubmittedChanges() {
        return notifySubmittedChanges;
    }

    public void setNotifySubmittedChanges(boolean notifySubmittedChanges) {
        this.notifySubmittedChanges = notifySubmittedChanges;
    }

    public boolean isNotifyAbandonedChanges() {
        return notifyAbandonedChanges;
    }

    public void setNotifyAbandonedChanges(boolean notifyAbandonedChanges) {
        this.notifyAbandonedChanges = notifyAbandonedChanges;
    }
}
