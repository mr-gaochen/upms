package com.eric.grace.upms.modules.sys.mapper;

import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * SysUserMapper:
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:08
 */
@Mapper
public interface SysUserMapper extends CommonDao<SysUser> {

    /**
     * 根据用户名获取用户基本信息
     * @param username
     * @return
     */
    SysUser selectUserByUsername(String username);
}
