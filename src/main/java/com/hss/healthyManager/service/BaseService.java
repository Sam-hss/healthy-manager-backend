package com.hss.healthyManager.service;

import com.hss.healthyManager.utils.dto.Condition;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T> {

    /**
     * 查询所有数据
     *
     * @return 结果List
     */
    List<T> selectAll();

    /**
     * 主键查询
     *
     * @param key 主键
     * @return T
     */
    T selectByKey(Serializable key);

    /**
     * 插入数据
     *
     * @param entity 传入实体
     * @return 受影响行数
     */
    int insert(T entity);

    /**
     * 主键删除
     *
     * @param key 主键
     * @return 受影响行数
     */
    int deleteByKey(Serializable key);

    /**
     * 批量删除
     *
     * @return 受影响行数
     */
    int deletes(List<String> keys);

    /**
     * 删除数据
     *
     * @param entity 实体
     * @return 受影响行数
     */
    int delete(T entity);

    /**
     * 更新数据
     *
     * @param entity 实体
     * @return 受影响行数
     */
    int update(T entity);

    /**
     * mapper Example原生查询方式
     *
     * @param example example
     * @return 结果List
     */
    List<T> selectByExample(Example example);

    /**
     * 条件排序查询
     * 1.查询条件clauses和排序条件sortMap都可以为空
     * 2.如果查询条件和排序map都为空，则返回所有数据
     * 3.pageSize和pageNum该方法无效
     * 4.clauses定义查询条件
     * 5.sortMap定义排序条件，key为属性字段，value="DESC" 为倒序，value="ASC"为正序。
     *
     * @param condition 条件
     * @return 结果List
     */
    List<T> select(Condition condition);

    /**
     * 条件分页排序查询
     * 1.查询条件clauses和排序条件sortMap都可以为空
     * 2.如果查询条件和排序map都为空，则返回所有数据
     * 3.clauses定义匹查询条件
     * 4.sortMap定义排序条件，key为属性字段，value="DESC" 为倒序，value="ASC"为正序。
     *
     * @param condition 条件
     * @return 结果List
     */
    PageInfo<T> selectPage(Condition condition);

}
