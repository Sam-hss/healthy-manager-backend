package com.hss.healthyManager.shiro;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.dao.ResourcesDao;
import com.hss.healthyManager.entity.Resources;
import com.hss.healthyManager.entity.Role;
import com.hss.healthyManager.entity.User;
import com.hss.healthyManager.service.RoleService;
import com.hss.healthyManager.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Resource
    private ResourcesDao resourcesDao;
    @Autowired
    private RoleService roleService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        Set<String> permissionSet = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Role role = roleService.selectByKey(user.getRoleId());
        List<Resources> roleResource = resourcesDao.findRoleResource(user.getRoleId(), null);
        if (!CollectionUtils.isEmpty(roleResource)) {
            roleResource.forEach(v -> {
                if (!StringUtils.isEmpty(v.getPermission())) {
                    permissionSet.add(v.getPermission());
                }
            });
        }
        info.addRole(role.getRoleName());
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证登录
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new MyException(ExceptionEnums.ACCOUNT_IS_NOT_EXIT);
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
