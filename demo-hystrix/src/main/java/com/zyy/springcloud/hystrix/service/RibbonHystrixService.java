package com.zyy.springcloud.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhangyanyan
 * @date: 2018/1/24
 */
@Service
public class RibbonHystrixService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RibbonHystrixService.class);

//    /**
//     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
//     *
//     * @param id id
//     * @return 通过id查询到的用户
//     */
//    @HystrixCommand(fallbackMethod = "fallback")
//    public Account findById(Long id) {
//        AccountResult accountResult = this.restTemplate.getForObject("http://demo-service-provider/account/" + id, AccountResult.class);
//        if(null == accountResult) {
//            return new Account("zhangSan", 10);
//        }
//        return accountResult.getAccount();
//    }
//
//    /**
//     * hystrix fallback方法
//     *
//     * @param id id
//     * @return 默认的用户
//     */
//    public Account fallback(Long id) {
//        logger.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
//        Account account = new Account();
//        account.setId(-1L);
//        account.setName("default username");
//        account.setAge(0);
//        return account;
//    }
}
