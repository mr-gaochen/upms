package com.eric.grace.upms.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * SysMenuController: 系统菜单接口层
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:48
 */
@RestController
@RequestMapping("sys/menu/")
@Api(value = "SysMenu操作类", description = "Menu相关操作接口定义类")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;



    /**
     * 所有菜单列表
     */
    @PostMapping("/list")
   // @RequiresPermissions("sys:menu:list")
    public ResponseVo list(){
        List<SysMenu> menuList = sysMenuService.selectList(new EntityWrapper<>());
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, menuList);
    }


    /***
     * 保存菜单
     * @author Mr.Eric
     * @date 2018/4/24 下午2:28
     * @param menu
     * @return R
     */
    @PostMapping("/save")
   // @RequiresPermissions("sys:menu:save")
    public ResponseVo save(@RequestBody SysMenu menu) {
        return sysMenuService.save(menu);
    }




    /***
     * 修改菜单
     * @author Mr.Eric
     * @date 2018/4/24 下午2:45
     * @param menu
     * @return R
     */
    @PostMapping("/update")
   // @RequiresPermissions("sys:menu:update")
    public ResponseVo update(@RequestBody SysMenu menu){
        return sysMenuService.updateEntity(menu);
    }


    /**
     *  删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/delete")
 //   @RequiresPermissions("sys:menu:delete")
    public ResponseVo delete(String menuId){
        return sysMenuService.deleteByMenuId(menuId);
    }



}