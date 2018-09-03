package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DownloadSchemeInfo extends BaseModel {

    /**
     * The URL of the download scheme, where '${project}' is used as placeholder for the project name.
     */
    private String url;

    /**
     * Whether this download scheme requires authentication.
     */
    @JsonProperty("is_auth_required")
    private boolean isAuthRequired;

    /**
     * Whether this download scheme supports authentication.
     */
    @JsonProperty("is_auth_supported")
    private boolean isAuthSupported;


    /**
     * Download commands as a map which maps the command name to the download command.
     * In the download command '${project}' is used as placeholder for the project name,
     * and '${ref}' is used as placeholder for the (change) ref.
     * <p>
     * Empty, if accessed anonymously and the download scheme requires authentication.
     */
    private Object commands;

    /**
     * Clone commands as a map which maps the command name to the clone command.
     * In the clone command '${project}' is used as placeholder for the project name
     * and '${project-base-name}' as name for the project base name (e.g. for a project
     * 'foo/bar' '${project}' is a placeholder for 'foo/bar' and '${project-base-name}'
     * is a placeholder for 'bar').
     * <p>
     * Empty, if accessed anonymously and the download scheme requires authentication.
     */
    @JsonProperty("clone_commands")
    private Object cloneCommands;

}
