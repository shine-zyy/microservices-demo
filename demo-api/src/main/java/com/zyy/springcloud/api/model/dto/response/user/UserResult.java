package com.zyy.springcloud.api.model.dto.response.user;

import com.zyy.springcloud.api.model.dto.response.Result;

import java.util.Date;

/**
 * 返回用户信息
 *
 * @author: zhangyanyan
 * @date: 2018/2/8
 */
public class UserResult extends Result {
    private static final long serialVersionUID = 4760675676165788052L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户账户
     */
    private String account;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 账号
     */
    private String name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
