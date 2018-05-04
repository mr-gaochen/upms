package com.eric.grace.upms.modules.sys.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询所有列表
     * @param page
     * @param params
     * @return
     */
    List<SysRole> queryAll(Page<SysRole> page,  @Param("params") Map<String, String> params);
}
