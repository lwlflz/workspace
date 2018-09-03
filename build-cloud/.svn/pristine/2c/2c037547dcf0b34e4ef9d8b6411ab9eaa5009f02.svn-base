package com.build.cloud.modules.sys.shiro;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.HttpRequestUtils;
import com.build.cloud.modules.sys.dao.SysMenuDao;
import com.build.cloud.modules.sys.dao.SysUserDao;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sunsine.common.util.IPUtil;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: UserRealm
 * @Description: Realm认证
 * @author: liutao
 * @date: 2018年3月16日 下午12:21:10
 */
@Component
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	// 用户登录次数计数 redisKey 前缀
	private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
	// 用户登录是否被锁定 一小时 redisKey 前缀
	private String SHIRO_IS_LOCK = "shiro_is_lock_";
	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		String userId = user.getId();
		List<String> permsList;
		// 系统管理员，拥有最高权限
		if (StrUtil.equals(userId, Constant.SUPER_ADMIN)) {
			List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
			permsList = Lists.newArrayListWithCapacity(menuList.size());
			for (SysMenuEntity menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserDao.queryAllPerms(userId);
		}
		// 用户权限列表
		Set<String> permsSet = Sets.newHashSet();
		for (String perms : permsList) {
			if (StrUtil.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}
	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
		throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String name = token.getUsername();
		String pwd = String.valueOf(token.getPassword());
		// 访问一次，计数一次
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.increment(SHIRO_LOGIN_COUNT + name, 1);
		// 计数大于5时，设置用户被锁定一小时
		if (Convert.toInt(opsForValue.get(SHIRO_LOGIN_COUNT + name)) >= 5) {
			opsForValue.set(SHIRO_IS_LOCK + name, "LOCK");
			redisTemplate.expire(SHIRO_IS_LOCK + name, 1, TimeUnit.HOURS);
		}
		if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + name))) {
			throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
		}
		// 查询用户信息
		SysUserEntity user = new SysUserEntity();
		user.setLoginName(name);
		user = sysUserDao.selectOne(user);
		
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		String password = user.getPassword();
		if(!StrUtil.equals(ShiroUtils.sha256(pwd, user.getSalt()), password)) {
			throw new UnknownAccountException("账号或密码不正确");
		} else if ("1".equals(user.getStatus())) {
			/**
			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
			throw new DisabledAccountException("此帐号已经设置为禁止登录！");
		} else if ("2".equals(user.getStatus())) {
			/**
			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
			throw new DisabledAccountException("此帐号被冻结！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(user.getSalt()), getName());
		final SysUserEntity userEntity = new SysUserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setLastLoginDate(new Date());
		userEntity.setLastLoginIp(IPUtil.getRemoteAddr(HttpRequestUtils.getRequest()));
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				sysUserDao.updateById(userEntity);
			}
		});
		// 清空登录计数
		opsForValue.set(SHIRO_LOGIN_COUNT + name, "0");
		return info;
	}
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}
}
