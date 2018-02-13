package com.zyy.springcloud.hystrix.controller;

import com.zyy.springcloud.api.model.dto.response.user.UserResult;
import com.zyy.springcloud.hystrix.service.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyanyan
 * @date: 2018/1/24
 */
@RestController
public class RibbonHystrixController {
    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @GetMapping("/ribbon/{id}")
    public UserResult findById(@PathVariable Long id) {
        return new UserResult();
       // return ribbonHystrixService.findById(id);
    }
}
