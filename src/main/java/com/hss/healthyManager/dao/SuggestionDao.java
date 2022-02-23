package com.hss.healthyManager.dao;

import com.hss.healthyManager.entity.Suggestion;
import org.apache.ibatis.annotations.Param;

public interface SuggestionDao extends BaseDao<Suggestion> {


    /**
     * @param ids id数组
     * @return 操作成功数量
     */
    Integer deleteByIds(@Param("ids") Integer[] ids);

    /**
     * @param ids    id数组
     * @param isRead 阅读标志
     * @return 操作成功数量
     */
    Integer setInfoReadByIds(@Param("ids") Integer[] ids, @Param("isRead") Integer isRead);
}
