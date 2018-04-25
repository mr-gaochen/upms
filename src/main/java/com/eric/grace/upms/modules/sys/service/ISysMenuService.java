package com.eric.grace.upms.modules.sys.service;

import com.eric.grace.dao.common.service.ICommonService;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.modules.sys.entity.SysMenu;

/**
 * ISysMenuService: 系统菜单业务接口
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:46
 */
public interface ISysMenuService extends ICommonService<SysMenu> {

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    ResponseVo save(SysMenu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    ResponseVo updateEntity(SysMenu menu);
}