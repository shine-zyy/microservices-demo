package com.zyy.springcloud.api.model.dto.request.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * 订单DTO
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -9128832153248406140L;

    @ApiModelProperty(notes = "用户ID", required = true)
    @Min(value = 0, message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(notes = "商品列表", required = true)
    @NotEmpty(message = "请选择要购买的商品")
    private List<OrderGoodsDTO> goodsList ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }
}
