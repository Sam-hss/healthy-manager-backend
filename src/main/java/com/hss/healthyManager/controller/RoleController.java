package com.hss.healthyManager.controller;

import com.hss.healthyManager.entity.Role;
import com.hss.healthyManager.service.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Api(description = "角色相关接口")
@Controller
@RequestMapping(value = "api/role")
public class RoleController extends BaseController<RoleService, Role, Integer> {

    @Override
    @RequiresPermissions("role:add")
    public ResponseEntity<Role> save(@RequestBody Role entity) {
        return super.save(entity);
    }

    @Override
    @RequiresPermissions("role:update")
    public ResponseEntity<Role> update(@RequestBody Role entity) {
        return super.update(entity);
    }

    @Override
    @RequiresPermissions("role:delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return super.delete(id);
    }
}
