package com.zyy.springcloud.api.model.dto.response.user;

import com.zyy.springcloud.api.model.dto.response.Result;
import com.zyy.springcloud.api.model.entity.user.User;

import java.util.List;

/**
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public class UserListResult extends Result {
    private static final long serialVersionUID = -9019239704971635712L;
    List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
