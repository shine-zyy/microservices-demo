package com.zyy.springcloud.cache.autoconfigure;

public class Setting {
    private String eviction;

    public String getEviction() {
        return eviction;
    }

    public void setEviction(String eviction) {
        this.eviction = eviction;
    }

    public static Setting instance(String s) {
        Setting setting = new Setting();
        if (s != null) {
            String[] arraySetting = s.split(";");
            if (arraySetting != null && arraySetting.length > 0) {
                setting.setEviction(arraySetting[0]);
            }
        }
        return setting;
    }
}
