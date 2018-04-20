package com.eric.grace.upms.controller.dto;

/**
 * RequestPassword: 更新密码
 *
 * @author: MrServer
 * @since: 2018/4/19 下午2:06
 */
public class RequestPassword {



    private String id ;

    private String oldPassword;

    private String password;

    private String repassword;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}