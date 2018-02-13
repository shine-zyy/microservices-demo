package com.zyy.springcloud.serviceuser.dao;

import com.zyy.springcloud.api.model.entity.user.User;

import java.util.List;

/**
 * 用户持久层
 *
 * @author: zhangyanyan
 * @date: 2018/2/12
 */
public interface UserDAO {
    List<User> query(User user);

    Boolean insert(User user);
}
