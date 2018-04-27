package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;
import com.eric.grace.upms.common.constant.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * SysUser: 系统用户表
 *
 * @author: MrServer
 * @since: 2018/4/18 下午2:56
 */

@TableName("t_sys_user")
@ApiModel(value = "系统用户")
public class SysUser extends BaseModel<SysUser> {

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;


    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;


    @ApiModelProperty(value = "真实姓名")
    @TableField("realname")
    private String realname;


    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;


    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private String birthday;


    @ApiModelProperty(value = "性别 0: 女  1：男  -1：中性")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "说明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "头像")
    @TableField("avator")
    private String avator;

    @ApiModelProperty(value = "用户状态")
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 角色ID列表
     */
    @TableField(exist = false)
    private List<String> roleIdList;
    @TableField(exist = false)
    private String roleIds;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }


    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }


    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}



