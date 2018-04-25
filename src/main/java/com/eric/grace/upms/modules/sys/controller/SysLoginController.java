package com.eric.grace.upms.modules.sys.controller;

import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.upms.common.utils.ShiroUtils;
import com.eric.grace.upms.modules.sys.controller.dto.LoginDto;
import com.eric.grace.upms.modules.sys.service.ISysUserService;
import com.eric.grace.utils.io.IoUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ApiLoginController: 登录控制层
 *
 * @author: MrServer
 * @since: 2018/4/20 上午11:06
 */

@RestController
@RequestMapping("/sys")
public class SysLoginController {


    @Autowired
    private Producer producer;

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("login")
    public ResponseVo login(@RequestBody LoginDto loginDto) {

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!loginDto.getCaptcha().equalsIgnoreCase(kaptcha)){
            return ResultUtil.error(GraceExceptionEnum.BUSINESS_FAILE.getCode(),"验证码不正确");
        }

        return sysUserService.login(loginDto.getUsername(), loginDto.getPassword());
    }


    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }


}