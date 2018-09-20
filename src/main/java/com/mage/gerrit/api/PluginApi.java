package com.mage.gerrit.api;

import com.mage.gerrit.model.PluginInfo;

import java.util.Map;

public interface PluginApi {
    Map<String, PluginInfo> get();

    Map<String, PluginInfo> getAll();

    Map<String, PluginInfo> get(boolean all, String prefix, String regex, String match, int limit, int skip);

    Map<String, PluginInfo> getByPrefix(String prefix);

    Map<String, PluginInfo> getByRegex(String regex);

    Map<String, PluginInfo> getByMatch(String match);

    Map<String, PluginInfo> getByLimit(int limit);

    Map<String, PluginInfo> getBySkip(int skip);

    PluginInfo getStatus(String id);

    PluginInfo enable(String id);

    PluginInfo disable(String id);

    PluginInfo reload(String id);
}
