package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysUserRoleMapper: 用户与角色对应关系
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:57
 */
@Mapper
public interface SysUserRoleMapper extends CommonDao<SysUserRole> {

    /**
     * 根据userId 物理删除角色
     * @param userId
     */
    void deleteRolesByUserId(String userId);


    /**
     * 根据用户ID获取角色ID
     * @param userId
     * @return
     */
    List<String> queryRoleIdList(String userId);
}
