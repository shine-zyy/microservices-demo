package com.zyy.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心配置信息
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;

    @Value("${config_from}")
    private String configFrom;

    @Autowired
    private Environment environment;

    @RequestMapping("/from")
    public String from() {
        return "value: " + from + "  env: " + environment.getProperty("from", "undifined")
                + " config_from: " + configFrom;
    }
}
