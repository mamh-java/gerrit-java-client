package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class ChangeInfo extends BaseModel {
    /**
     * id
     * <p>
     * The ID of the change in the format "'<project>~<branch>~<Change-Id>'",
     * where 'project', 'branch' and 'Change-Id' are URL encoded. For 'branch' the refs/heads/ prefix is omitted.
     */
    private String id;

    /**
     * project
     * <p>
     * The name of the project.
     */
    private String project;

    /**
     * branch
     * <p>
     * The name of the target branch.
     * The refs/heads/ prefix is omitted.
     */
    private String branch;

    /**
     * topic
     * <p>
     * optional
     * <p>
     * The topic to which this change belongs.
     */
    private String topic;


    /**
     * assignee
     * <p>
     * optional
     * <p>
     * The assignee of the change as an AccountInfo entity.
     */
    private AccountInfo assignee;


    /**
     * hashtags
     * <p>
     * optional
     * <p>
     * List of hashtags that are set on the change (only populated when NoteDb is enabled).
     * TODO
     */
    private Object hashtags;

    /**
     * change_id
     * <p>
     * The Change-Id of the change.
     */
    @JsonProperty("change_id")
    private String changeId;


    /**
     * subject
     * <p>
     * The subject of the change (header line of the commit message).
     */
    private String subject;

    /**
     * status
     * <p>
     * The status of the change (NEW, MERGED, ABANDONED).
     */
    private String status;

    /**
     * created
     * <p>
     * The timestamp of when the change was created.
     */
    @JsonProperty("created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp created;


    /**
     * updated
     * <p>
     * The timestamp of when the change was last updated.
     */
    @JsonProperty("updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp updated;


    /**
     * submitted
     * <p>
     * only set for merged changes
     * <p>
     * The timestamp of when the change was submitted.
     */
    @JsonProperty("submitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp submitted;


    /**
     * submitter
     * <p>
     * only set for merged changes
     * <p>
     * The user who submitted the change, as an AccountInfo entity.
     */
    @JsonProperty("submitter")
    private AccountInfo submitter;


    /**
     * starred
     * <p>
     * not set if false
     * <p>
     * Whether the calling user has starred this change with the default label.
     */
    private boolean starred;

    /**
     * stars
     * <p>
     * optional
     * <p>
     * A list of star labels that are applied by the calling user to this change.
     * The labels are lexicographically sorted.
     */
    private List<String> starts;

    /**
     * reviewed
     * <p>
     * not set if false
     * <p>
     * Whether the change was reviewed by the calling user. Only set if reviewed is requested.
     */
    private boolean reviewed;


    /**
     * submit_type
     * <p>
     * optional
     * <p>
     * The submit type of the change.
     * Not set for merged changes.
     */
    @JsonProperty("submit_type")
    private String submitType;

    /**
     * mergeable
     * <p>
     * optional
     * <p>
     * Whether the change is mergeable.
     * Not set for merged changes, or if the change has not yet been tested.
     */
    private boolean mergeable;

    /**
     * submittable
     * <p>
     * optional
     * <p>
     * Whether the change has been approved by the project submit rules.
     * Only set if requested.
     */
    private boolean submittable;

    /**
     * insertions
     * <p>
     * Number of inserted lines.
     */
    private int insertions;

    /**
     * deletions
     * <p>
     * Number of deleted lines.
     */
    private int deletions;

    /**
     * unresolved_comment_count
     * <p>
     * optional
     * <p>
     * Number of unresolved comments. Not set if the current change index doesn’t have the data.
     */
    @JsonProperty("unresolved_comment_count")
    private int unresolvedCommentCount;

    /**
     * _number
     * <p>
     * The legacy numeric ID of the change.
     */
    @JsonProperty("_number")
    private int number;


    /**
     * owner
     * <p>
     * The owner of the change as an AccountInfo entity.
     */
    private AccountInfo owner;

    /**
     * actions
     * <p>
     * optional
     * <p>
     * Actions the caller might be able to perform on this revision.
     * The information is a map of view name to ActionInfo entities.
     * TODO
     */
    private Object actions;

    /**
     * labels
     * <p>
     * optional
     * <p>
     * The labels of the change as a map that maps the label names to LabelInfo entries.
     * Only set if labels or detailed labels are requested.
     * TODO
     */
    private Object labels;

    /**
     * permitted_labels
     * <p>
     * optional
     * <p>
     * A map of the permitted labels that maps a label name to the list of values that are allowed for that label.
     * Only set if detailed labels are requested.
     * TODO
     */
    @JsonProperty("permitted_labels")
    private Object permittedLabels;


    /**
     * removable_reviewers
     * <p>
     * optional
     * <p>
     * The reviewers that can be removed by the calling user as a list of AccountInfo entities.
     * Only set if detailed labels are requested.
     */
    @JsonProperty("removable_reviewers")
    private List<AccountInfo> removableReviewers;


    /**
     * reviewers
     * <p>
     * optional
     * <p>
     * The reviewers as a map that maps a reviewer state to a list of AccountInfo entities.
     * Possible reviewer states are REVIEWER, CC and REMOVED.
     * REVIEWER: Users with at least one non-zero vote on the change.
     * CC: Users that were added to the change, but have not voted.
     * REMOVED: Users that were previously reviewers on the change, but have been removed.
     * Only set if detailed labels are requested.
     */
    private List<AccountInfo> reviewers;

    /**
     * pending_reviewers
     * <p>
     * optional
     * <p>
     * Updates to reviewers that have been made while the change was in the WIP state.
     * Only present on WIP changes and only if there are pending reviewer updates to report.
     * These are reviewers who have not yet been notified about being added to or removed from the change.
     * Only set if detailed labels are requested.
     */
    @JsonProperty("pending_reviewers")
    private List<AccountInfo> pendingReviewers;


    /**
     * reviewer_updates
     * <p>
     * optional
     * <p>
     * Updates to reviewers set for the change as ReviewerUpdateInfo entities.
     * Only set if reviewer updates are requested and if NoteDb is enabled.
     * TODO
     */
    @JsonProperty("reviewer_updates")
    private Object reviewerUpdates;

    /**
     * messages
     * <p>
     * optional
     * <p>
     * Messages associated with the change as a list of ChangeMessageInfo entities.
     * Only set if messages are requested.
     * TODO
     */
    private List<Object> messages;

    /**
     * current_revision
     * <p>
     * optional
     * <p>
     * The commit ID of the current patch set of this change.
     * Only set if the current revision is requested or if all revisions are requested.
     */
    @JsonProperty("current_revision")
    private String currentRevision;

    /**
     * revisions
     * <p>
     * optional
     * <p>
     * All patch sets of this change as a map that maps the commit ID of
     * the patch set to a RevisionInfo entity.
     * Only set if the current revision is requested (in which case it will only contain
     * a key for the current revision) or if all revisions are requested.
     * TODO
     */
    private Object revisions;

    /**
     * tracking_ids
     * <p>
     * optional
     * <p>
     * A list of TrackingIdInfo entities describing references to external tracking systems.
     * Only set if tracking ids are requested.
     */
    @JsonProperty("tracking_ids")
    private Object trackingIds;


    /**
     * _more_changes
     * <p>
     * optional, not set if false
     * <p>
     * Whether the query would deliver more results if not limited.
     * Only set on the last change that is returned.
     */
    @JsonProperty("_more_changes")
    private boolean moreChanges;

    /**
     * problems
     * <p>
     * optional
     * <p>
     * A list of ProblemInfo entities describing potential problems
     * with this change. Only set if CHECK is set.
     * TODO
     */
    private Object problems;

    /**
     * is_private
     * <p>
     * optional, not set if false
     * <p>
     * When present, change is marked as private.
     */
    @JsonProperty("is_private")
    private boolean isPrivate;

    /**
     * work_in_progress
     * <p>
     * optional, not set if false
     * <p>
     * When present, change is marked as Work In Progress.
     */
    @JsonProperty("work_in_progress")
    private boolean workInProgress;


    /**
     * has_review_started
     * <p>
     * optional, not set if false
     * <p>
     * When present, change has been marked Ready at some point in time.
     */
    @JsonProperty("has_review_started")
    private boolean hasReviewStarted;


    /**
     * revert_of
     * <p>
     * optional
     * <p>
     * The numeric Change-Id of the change that this change reverts.
     */
    @JsonProperty("revert_of")
    private int revertOf;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public AccountInfo getAssignee() {
        return assignee;
    }

    public void setAssignee(AccountInfo assignee) {
        this.assignee = assignee;
    }

    public Object getHashtags() {
        return hashtags;
    }

    public void setHashtags(Object hashtags) {
        this.hashtags = hashtags;
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public AccountInfo getSubmitter() {
        return submitter;
    }

    public void setSubmitter(AccountInfo submitter) {
        this.submitter = submitter;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public List<String> getStarts() {
        return starts;
    }

    public void setStarts(List<String> starts) {
        this.starts = starts;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    public boolean isSubmittable() {
        return submittable;
    }

    public void setSubmittable(boolean submittable) {
        this.submittable = submittable;
    }

    public int getInsertions() {
        return insertions;
    }

    public void setInsertions(int insertions) {
        this.insertions = insertions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public int getUnresolvedCommentCount() {
        return unresolvedCommentCount;
    }

    public void setUnresolvedCommentCount(int unresolvedCommentCount) {
        this.unresolvedCommentCount = unresolvedCommentCount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public AccountInfo getOwner() {
        return owner;
    }

    public void setOwner(AccountInfo owner) {
        this.owner = owner;
    }

    public Object getActions() {
        return actions;
    }

    public void setActions(Object actions) {
        this.actions = actions;
    }

    public Object getLabels() {
        return labels;
    }

    public void setLabels(Object labels) {
        this.labels = labels;
    }

    public Object getPermittedLabels() {
        return permittedLabels;
    }

    public void setPermittedLabels(Object permittedLabels) {
        this.permittedLabels = permittedLabels;
    }

    public List<AccountInfo> getRemovableReviewers() {
        return removableReviewers;
    }

    public void setRemovableReviewers(List<AccountInfo> removableReviewers) {
        this.removableReviewers = removableReviewers;
    }

    public List<AccountInfo> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<AccountInfo> reviewers) {
        this.reviewers = reviewers;
    }

    public List<AccountInfo> getPendingReviewers() {
        return pendingReviewers;
    }

    public void setPendingReviewers(List<AccountInfo> pendingReviewers) {
        this.pendingReviewers = pendingReviewers;
    }

    public Object getReviewerUpdates() {
        return reviewerUpdates;
    }

    public void setReviewerUpdates(Object reviewerUpdates) {
        this.reviewerUpdates = reviewerUpdates;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public String getCurrentRevision() {
        return currentRevision;
    }

    public void setCurrentRevision(String currentRevision) {
        this.currentRevision = currentRevision;
    }

    public Object getRevisions() {
        return revisions;
    }

    public void setRevisions(Object revisions) {
        this.revisions = revisions;
    }

    public Object getTrackingIds() {
        return trackingIds;
    }

    public void setTrackingIds(Object trackingIds) {
        this.trackingIds = trackingIds;
    }

    public boolean isMoreChanges() {
        return moreChanges;
    }

    public void setMoreChanges(boolean moreChanges) {
        this.moreChanges = moreChanges;
    }

    public Object getProblems() {
        return problems;
    }

    public void setProblems(Object problems) {
        this.problems = problems;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(boolean workInProgress) {
        this.workInProgress = workInProgress;
    }

    public boolean isHasReviewStarted() {
        return hasReviewStarted;
    }

    public void setHasReviewStarted(boolean hasReviewStarted) {
        this.hasReviewStarted = hasReviewStarted;
    }

    public int getRevertOf() {
        return revertOf;
    }

    public void setRevertOf(int revertOf) {
        this.revertOf = revertOf;
    }
}
