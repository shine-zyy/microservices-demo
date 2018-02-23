package com.zyy.springcloud.cache.core;

import com.zyy.springcloud.cache.core.load.LoadHandler;
import org.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCacheBeanFactory implements FactoryBean<Cache> {
    public static Map<String, Cache> remoteCacheMap = new ConcurrentHashMap<String, Cache>();
    // 缓存名称
    String cacheName;
    // 应用名称
    String appName;

    LoadHandler loadHandler;

    int maxElementsInMemory = 5000;

    public String getName() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.appName).append(":").append(this.cacheName);
        return sb.toString();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getMaxElementsInMemory() {
        return maxElementsInMemory;
    }

    public void setMaxElementsInMemory(int maxElementsInMemory) {
        this.maxElementsInMemory = maxElementsInMemory;
    }

    public LoadHandler getLoadHandler() {
        return loadHandler;
    }

    public void setLoadHandler(LoadHandler loadHander) {
        this.loadHandler = loadHander;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public abstract Cache getObject() throws Exception;

    public Class<?> getObjectType() {
        return Cache.class;
    }

    public boolean isSingleton() {
        return Boolean.TRUE;
    }
}
