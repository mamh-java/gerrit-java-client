package com.mage.gerrit.model;

/**
 * The CapabilityInfo entity contains information about the global capabilities of a user.
 */
public class CapabilityInfo extends BaseModel {
    /**
     * Whether the user has the Access Database capability.
     */
    private boolean accessDatabase;

    /**
     * Whether the user has the Administrate Server capability.
     */
    private boolean administrateServer;

    /**
     * Whether the user has the Create Account capability.
     */
    private boolean createAccount;

    /**
     * Whether the user has the Create Group capability.
     */
    private boolean createGroup;

    /**
     * Whether the user has the Create Project capability.
     */
    private boolean createProject;

    /**
     * Whether the user has the Email Reviewers capability.
     */
    private boolean emailReviewers;

    /**
     * Whether the user has the Flush Caches capability.
     */
    private boolean flushCaches;

    /**
     * Whether the user has the Kill Task capability.
     */
    private boolean killTask;

    /**
     * Whether the user has the Maintain Server capability.
     */
    private boolean maintainServer;

    /**
     * The name of the thread pool used by the user, see Priority capability.
     */
    private QueueType priority;

    /**
     * The Query Limit of the user as QueryLimitInfo.
     */
    private QueryLimitInfo queryLimit;

    /**
     * Whether the user has the Run As capability.
     */
    private boolean runAs;

    /**
     * Whether the user has the Run Garbage Collection capability.
     */
    private boolean runGC;

    /**
     * Whether the user has the Stream Events capability.
     */
    private boolean streamEvents;

    /**
     * Whether the user has the View All Accounts capability.
     */
    private boolean viewAllAccounts;


    /**
     * Whether the user has the View Caches capability.
     */
    private boolean viewCaches;

    /**
     * Whether the user has the View Connections capability.
     */
    private boolean viewConnections;

    /**
     * Whether the user has the View Plugins capability.
     */
    private boolean viewPlugins;

    /**
     * Whether the user has the View Queue capability.
     */
    private boolean viewQueue;

    public boolean isAccessDatabase() {
        return accessDatabase;
    }

    public void setAccessDatabase(boolean accessDatabase) {
        this.accessDatabase = accessDatabase;
    }

    public boolean isAdministrateServer() {
        return administrateServer;
    }

    public void setAdministrateServer(boolean administrateServer) {
        this.administrateServer = administrateServer;
    }

    public boolean isCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(boolean createAccount) {
        this.createAccount = createAccount;
    }

    public boolean isCreateGroup() {
        return createGroup;
    }

    public void setCreateGroup(boolean createGroup) {
        this.createGroup = createGroup;
    }

    public boolean isCreateProject() {
        return createProject;
    }

    public void setCreateProject(boolean createProject) {
        this.createProject = createProject;
    }

    public boolean isEmailReviewers() {
        return emailReviewers;
    }

    public void setEmailReviewers(boolean emailReviewers) {
        this.emailReviewers = emailReviewers;
    }

    public boolean isFlushCaches() {
        return flushCaches;
    }

    public void setFlushCaches(boolean flushCaches) {
        this.flushCaches = flushCaches;
    }

    public boolean isKillTask() {
        return killTask;
    }

    public void setKillTask(boolean killTask) {
        this.killTask = killTask;
    }

    public boolean isMaintainServer() {
        return maintainServer;
    }

    public void setMaintainServer(boolean maintainServer) {
        this.maintainServer = maintainServer;
    }

    public QueueType getPriority() {
        return priority;
    }

    public void setPriority(QueueType priority) {
        this.priority = priority;
    }

    public QueryLimitInfo getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(QueryLimitInfo queryLimit) {
        this.queryLimit = queryLimit;
    }

    public boolean isRunAs() {
        return runAs;
    }

    public void setRunAs(boolean runAs) {
        this.runAs = runAs;
    }

    public boolean isRunGC() {
        return runGC;
    }

    public void setRunGC(boolean runGC) {
        this.runGC = runGC;
    }

    public boolean isStreamEvents() {
        return streamEvents;
    }

    public void setStreamEvents(boolean streamEvents) {
        this.streamEvents = streamEvents;
    }

    public boolean isViewAllAccounts() {
        return viewAllAccounts;
    }

    public void setViewAllAccounts(boolean viewAllAccounts) {
        this.viewAllAccounts = viewAllAccounts;
    }

    public boolean isViewCaches() {
        return viewCaches;
    }

    public void setViewCaches(boolean viewCaches) {
        this.viewCaches = viewCaches;
    }

    public boolean isViewConnections() {
        return viewConnections;
    }

    public void setViewConnections(boolean viewConnections) {
        this.viewConnections = viewConnections;
    }

    public boolean isViewPlugins() {
        return viewPlugins;
    }

    public void setViewPlugins(boolean viewPlugins) {
        this.viewPlugins = viewPlugins;
    }

    public boolean isViewQueue() {
        return viewQueue;
    }

    public void setViewQueue(boolean viewQueue) {
        this.viewQueue = viewQueue;
    }
}
