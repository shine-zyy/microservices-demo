package com.zyy.springcloud.api.model.dto.request.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 添加用户DTO
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
public class UserAddDTO implements Serializable {
    private static final long serialVersionUID = -7455736462061115652L;

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, message = "账号长度不能少于2个字符")
    private String account;

    @Length(min = 11, max = 11, message = "请输入正确的手机号")
    private String mobile;

    @Length(min = 6, max = 20, message = "请输入长度为6到20个字符的密码")
    private String password;

    /**
     * 姓名
     */
    private String name;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
