package com.eric.grace.upms.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.upms.entity.SysRole;
import com.eric.grace.upms.mapper.SysRoleMapper;
import com.eric.grace.upms.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * SysRoleServiceImpl: 系统角色业务实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:30
 */
@Service
public class SysRoleServiceImpl extends CommonServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
}