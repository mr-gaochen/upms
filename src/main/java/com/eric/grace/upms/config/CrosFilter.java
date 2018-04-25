package com.eric.grace.upms.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CrosFilter: springboot 跨域
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:39
 */
@Component
@Order(-1)
public class CrosFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String method = ((HttpServletRequest) req).getMethod();
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Access-Control-Allow-Origin,X-Requested-With, Authorization,Content-Type, Accept, Token");
        if ("OPTIONS".equals(method)) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }


}