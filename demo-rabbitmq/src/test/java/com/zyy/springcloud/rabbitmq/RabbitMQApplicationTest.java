package com.zyy.springcloud.rabbitmq;

import com.zyy.springcloud.rabbitmq.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitMQApplicationTest {
    @Autowired
    private Sender sender;

    @Test
    public void hello() {
        sender.send();
    }
}
