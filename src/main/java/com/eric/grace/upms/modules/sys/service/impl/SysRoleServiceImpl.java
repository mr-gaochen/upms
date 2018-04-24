package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import com.eric.grace.upms.modules.sys.mapper.SysRoleMapper;
import com.eric.grace.upms.modules.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * SysRoleServiceImpl: 系统角色业务实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:30
 */
@Service
public class SysRoleServiceImpl extends CommonServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    @Override
    public ResponseVo save(SysRole role) {

        role.setId("");



        return null;
    }
}