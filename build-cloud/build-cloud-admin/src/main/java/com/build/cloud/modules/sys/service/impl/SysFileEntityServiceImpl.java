package com.build.cloud.modules.sys.service.impl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.sys.dao.SysFileEntityDao;
import com.build.cloud.modules.sys.entity.SysFileEntityEntity;
import com.build.cloud.modules.sys.service.ISysFileEntityService;
@Service("sysFileEntityService")
public class SysFileEntityServiceImpl extends ServiceImpl<SysFileEntityDao, SysFileEntityEntity>
		implements ISysFileEntityService {
	
}
