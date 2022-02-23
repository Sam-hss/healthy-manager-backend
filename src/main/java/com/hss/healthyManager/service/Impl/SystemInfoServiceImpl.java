package com.hss.healthyManager.service.Impl;

import com.hss.healthyManager.dao.SystemInfoDao;
import com.hss.healthyManager.entity.SystemInfo;
import com.hss.healthyManager.service.SystemInfoService;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoServiceImpl extends BaseServiceImpl<SystemInfo, SystemInfoDao> implements SystemInfoService {
}
