package com.zyy.springcloud.serviceuser.service;

import com.zyy.springcloud.api.model.dto.request.user.UserAddDTO;
import com.zyy.springcloud.api.model.dto.request.user.UserQueryDTO;
import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.dto.response.user.UserListResult;
import com.zyy.springcloud.api.model.dto.response.user.UserResult;
import com.zyy.springcloud.api.model.entity.user.User;
import com.zyy.springcloud.api.model.enums.ResultCode;
import com.zyy.springcloud.api.model.enums.user.UserStatusEnums;
import com.zyy.springcloud.serviceuser.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户管理
 *
 * @author zhangyanyan
 * @date 2018/2/2
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public UserResult queryById(Long id) {
        UserResult result = new UserResult();
        User user = new User();
        user.setId(id);
        List<User> userList = userDAO.query(user);
        if (CollectionUtils.isEmpty(userList)) {
            result.setCode(ResultCode.USER_NOT_EXIST.getCode());
            result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
            return result;
        }
        BeanUtils.copyProperties(userList.get(0), result);
        result.setSuccess(true);
        return result;
    }

    public Result add(UserAddDTO addDTO) {
        //判断用户账号是否已存在
        User user = new User();
        user.setAccount(addDTO.getAccount());
        List<User> userList = userDAO.query(user);
        if (!CollectionUtils.isEmpty(userList)) {
            return Result.fail(ResultCode.USER_EXIST);
        }

        User userPO = new User();
        BeanUtils.copyProperties(addDTO, userPO);
        userPO.setStatus(UserStatusEnums.ENABLE.getValue());
        Boolean isAdd = userDAO.insert(userPO);
        return isAdd ? Result.success() : Result.fail(ResultCode.USER_CREATE_FAIL);
    }

    public UserListResult search(UserQueryDTO userQueryDTO) {
        UserListResult result = new UserListResult();
        User user = new User();
        BeanUtils.copyProperties(userQueryDTO, user);
        List<User> userList = userDAO.query(user);
        if (CollectionUtils.isEmpty(userList)) {
            result.setCode(ResultCode.USER_NOT_EXIST.getCode());
            result.setMsg(ResultCode.USER_NOT_EXIST.getDesc());
            return result;
        }
        result.setUserList(userList);
        result.setSuccess(true);
        return result;
    }
}
