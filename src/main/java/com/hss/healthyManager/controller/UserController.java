package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.entity.User;
import com.hss.healthyManager.service.UserService;
import com.hss.healthyManager.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Api(description = "用户相关接口")
@Controller
@RequestMapping(value = "api/user")
public class UserController extends BaseController<UserService, User, Integer> {

    @Override
    public ResponseEntity<User> save(@RequestBody User entity) {
        //默认给新用户设置角色id为2
        if (entity.getRoleId() == null) {
            entity.setRoleId(2);
        }
        if (entity.getUsername() == null) {
            entity.setUsername(entity.getStuNo());
        }
        if (entity.getPassword() == null) {
            entity.setPassword("123");
        }
        if (StringUtils.isBlank(entity.getUsername())) {
            throw new MyException(ExceptionEnums.ADD_ERROR);
        }
        if (this.service.findByUsername(entity.getUsername()) != null) {
            throw new MyException(ExceptionEnums.ACCOUNT_IS_EXIT);
        }
        entity.setPassword(MD5Utils.encrypt(entity.getUsername(), entity.getPassword()));
        return super.save(entity);
    }

    @Override
    public ResponseEntity<User> update(@RequestBody User entity) {
        if (this.service.selectByKey(entity.getId()).getPassword().equals(entity.getPassword())) {
            return super.update(entity);
        } else {
            entity.setPassword(MD5Utils.encrypt(entity.getUsername(), entity.getPassword()));
            return super.update(entity);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUserName(@RequestBody User entity) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (this.service.findByUsername(entity.getUsername()) != null && !entity.getUsername().equals(user.getUsername())) {
            throw new MyException(ExceptionEnums.ACCOUNT_IS_EXIT);
        }
        if (this.service.selectByKey(entity.getId()).getPassword().equals(entity.getPassword())) {
            return super.update(entity);
        } else {
            entity.setPassword(MD5Utils.encrypt(entity.getUsername(), entity.getPassword()));
            return super.update(entity);
        }
    }

    @Override
    @GetMapping(value = "delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return super.delete(id);
    }

    @ApiOperation(value = "用户登录接口")
    @RequestMapping("login")
    public ResponseEntity<User> login(@RequestBody User entity, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(entity.getUsername(), entity.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.info("登陆失败:" + e.getMessage());
            throw e;
        }
    }

    @ApiOperation(value = "用户注销接口")
    @GetMapping("/loginOut")
    public ResponseEntity<String> loginOut() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return ResponseEntity.ok("退出登录");

    }

    @ApiOperation(value = "获取所有相应角色用户")
    @GetMapping("/getAllStudent/{roleId}")
    public ResponseEntity<List<User>> getAllStudent(@PathVariable("roleId") String roleId) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        List<User> users = this.service.selectByExample(example);
        return ResponseEntity.ok(users);
    }
}
