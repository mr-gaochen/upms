package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysDeptMapper:
 *
 * @author: MrServer
 * @since: 2018/4/24 下午2:22
 */
@Mapper
public interface SysDeptMapper extends CommonDao<SysDept> {

    // 获取所有部门
    List<SysDept> queryAll();

    // 查询当前机构下的子机构
    List<String> queryDetpIdList(String parentId);
}
