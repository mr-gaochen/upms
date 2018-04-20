package com.eric.grace.upms.controller;

import com.eric.grace.dao.common.controller.BaseController;
import com.eric.grace.upms.entity.SysRole;
import com.eric.grace.upms.service.ISysRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysRoleController: 系统角色控制成
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:31
 */
@RestController
@RequestMapping("api/sys/permission/role/")
@Api(value = "SysRole操作类", description = "Role相关操作接口定义类")
public class SysRoleController extends BaseController<ISysRoleService, SysRole> {








}