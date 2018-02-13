package com.zyy.springcloud.api.model.dto.response.goods;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 商品列表返回
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
public class GoodsListResult extends Result implements Serializable {
    private static final long serialVersionUID = 2385020430182675378L;
    /**
     * 商品列表
     */
    private List<GoodsResult> goodsList ;

    public List<GoodsResult> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsResult> goodsList) {
        this.goodsList = goodsList;
    }
}
