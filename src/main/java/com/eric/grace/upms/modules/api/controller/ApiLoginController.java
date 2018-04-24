//package com.eric.grace.upms.modules.api.controller;
//
//import com.eric.grace.service.exception.enums.ExceptionEnums;
//import com.eric.grace.service.exception.enums.GraceExceptionEnum;
//import com.eric.grace.service.result.ResponseVo;
//import com.eric.grace.service.result.ResultUtil;
//import com.eric.grace.upms.modules.api.annotation.AuthIgnore;
//import com.eric.grace.upms.modules.api.service.TokenService;
//import com.eric.grace.upms.modules.api.service.UserService;
//import com.eric.grace.utils.common.StrUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * ApiLoginController: API登录授权
// *
// * @author: MrServer
// * @since: 2018/4/24 下午12:35
// */
//@RestController
//@RequestMapping("/api")
//public class ApiLoginController {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private TokenService tokenService;
//
//
//    /**
//     * 登录
//     */
//    @AuthIgnore
//    @PostMapping("login")
//    public ResponseVo login(String username, String password) {
//
//        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
//            return ResultUtil.error(GraceExceptionEnum.PARAMS_ERROR);
//        }
//
//        //用户登录
//        String userId = userService.login(username, password);
//
//        //生成token
//
//        return null;
//    }
//
//
//}