package com.zyy.springcloud.serviceorder.rpc.feign;

import com.zyy.springcloud.api.api.GoodsAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author: zhangyanyan
 * @date: 2018/2/22
 */
@FeignClient(name = "${feign.client.goods.name}")
public interface GoodsFeignClient extends GoodsAPI{
}
