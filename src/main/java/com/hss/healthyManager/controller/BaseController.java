package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import com.hss.healthyManager.service.BaseService;
import com.hss.healthyManager.utils.dto.Condition;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;


@Slf4j
public abstract class BaseController<S extends BaseService<T>, T, ID extends Serializable> {

    @Autowired
    protected S service;

    @ApiOperation(value = "基础接口: 新增操作")
    @PostMapping(value = "add")
    public ResponseEntity<T> save(@RequestBody T entity) {
        try {
            if (service.insert(entity) < 1) {
                throw new MyException(ExceptionEnums.ADD_ERROR);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.ADD_ERROR);
        }
        return ResponseEntity.ok(entity);
    }

    @ApiOperation(value = "基础接口: 返回指定ID的数据")
    @GetMapping(value = "get/{id}")
    public ResponseEntity<T> get(@PathVariable("id") ID id) {
        T t = null;
        try {
            t = service.selectByKey(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.GET_ITEM);
        }
        return ResponseEntity.ok(t);
    }

    @ApiOperation(value = "基础接口: 返回所有数据")
    @GetMapping(value = "all")
    public ResponseEntity<List<T>> all() {
        List<T> list;
        try {
            list = service.selectAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.GET_LIST_ERROR);
        }
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "基础接口: 分页返回数据")
    @PostMapping(value = "page")
    public ResponseEntity<PageInfo<T>> page(@RequestBody Condition condition) {
        PageInfo<T> page;
        try {
            page = service.selectPage(condition);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.GET_LIST_ERROR);
        }
        return ResponseEntity.ok(page);
    }

    @ApiOperation(value = "基础接口: 修改数据")
    @PostMapping(value = "update")
    public ResponseEntity<T> update(@RequestBody T entity) {
        try {
            if (service.update(entity) < 1) {
                throw new MyException(ExceptionEnums.UPDATE_ERROR);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.UPDATE_ERROR);
        }
        return ResponseEntity.ok(entity);
    }

    @ApiOperation(value = "基础接口: 删除指定ID的数据")
    @GetMapping(value = "delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") ID id) {
        try {
            if (service.deleteByKey(id) < 1) {
                throw new MyException(ExceptionEnums.DELETE_ERROR);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyException(ExceptionEnums.DELETE_ERROR);
        }
        return ResponseEntity.ok("删除成功");
    }
}
