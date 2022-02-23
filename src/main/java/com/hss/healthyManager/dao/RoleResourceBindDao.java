package com.hss.healthyManager.dao;

import com.hss.healthyManager.entity.RoleResourceBind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceBindDao extends BaseDao<RoleResourceBind> {


    Integer addBindInfo(@Param("resourceId") Integer resourceId, @Param("roleList") List<Integer> roleList);


    /**
     * 删除绑定该资源的所有信息
     *
     * @param resourceId 资源id
     * @return 0 失败  1成功
     */
    Integer deleteBindInfo(@Param("resourceId") Integer resourceId);

}
