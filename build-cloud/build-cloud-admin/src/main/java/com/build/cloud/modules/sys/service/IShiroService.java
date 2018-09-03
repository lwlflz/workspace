package com.build.cloud.modules.sys.service;
import java.util.Set;

import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserTokenEntity;
/**
 * @ClassName: IShiroService
 * @Description: shiro相关接口
 * @author: liutao
 * @date: 2018年3月31日 下午3:45:46
 */
public interface IShiroService {
	/**
	 * 获取用户权限列表
	 */
	Set<String> getUserPermissions(String userId);
	SysUserTokenEntity queryByToken(String token);
	/**
	 * 根据用户ID，查询用户
	 * @param userId
	 */
	SysUserEntity queryUser(String userId);
}
