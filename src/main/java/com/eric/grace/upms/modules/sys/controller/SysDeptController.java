package com.eric.grace.upms.modules.sys.controller;

import com.eric.grace.upms.modules.sys.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysDeptController: 部门管理
 *
 * @author: MrServer
 * @since: 2018/4/25 上午11:48
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {

    @Autowired
    private ISysDeptService deptService;



}