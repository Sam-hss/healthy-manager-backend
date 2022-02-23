package com.hss.healthyManager.config;

import com.hss.healthyManager.filter.CorsFilter;
import com.hss.healthyManager.filter.ShiroFiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean crosFilterRegistration() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new CorsFilter());
        //过滤器名称
        registrationBean.setName("CorsFilter");
        //拦截规则
        registrationBean.addUrlPatterns("/*");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        log.info("开启cros-Filter过滤器。");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new ShiroFiter());
        //过滤器名称
        registrationBean.setName("ShiroFilter");
        //拦截规则
        registrationBean.addUrlPatterns("/*");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        log.info("开启shiro-Filter过滤器。");

        return registrationBean;
    }
}
