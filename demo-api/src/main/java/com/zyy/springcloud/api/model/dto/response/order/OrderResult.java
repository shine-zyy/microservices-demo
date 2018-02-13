package com.zyy.springcloud.api.model.dto.response.order;

import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.entity.order.OrderGoods;

import java.io.Serializable;
import java.util.List;

/**
 * 订单返回信息
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderResult extends Result implements Serializable {
    private static final long serialVersionUID = -1095457848189752640L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 订单编号
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
     * 配送地址
     */
    private String address;
    /**
     * 商品列表
     */
    private List<OrderGoods> orderGoodsList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }
}
