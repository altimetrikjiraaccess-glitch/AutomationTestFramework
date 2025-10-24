package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String CONFIG_FILE = "/config.properties";
    private static ConfigLoader instance;
    private final Properties properties = new Properties();

    private ConfigLoader() {
        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file '" + CONFIG_FILE + "' not found in classpath");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public String getBaseUrl() {
        return getRequiredProperty("base.url");
    }

    public String getSalesforceUsername() {
        return getRequiredProperty("salesforce.username");
    }

    public String getSalesforcePassword() {
        return getRequiredProperty("salesforce.password");
    }

    public String getJiraBaseUrl() {
        return getOptionalProperty("jira.base.url", "JIRA_BASE_URL");
    }

    public String getJiraUsername() {
        return getOptionalProperty("jira.username", "JIRA_USERNAME");
    }

    public String getJiraApiToken() {
        return getOptionalProperty("jira.api.token", "JIRA_API_TOKEN");
    }

    private String getRequiredProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Property '" + key + "' is not defined in configuration");
        }
        return value;
    }

    private String getOptionalProperty(String key, String environmentVariable) {
        String value = resolvePlaceholder(properties.getProperty(key));
        if (value == null || value.isBlank()) {
            value = System.getenv(environmentVariable);
        }
        if (value == null || value.isBlank()) {
            return null;
        }
        return value;
    }

    private String resolvePlaceholder(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        String trimmed = rawValue.trim();
        if (trimmed.startsWith("${") && trimmed.endsWith("}")) {
            String placeholder = trimmed.substring(2, trimmed.length() - 1);
            if (!placeholder.isBlank()) {
                return System.getenv(placeholder);
            }
            return null;
        }
        return trimmed;
    }
}
