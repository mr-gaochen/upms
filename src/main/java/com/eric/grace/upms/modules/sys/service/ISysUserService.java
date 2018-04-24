package com.eric.grace.upms.modules.sys.service;


import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.controller.dto.RequestPassword;
import com.eric.grace.upms.modules.sys.controller.dto.RequestUser;
import com.eric.grace.upms.modules.sys.entity.SysUser;

/**
 * ISysUserService: 用户接口类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:05
 */
public interface ISysUserService extends ICommonService<SysUser> {

    /**
     * 根据用户名获取用户基本信息
     * @param username
     * @return
     */
    SysUser selectUserByUsername(String username);


    /**
     * 创建用户
     * @param requestUser
     * @return
     */
    ResponseVo saveEntity(RequestUser requestUser);


    /**
     * 重置密码
     * @param userId
     * @return
     */
    ResponseVo resetPassword(String userId);


    /**
     * 更新密码
     * @param requestPassword
     * @return
     */
    ResponseVo EditPassword(RequestPassword requestPassword);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ResponseVo login(String username, String password);
}
