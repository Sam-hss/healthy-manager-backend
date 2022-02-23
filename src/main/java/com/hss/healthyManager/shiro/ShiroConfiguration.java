package com.hss.healthyManager.shiro;


import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;


@Configuration
public class ShiroConfiguration {

    @Autowired
    private ShiroService shiroService;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //登录界面url
        bean.setLoginUrl("/api/user/login");
        //登陆后跳转成功url
        bean.setSuccessUrl("/success");
        //未授权跳转url
        bean.setUnauthorizedUrl("/unauthorized");
        // 配置数据库中的resource
        Map<String, String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;

    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm, @Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        manager.setRememberMeManager(cookieRememberMeManager);
        return manager;
    }

    @Bean("cookieRememberMeManager")
    public CookieRememberMeManager cookieRememberMeManager(@Qualifier("simpleCookie") SimpleCookie simpleCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("cGxhdGZvcm0AAAAAAAAAAA=="));
        manager.setCookie(simpleCookie);
        return manager;
    }

    @Bean("simpleCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);
        return simpleCookie;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(credentialMatcher);
        return authRealm;
    }

    /**
     * 凭证验证器
     *
     * @return
     */
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
