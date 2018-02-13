package com.zyy.springcloud.api.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = 5641321210019206524L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified = new Date();

    /**
     * 虚拟时间，用于查询参数
     */
    private Date virtualCreateTime;
    /**
     * 创建人ID
     */
    private Long creator;

    /**
     * 修改人ID
     */
    private Long modifier;

    /**
     * 版本号
     */
    private Long version;

    /**
     * appCode
     */
    private String appCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getVirtualCreateTime() {
        return virtualCreateTime;
    }

    public void setVirtualCreateTime(Date virtualCreateTime) {
        this.virtualCreateTime = virtualCreateTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
