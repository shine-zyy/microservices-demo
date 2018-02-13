package com.zyy.springcloud.feignconsumer.service.user;

import com.zyy.springcloud.api.model.dto.request.user.UserQueryDTO;
import com.zyy.springcloud.feignconsumer.vo.UserListVO;
import com.zyy.springcloud.feignconsumer.vo.UserVO;

/**
 * 用户管理
 *
 * @author: zhangyanyan
 * @date: 2018/2/13
 */
public interface UserService {
    /**
     * 查询用户信息
     *
     * @param queryDTO
     * @return
     */
    UserListVO search(UserQueryDTO queryDTO);

}
