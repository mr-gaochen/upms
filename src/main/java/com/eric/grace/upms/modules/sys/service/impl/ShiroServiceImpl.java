package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.entity.SysUser;
import com.eric.grace.upms.modules.sys.entity.TokenEntity;
import com.eric.grace.upms.modules.sys.mapper.SysMenuMapper;
import com.eric.grace.upms.modules.sys.mapper.SysUserMapper;
import com.eric.grace.upms.modules.sys.mapper.TokenMapper;
import com.eric.grace.upms.modules.sys.service.IShiroService;
import com.eric.grace.utils.common.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ShiroServiceImpl:
 *
 * @author: MrServer
 * @since: 2018/4/25 下午12:16
 */
@Service
public class ShiroServiceImpl implements IShiroService {


    @Autowired
    private SysMenuMapper sysMenuDao;

    @Autowired
    private SysUserMapper sysUserDao;

    @Autowired
    private TokenMapper tokenDao;


    @Override
    public Set<String> getUserPermissions(String userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == SysConstant.SUPER_ADMIN) {
            List<SysMenu> menuList = sysMenuDao.queryList();
            permsList = new ArrayList<>(menuList.size());
            for (SysMenu menu : menuList) {
                permsList.add(menu.getMenuPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StrUtil.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public TokenEntity queryByToken(String token) {
        return tokenDao.queryByToken(token);
    }

    @Override
    public SysUser queryUser(String userId) {
        return sysUserDao.selectById(userId);
    }
}