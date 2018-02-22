package com.zyy.springcloud.serviceorder.service;

import com.zyy.springcloud.api.exception.BizException;
import com.zyy.springcloud.api.model.dto.request.order.OrderGoodsDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsListResult;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsResult;
import com.zyy.springcloud.api.model.entity.order.OrderGoods;
import com.zyy.springcloud.api.model.enums.ResultCode;
import com.zyy.springcloud.serviceorder.dao.OrderGoodsDAO;
import com.zyy.springcloud.serviceorder.rpc.feign.GoodsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单商品服务
 *
 * @author: zhangyanyan
 * @date: 2018/2/22
 */
@Service
public class OrderGoodsService {
    @Autowired
    private OrderGoodsDAO orderGoodsDAO;
    @Autowired
    private GoodsFeignClient goodsFeignClient;

    @Transactional(rollbackFor = Exception.class)
    public Boolean save(Long orderId, List<OrderGoodsDTO> goodsList){
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        ArrayList<Long> goodsIdList = (ArrayList<Long>) goodsList.stream()
                .map(OrderGoodsDTO::getGoodsId).collect(Collectors.toList());
        BaseResponse<GoodsListResult> response = goodsFeignClient.queryList(goodsIdList);
        if (!response.isSuccess()) {
            throw new BizException(response.getCode(), response.getMsg());
        }
        List<GoodsResult> goodsListResult = response.getData().getGoodsList();
        for (OrderGoodsDTO orderGoodsDTO : goodsList) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setQuantity(orderGoodsDTO.getQuantity());
            orderGoods.setGoodsId(orderGoodsDTO.getGoodsId());
            for (GoodsResult goodsResult : goodsListResult) {
                if (goodsResult.getId() == orderGoodsDTO.getGoodsId()) {
                    orderGoods.setPrice(goodsResult.getPrice());
                    orderGoods.setAmount(goodsResult.getPrice().multiply(new BigDecimal(orderGoodsDTO.getQuantity())));
                    orderGoods.setGoodsName(goodsResult.getName());
                }
            }
            orderGoodsList.add(orderGoods);
        }
        //保存订单商品
        Boolean isSuccess = orderGoodsDAO.insert(orderGoodsList);
        if(!isSuccess) {
            throw new BizException(ResultCode.ORDER_GOODS_INSERT_ERROR);
        }
        return true;
    }

    public List<OrderGoods> query(OrderGoods orderGoods) {
        return orderGoodsDAO.query(orderGoods);
    }
}
