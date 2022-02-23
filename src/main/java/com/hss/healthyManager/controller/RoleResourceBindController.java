package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.entity.RoleResourceBind;
import com.hss.healthyManager.service.RoleResourceBindService;
import com.hss.healthyManager.utils.dto.BindInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Api(description = "角色资源绑定相关接口")
@Controller
@RequestMapping(value = "api/roleResourceBind")
public class RoleResourceBindController extends BaseController<RoleResourceBindService, RoleResourceBind, Integer> {

    @GetMapping("/getRoles/{resourceId}")
    @ResponseBody
    @ApiOperation(value = "获取某个资源下面所有绑定的角色")
    List<Integer> getRoleListByResourceId(@PathVariable("resourceId") Integer resourceId) {
        return this.service.getRoleIds(resourceId);
    }

    @PostMapping("/saveBind")
    @ApiOperation(value = "删除原资源下面的所有角色,然后加上现在的所有角色")
    ResponseEntity<String> saveBind(@RequestBody BindInfo bindInfo) {
        try {
            if (Objects.equals(bindInfo.getRoleList(), this.getRoleListByResourceId(bindInfo.getResourceId()))) {
                return ResponseEntity.ok("资源信息已保存，权限信息未发生更改！");
            } else {
                this.service.deleteBindInfo(bindInfo.getResourceId());
                this.service.addBindInfo(bindInfo.getResourceId(), bindInfo.getRoleList());
            }
        } catch (Exception e) {
            throw new MyException(ExceptionEnums.UPDATE_ERROR);
        }
        return ResponseEntity.ok("更新权限信息成功");
    }
}
