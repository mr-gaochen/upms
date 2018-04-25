package com.eric.grace.upms.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * SysDept: 部门
 *
 * @author: MrServer
 * @since: 2018/4/24 下午2:14
 */
@TableName("t_sys_dept")
@ApiModel(value = "部门模型")
public class SysDept extends BaseModel<SysDept>{

    //上级部门ID，一级部门为 "0"
    private String parentId;
    //部门名称
    private String name;

    //上级部门名称
    @TableField(exist = false)
    private String parentName;

    //排序
    private Integer orderNum;


    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<?> list;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}