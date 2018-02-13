package com.zyy.springcloud.rabbitmq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息发送方
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@Component
public class Sender {
    private final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String content = "hello " + new Date();
        logger.info("Sender: {}", content);
        this.rabbitTemplate.convertAndSend("hello", content);
    }
}
