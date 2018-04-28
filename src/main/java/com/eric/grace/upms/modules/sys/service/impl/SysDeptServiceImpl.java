package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysDept;
import com.eric.grace.upms.modules.sys.mapper.SysDeptMapper;
import com.eric.grace.upms.modules.sys.service.ISysDeptService;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * SysDeptServiceImpl:
 *
 * @author: MrServer
 * @since: 2018/4/24 下午2:21
 */
@Service
public class SysDeptServiceImpl extends CommonServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper deptDao;

    /***
     * 获取部门列表
     * @author Mr.Eric
     * @date 2018/4/28 上午11:05
     * @param
     * @return java.util.List<com.eric.grace.upms.modules.sys.entity.SysDept>
     */
    @Override
    public List<SysDept> queryList() {
        return deptDao.queryAll();
    }

    /**
     * 创建部门
     *
     * @param dept
     * @param userId
     */
    @Override
    public ResponseVo save(SysDept dept, String userId) {
        dept.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
        dept.setUpdateUserId(userId);
        dept.setCreateUserId(userId);
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        dept.setId(RandomUtil.simpleUUID());
        if (StrUtil.isBlank(dept.getParentId())) {
            dept.setParentId("-1");
        }
        super.insert(dept);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


    /**
     * 查询当前机构下的子机构
     *
     * @param parentId
     * @return
     */
    @Override
    public List<String> queryDetpIdList(String parentId) {
        return deptDao.queryDetpIdList(parentId);
    }


}