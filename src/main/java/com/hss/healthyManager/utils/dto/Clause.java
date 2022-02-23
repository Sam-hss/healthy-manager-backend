package com.hss.healthyManager.utils.dto;

import lombok.Data;


@Data
public class Clause {

    /**
     * 属性名
     */
    private String column;

    /**
     * 条件匹配符
     * 支持[=,<,>,<=,<=,like,between,isNull,isNotNull],
     * operation为isNull或isNotNull时,value无效
     * operation为between时，value为arrayList<T>型 [value1,value2]
     */
    private String operation;

    /**
     * 属性值
     */
    private Object value;
}
