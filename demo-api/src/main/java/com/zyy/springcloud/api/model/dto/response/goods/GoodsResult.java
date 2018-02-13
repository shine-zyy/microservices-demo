package com.zyy.springcloud.api.model.dto.response.goods;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品返回信息
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class GoodsResult extends Result implements Serializable {
    private static final long serialVersionUID = -251365352613691737L;
    /**
     * 商品ID
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 商品详情
     */
    private String descb;

    /**
     * 库存
     */
    private Integer repertory;

    /**
     * 状态
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescb() {
        return descb;
    }

    public void setDescb(String descb) {
        this.descb = descb;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
