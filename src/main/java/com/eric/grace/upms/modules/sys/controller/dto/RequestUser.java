package com.eric.grace.upms.modules.sys.controller.dto;


import com.eric.grace.upms.modules.sys.entity.SysUser;

/**
 * RequestUser: 创建用户实体
 *
 * @author: MrServer
 * @since: 2018/4/18 下午5:15
 */
public class RequestUser {

    private SysUser sysUser;

    private String roles;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}