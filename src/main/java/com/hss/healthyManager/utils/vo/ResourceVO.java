package com.hss.healthyManager.utils.vo;

import com.hss.healthyManager.entity.Resources;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends Resources {
    List<ResourceVO> children;
}
