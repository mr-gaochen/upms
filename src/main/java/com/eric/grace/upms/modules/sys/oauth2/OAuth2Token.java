package com.eric.grace.upms.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * OAuth2Token:
 *
 * @author: MrServer
 * @since: 2018/4/25 下午12:00
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}