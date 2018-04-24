package com.eric.grace.upms.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.eric.grace.dao.common.model.BaseModel;

import java.util.Date;

/**
 * TokenEntity:
 *
 * @author: MrServer
 * @since: 2018/4/20 上午11:15
 */
@TableName("t_sys_token")
public class TokenEntity extends BaseModel<TokenEntity>{

    //用户ID
    private String userId;
    //token
    private String token;

    //过期时间
    private Date expireTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}