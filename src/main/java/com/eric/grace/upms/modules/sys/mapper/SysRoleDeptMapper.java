package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysRoleDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysRoleDeptMapper: 角色部门
 *
 * @author: MrServer
 * @since: 2018/5/3 下午4:35
 */
@Mapper
public interface SysRoleDeptMapper extends CommonDao<SysRoleDept> {


    void deleteByRoleId(String roleId);

    List<String> queryDeptIdList(String roleId);
}