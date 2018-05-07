package com.eric.grace.upms.modules.sys.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.dao.CommonDao;
import com.eric.grace.upms.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    /**
     * 根据用户ID获取所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(String userId);

    /**
     * 批量删除用户
     * @param userId
     */
    void deleteBatch(String[] userId);

    /**
     * 获取所有用户
     * @param page
     * @param params
     * @return
     */
    List<SysUser> queryAll(Page<SysUser> page, @Param("params") Map<String, String> params);

//    /**
//     * 获取用户拥有的菜单ID
//     * @param userId
//     * @return
//     */
//    List<String> queryAllMenuId(String userId);
}
