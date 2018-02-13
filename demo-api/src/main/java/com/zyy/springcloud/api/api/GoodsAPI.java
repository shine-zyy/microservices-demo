package com.zyy.springcloud.api.api;

import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsListResult;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * 商品服务API
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@Api(value = "Goods", description = "商品服务中心API", protocols = "http")
public interface GoodsAPI {
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<GoodsResult> queryById(@PathVariable("id") Long id);

}
