package com.eric.grace.upms.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysRole;

import java.util.Map;

/**
 * ISysRoleService: 系统角色接口类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:30
 */
public interface ISysRoleService extends ICommonService<SysRole> {
    /**
     * 创建角色
     * @param role
     * @return
     */
    ResponseVo save(SysRole role,String createId);

    /**
     * 批量删除角色
     * @param roleId
     */
    String[] deleteBatch(String roleId);

    /**
     * 根据角色编码查询角色
     * @param roleCode
     * @return
     */
    SysRole selectByRoleCode(String roleCode);

    /**
     * 更新角色
     * @param role
     */
    void update(SysRole role);

    /**
     * 条件查询角色
     * @param page
     * @param params
     * @return
     */
    Page<SysRole> selectOptionPage(Page<SysRole> page, Map<String, String> params);
}
