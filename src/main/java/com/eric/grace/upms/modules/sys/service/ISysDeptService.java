package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysDept;

import java.util.List;

/**
 * ISysDeptService: 部门业务
 *
 * @author: MrServer
 * @since: 2018/4/24 下午2:20
 */
public interface ISysDeptService extends ICommonService<SysDept> {

    /**
     * 获取部门信息
     * @return
     */
    List<SysDept> queryList();


    /**
     * 创建角色
     * @param dept
     * @param userId
     */
    ResponseVo save(SysDept dept, String userId);

    /**
     * 当前当前机构下的子机构
     * @param id
     * @return
     */
    List<String> queryDetpIdList(String id);
}
