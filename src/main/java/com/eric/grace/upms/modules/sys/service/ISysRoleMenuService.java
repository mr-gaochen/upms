package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.upms.modules.sys.entity.SysRoleMenu;

import java.util.List;

/**
 * ISysRoleMenuService:  角色菜单 业务接口
 *
 * @author: MrServer
 * @since: 2018/5/3 下午3:39
 */
public interface ISysRoleMenuService  extends ICommonService<SysRoleMenu> {


    /**
     * 更新保存角色菜单
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<String> queryMenuIdList(String roleId);

}