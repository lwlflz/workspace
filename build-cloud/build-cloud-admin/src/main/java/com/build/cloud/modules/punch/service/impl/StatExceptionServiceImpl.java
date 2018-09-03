package com.build.cloud.modules.punch.service.impl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.punch.dao.StatExceptionDao;
import com.build.cloud.modules.punch.entity.StatExceptionEntity;
import com.build.cloud.modules.punch.service.IStatExceptionService;

@Service
public class StatExceptionServiceImpl extends ServiceImpl<StatExceptionDao, StatExceptionEntity> implements IStatExceptionService {
}
