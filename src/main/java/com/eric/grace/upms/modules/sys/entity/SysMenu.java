package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * SysMenu: 系统菜单
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:35
 */
@TableName("t_sys_menu")
@ApiModel(value = "系统菜单模型")
public class SysMenu extends BaseModel<SysMenu> {


    @ApiModelProperty(value = "菜单名称")
    @TableField("meun_name")
    private String menuName;

    @ApiModelProperty(value = "菜单路径")
    @TableField("meun_href")
    private String menuHref;

    @ApiModelProperty(value = "菜单类型  0：目录   1：菜单   2：按钮 ")
    @TableField("meun_type")
    private String menuType;

    @ApiModelProperty(value = "菜单图标")
    @TableField("meun_icon")
    private String menuIcon;

    @ApiModelProperty(value = "菜单排序")
    @TableField("meun_sort")
    private String menuSort;

    @ApiModelProperty(value = "父菜单")
    @TableField("parent_id")
    private String parentId;


    @ApiModelProperty(value = "权限标识符")
    @TableField("menu_perms")
    private String menuPerms;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(String menuSort) {
        this.menuSort = menuSort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuPerms() {
        return menuPerms;
    }

    public void setMenuPerms(String menuPerms) {
        this.menuPerms = menuPerms;
    }
}