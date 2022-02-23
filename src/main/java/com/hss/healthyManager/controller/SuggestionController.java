package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.entity.Suggestion;
import com.hss.healthyManager.service.SuggestionService;
import com.hss.healthyManager.utils.dto.InfoDTO;
import com.hss.healthyManager.utils.dto.InfoReadDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Api(description = "医师建议相关接口")
@Controller
@RequestMapping(value = "api/suggestion")
public class SuggestionController extends BaseController<SuggestionService, Suggestion, Integer> {

    @Override
    @RequiresPermissions("suggestion:add")
    public ResponseEntity<Suggestion> save(@RequestBody Suggestion entity) {
        return super.save(entity);
    }

    @PostMapping("/getInfoPage")
    public ResponseEntity<PageInfo<Suggestion>> getPage(@RequestBody InfoDTO infoDTO) {
        return ResponseEntity.ok(this.service.getPage(infoDTO));
    }
    //标记为未读
    @GetMapping("/getUnReadInfoCount")
    public ResponseEntity getUnReadInfoCount(Integer userId) {
        List<Suggestion> info = this.service.getUnReadMessageByUserId(userId);
        return ResponseEntity.ok(info.size());
    }
    //标记为已读
    @PostMapping("/setInfoRead")
    public ResponseEntity setInfoRead(@RequestBody InfoReadDTO infoReadDTO) {
        if (this.service.markToRead(infoReadDTO) > 0) {
            return ResponseEntity.ok("操作成功");
        } else {
            throw new MyException(ExceptionEnums.UPDATE_ERROR);
        }
    }
    //删除建议
    @PostMapping("/deleteByIds")
    public ResponseEntity deleteByIds(@RequestBody Integer[] ids) {
        if (this.service.deleteInfoByIds(ids) > 0) {
            return ResponseEntity.ok("操作成功");
        } else {
            throw new MyException(ExceptionEnums.DELETE_ERROR);
        }
    }
}
