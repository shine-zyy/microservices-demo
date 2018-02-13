package com.zyy.springcloud.feignconsumer.feign.user;

import com.zyy.springcloud.api.api.UserAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
@FeignClient(name = "${feign.client.service-user.name}")
public interface UserFeignClient extends UserAPI{
}
