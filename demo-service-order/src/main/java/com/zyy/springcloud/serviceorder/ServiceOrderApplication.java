package com.zyy.springcloud.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 订单服务中心
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zyy.springcloud.serviceorder.dao")
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
