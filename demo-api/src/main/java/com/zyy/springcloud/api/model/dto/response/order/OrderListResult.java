package com.zyy.springcloud.api.model.dto.response.order;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表返回
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class OrderListResult extends Result implements Serializable{
    private static final long serialVersionUID = 3994553160752622506L;
    /**
     * 订单列表
     */
    private List<OrderResult>  orderList ;

    public List<OrderResult> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderResult> orderList) {
        this.orderList = orderList;
    }
}
