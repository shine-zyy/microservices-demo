package com.zyy.springcloud.cache.core.load;


import com.zyy.springcloud.cache.core.Cache;

public interface LoadHandler {
    void load(Cache cache);
}
