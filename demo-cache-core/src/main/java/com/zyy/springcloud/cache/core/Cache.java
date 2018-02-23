package com.zyy.springcloud.cache.core;


import com.zyy.springcloud.cache.core.load.LoadHandler;

import java.util.List;

public interface Cache {
    String getCacheName();

    boolean set(String key, Object value) throws Exception;

    Object get(String key) throws Exception;

    boolean remove(String key) throws Exception;

    void setLoadHandler(LoadHandler loadHandler);

    LoadHandler getloadHandler();

    List<?> getKeys();

    long getCount();

    long getHitCount();

    boolean reLoad();

    public boolean removeAll();
}
