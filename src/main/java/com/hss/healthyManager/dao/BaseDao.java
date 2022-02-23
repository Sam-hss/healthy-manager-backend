package com.hss.healthyManager.dao;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;


@RegisterMapper
public interface BaseDao<T> extends Mapper<T> {
}
