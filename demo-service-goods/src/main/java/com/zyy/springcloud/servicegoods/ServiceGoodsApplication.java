package com.zyy.springcloud.servicegoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 商品服务中心
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zyy.springcloud.servicegoods.dao")
public class ServiceGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGoodsApplication.class, args);
    }
}
