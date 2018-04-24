package com.eric.grace.upms.modules.api.service;

/**
 * UserService: 用户
 *
 * @author: MrServer
 * @since: 2018/4/24 下午12:36
 */
public interface UserService {

    /**
     *  用户登录
     * @param username 手机号
     * @param password  密码
     * @return  返回用户ID
     */
    String login(String username, String password);

}