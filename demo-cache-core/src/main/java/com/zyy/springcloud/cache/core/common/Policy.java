package com.zyy.springcloud.cache.core.common;

public enum Policy {
    TIME_WRITE("TIME_WRITE"), GC("GC");
    private final String Policy;

    private Policy(String policy) {
        this.Policy = policy;
    }
}