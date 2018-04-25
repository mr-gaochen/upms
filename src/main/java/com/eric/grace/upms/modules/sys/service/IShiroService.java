package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.upms.modules.sys.entity.SysUser;
import com.eric.grace.upms.modules.sys.entity.TokenEntity;

import java.util.Set;

/**
 * IShiroService:  shiro相关接口
 *
 * @author: MrServer
 * @since: 2018/4/25 下午12:15
 */
public interface IShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    /**
     * 根据token查询
     * @param token
     * @return
     */
    TokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(String userId);

}