package com.zyy.springcloud.api.model.entity.order;

import com.zyy.springcloud.api.model.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单商品模型
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public class OrderGoods extends BaseEntity {
    private static final long serialVersionUID = -4969442701539635086L;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品总价
     */
    private BigDecimal amount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
