package com.zyy.springcloud.api.model.dto.response.order;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品返回
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderGoodsResult extends Result implements Serializable{
    private static final long serialVersionUID = 8366089534685976859L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 订单ID
     */
    private Long orderID;
    /**
     * 商品ID
     */
    private Long goodsID;
    /**
     * 商品名称
     */
    private String goodsNme;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 总金额
     */
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Long goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsNme() {
        return goodsNme;
    }

    public void setGoodsNme(String goodsNme) {
        this.goodsNme = goodsNme;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
