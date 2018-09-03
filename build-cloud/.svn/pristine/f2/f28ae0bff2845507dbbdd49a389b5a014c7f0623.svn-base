package com.build.cloud.modules.sys.util;
import com.build.cloud.common.utils.SpringContextUtils;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysUserService;
public class UserUtil {
	private static ISysUserService sysUserService = (ISysUserService)SpringContextUtils.getBean("sysUserService");
	public static String getUserName(String userId) {
		SysUserEntity entity = sysUserService.selectById(userId);
		return entity.getUserName();
	}
	
	public static SysUserEntity getUser(String userId) {
		SysUserEntity entity = sysUserService.selectById(userId);
		return entity;
	}
}
