package com.zyy.springcloud.api.model.entity.order;

import com.zyy.springcloud.api.model.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单实体
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class Order extends BaseEntity {

    private static final long serialVersionUID = -1955288499845795688L;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    /**
     * 订单配送地址
     */
    private String address;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
