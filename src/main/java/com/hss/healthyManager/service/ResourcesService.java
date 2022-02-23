package com.hss.healthyManager.service;

import com.hss.healthyManager.entity.Resources;
import com.hss.healthyManager.utils.vo.ResourceVO;

import java.util.List;

public interface ResourcesService extends BaseService<Resources> {

    List<ResourceVO> getResourceTreeByRoleId(Integer roleId, Integer typeId);

    List<ResourceVO> getResourceTreeByUserId(Integer userId, Integer typeId);
}
