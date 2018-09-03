package com.build.cloud.modules.bs.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.bs.dao.BsWorkerCertificateDao;
import com.build.cloud.modules.bs.entity.BsWorkerCertificateEntity;
import com.build.cloud.modules.bs.service.IBsWorkerCertificateService;
@Service("bsWorkerCertificateService")
public class BsWorkerCertificateServiceImpl extends ServiceImpl<BsWorkerCertificateDao, BsWorkerCertificateEntity> implements IBsWorkerCertificateService{

}
