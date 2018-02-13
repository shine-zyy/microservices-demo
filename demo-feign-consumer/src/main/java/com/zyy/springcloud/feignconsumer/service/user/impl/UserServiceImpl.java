package com.zyy.springcloud.feignconsumer.service.user.impl;

import com.zyy.springcloud.api.model.dto.request.user.UserQueryDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.user.UserListResult;
import com.zyy.springcloud.api.model.entity.user.User;
import com.zyy.springcloud.feignconsumer.feign.user.UserFeignClient;
import com.zyy.springcloud.feignconsumer.service.user.UserService;
import com.zyy.springcloud.feignconsumer.vo.UserListVO;
import com.zyy.springcloud.feignconsumer.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserListVO search(UserQueryDTO queryDTO) {
        UserListVO userListVO = new UserListVO();
        BaseResponse<UserListResult> response = userFeignClient.search(queryDTO.getAccount(), queryDTO.getMobile());
        if (!response.isSuccess()) {
            userListVO.setCode(response.getCode());
            userListVO.setMsg(response.getMsg());
            return userListVO;
        }
        List<UserVO> userVOList = new ArrayList<>();
        for(User user : response.getData().getUserList()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }
        userListVO.setUserList(userVOList);
        userListVO.setSuccess(true);
        return userListVO;
    }
}
