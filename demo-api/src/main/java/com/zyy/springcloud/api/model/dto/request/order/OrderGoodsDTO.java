package com.zyy.springcloud.api.model.dto.request.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderGoodsDTO implements Serializable {

    private static final long serialVersionUID = -8611715734602833128L;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private Integer quantity;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
