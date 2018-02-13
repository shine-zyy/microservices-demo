package com.zyy.springcloud.serviceorder.dao;

import com.zyy.springcloud.api.model.entity.order.OrderGoods;

import java.util.List; /**
 * 订单商品
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public interface OrderGoodsDAO {
    Boolean insert(List<OrderGoods> orderGoodsList);

    List<OrderGoods> query(OrderGoods orderGoods);

}
