package com.zyy.springcloud.api.model.enums;

public enum ResultCode {
    SUCCESS("成功", 1000000),
    ERROR("系统错误", -1),
    PARAM_ERROR("请求参数错误", 1010000),

    // user-service code起始码 020000, 功能起始码020101
    USER_EXIST("用户已注册", 1020101),
    USER_NOT_EXIST("用户不存在", 1020102),
    USER_CREATE_FAIL("新增用户失败", 1020201),

    // goods-service code起始码 030000, 功能起始码030101
    GOODS_NOT_EXIST("商品不存在", 1030101),

    // order-service code起始码 040000, 功能起始码040101
    ORDER_NOT_EXIST("订单不存在", 1040101),
    ORDER_CREATE_FAIL("创建订单失败", 1040201),
    ORDER_GOODS_IS_NULL("创建订单失败,请选择商品", 1040202),
    ORDER_GOODS_INSERT_ERROR("创建订单失败,保存订单商品失败", 1040203);

    private String desc;
    private Integer code;

    ResultCode(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
