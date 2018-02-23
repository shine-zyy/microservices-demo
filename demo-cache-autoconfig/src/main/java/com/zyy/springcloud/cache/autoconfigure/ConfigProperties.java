package com.zyy.springcloud.cache.autoconfigure;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;

import java.util.Map;

public class ConfigProperties {
    public static final String PREFIX = "cache.";

    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    private Map<String, Object> setting;

    public Map<String, Object> getSetting() {
        return setting;
    }

    public void setSetting(Map<String, Object> setting) {
        this.setting = setting;
    }

    public static ConfigProperties init(Environment environment) {
        ConfigProperties config = new ConfigProperties();
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, PREFIX);
        config.setSetting(propertyResolver.getSubProperties("setting."));
        config.setAppName(propertyResolver.getProperty("appName"));
        return config;
    }
}
