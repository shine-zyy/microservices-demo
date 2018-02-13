package com.zyy.springcloud.api.model.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

/**
 * 更新订单
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderUpdateDTO extends OrderDTO{
    private static final long serialVersionUID = -5922947709743916100L;

    @ApiModelProperty(notes = "订单ID", required = true)
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
