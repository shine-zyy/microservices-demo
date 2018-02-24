package com.zyy.springcloud.serviceuser.controller;

import com.zyy.springcloud.api.api.UserAPI;
import com.zyy.springcloud.api.controller.AbstractController;
import com.zyy.springcloud.api.model.dto.request.user.UserAddDTO;
import com.zyy.springcloud.api.model.dto.request.user.UserQueryDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.user.UserListResult;
import com.zyy.springcloud.api.model.dto.response.user.UserResult;
import com.zyy.springcloud.api.model.entity.user.User;
import com.zyy.springcloud.common.cache.RUserCache;
import com.zyy.springcloud.serviceuser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务中心
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
@RestController
public class UserController extends AbstractController implements UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private RUserCache rUserCache;

    @Override
    public BaseResponse<UserResult> queryById(@PathVariable Long uid) {
        User user = (User) rUserCache.getUserInfo();
        UserResult result = new UserResult();
        if (null == user) {
            result = userService.queryById(uid);
            if(result.isSuccess()) {
                user = new User();
                BeanUtils.copyProperties(result, user);
                rUserCache.setUserInfo(user);
            }
        } else {
            BeanUtils.copyProperties(user, result);
            result.setSuccess(true);
        }
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<UserListResult> search(String account, String mobile) {
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        userQueryDTO.setMobile(mobile);
        userQueryDTO.setAccount(account);
        UserListResult result = userService.search(userQueryDTO);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse add(@RequestBody UserAddDTO addDTO) {
        Result result = userService.add(addDTO);
        return buildJson(result.getCode(), result.getMsg());
    }
}
