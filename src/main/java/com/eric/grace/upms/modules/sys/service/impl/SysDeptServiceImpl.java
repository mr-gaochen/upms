package com.eric.grace.upms.modules.sys.service.impl;

import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.upms.modules.sys.entity.SysDept;
import com.eric.grace.upms.modules.sys.mapper.SysDeptMapper;
import com.eric.grace.upms.modules.sys.service.ISysDeptService;
import org.springframework.stereotype.Service;

/**
 * SysDeptServiceImpl:
 *
 * @author: MrServer
 * @since: 2018/4/24 下午2:21
 */
@Service
public class SysDeptServiceImpl extends CommonServiceImpl<SysDeptMapper,SysDept> implements ISysDeptService {
}