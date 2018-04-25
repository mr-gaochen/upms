package com.eric.grace.upms.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eric.grace.dao.common.service.impl.CommonServiceImpl;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.constant.SysConstant;
import com.eric.grace.upms.modules.sys.entity.SysMenu;
import com.eric.grace.upms.modules.sys.mapper.SysMenuMapper;
import com.eric.grace.upms.modules.sys.service.ISysMenuService;
import com.eric.grace.utils.common.RandomUtil;
import com.eric.grace.utils.common.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * SysMenuServiceImpl: 系统菜单实现类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:46
 */
@Service
public class SysMenuServiceImpl extends CommonServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

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

    @Override
    public ResponseVo updateEntity(SysMenu menu) {
        ResponseVo responseVo = verifyForm(menu);


        return null;
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


        //上级菜单类型
        String parentType = SysConstant.MenuType.CATALOG.getValue();
        if (!menu.getParentId().equals("0")) {
            EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
            wrapper.eq("parent_id", menu.getParentId());
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
            if (parentType != SysConstant.MenuType.MENU.getValue()) {
                return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR.getCode(), "上级菜单只能为菜单类型");
            }
            return null;
        }

        return null;
    }


}