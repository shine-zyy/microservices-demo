package com.zyy.springcloud.api.model.dto.request.user;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询用户DTO
 *
 * @author zhangyanyan
 * @date 2018/1/2
 */
public class UserQueryDTO implements Serializable {
    private static final long serialVersionUID = -416934076542306949L;
    /**
     * 账号
     */
    private String account;

    @NotNull(message = "手机号不能为空")
    private String mobile;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
