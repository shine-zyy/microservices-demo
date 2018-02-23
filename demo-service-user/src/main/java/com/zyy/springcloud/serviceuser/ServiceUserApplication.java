package com.zyy.springcloud.serviceuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务中心
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zyy.springcloud.serviceuser.dao")
@ComponentScan(basePackages={"com.zyy.springcloud"})
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
