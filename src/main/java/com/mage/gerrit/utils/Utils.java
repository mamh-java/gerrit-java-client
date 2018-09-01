package com.mage.gerrit.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {
    public static void pprint(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(o));
    }

    public static String getenv(String key) {
        return Utils.getenv(key, "");
    }

    public static String getenv(String key, String defaultvalue) {
        String env = System.getenv(key);
        if (env == null) {
            env = defaultvalue;
        }
        return env;
    }


}
