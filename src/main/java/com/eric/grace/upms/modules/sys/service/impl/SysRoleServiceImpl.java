package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import com.eric.grace.upms.modules.sys.mapper.SysRoleMapper;
import com.eric.grace.upms.modules.sys.service.ISysRoleService;
import com.eric.grace.utils.common.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * SysRoleServiceImpl: 系统角色业务实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:30
 */
@Service
public class SysRoleServiceImpl extends CommonServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    /**
     * 创建角色
     * @param role
     * @param createId
     * @return
     */
    @Override
    public ResponseVo save(SysRole role, String createId) {

        role.setId(RandomUtil.simpleUUID());
        role.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
        role.setCreateTime(new Date());
        role.setUpdateUserId(createId);
        role.setCreateUserId(createId);
        role.setUpdateTime(new Date());
        super.insert(role);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS,role);
    }
}