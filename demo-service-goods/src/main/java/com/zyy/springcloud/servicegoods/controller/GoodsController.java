package com.zyy.springcloud.servicegoods.controller;

import com.zyy.springcloud.api.api.GoodsAPI;
import com.zyy.springcloud.api.controller.AbstractController;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsResult;
import com.zyy.springcloud.servicegoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品服务
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@RestController
public class GoodsController extends AbstractController implements GoodsAPI {
    @Autowired
    private GoodsService goodsService;

    @Override
    public BaseResponse<GoodsResult> queryById(@PathVariable Long id) {
        GoodsResult result = goodsService.queryById(id);
        return buildJson(result.getCode(), result.getMsg(), result);
    }
}
