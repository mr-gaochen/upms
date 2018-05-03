package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysRoleMenu;
import com.eric.grace.upms.modules.sys.mapper.SysRoleMenuMapper;
import com.eric.grace.upms.modules.sys.service.ISysRoleMenuService;
import com.eric.grace.utils.common.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * SysRoleMenuServiceImpl:
 *
 * @author: MrServer
 * @since: 2018/5/3 下午3:42
 */
@Service
public class SysRoleMenuServiceImpl extends CommonServiceImpl<SysRoleMenuMapper,SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(String roleId, List<String> menuIdList) {

        //先删除角色与菜单关系
        sysRoleMenuDao.deleteByRoleId(roleId);

        if(menuIdList.size() == 0){
            return ;
        }

        ArrayList list = new ArrayList();
        for (String menuId : menuIdList ) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setId(RandomUtil.simpleUUID());
            roleMenu.setUpdateTime(new Date());
            roleMenu.setCreateTime(new Date());
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenu.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
            list.add(roleMenu);
        }
        //保存角色与菜单关系
        super.insertBatch(list);
    }

    @Override
    public List<String> queryMenuIdList(String roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }
}