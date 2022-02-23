package com.hss.healthyManager.service.Impl;

import com.hss.healthyManager.dao.ResourcesDao;
import com.hss.healthyManager.entity.Resources;
import com.hss.healthyManager.entity.User;
import com.hss.healthyManager.service.ResourcesService;
import com.hss.healthyManager.service.UserService;
import com.hss.healthyManager.utils.vo.ResourceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources, ResourcesDao> implements ResourcesService {

    @Autowired
    UserService userService;

    @Override
    public List<ResourceVO> getResourceTreeByRoleId(Integer roleId, Integer typeId) {
        List<Resources> allNode = this.entityDao.findRoleResource(roleId, typeId);
        List<ResourceVO> all = new ArrayList<>();
        allNode.forEach(node -> {
            ResourceVO resourceVO = new ResourceVO();
            BeanUtils.copyProperties(node, resourceVO, "children");
            List<ResourceVO> list = new ArrayList<>();
            resourceVO.setChildren(list);
            all.add(resourceVO);
        });
        List<ResourceVO> first = all.stream().filter(v -> v.getParentId() == 0).collect(Collectors.toList());
        first.forEach(v -> getTree(v, all));
        return first;
    }

    @Override
    public List<ResourceVO> getResourceTreeByUserId(Integer userId, Integer typeId) {
        User user = userService.selectByKey(userId);
        if (user.getRoleId() != null) {
            return getResourceTreeByRoleId(user.getRoleId(), typeId);
        }
        return null;
    }

    private ResourceVO getTree(ResourceVO resourceVO, List<ResourceVO> all) {
        all.forEach(resource -> {
            if (resource.getParentId().equals(resourceVO.getId())) {
                resourceVO.getChildren().add(getTree(resource, all));
            }
        });
        return resourceVO;
    }
}
