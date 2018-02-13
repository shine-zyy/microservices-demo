package com.zyy.springcloud.api.model.dto.request.goods;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品DTO
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class GoodsDTO implements Serializable {
    private static final long serialVersionUID = -360931317761244150L;
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
    private String detail;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
