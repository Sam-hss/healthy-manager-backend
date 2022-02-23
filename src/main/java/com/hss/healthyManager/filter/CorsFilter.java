package com.hss.healthyManager.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //是否支持cookie跨域
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        //指定允许其他域名访问
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));

        //响应头设置
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,client_id, uuid, Authorization,user-agent,X-XSRF-TOKEN");

        // 设置过期时间
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

        //响应类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        // 支持HTTP1.1.
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        // 支持HTTP 1.0. 
        httpServletResponse.setHeader("Pragma", "no-cache");

        httpServletResponse.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(204);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
