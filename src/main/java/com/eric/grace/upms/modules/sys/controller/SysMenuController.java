package com.eric.grace.upms.modules.sys.controller;

import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysMenuController: 系统菜单接口层
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:48
 */
@RestController
@RequestMapping("api/sys/permission/menu/")
@Api(value = "SysMenu操作类", description = "Menu相关操作接口定义类")
public class SysMenuController { //extends BaseController<ISysMenuService, SysMenu>

    @Autowired
    private ISysMenuService sysMenuService;



}