package com.build.cloud.modules.sys.oauth2;
import java.io.IOException;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.HttpRequestUtils;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;
import com.build.cloud.modules.bs.service.IBsWorkerService;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserTokenEntity;
import com.build.cloud.modules.sys.service.IShiroService;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.google.common.base.Objects;

import cn.hutool.core.collection.CollectionUtil;
/**
 * @ClassName: OAuth2Realm
 * @Description: 认证
 * @author: liutao
 * @date: 2018年3月31日 下午3:50:27
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
	@Autowired
	private IShiroService shiroService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@Autowired
	private IBsWorkerService bsWorkerService;
	@Autowired
	private RedisUtils redisUtils;
	private static final long EXPIRE = 3600 * 24 * 30;
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof OAuth2Token; // 表示此Realm只支持OAuth2Token类型
	}
	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		String userId = user.getId();
		// 用户权限列表
		//Set<String> permsSet = redisUtils.get(RedisKeys.getUserPermissions(userId), Set.class);
		//if (CollectionUtil.isEmpty(permsSet)) {
		
		Set<String> permsSet = null;
		if(Objects.equal("2", user.getMgrType())){//管理员，先取所有资源权限7月再改造qindq@20180614
			permsSet = shiroService.getUserPermissions(Constant.SUPER_ADMIN);
		}else{
			permsSet = shiroService.getUserPermissions(userId);
		}
			//redisUtils.set(RedisKeys.getUserPermissions(userId), permsSet);
		//}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}
	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
		throws AuthenticationException {
		String accessToken = (String)token.getPrincipal();
		// 根据accessToken，查询用户信息
		SysUserTokenEntity tokenEntity = redisUtils.get(RedisKeys.getTokenKey(accessToken), SysUserTokenEntity.class);
		long expire = EXPIRE;
		if (tokenEntity == null) {
			tokenEntity = shiroService.queryByToken(accessToken);
			if (tokenEntity != null) {
				expire = tokenEntity.getExpireTime().getTime() - System.currentTimeMillis();
				redisUtils.set(RedisKeys.getTokenKey(accessToken), tokenEntity, expire); // 将token信息放入缓存
			}
		}
		// token失效
		if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
			throw new IncorrectCredentialsException("token失效，请重新登录");
		}
		// 查询用户信息
		SysUserEntity user = redisUtils.get(RedisKeys.getUserKey(tokenEntity.getUserId()), SysUserEntity.class);
		if (user == null) {
			user = shiroService.queryUser(tokenEntity.getUserId());
			if (user != null) {
				redisUtils.set(RedisKeys.getUserKey(user.getId()), user, expire); // 将用户信息放入缓存
			} else {
				throw new IncorrectCredentialsException("token失效，请重新登录");
			}
		}
		// 账号停用处理
		EntityWrapper<SysEmployeeEntity> empWrapper = new EntityWrapper<SysEmployeeEntity>();
		empWrapper.eq("user_id", user.getId());
		SysEmployeeEntity employee = sysEmployeeService.selectOne(empWrapper);
		if(employee != null && "1".equals(employee.getStatus())) {
			throw new BusinessException("账号已经停用,请联系管理员");
		}
		// 账号锁定
		if (user.getStatus().equals("2")) {
			
		} else if (user.getStatus().equals("1")) {
			throw new LockedAccountException("账号已被禁用,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
		return info;
	}
}
