package com.zyy.springcloud.apigateway;

import com.zyy.springcloud.apigateway.filter.AccessFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * API网关服务
 *
 * @author zhangyanyan
 * @date 2018/1/5
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
          "(?<name>^.+)-(?<version>v.+$)"
                , "${version}/${name}"
        );
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
    }
}
