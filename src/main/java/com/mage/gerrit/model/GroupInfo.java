package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class GroupInfo extends BaseModel {
    /**
     * The URL encoded UUID of the group.
     * <p>
     * The type of a group can be deduced from the group’s UUID:
     * UUID matches "^[0-9a-f]{40}$"  Gerrit internal group
     * UUID starts with "global:"     Gerrit system group
     * UUID starts with "ldap:"       LDAP group
     * UUID starts with "<prefix>:"   other external group
     */
    private String id;

    /**
     * not set if returned in a map where the group name is used as map key
     * The name of the group.
     */
    private String name;

    /**
     * URL to information about the group. Typically a URL to a web page that
     * permits users to apply to join the group, or manage their membership.
     */
    private String url;

    /**
     * Options of the group.
     */
    private GroupOptionsInfo options;
    /**
     * only for internal groups
     * The description of the group.
     */
    private String description;

    /**
     * only for internal groups
     * The numeric ID of the group.
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * only for internal groups
     * The name of the owner group.
     */
    private String owner;

    /**
     * only for internal groups
     * The URL encoded UUID of the owner group.
     */
    @JsonProperty("owner_id")
    private String ownerId;

    /**
     * only for internal groups
     * The timestamp of when the group was created.
     * Timestamps are given in UTC and have the format "'yyyy-mm-dd hh:mm:ss.fffffffff'" where "'ffffffffff'" represents nanoseconds.
     */
    @JsonProperty("created_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdOn;

    /**
     * optional, only for internal groups, not set if false
     * Whether the query would deliver more results if not limited.
     * Only set on the last group that is returned by a group query.
     * <p>
     * 这个和AccountInfo 中的_more_accounts 比较类似，就是列出很多的时候，如果进行了
     * 过滤，会在最后一个 中加上 这个标记 表示 后面还有没有了。
     */
    @JsonProperty("_more_groups")
    private boolean moreGroups;

    /**
     * optional, only for internal groups
     * A list of AccountInfo entities describing the direct members.
     * Only set if members are requested.
     */
    private List<AccountInfo> members;

    /**
     * optional, only for internal groups
     * A list of GroupInfo entities describing the direct subgroups.
     * Only set if subgroups are requested.
     */
    private List<GroupInfo> includes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isMoreGroups() {
        return moreGroups;
    }

    public void setMoreGroups(boolean moreGroups) {
        this.moreGroups = moreGroups;
    }

    public List<AccountInfo> getMembers() {
        return members;
    }

    public void setMembers(List<AccountInfo> members) {
        this.members = members;
    }

    public List<GroupInfo> getIncludes() {
        return includes;
    }

    public void setIncludes(List<GroupInfo> includes) {
        this.includes = includes;
    }
}
