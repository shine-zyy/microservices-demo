package com.zyy.springcloud.feignconsumer.vo;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 返回终端用户列表
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public class UserListVO extends Result implements Serializable{
    private static final long serialVersionUID = -4282700190934146663L;

    private List<UserVO> userList;

    public List<UserVO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserVO> userList) {
        this.userList = userList;
    }
}
