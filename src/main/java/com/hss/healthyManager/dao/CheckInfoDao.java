package com.hss.healthyManager.dao;

import com.hss.healthyManager.entity.CheckInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckInfoDao extends BaseDao<CheckInfo> {

    List<CheckInfo> getDataAnalysis(@Param("userId") Integer userId);

    CheckInfo getCurrentCheckInfo(@Param("userId") Integer userId);
}
