package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysMenuMapper: 系统菜单
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:45
 */
@Mapper
public interface SysMenuMapper extends CommonDao<SysMenu> {

    // 获取所有菜单
    List<SysMenu> queryList();

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(String parentId);


    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenu> queryUserList(String userId);

    List<SysMenu> queryAllMenuId(String userId);

    List<SysMenu> selectAllList();
}
