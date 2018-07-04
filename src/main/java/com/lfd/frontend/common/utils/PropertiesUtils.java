package com.lfd.frontend.common.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ryan on 1/8/16.
 */
public class PropertiesUtils {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
