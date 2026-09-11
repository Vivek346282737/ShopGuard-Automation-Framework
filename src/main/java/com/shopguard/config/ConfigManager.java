package com.shopguard.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config/config.properties nahi mila!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Config properties load nahi ho payi.", e);
        }
    }

    public static String getProperty(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) return sysProp;
        String envVar = System.getenv(key.toUpperCase().replace('.', '_'));
        if (envVar != null && !envVar.isBlank()) return envVar;
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return (val != null && !val.isBlank()) ? val : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String val = getProperty(key);
        return (val != null) ? Boolean.parseBoolean(val) : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String val = getProperty(key);
        try {
            return (val != null) ? Integer.parseInt(val) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
