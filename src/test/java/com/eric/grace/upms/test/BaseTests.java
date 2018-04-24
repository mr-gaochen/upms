package com.eric.grace.upms.test;

import com.eric.grace.upms.modules.sys.service.ISysUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BaseTests: 测试基础类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午4:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTests {


    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Test
    public void testUserRole() {

        sysUserRoleService.saveUserRoles("1","1,2,3,4,5,6");
    }


}