package com.zyy.springcloud.serviceuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户服务中心
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zyy.springcloud.serviceuser.dao")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
