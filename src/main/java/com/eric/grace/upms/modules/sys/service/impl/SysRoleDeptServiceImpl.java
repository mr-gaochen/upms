package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysRoleDept;
import com.eric.grace.upms.modules.sys.mapper.SysRoleDeptMapper;
import com.eric.grace.upms.modules.sys.service.ISysRoleDeptService;
import com.eric.grace.utils.common.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SysRoleDeptServiceImpl: 角色部门业务实现类
 *
 * @author: MrServer
 * @since: 2018/5/3 下午4:36
 */
@Service
public class SysRoleDeptServiceImpl extends CommonServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {

    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;


    @Override
    public void saveOrUpdate(String roleId, List<String> deptIdList) {

        //先删除角色与菜单关系
        sysRoleDeptMapper.deleteByRoleId(roleId);

        if (deptIdList.size() == 0) {
            return;
        }

        ArrayList<SysRoleDept> list = new ArrayList();

        for (String deptId : deptIdList) {
            SysRoleDept roleDept = new SysRoleDept();
            roleDept.setId(RandomUtil.simpleUUID());
            roleDept.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
            roleDept.setRoleId(roleId);
            roleDept.setDeptId(deptId);
            roleDept.setCreateTime(new Date());
            roleDept.setUpdateTime(new Date());
            list.add(roleDept);
        }
        super.insertBatch(list);
    }

    @Override
    public List<String> queryDeptIdList(String roleId) {
        return sysRoleDeptMapper.queryDeptIdList(roleId);
    }
}