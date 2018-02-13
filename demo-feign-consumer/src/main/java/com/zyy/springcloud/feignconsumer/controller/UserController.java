package com.zyy.springcloud.feignconsumer.controller;

import com.zyy.springcloud.api.model.dto.request.user.UserQueryDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.feignconsumer.service.user.UserService;
import com.zyy.springcloud.feignconsumer.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author zhangyanyan
 * @date 2018/1/2
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/json"})
    public BaseResponse<UserListVO> search(@RequestParam(value = "acount", required = false) String account,
                                       @RequestParam(value = "mobile", required = true) String mobile) {

        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setAccount(account);
        queryDTO.setMobile(mobile);
        UserListVO result = userService.search(queryDTO);
        return buildJson(result.getCode(), result.getMsg(), result);
    }
}
