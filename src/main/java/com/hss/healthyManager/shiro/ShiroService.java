package com.hss.healthyManager.shiro;

import com.hss.healthyManager.dao.ResourcesDao;
import com.hss.healthyManager.entity.Resources;
import com.hss.healthyManager.entity.User;
import com.hss.healthyManager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ShiroService {

    @Resource
    private ResourcesDao resourcesDao;
    @Autowired
    private UserService userService;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        /*
            配置访问权限
            - anon:所有url都都可以匿名访问
            - authc: 需要认证才能进行访问（此处指所有非匿名的路径都需要登陆才能访问）
            - user:配置记住我或认证通过可以访问
         */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/api/user/loginOut", "anon");

        //放行注册请求
        filterChainDefinitionMap.put("/api/user/add", "anon");

        filterChainDefinitionMap.put("/error", "anon");

        filterChainDefinitionMap.put("/loginUser", "anon");

        //设置文件上传为匿名访问
        filterChainDefinitionMap.put("/api/uploadFile/upload", "anon");

        filterChainDefinitionMap.put("/login", "anon");
        //未授权跳转url
        filterChainDefinitionMap.put("/unauthorized", "anon");
        // 加载数据库中配置的资源权限列表
        List<Resources> resourcesList = resourcesDao.listUrlAndPermission();
        for (Resources resource : resourcesList) {
            if (!StringUtils.isEmpty(resource.getUrl()) && !StringUtils.isEmpty(resource.getPermission())) {
                String permission = "perms[" + resource.getPermission() + "]";
                filterChainDefinitionMap.put(resource.getUrl(), permission);
            }
        }
        // 不存在什么特别关键的操作，所以直接使用user认证，相关用authc
        filterChainDefinitionMap.put("/**", "user");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {
        ShiroFilterFactoryBean shirFilter = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shirFilter) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shirFilter.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shirFilter.getFilterChainDefinitionMap().clear();
            shirFilter.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shirFilter.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
        log.info("用户权限重新加载成功！！");
    }

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    public void reloadAuthorizingByUserId(User user) {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user.getId(), realmName);
        subject.runAs(principals);
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();

        log.info("用户[{}]的权限更新成功！！", user.getUsername());

    }

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    public void reloadAuthorizingByRoleId(Integer roleId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        List<User> userList = userService.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (User user : userList) {
            reloadAuthorizingByUserId(user);
        }
    }
}
