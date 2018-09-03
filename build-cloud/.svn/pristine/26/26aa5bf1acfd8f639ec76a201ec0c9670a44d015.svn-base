package com.build.cloud.modules.punch.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.punch.dao.ProAttendUserDao;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
import com.build.cloud.modules.punch.service.AttendService;
import com.build.cloud.modules.punch.service.IProAttendUserService;

import cn.hutool.core.collection.CollectionUtil;
@Service("proAttendUserService")
public class ProAttendUserServiceImpl extends ServiceImpl<ProAttendUserDao, ProAttendUserEntity>
		implements IProAttendUserService {
	
	@Transactional
	@Override
	public boolean save() {
		List<ProAttendUserEntity> attendUsers = AttendService.getService().getAttendUser();
		if (CollectionUtil.isEmpty(attendUsers)) {
			return false;
		}
		return insertOrUpdateBatch(attendUsers);
	}
	
	
}
