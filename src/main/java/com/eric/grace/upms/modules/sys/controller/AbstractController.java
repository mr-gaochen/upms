package com.eric.grace.upms.modules.sys.controller;

import com.eric.grace.upms.modules.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractController: Controller公共组件
 *
 * @author: MrServer
 * @since: 2018/4/24 下午12:26
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getId();
    }


    protected String getDeptId() {
        return getUser().getDeptId();
    }


}