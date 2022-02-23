package com.hss.healthyManager.dao;

import com.hss.healthyManager.entity.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesDao extends BaseDao<Resources> {

    /**
     * 根据角色id返回对应资源
     *
     * @param roleId 角色id
     * @return 对应资源
     */
    List<Resources> findRoleResource(@Param("roleId") Integer roleId, @Param("typeId") Integer typeId);


    /**
     * 获取所有url 和 权限
     *
     * @return 对应所有资源
     */
    List<Resources> listUrlAndPermission();
}
