package com.zyy.springcloud.api.api;

import com.zyy.springcloud.api.model.dto.request.order.OrderCreateDTO;
import com.zyy.springcloud.api.model.dto.request.order.OrderUpdateDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.order.OrderListResult;
import com.zyy.springcloud.api.model.dto.response.order.OrderResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
@Api(value = "Order", description = "订单中心API", protocols = "http")
public interface OrderAPI {

    @ApiOperation(value = "创建订单", notes = "创建订单", protocols = "http")
    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<Result> create(@RequestBody OrderCreateDTO orderCreateDTO);

    @ApiOperation(value = "修改订单", notes = "修改订单", protocols = "http")
    @RequestMapping(value = "/order", method = RequestMethod.PUT, produces = {"application/json"})
    BaseResponse<Result> update(@RequestBody OrderUpdateDTO orderUpdateDTO);

    @ApiOperation(value = "查询订单详情", notes = "查询订单详情", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<OrderResult> queryById(@PathVariable("id") Long id);

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "订单状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "beginDate", value = "创建订单开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "创建订单结束时间", paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/order/list", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<OrderListResult> queryList(@RequestParam(name = "id", required = false) Long id,
                                            @RequestParam(name = "status", required = false) String status,
                                            @RequestParam(name = "beginDate", required = false) String beginDate,
                                            @RequestParam(name = "endDate", required = false) String endDate);

    @ApiOperation(value = "删除订单", notes = "查询订单", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    BaseResponse<Result> delete(@PathVariable("id") Long id);

}
