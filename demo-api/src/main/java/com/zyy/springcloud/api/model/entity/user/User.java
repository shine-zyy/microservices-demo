package com.zyy.springcloud.api.model.entity.user;

import com.zyy.springcloud.api.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyanyan
 * @date 2018/1/2
 */
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -81849676368907419L;
    /**
     * 用户账户
     */
    private String account;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 注册时间
     */
    private Date regTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 用户状态
     */
    private String status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
