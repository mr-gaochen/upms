package com.eric.grace.upms.modules.sys.controller.dto;

/**
 * LoginDto:
 *
 * @author: MrServer
 * @since: 2018/4/20 下午4:15
 */
public class LoginDto {

    private String username;

    private String password;

    private String captcha;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}