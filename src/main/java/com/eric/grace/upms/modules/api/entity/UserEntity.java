package com.eric.grace.upms.modules.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * UserEntity:
 *
 * @author: MrServer
 * @since: 2018/4/24 下午12:48
 */
public class UserEntity implements Serializable {


    //用户ID
    private String userId;
    //用户名
    private String username;
    //手机号
    private String phone;
    //密码
    transient private String password;
    //创建时间
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}