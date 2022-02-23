package com.hss.healthyManager.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@Slf4j
public class ShiroFiter extends FormAuthenticationFilter {

    /**
     * OPTIONS请求即预检请求，可用于检测服务器允许的http方法。
     * 当发起跨域请求时，由于安全原因，触发一定条件时浏览器会在正式请求之前自动先发起OPTIONS请求，即CORS预检请求，
     * 服务器若接受该跨域请求，浏览器才继续发起正式请求。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean allowed = super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            // 判断请求是否是options请求，若是直接过滤
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }
        return allowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }
}
