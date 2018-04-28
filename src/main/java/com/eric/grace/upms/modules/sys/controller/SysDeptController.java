package com.eric.grace.upms.modules.sys.controller;

import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.modules.sys.entity.SysDept;
import com.eric.grace.upms.modules.sys.service.ISysDeptService;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SysDeptController: 部门管理
 *
 * @author: MrServer
 * @since: 2018/4/25 上午11:48
 */
@RestController
@RequestMapping("/sys/dept/")
public class SysDeptController extends AbstractController {

    @Autowired
    private ISysDeptService deptService;


    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("list")
    // @RequiresPermissions("sys:dept:list")
    public ResponseVo list() {
        List<SysDept> deptList = deptService.queryList();
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS, deptList);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("sys:dept:update")
    public ResponseVo update(@RequestBody SysDept dept) {

        if (dept.getId().equals(dept.getParentId())){
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(),"当前机构不能和父目录在同一级");
        }

        deptService.updateById(dept);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("sys:dept:save")
    public ResponseVo save(@RequestBody SysDept dept) {
        return deptService.save(dept, getUserId());

    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    // @RequiresPermissions("sys:dept:delete")
    public ResponseVo delete(@PathVariable String id) {
        //判断是否有子部门
        List<String> deptList = deptService.queryDetpIdList(id);
        if (deptList.size() > 0) {
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(), "请先删除子机构");
        }
        deptService.deleteById(id);
        return ResultUtil.success(GraceExceptionEnum.BUSIONESS_SUCCESS);
    }


}