package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.upms.modules.sys.entity.SysRoleDept;

import java.util.List;

/**
 * ISysRoleDeptService: 部门角色
 *
 * @author: MrServer
 * @since: 2018/5/3 下午4:32
 */
public interface ISysRoleDeptService extends ICommonService<SysRoleDept> {

    void saveOrUpdate(String roleId, List<String> deptIdList);

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<String> queryDeptIdList(String roleId);

}