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
}
