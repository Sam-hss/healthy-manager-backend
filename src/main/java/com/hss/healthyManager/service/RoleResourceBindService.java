package com.hss.healthyManager.service;

import com.hss.healthyManager.entity.RoleResourceBind;

import java.util.List;

public interface RoleResourceBindService extends BaseService<RoleResourceBind> {

    List<Integer> getResourceIds(Integer roleId);

    List<Integer> getRoleIds(Integer resourceId);

    /**
     * @param resourceId 资源id
     * @param roleList   角色ids
     * @return 0失败  >0 成功
     */
    Integer addBindInfo(Integer resourceId, List<Integer> roleList);


    /**
     * @param resourceId 资源id
     * @return 0 失败  1成功
     */
    Integer deleteBindInfo(Integer resourceId);
}
