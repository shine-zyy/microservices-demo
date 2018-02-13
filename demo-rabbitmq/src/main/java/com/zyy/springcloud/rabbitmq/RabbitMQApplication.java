package com.zyy.springcloud.rabbitmq;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 消息队列
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@SpringBootApplication
public class RabbitMQApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RabbitMQApplication.class).run(args);
    }
}
