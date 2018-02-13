package com.zyy.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 熔断器
 *
 * 熔断器的原理如同电力过载保护器。它可以实现快速失败，如果它在一段时间内侦测到许多类似的错误，
 * 会强迫其以后的多个调用快速失败，不再访问远程服务器，从而防止应用程序不断地尝试执行可能会失败的操作，
 * 使得应用程序继续执行而不用等待修正错误，或者浪费CPU时间去等到长时间的超时产生。
 * 熔断器也可以使应用程序能够诊断错误是否已经修正，如果已经修正，应用程序会再次尝试调用操作。
 * 熔断器模式就像是那些容易导致错误的操作的一种代理。这种代理能够记录最近调用发生错误的次数，
 * 然后决定使用允许操作继续，或者立即返回错误。
 *
 * @author: zhangyanyan
 * @date: 2018/1/24
 * @see {http://book.itmuch.com/2%20Spring%20Cloud/2.4%20%E7%86%94%E6%96%AD%E5%99%A8.html}
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class HystrixApplication {
    /**
     * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
     *
     * @return restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
