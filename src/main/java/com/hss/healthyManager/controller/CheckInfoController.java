package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.dao.CheckInfoDao;
import com.hss.healthyManager.entity.CheckInfo;
import com.hss.healthyManager.service.CheckInfoService;
import com.hss.healthyManager.utils.dto.AnalysisData;
import com.hss.healthyManager.utils.dto.HealthDTO;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Api(description = "体检表信息接口")
@Controller
@RequestMapping(value = "api/checkInfo")
public class CheckInfoController extends BaseController<CheckInfoService, CheckInfo, Integer> {


    @Resource
    CheckInfoDao checkInfoDao;


    @Override
    @RequiresPermissions("checkInfo:add")
    public ResponseEntity<CheckInfo> save(@RequestBody CheckInfo entity) {
        System.out.println("保存的体检表为：" + entity);
        return super.save(entity);
    }

    @Override
    @RequiresPermissions("checkInfo:update")
    public ResponseEntity<CheckInfo> update(@RequestBody CheckInfo entity) {
        return super.update(entity);
    }

    @Override
    @RequiresPermissions("checkInfo:delete")
    @GetMapping(value = "delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer integer) {
        return super.delete(integer);
    }

    /**
     * 判断体检表是否存在
     *
     * @param userId    用户id
     * @param checkYear 检查年份
     * @return 是否存在
     */
    @GetMapping("/judgeCheckIsExist")
    ResponseEntity judgeCheckIsExist(Integer userId, String checkYear) {
        Example example = new Example(CheckInfo.class);
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("checkYear", checkYear);
        List<CheckInfo> checkInfos = this.service.selectByExample(example);
        if (checkInfos == null || checkInfos.size() == 0) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/getBim")
    ResponseEntity judgeIsHealth(Double height, Double weight) {
        String suggestion;
        if (height == null || weight == null) {
            throw new MyException(ExceptionEnums.NO_WEIGHT_HEIGHT);
        }
        Double result = weight / ((height / 100) * (height / 100));
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.UP);
        result = Double.valueOf(nf.format(result));
        if (result < 19) {
            suggestion = "体重偏低";
        } else if (result < 25) {
            suggestion = "健康体重";
        } else if (result < 30) {
            suggestion = "超重";
        } else if (result < 39) {
            suggestion = "严重超重";
        } else {
            suggestion = "极度超重";
        }
        return ResponseEntity.ok(new HealthDTO().setBim(result).setSuggestion(suggestion));
    }

    @GetMapping("/getDataAnalysis/{userId}")
    ResponseEntity getDataAnalysis(@PathVariable("userId") Integer userId) {
        List<CheckInfo> analysis = checkInfoDao.getDataAnalysis(userId);
        if (analysis == null) {
            throw new MyException(ExceptionEnums.NO_CHECK_INFO);
        } else {
            List<String> label = new ArrayList<>();
            List<Double> height = new ArrayList<>();
            List<Double> weight = new ArrayList<>();
            analysis.forEach(v -> {
                label.add(v.getCheckYear());
                height.add(v.getHeight());
                weight.add(v.getWeight());
            });
            return ResponseEntity.ok(new AnalysisData().setLabel(label).setHeight(height).setWeight(weight));
        }
    }

    @GetMapping("/getCurrentCheckInfo/{userId}")
    ResponseEntity<CheckInfo> getCurrentCheckInfo(@PathVariable("userId") Integer userId) {
        CheckInfo checkInfo = checkInfoDao.getCurrentCheckInfo(userId);
        if (checkInfo == null) {
            throw new MyException(ExceptionEnums.NO_CHECK_INFO);
        } else {
            return ResponseEntity.ok(checkInfo);
        }
    }

}

