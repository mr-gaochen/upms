package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.mapper.SysMenuMapper;
import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import org.springframework.stereotype.Service;

/**
 * SysMenuServiceImpl: 系统菜单实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:46
 */
@Service
public class SysMenuServiceImpl extends CommonServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}