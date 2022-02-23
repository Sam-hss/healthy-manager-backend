package com.hss.healthyManager.service.Impl;


import com.alibaba.fastjson.JSONObject;
import com.hss.healthyManager.dao.BaseDao;
import com.hss.healthyManager.service.BaseService;
import com.hss.healthyManager.utils.dto.Clause;
import com.hss.healthyManager.utils.dto.Condition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T, M extends BaseDao<T>> implements BaseService<T> {


    private Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    protected M entityDao;


    @Override
    public List<T> selectAll() {
        return entityDao.selectAll();
    }

    @Override
    public T selectByKey(Serializable key) {
        log.info("执行筛选==>参数为【" + key + "】");
        return entityDao.selectByPrimaryKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T entity) {
        log.info("执行插入==>参数为【" + entity + "】");
        return entityDao.insertSelective(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByKey(Serializable key) {
        log.info("执行删除==>参数为【" + key + "】");
        return entityDao.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletes(List<String> keys) {
        log.info("执行批量删除==>参数为【" + keys + "】");
        String keyName = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                keyName = field.getName();
                break;
            }
        }
        Example example = new Example(clazz);
        example.createCriteria().andIn(keyName, keys);
        return entityDao.deleteByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T entity) {
        log.info("执行删除==>参数为【" + entity + "】");
        if (entity != null) {
            return entityDao.delete(entity);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity) {
        log.info("执行修改==>参数为【" + entity + "】");
        return entityDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return entityDao.selectByExample(example);
    }

    @Override
    public List<T> select(Condition condition) {
        log.info("执行条件查询===>参数为【" + JSONObject.toJSONString(condition) + "】");
        Map<String, EntityColumn> propertyMap = EntityHelper.getEntityTable(clazz).getPropertyMap();
        Example example = new Example(clazz);
        List<Clause> clauses = condition.getClauses();
        if (!CollectionUtils.isEmpty(clauses)) {
            Example.Criteria criteria = example.createCriteria();
            clauses.forEach(clause -> {
                if (!StringUtils.isEmpty(clause.getColumn())) {
                    if ("isNull".equalsIgnoreCase(clause.getOperation())) {
                        criteria.andIsNull(clause.getColumn());
                    }
                    if ("isNotNull".equalsIgnoreCase(clause.getOperation())) {
                        criteria.andIsNotNull(clause.getColumn());
                    } else {
                        if (!StringUtils.isEmpty(clause.getValue())) {
                            if ("between".equalsIgnoreCase(clause.getOperation())) {
                                ArrayList<?> list = (ArrayList<?>) clause.getValue();
                                criteria.andBetween(clause.getColumn(), list.get(0), list.get(1));
                            }
                            if ("like".equalsIgnoreCase(clause.getOperation())) {
                                criteria.andLike(clause.getColumn(), "%" + clause.getValue() + "%");
                            }
                            if ("=".equalsIgnoreCase(clause.getOperation())) {
                                criteria.andCondition(propertyMap.get(clause.getColumn()).getColumn() + clause.getOperation(), clause.getValue());
                            }
                        }
                    }
                }
            });
        }

        Map<String, Object> sortMap = condition.getSortMap();
        if (sortMap.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sortMap.forEach((k, v) -> {
                if (!StringUtils.isEmpty(k) && !StringUtils.isEmpty(v)) {
                    sb.append(propertyMap.get(k).getColumn());
                    sb.append(" ");
                    sb.append(v);
                    sb.append(",");
                }
            });
            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1);
            }
            example.setOrderByClause(sb.toString());
        }
        return selectByExample(example);
    }

    @Override
    public PageInfo<T> selectPage(Condition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<T> list = select(condition);
        return new PageInfo<>(list);
    }
}
