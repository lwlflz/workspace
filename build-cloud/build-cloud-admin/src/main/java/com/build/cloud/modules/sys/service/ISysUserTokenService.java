package com.build.cloud.modules.sys.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserTokenEntity;
/**
 * @ClassName: SysUserTokenService
 * @Description: 用户Token
 * @author: liutao
 * @date: 2018年3月31日 下午3:57:30
 */
public interface ISysUserTokenService extends IService<SysUserTokenEntity> {
	/**
	 * 生成token
	 * @param SysUserEntity 用户
	 */
	Result createToken(SysUserEntity user);
	/**
	 * 退出，修改token值
	 * @param userId 用户ID
	 */
	void logout(String userId);
}
