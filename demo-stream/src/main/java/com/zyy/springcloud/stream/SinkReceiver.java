package com.zyy.springcloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 消息接收者
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@EnableBinding(Sink.class)
public class SinkReceiver {
    private final Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("SinkReceiver: {}", payload);
    }
}
