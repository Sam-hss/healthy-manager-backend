package com.hss.healthyManager.controller;

import com.hss.healthyManager.entity.HealthDocument;
import com.hss.healthyManager.service.HealthDocumentService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Api(description = "健康文档相关接口")
@Controller
@RequestMapping(value = "api/healthDocument")
public class HealthDocumentController extends BaseController<HealthDocumentService, HealthDocument, Integer> {

    @Override
    @RequiresPermissions("healthDocument:add")
    public ResponseEntity<HealthDocument> save(@RequestBody HealthDocument entity) {
        if (entity.getIsPublished() == 1) {
            entity.setPublishData(new Date());
        }
        return super.save(entity);
    }

    @Override
    @RequiresPermissions("healthDocument:update")
    public ResponseEntity<HealthDocument> update(@RequestBody HealthDocument entity) {
        if (entity.getIsPublished() == 1) {
            entity.setPublishData(new Date());
        }
        return super.update(entity);
    }

    @Override
    @GetMapping(value = "delete/{id}")
    @RequiresPermissions("healthDocument:delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return super.delete(id);
    }

}
