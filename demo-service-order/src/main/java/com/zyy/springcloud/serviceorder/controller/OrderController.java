package com.zyy.springcloud.serviceorder.controller;

import com.zyy.springcloud.api.controller.AbstractController;
import com.zyy.springcloud.api.model.dto.request.order.OrderCreateDTO;
import com.zyy.springcloud.api.model.dto.request.order.OrderQueryDTO;
import com.zyy.springcloud.api.model.dto.request.order.OrderUpdateDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.order.OrderListResult;
import com.zyy.springcloud.api.model.dto.response.order.OrderResult;
import com.zyy.springcloud.api.api.OrderAPI;
import com.zyy.springcloud.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 订单管理
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
@RestController
public class OrderController extends AbstractController implements OrderAPI {
    @Autowired
    private OrderService orderService;

    @Override
    public BaseResponse<Result> create(@RequestBody OrderCreateDTO orderCreateDTO) {
        Result result = orderService.create(orderCreateDTO);
        return buildJson(result.getCode(), result.getMsg(), null);
    }

    @Override
    public BaseResponse<Result> update(OrderUpdateDTO orderUpdateDTO) {
        Result result = orderService.update(orderUpdateDTO);
        return buildJson(result.getCode(), result.getMsg(), null);
    }

    @Override
    public BaseResponse<OrderResult> queryById(@PathVariable Long id) {
        OrderResult result = orderService.queryById(id);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<OrderListResult> queryList(Long id, String status, String beginDate, String endDate) {
        OrderQueryDTO orderQueryDTO = new OrderQueryDTO();
        orderQueryDTO.setId(id);
        orderQueryDTO.setStatus(status);
        orderQueryDTO.setBeginDate(beginDate);
        orderQueryDTO.setEndDate(endDate);
        OrderListResult result = orderService.queryList(orderQueryDTO);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<Result> delete(@PathVariable Long id) {
        Result result = orderService.delete(id);
        return buildJson(result.getCode(), result.getMsg(), null);
    }

}
