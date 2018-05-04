package com.eric.grace.upms.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.common.utils.SpringContextHolder;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import com.eric.grace.upms.modules.sys.mapper.SysRoleMapper;
import com.eric.grace.upms.modules.sys.mapper.SysUserMapper;
import com.eric.grace.upms.modules.sys.service.ISysRoleDeptService;
import com.eric.grace.upms.modules.sys.service.ISysRoleMenuService;
import com.eric.grace.upms.modules.sys.service.ISysRoleService;
import com.eric.grace.utils.collection.CollUtil;
import com.eric.grace.utils.common.ArrayUtil;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * SysRoleServiceImpl: 系统角色业务实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:30
 */
@Service
public class SysRoleServiceImpl extends CommonServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private static Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysRoleDeptService sysRoleDeptService;


    /**
     * 创建角色
     *
     * @param role
     * @param createId
     * @return
     */
    @Override
    @Transactional
    public ResponseVo save(SysRole role, String createId) {

        role.setId(RandomUtil.simpleUUID());
        role.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
        role.setCreateTime(new Date());
        role.setUpdateUserId(createId);
        role.setCreateUserId(createId);
        role.setUpdateTime(new Date());
        super.insert(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());

        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, role);
    }


    /**
     * 批量删除角色
     *
     * @param ids
     */
    @Override
    @Transactional
    public String[] deleteBatch(String ids) {
        logger.info("需要删除的id:{}", ids);
        String[] idArray = null;
        if (StrUtil.sub(ids, 0, -1).equals(',')) {
            idArray = StrUtil.splitToArray(StrUtil.sub(ids, 0, -1), ',');
        } else {
            idArray = StrUtil.splitToArray(ids, ',');
        }

        if (ArrayUtil.contains(idArray, "0")) {
            for (String str : idArray) {
                if (str.equals("0")) {
                    idArray = ArrayUtil.removeEle(idArray, str);
                }
            }
        }

        SpringContextHolder.getBean(SysRoleMapper.class).deleteBatch(idArray);
        return idArray;
    }


    /**
     * 根据角色名称查询
     *
     * @param roleCode
     * @return
     */
    @Override
    public SysRole selectByRoleCode(String roleCode) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("role_code", roleCode);
        return (SysRole) selectOne(wrapper);
    }

    /**
     * 更新角色
     * @param role
     */
    @Override
    @Transactional
    public void update(SysRole role) {
        super.updateById(role);
        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());
    }


    /**
     * 条件查询角色
     * @param page
     * @param params
     * @return
     */
    @Override
    public Page<SysRole> selectOptionPage(Page<SysRole> page, Map<String, String> params) {
        return page.setRecords(SpringContextHolder.getBean(SysRoleMapper.class).queryAll(page, params));
    }


}