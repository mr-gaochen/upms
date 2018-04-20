package com.eric.grace.upms.controller.dto;

/**
 * LoginDto:
 *
 * @author: MrServer
 * @since: 2018/4/20 下午4:15
 */
public class LoginDto {

    private String username;

    private String password;


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
}