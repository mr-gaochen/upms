package com.eric.grace.upms.modules.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.eric.grace.dao.common.model.page.FrontPage;
import com.eric.grace.dao.common.model.page.GracePage;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.service.util.StringTools;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.common.utils.ValidatorUtils;
import com.eric.grace.upms.modules.sys.entity.SysRole;
import com.eric.grace.upms.modules.sys.service.*;
import com.eric.grace.utils.common.StrUtil;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRoleController: 系统角色控制成
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:31
 */
@RestController
@RequestMapping("sys/role/")
@Api(value = "SysRole操作类", description = "Role相关操作接口定义类")
public class SysRoleController extends AbstractController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private ISysRoleDeptService sysRoleDeptService;


    /***
     * 创建角色
     * @author Mr.Eric
     * @date 2018/4/24 下午1:15
     * @param role
     * @return com.eric.grace.service.result.ResponseVo
     */
    @PostMapping("/save")
    //  @RequiresPermissions("sys:role:save")
    public ResponseVo save(@RequestBody SysRole role) {
        ValidatorUtils.validateEntity(role);
        return sysRoleService.save(role, getUserId());
    }


    /**
     * 修改角色
     */
    @PostMapping("/update")
    // @RequiresPermissions("sys:role:update")
    public ResponseVo update(@RequestBody SysRole role) {
        ValidatorUtils.validateEntity(role);
        sysRoleService.update(role);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, role);
    }


    /**
     * 删除角色
     */
    @DeleteMapping("/delete/{ids}")
    //@RequiresPermissions("sys:role:delete")
    public ResponseVo delete(@PathVariable String ids) {

        if (StrUtil.isBlank(ids)) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
        }

        if (ids.equals("0")) {
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(),"超级管理员不能删除");
        }

        String[] idArray = sysRoleService.deleteBatch(ids);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, idArray);
    }


    /***
     * 角色信息
     * @author Mr.Eric
     * @date 2018/4/24 下午2:25
     * @param roleId
     * @return com.eric.grace.service.result.ResponseVo
     */
    @GetMapping("/info/{roleId}")
   // @RequiresPermissions("sys:role:info")
    public ResponseVo info(@PathVariable("roleId") String roleId) {
        SysRole role = sysRoleService.selectById(roleId);
        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的部门
        List<String> deptIdList = sysRoleDeptService.queryDeptIdList(roleId);
        role.setDeptIdList(deptIdList);

        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS,role);
    }


    /**
     * 验证角色编码是否占用
     * @param roleCode
     * @return
     */
    @GetMapping("/info/codeExists")
    public ResponseVo<Boolean> findRoleByCode(String roleCode) {
        SysRole sysRole = sysRoleService.selectByRoleCode(roleCode);
        if (null == sysRole) {
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, true);
        } else {
            return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, false);
        }

    }


    /***
     * 获取角色列表 分页 + 条件查询
     * @author Mr.Eric
     * @date 2018/4/24 下午1:13
     * @param spage
     * @return com.eric.grace.service.result.ResponseVo
     */
    @PostMapping("/list")
//    @RequiresPermissions("sys:role:list")
    public ResponseVo list(@RequestBody FrontPage<SysRole> spage) {
        Page<SysRole> page = new Page<SysRole>(spage.getCurentPage(), spage.getPageRowNum());
        if (null != spage.getSort()) {
            if (null == spage.getSort().getPredicate() || StrUtil.isBlank(spage.getSort().getPredicate())) {
                spage.getSort().setPredicate("update_time");
            }
            //设置排序字段
            page.setOrderByField(spage.getSort().getPredicate());
            page.setAsc(spage.getSort().getReverse());
        }

       // EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
        Map<String,String> params = new HashedMap();
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
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != SysConstant.SUPER_ADMIN) {
            params.put("create_user_id",getUserId());

        }
        Page<SysRole> pageList = sysRoleService.selectOptionPage(page, params);

        GracePage<SysRole> gracePage = new GracePage<SysRole>(pageList);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, gracePage);
    }



    /**
     * 获取角色列表 不分页
     */
    @PostMapping("/select")
   // @RequiresPermissions("sys:role:select")
    public ResponseVo select(){
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if(getUserId() != SysConstant.SUPER_ADMIN){
            map.put("create_user_id", getUserId());
        }
        List<SysRole> list = sysRoleService.selectByMap(map);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS,list);
    }


}