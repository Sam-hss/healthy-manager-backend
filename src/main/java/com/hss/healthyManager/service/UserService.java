package com.hss.healthyManager.service;

import com.hss.healthyManager.entity.User;

public interface UserService extends BaseService<User> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);
}
