package com.zyy.springcloud.api.model.enums.user;

public enum UserStatusEnums {

    ENABLE("正常", "ENABLE"),
    DISABLE("禁用", "DISABLE");

    private String desc;

    private String value;

    private UserStatusEnums(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }

}
