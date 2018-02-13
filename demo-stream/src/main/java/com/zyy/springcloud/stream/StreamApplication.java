package com.zyy.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring cloud Stream 是一个用来为微服务应用构建消息驱动能力的框架
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }
}
