package com.build.cloud.modules.punch.service.impl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.punch.dao.DevEmployeeDao;
import com.build.cloud.modules.punch.entity.DevEmployeeEntity;
import com.build.cloud.modules.punch.service.IDevEmployeeService;

@Service
public class DevEmployeeServiceImpl extends ServiceImpl<DevEmployeeDao, DevEmployeeEntity> implements IDevEmployeeService {
}
