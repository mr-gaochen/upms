package com.eric.grace.upms.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.entity.SysRoleMenu;
import com.eric.grace.upms.modules.sys.mapper.SysMenuMapper;
import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import com.eric.grace.upms.modules.sys.service.ISysUserService;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SysMenuServiceImpl: 系统菜单实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:46
 */
@Service
public class SysMenuServiceImpl extends CommonServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


//    @Autowired
//    private ISysUserService sysUserService;

    @Autowired
    private SysMenuMapper sysMenuMapper;


    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public ResponseVo save(SysMenu menu) {
        ResponseVo responseVo = verifyForm(menu);
        if (null == responseVo) {
            menu.setId(RandomUtil.simpleUUID());
            menu.setCreateTime(new Date());
            menu.setUpdateTime(new Date());
            menu.setDelFlag(SysConstant.DEFAULT_DEL_FLAG_NO);
            super.insert(menu);
            responseVo = ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
        }
        return responseVo;
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @Override
    public ResponseVo updateEntity(SysMenu menu) {
        ResponseVo responseVo = verifyForm(menu);
        if (null == responseVo) {
            menu.setUpdateTime(new Date());
            super.updateById(menu);
            responseVo = ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
        }
        return responseVo;
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public ResponseVo deleteByMenuId(String menuId) {
        //判断是否有子菜单或按钮
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("parent_id", menuId);
        List<SysMenu> menuList = super.selectList(wrapper);
        if (menuList.size() > 0) {
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(), "请先删除子菜单或者目录");
        }
        super.deleteById(menuId);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


    /**
     * 获取用户的菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> getUserMenuList(String userId) {

        List<SysMenu> menuList = null;

        //系统管理员，拥有最高权限
        if (userId == SysConstant.SUPER_ADMIN) {
            return sysMenuMapper.selectAllList();
        }
        //用户菜单列表
        menuList = sysMenuMapper.queryAllMenuId(userId);
        return  menuList;
       // return getAllMenuList(menuIdList);
    }


    /**
     * 获取所有菜单列表
     */
//    private List<SysMenu> getAllMenuList(List<String> menuIdList) {
//        //查询根菜单列表
//        List<SysMenu> menuList = queryListParentId("0", menuIdList);
//        //递归获取子菜单
//        getMenuTreeList(menuList, menuIdList);
//
//        return menuList;
//    }


    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }


    /**
     * 递归
     */
//    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList) {
//        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
//        for (SysMenu entity : menuList) {
//            if (entity.getMenuType() == SysConstant.MenuType.CATALOG.getValue()) {//目录
//                entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
//            }
//            subMenuList.add(entity);
//        }
//        return subMenuList;
//    }


    public List<SysMenu> queryListParentId(String parentId) {
        return sysMenuMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }


    // 验证菜单
    public ResponseVo verifyForm(SysMenu menu) {

        if (StrUtil.isBlank(menu.getMenuName())) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "上级菜单不能为空");
        }

        //菜单
        if (menu.getMenuType().equals(SysConstant.MenuType.MENU.getValue())) {
            if (StrUtil.isBlank(menu.getMenuHref())) {
                return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "菜单URL不能为空");
            }
        }


        //获取上级菜单类型
        String parentType = SysConstant.MenuType.CATALOG.getValue();
        if (!menu.getParentId().equals("0")) {
            EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
            wrapper.eq("id", menu.getParentId());
            SysMenu parentMenu = super.selectOne(wrapper);
            parentType = parentMenu.getMenuType();
        }


        //目录、菜单
        if (menu.getMenuType().equals(SysConstant.MenuType.CATALOG.getValue()) ||
                menu.getMenuType().equals(SysConstant.MenuType.MENU.getValue())) {
            if (!parentType.equals(SysConstant.MenuType.CATALOG.getValue())) {
                return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "上级菜单只能为目录类型");
            }
            return null;
        }

        //按钮
        if (menu.getMenuType().equals(SysConstant.MenuType.BUTTON.getValue())) {
            if (!parentType.equals(SysConstant.MenuType.MENU.getValue())) {
                return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "上级菜单只能为菜单类型");
            }
            return null;
        }

        return null;
    }



}