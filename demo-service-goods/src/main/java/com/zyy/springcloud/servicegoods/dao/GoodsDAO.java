package com.zyy.springcloud.servicegoods.dao;

import com.zyy.springcloud.api.model.entity.goods.Goods;

import java.util.List;

/**
 * 商品持久层
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public interface GoodsDAO {
    List<Goods> query(Goods goods);
}
