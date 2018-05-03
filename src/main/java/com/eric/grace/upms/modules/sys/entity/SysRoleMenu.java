package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;
import io.swagger.annotations.ApiModel;

/**
 * SysRoleMenu: 系统角色对应关系
 *
 * @author: MrServer
 * @since: 2018/5/3 下午3:31
 */
@TableName("t_sys_role_menu")
@ApiModel(value = "系统角色菜单")
public class SysRoleMenu extends BaseModel<SysRoleMenu> {


    // 角色Id
    private String roleId;

    // 菜单Id
    private String menuId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}