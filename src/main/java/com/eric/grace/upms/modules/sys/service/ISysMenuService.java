package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * ISysMenuService: 系统菜单业务接口
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:46
 */
public interface ISysMenuService extends ICommonService<SysMenu> {

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    ResponseVo save(SysMenu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    ResponseVo updateEntity(SysMenu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    ResponseVo deleteByMenuId(String menuId);

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(String userId);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(String parentId);


    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();


}