package com.zyy.springcloud.serviceorder.service;

import com.zyy.springcloud.api.model.dto.request.order.OrderCreateDTO;
import com.zyy.springcloud.api.model.dto.request.order.OrderQueryDTO;
import com.zyy.springcloud.api.model.dto.request.order.OrderUpdateDTO;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.order.OrderListResult;
import com.zyy.springcloud.api.model.dto.response.order.OrderResult;
import com.zyy.springcloud.api.model.entity.order.Order;
import com.zyy.springcloud.api.model.entity.order.OrderGoods;
import com.zyy.springcloud.api.model.enums.ResultCode;
import com.zyy.springcloud.serviceorder.dao.OrderDAO;
import com.zyy.springcloud.serviceorder.dao.OrderGoodsDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderGoodsDAO orderGoodsDAO;

    @Transactional
    public Result create(OrderCreateDTO orderCreateDTO) {
        Order orderPO = new Order();
        BeanUtils.copyProperties(orderCreateDTO, orderPO);
        //保存订单
        Boolean isSuccess = orderDAO.insert(orderPO);
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        BeanUtils.copyProperties(orderCreateDTO.getGoodsList(), orderGoodsList);
        //保存订单商品
        isSuccess = orderGoodsDAO.insert(orderGoodsList);
        return isSuccess ? Result.success() : Result.fail(ResultCode.ORDER_CREATE_FAIL);
    }

    public Result update(OrderUpdateDTO orderUpdateDTO) {
        return Result.success();
    }

    public OrderResult queryById(Long orderId) {
        OrderResult orderResult = new OrderResult();

        Order order = new Order();
        order.setId(orderId);
        List<Order> orderList = orderDAO.query(order);
        if(CollectionUtils.isEmpty(orderList)) {
             orderResult.setCode(ResultCode.ORDER_NOT_EXIST.getCode());
             orderResult.setMsg(ResultCode.ORDER_NOT_EXIST.getDesc());
             return orderResult;
        }
        BeanUtils.copyProperties(orderList.get(0), orderResult);
        //查询订单商品
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(orderId);
        List<OrderGoods> orderGoodsList = orderGoodsDAO.query(orderGoods);
        orderResult.setOrderGoodsList(orderGoodsList);
        orderResult.setSuccess(true);
        return orderResult;

    }

    public OrderListResult queryList(OrderQueryDTO orderQueryDTO) {
        OrderListResult orderListResult = new OrderListResult();

        Order order = new Order();
        BeanUtils.copyProperties(orderQueryDTO, order);
        List<Order> orderList = orderDAO.query(order);
        if(CollectionUtils.isEmpty(orderList)) {
            orderListResult.setCode(ResultCode.ORDER_NOT_EXIST.getCode());
            orderListResult.setMsg(ResultCode.ORDER_NOT_EXIST.getDesc());
            return orderListResult;
        }

        List<OrderResult> orderResultList = new ArrayList<>();
        for(Order orderDB : orderList) {
            OrderResult orderResult = new OrderResult();
            BeanUtils.copyProperties(orderDB, orderResult);
            //查询订单商品
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderDB.getId());
            List<OrderGoods> orderGoodsList = orderGoodsDAO.query(orderGoods);
            orderResult.setOrderGoodsList(orderGoodsList);
            orderResultList.add(orderResult);
        }

        orderListResult.setOrderList(orderResultList);
        orderListResult.setSuccess(true);
        return orderListResult;
    }

    public Result delete(Long id) {
        return Result.success();
    }
}
