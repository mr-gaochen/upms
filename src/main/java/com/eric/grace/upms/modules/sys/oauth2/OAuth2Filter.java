package com.eric.grace.upms.modules.sys.oauth2;

import com.alibaba.fastjson.JSON;
import com.eric.grace.service.exception.enums.GraceExceptionEnum;
import com.eric.grace.service.result.ResultUtil;
import com.eric.grace.utils.common.StrUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OAuth2Filter: oauth2过滤器
 *
 * @author: MrServer
 * @since: 2018/4/25 上午11:56
 */
public class OAuth2Filter extends AuthenticatingFilter {


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if (StrUtil.isBlank(token)) {
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StrUtil.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String json = JSON.toJSONString(ResultUtil.error(GraceExceptionEnum.NO_AUTHORITY));
            httpResponse.getWriter().print(json);
            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();

            String json = JSON.toJSONString(ResultUtil.error(GraceExceptionEnum.NO_AUTHORITY));

            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("Authorization");
        //如果header中不存在token，则从参数中获取token
        if (StrUtil.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }


}