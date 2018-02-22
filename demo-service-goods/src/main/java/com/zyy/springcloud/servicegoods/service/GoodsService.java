package com.zyy.springcloud.servicegoods.service;

import com.zyy.springcloud.api.model.dto.response.goods.GoodsListResult;
import com.zyy.springcloud.api.model.dto.response.goods.GoodsResult;
import com.zyy.springcloud.api.model.entity.goods.Goods;
import com.zyy.springcloud.api.model.enums.ResultCode;
import com.zyy.springcloud.servicegoods.dao.GoodsDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDAO goodsDAO;

    public GoodsResult queryById(Long id) {
        GoodsResult result = new GoodsResult();
        Goods goods = new Goods();
        goods.setId(id);
        List<Goods> goodsList = goodsDAO.query(goods);
        if (CollectionUtils.isEmpty(goodsList)) {
            result.setCode(ResultCode.GOODS_NOT_EXIST.getCode());
            result.setMsg(ResultCode.GOODS_NOT_EXIST.getDesc());
            return result;
        }

        BeanUtils.copyProperties(goodsList.get(0), result);
        result.setSuccess(true);
        return result;
    }

    public GoodsListResult queryList(ArrayList<Long> goodsIdList) {
        GoodsListResult result = new GoodsListResult();
        List<GoodsResult> goodsList = new ArrayList<>();
        for (Long goodsId : goodsIdList) {
            GoodsResult goodsResult = queryById(goodsId);
            if (!goodsResult.isSuccess()) {
                result.setCode(goodsResult.getCode());
                result.setMsg(goodsResult.getMsg());
                return result;
            } else {
                goodsList.add(goodsResult);
            }
        }
        result.setGoodsList(goodsList);
        result.setSuccess(true);
        return result;
    }
}
