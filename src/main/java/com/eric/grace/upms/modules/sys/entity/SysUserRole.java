package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;


/**
 * SysUserRole: 系统用户角色
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:50
 */
@TableName("t_sys_user_role")
public class SysUserRole extends BaseModel<SysRole> {

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;



    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}