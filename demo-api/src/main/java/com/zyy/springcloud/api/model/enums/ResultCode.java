package com.zyy.springcloud.api.model.enums;

public enum ResultCode {
    SUCCESS("成功", "000000"),
    ERROR("系统错误", "-1"),
    PARAM_ERROR("请求参数错误", "010000"),

    // user-service code起始码 020000, 功能起始码020101
    USER_EXIST("用户已注册", "020101"),
    USER_NOT_EXIST("用户不存在", "020102"),
    USER_CREATE_FAIL("新增用户失败", "020201"),

    // goods-service code起始码 030000, 功能起始码030101
    GOODS_NOT_EXIST("商品不存在","030101"),

    // order-service code起始码 040000, 功能起始码040101
    ORDER_NOT_EXIST("订单不存在","040101"),
    ORDER_CREATE_FAIL("创建订单失败","040201");

    private String desc;
    private String code;

    ResultCode(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
