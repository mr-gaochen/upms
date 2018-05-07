package com.eric.grace.upms.modules.sys.controller;

import com.alibaba.fastjson.JSON;
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
import com.eric.grace.upms.modules.sys.entity.SysDept;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.entity.SysUser;
import com.eric.grace.upms.modules.sys.service.IShiroService;
import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import com.eric.grace.upms.modules.sys.service.ISysUserRoleService;
import com.eric.grace.upms.modules.sys.service.ISysUserService;
import com.eric.grace.utils.collection.CollUtil;
import com.eric.grace.utils.common.ArrayUtil;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SysUserController: 系统用户类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:10
 */
@RestController
@RequestMapping("/sys/user/")
@Api(value = "SysUser操作类", description = "User相关操作接口定义类")
public class SysUserController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);


    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IShiroService shiroService;


    /**
     * 创建用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("add")
    // @RequiresPermissions("sys:user:add")
    public ResponseVo createUser(@RequestBody SysUser sysUser) {
        return sysUserService.saveEntity(sysUser, getUserId());
    }


    /**
     * 根据用户名 or userId获取用户
     *
     * @param userId
     * @param username
     * @return
     */
    @GetMapping("findOne")
    public ResponseVo findUserByIdOrUserName(String userId, String username) {

        if (StrUtil.isBlank(userId) && StrUtil.isBlank(username)) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
        }

        if (!StrUtil.isBlank(userId)) {
            SysUser sysUser = sysUserService.selectById(userId);
            if (null != sysUser) {
                //获取用户所属的角色列表
                List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
                sysUser.setRoleIdList(roleIdList);
            }
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, sysUser);
        }

        if (!StrUtil.isBlank(username)) {
            SysUser sysUser = sysUserService.selectUserByUsername(username);
            if (null != sysUser) {
                //获取用户所属的角色列表
                List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
                sysUser.setRoleIdList(roleIdList);
            }
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, sysUser);
        }

        return null;
    }


//    /**
//     * 修改用户角色
//     *
//     * @return
//     */
//    @PutMapping(value = "editUserRole")
//    public ResponseVo editUserRoles(String userId, String roles) {
//        if (StrUtil.isBlank(userId) && StrUtil.isBlank(roles)) {
//            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
//        }
//        sysUserRoleService.saveUserRoles(userId, roles, getUserId());
//        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
//    }


    /**
     * 修改用户基本信息
     *
     * @return
     */
    @PostMapping(value = "update")
    public ResponseVo editUser(@RequestBody SysUser sysUser) {
       return sysUserService.updateUser(sysUser,getUserId());
    }


    /**
     * 重置密码
     *
     * @return
     */
    @PostMapping(value = "resetPw")
    public ResponseVo resetPw(@RequestBody String id) {
        return sysUserService.resetPassword(id);
    }


//    /**
//     * 更新密码
//     *
//     * @param requestPassword
//     * @return
//     */
//    @PostMapping(value = "editPw")
//    public ResponseVo editPw(@RequestBody RequestPassword requestPassword) {
//        return sysUserService.EditPassword(requestPassword);
//    }


    /**
     * 条件查询用户list
     *
     * @param spage
     * @return
     */
    @PostMapping("list")
    // @RequiresPermissions("sys:user:list")
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

      //  EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        Map<String,String> params = new HashedMap();
        // 构造查询参数
        if (spage.getSearch() != null) {
            Field[] fields = spage.getSearch().getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(spage.getSearch());
                    if (null != value && !value.equals("")) {
                        String fieldname = StringTools.underscoreName(fields[i].getName());
                        params.put(fieldname,value.toString());
                    }
                    fields[i].setAccessible(false);
                } catch (Exception e) {
                }
            }
        }

        Page<SysUser> pageList = sysUserService.selectOptionPage(page, params);
        GracePage<SysUser> gracePage = new GracePage<SysUser>(pageList);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, gracePage);
    }


    /**
     * 获取当期用户信息
     *
     * @return
     */
    @PostMapping("info")
    public ResponseVo getCurrent() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("menuList", menuList);
        map.put("permissions", permissions);
        map.put("current",getUser());
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, map);

    }



    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{userIds}")
    // @RequiresPermissions("sys:user:delete")
    public ResponseVo delete(@PathVariable String userIds) {

        if (StrUtil.isBlank(userIds)) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
        }

        String[] ids = userIds.split(",");
        if (ArrayUtil.contains(ids, getUserId()) || ArrayUtil.contains(ids, "1")) {
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(), "当前用户不能删除");
        }

        String[] idArray = sysUserService.deleteBatch(userIds);

        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, idArray);
    }

}