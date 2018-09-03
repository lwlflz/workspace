package com.build.cloud.modules.common.service.impl;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.build.cloud.common.utils.SpringContextUtils;
import com.build.cloud.modules.common.service.IConstantService;
import com.build.cloud.modules.sys.dao.SysUserDao;
import com.build.cloud.modules.sys.entity.SysUserEntity;
@Component
@DependsOn("springContextUtils")
public class ConstantServiceImpl implements IConstantService {
	private SysUserDao sysUserDao = SpringContextUtils.getBean(SysUserDao.class);
	public static IConstantService me() {
		return SpringContextUtils.getBean(ConstantServiceImpl.class);
	}
	@Override
	public String getUserNameById(String userId) {
		SysUserEntity user = sysUserDao.selectById(userId);
		if (user != null) {
			return user.getUserName();
		} else {
			return "--";
		}
	}
	@Override
	public String getLoginNameById(String userId) {
		SysUserEntity user = sysUserDao.selectById(userId);
		if (user != null) {
			return user.getLoginName();
		} else {
			return "--";
		}
	}
}
