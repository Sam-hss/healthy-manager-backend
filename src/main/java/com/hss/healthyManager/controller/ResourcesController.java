package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.entity.Resources;
import com.hss.healthyManager.entity.User;
import com.hss.healthyManager.service.ResourcesService;
import com.hss.healthyManager.service.RoleResourceBindService;
import com.hss.healthyManager.shiro.ShiroService;
import com.hss.healthyManager.utils.vo.ResourceVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(description = "资源相关接口")
@Controller
@RequestMapping(value = "api/resources")
public class ResourcesController extends BaseController<ResourcesService, Resources, Integer> {

    @Autowired
    ResourcesService resourceService;
    @Autowired
    ShiroService shiroService;
    @Autowired
    RoleResourceBindService roleResourceBindService;
    private static Integer PASS_CODE = 200;


    @GetMapping("/getMenuByRoleId")
    ResponseEntity<List<ResourceVO>> getMenuByRoleId(@RequestParam("roleId") Integer roleId, @RequestParam("typeId") Integer typeId) {
        List<ResourceVO> resourceVOS;
        try {
            resourceVOS = resourceService.getResourceTreeByRoleId(roleId, typeId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.RESOURCE_FOUNT_FAIL);
        }
        return ResponseEntity.ok(resourceVOS);
    }

    @GetMapping("/getMenuByUserId")
    ResponseEntity<List<ResourceVO>> getMenuByUserId(@RequestParam("userId") Integer userId, @RequestParam("typeId") Integer typeId) {
        List<ResourceVO> resourceVOS;
        try {
            resourceVOS = resourceService.getResourceTreeByUserId(userId, typeId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.RESOURCE_FOUNT_FAIL);
        }
        if (resourceVOS == null) {
            throw new MyException(ExceptionEnums.RESOURCE_FOUNT_FAIL);
        }
        return ResponseEntity.ok(resourceVOS);
    }

    @Override
    @RequiresPermissions("resource:add")
    public ResponseEntity<Resources> save(@RequestBody Resources entity) {
        ResponseEntity<Resources> data = super.save(entity);
        if (data.getStatusCodeValue() == PASS_CODE) {
            //更新权限
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            List<Integer> list = new ArrayList<>();
            list.add(user.getRoleId());
            roleResourceBindService.addBindInfo(entity.getId(), list);
            shiroService.updatePermission();
        }
        return data;
    }

    @Override
    @RequiresPermissions("resource:update")
    public ResponseEntity<Resources> update(@RequestBody Resources entity) {
        ResponseEntity<Resources> data = super.update(entity);
        if (data.getStatusCodeValue() == PASS_CODE) {
            //更新权限
            shiroService.updatePermission();
        }
        return data;
    }

    @Override
    @GetMapping(value = "delete/{id}")
    @RequiresPermissions("resource:delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        ResponseEntity<String> data = super.delete(id);
        if (data.getStatusCodeValue() == PASS_CODE) {
            //更新权限
            shiroService.updatePermission();
        }
        return data;

    }
}
