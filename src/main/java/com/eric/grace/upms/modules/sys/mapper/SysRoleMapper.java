package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * SysRoleMapper: 系统角色Mapper
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:08
 */
@Mapper
public interface SysRoleMapper extends CommonDao<SysRole> {
    /**
     * 批量删除角色
     * @param id
     */
    void deleteBatch( String[] id);
}
