package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;
import io.swagger.annotations.ApiModel;

/**
 * SysRoleDept: 角色与部门对应关系
 *
 * @author: MrServer
 * @since: 2018/5/3 下午4:33
 */
@TableName("t_sys_role_dept")
@ApiModel(value = "系统角色菜单")
public class SysRoleDept extends BaseModel<SysRoleDept> {

    private String deptId;

    private String roleId;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}