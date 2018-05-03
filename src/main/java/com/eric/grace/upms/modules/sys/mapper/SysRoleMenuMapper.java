package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * SysRoleMenuMapper:
 *
 * @author: MrServer
 * @since: 2018/5/3 下午3:43
 */
@Mapper
public interface SysRoleMenuMapper extends CommonDao<SysRoleMenu> {

    // 根据角色删除菜单
    void deleteByRoleId(String roleId);

    // 根据角色获取菜单ID
    List<String> queryMenuIdList(String roleId);
}
