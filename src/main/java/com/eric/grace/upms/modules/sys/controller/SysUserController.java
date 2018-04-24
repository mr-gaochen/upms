package com.eric.grace.upms.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.model.page.FrontPage;
import com.eric.grace.dao.common.model.page.GracePage;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.service.util.StringTools;
import com.eric.grace.upms.modules.sys.controller.dto.RequestPassword;
import com.eric.grace.upms.modules.sys.controller.dto.RequestUser;
import com.eric.grace.upms.modules.sys.entity.SysUser;
import com.eric.grace.upms.modules.sys.service.ISysUserRoleService;
import com.eric.grace.upms.modules.sys.service.ISysUserService;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

/**
 * SysUserController: 系统用户类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:10
 */
@RestController
@RequestMapping("api/sys/permission/user/")
@Api(value = "SysUser操作类", description = "User相关操作接口定义类")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);


    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 创建用户
     *
     * @param requestUser
     * @return
     */
    @PostMapping("addUser")
    public ResponseVo createUser(@RequestBody RequestUser requestUser) {
        return sysUserService.saveEntity(requestUser);
    }


    /**
     * 根据用户名 or userId获取用户
     *
     * @param userId
     * @param username
     * @return
     */
    @PostMapping("findOne")
    public ResponseVo findUserByIdOrUserName(String userId, String username) {

        if (StrUtil.isBlank(userId) && StrUtil.isBlank(username)) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
        }

        if (!StrUtil.isBlank(userId)) {
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, sysUserService.selectById(userId));
        }

        if (!StrUtil.isBlank(username)) {
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, sysUserService.selectUserByUsername(username));
        }

        return null;
    }


    /**
     * 修改用户角色
     *
     * @return
     */
    @PutMapping(value = "editUserRole")
    public ResponseVo editUserRoles(String userId, String roles) {
        if (StrUtil.isBlank(userId) && StrUtil.isBlank(roles)) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
        }
        sysUserRoleService.saveUserRoles(userId, roles);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


    /**
     * 修改用户基本信息
     *
     * @return
     */
    @PutMapping(value = "editUser")
    public ResponseVo editUser(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping(value = "resetPw")
    public ResponseVo resetPw(@RequestBody String userId) {
        return sysUserService.resetPassword(userId);
    }


    /**
     * 更新密码
     * @param requestPassword
     * @return
     */
    @PostMapping(value = "editPw")
    public ResponseVo editPw(@RequestBody RequestPassword requestPassword){
        return sysUserService.EditPassword(requestPassword);
    }



    /**
     * 条件查询用户list
     *
     * @param spage
     * @return
     */
    @PostMapping("list")
    public ResponseVo getAllUsersPages(@RequestBody FrontPage<SysUser> spage) {
        Page<SysUser> page = new Page<SysUser>(spage.getCurentPage(), spage.getPageRowNum());
        if (null != spage.getSort()) {
            if (null == spage.getSort().getPredicate() || StrUtil.isBlank(spage.getSort().getPredicate())) {
                spage.getSort().setPredicate("update_time");
            }
            //设置排序字段
            page.setOrderByField(spage.getSort().getPredicate());
            page.setAsc(spage.getSort().getReverse());
        }

        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();

        if (spage.getSearch() != null) {
            Field[] fields = spage.getSearch().getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(spage.getSearch());
                    if (null != value && !value.equals("")) {
                        String fieldname = StringTools.underscoreName(fields[i].getName());
                        wrapper.like(fieldname, value.toString());
                    }
                    fields[i].setAccessible(false);
                } catch (Exception e) {
                }
            }
        }
        Page<SysUser> pageList = sysUserService.selectPage(page, wrapper);
        GracePage<SysUser> gracePage = new GracePage<SysUser>(pageList);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, gracePage);
    }



    @PostMapping("current")
    public ResponseVo getCurrent() {
        SysUser sysUser = new SysUser();
        sysUser.setId(RandomUtil.simpleUUID());
        sysUser.setUsername("admin");
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, sysUser);

    }


}