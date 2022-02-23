package com.hss.healthyManager.service.Impl;

import com.hss.healthyManager.dao.HealthDocumentDao;
import com.hss.healthyManager.entity.HealthDocument;
import com.hss.healthyManager.service.HealthDocumentService;
import org.springframework.stereotype.Service;

@Service
public class HealthDocumentServiceImpl extends BaseServiceImpl<HealthDocument, HealthDocumentDao> implements HealthDocumentService {
}
