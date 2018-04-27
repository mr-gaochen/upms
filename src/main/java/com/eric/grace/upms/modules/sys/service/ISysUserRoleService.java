package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.upms.modules.sys.entity.SysUserRole;

import java.util.List;

/**
 * ISysUserRoleService: 用户与角色对应关系
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:54
 */
public interface ISysUserRoleService extends ICommonService<SysUserRole>{


    /**
     * 添加用户角色
     * @param userId
     * @param roleIds  角色集合
     */
    void saveUserRoles(String userId,List<String> roleIds,String updateUserId);


    /**
     * 根据用户ID 获取用户的角色列表
     * @param userId
     * @return
     */
    List<String> queryRoleIdList(String userId);
}
