package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysRole;

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

    SysRole selectByRoleCode(String roleCode);
}
