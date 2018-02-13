package com.zyy.springcloud.serviceorder.dao;

import com.zyy.springcloud.api.model.entity.order.Order;

import java.util.List;

/**
 * 订单
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public interface OrderDAO {

    Boolean insert(Order orderPO);

    List<Order> query(Order order);
}
