package com.eric.grace.upms.controller;

import com.eric.grace.service.result.ResponseVo;
import com.eric.grace.upms.controller.dto.LoginDto;
import com.eric.grace.upms.service.ISysUserService;
import com.eric.grace.utils.io.IoUtil;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api")
public class ApiLoginController {



    @Autowired
    private Producer producer;

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("login")
    public ResponseVo login(@RequestBody LoginDto loginDto){
        return sysUserService.login(loginDto.getUsername(), loginDto.getPassword());
    }



    @RequestMapping("captcha")
    public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //保存到shiro or spring secrity session

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }



}