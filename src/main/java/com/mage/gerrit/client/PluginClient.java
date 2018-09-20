package com.mage.gerrit.client;

import com.mage.gerrit.api.PluginApi;
import com.mage.gerrit.model.PluginInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mage.gerrit.utils.UrlUtils.joinParam;
import static com.mage.gerrit.utils.UrlUtils.joinPath;

public class PluginClient implements PluginApi {

    private static final String ROOT_ENDPOINT = "a/plugins/";
    private GerritHttpClient client;

    public PluginClient(GerritHttpClient client) {
        this.client = client;
    }


    @Override
    public Map<String, PluginInfo> get() {
        return client.get(ROOT_ENDPOINT, PluginInfo.class, Map.class, String.class);
    }

    @Override
    public Map<String, PluginInfo> get(boolean all, String prefix, String regex, String match, int limit, int skip) {
        List<NameValuePair> list = new ArrayList<>();
        if (all) {
            list.add(new BasicNameValuePair("all", null));
        } else {
            if (StringUtils.isNotEmpty(prefix)) {
                list.add(new BasicNameValuePair("p", prefix));
            } else if (StringUtils.isNotEmpty(regex)) {
                list.add(new BasicNameValuePair("r", regex));
            } else if (StringUtils.isNotEmpty(match)) {
                list.add(new BasicNameValuePair("m", match));
            }
        }
        if (limit > 0) {
            list.add(new BasicNameValuePair("n", String.valueOf(limit)));
        }
        if (skip > 0) {
            list.add(new BasicNameValuePair("S", String.valueOf(skip)));
        }

        String endpoint = joinParam(ROOT_ENDPOINT, list);
        return client.get(endpoint, PluginInfo.class, Map.class, String.class);
    }


    /**
     * All(a)
     * List all plugins including those that are disabled.
     * <p>
     * Request
     * GET /plugins/?all HTTP/1.0
     *
     * @return
     */
    @Override
    public Map<String, PluginInfo> getAll() {
        return get(true, null, null, null, 0, 0);
    }


    /**
     * Prefix(p)
     * Limit the results to those plugins that start with the specified prefix.
     * <p>
     * The match is case sensitive. May not be used together with m or r.
     * <p>
     * List all plugins that start with delete:
     * <p>
     * Request
     * GET /plugins/?p=delete HTTP/1.0
     *
     * @param prefix
     * @return
     */
    @Override
    public Map<String, PluginInfo> getByPrefix(String prefix) {
        return get(true, prefix, null, null, 0, 0);
    }

    /**
     * Regex(r)
     * Limit the results to those plugins that match the specified regex.
     * <p>
     * Boundary matchers '^' and '$' are implicit. For example: the regex 'test.*' will match
     * any plugins that start with 'test' and regex '.*test' will match any project that end with 'test'.
     * <p>
     * The match is case sensitive. May not be used together with m or p.
     * <p>
     * List all plugins that match regex some.*plugin:
     * <p>
     * Request
     * GET /plugins/?r=some.*plugin HTTP/1.0
     *
     * @param regex
     * @return
     */
    @Override
    public Map<String, PluginInfo> getByRegex(String regex) {
        return get(true, null, regex, null, 0, 0);
    }


    /**
     * Substring(m)
     * Limit the results to those plugins that match the specified substring.
     * <p>
     * The match is case insensitive. May not be used together with r or p.
     * <p>
     * List all plugins that match substring project:
     * <p>
     * Request
     * GET /plugins/?m=project HTTP/1.0
     *
     * @param match
     * @return
     */
    @Override
    public Map<String, PluginInfo> getByMatch(String match) {
        return get(true, null, null, match, 0, 0);
    }

    /**
     * Limit(n)
     * Limit the number of plugins to be included in the results.
     * <p>
     * Query the first plugin in the plugin list:
     * <p>
     * Request
     * GET /plugins/?n=1 HTTP/1.0
     *
     * @param limit
     * @return
     */
    @Override
    public Map<String, PluginInfo> getByLimit(int limit) {
        return get(true, null, null, null, limit, 0);
    }

    /**
     * Skip(S)
     * Skip the given number of plugins from the beginning of the list.
     * <p>
     * Query the second plugin in the plugin list:
     * <p>
     * Request
     * GET /plugins/?all&n=1&S=1 HTTP/1.0
     *
     * @param skip
     * @return
     */
    @Override
    public Map<String, PluginInfo> getBySkip(int skip) {
        return get(true, null, null, null, 0, skip);
    }


    /**
     * Get Plugin Status
     * 'GET /plugins/{plugin-id}/gerrit~status'
     * <p>
     * Retrieves the status of a plugin on the Gerrit server.
     * <p>
     * Request
     * GET /plugins/delete-project/gerrit~status HTTP/1.0
     *
     * @param id
     * @return
     */
    @Override
    public PluginInfo getStatus(String id) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gerrit~status");
        return client.get(endpoint, PluginInfo.class);
    }

    /**
     * Get Plugin Status
     * 'GET /plugins/{plugin-id}/gerrit~status'
     * <p>
     * Retrieves the status of a plugin on the Gerrit server.
     * <p>
     * Request
     * GET /plugins/delete-project/gerrit~status HTTP/1.0
     *
     * @param id
     * @return
     */
    @Override
    public PluginInfo enable(String id) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gerrit~enable");
        return client.post(endpoint, PluginInfo.class);
    }

    /**
     * Disable Plugin
     * 'POST /plugins/{plugin-id}/gerrit~disable'
     * <p>
     * OR
     * <p>
     * 'DELETE /plugins/{plugin-id}'
     * <p>
     * Disables a plugin on the Gerrit server.
     * <p>
     * Request
     * POST /plugins/delete-project/gerrit~disable HTTP/1.0
     *
     * @param id
     * @return
     */
    @Override
    public PluginInfo disable(String id) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gerrit~disable");
        return client.post(endpoint, PluginInfo.class);
    }

    /**
     * Reload Plugin
     * 'POST /plugins/{plugin-id}/gerrit~reload'
     * <p>
     * Reloads a plugin on the Gerrit server.
     * <p>
     * Request
     * POST /plugins/delete-project/gerrit~reload HTTP/1.0
     *
     * @param id
     * @return
     */
    @Override
    public PluginInfo reload(String id) {
        String endpoint = joinPath(ROOT_ENDPOINT, id);
        endpoint = joinPath(endpoint, "gerrit~reload");
        return client.post(endpoint, PluginInfo.class);
    }

}
