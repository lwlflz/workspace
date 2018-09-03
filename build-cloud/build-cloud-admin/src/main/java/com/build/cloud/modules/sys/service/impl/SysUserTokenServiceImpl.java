package com.build.cloud.modules.sys.service.impl;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.HttpRequestUtils;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.dao.SysUserTokenDao;
import com.build.cloud.modules.sys.entity.SysCompanyEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserTokenEntity;
import com.build.cloud.modules.sys.oauth2.TokenGenerator;
import com.build.cloud.modules.sys.service.ISysCompanyService;
import com.build.cloud.modules.sys.service.ISysUserTokenService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.sunsine.common.util.encrypt.MD5Util;
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity>
		implements ISysUserTokenService {
	private static final Logger LOG = LoggerFactory.getLogger(SysUserTokenServiceImpl.class);
	
	// 30天后过期
	private final static long EXPIRE = 3600 * 24 * 30;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ISysCompanyService sysCompanyService;
	
	@Override
	public Result createToken(SysUserEntity user) {
		// 当前时间
		Date now = new Date();
		// 过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
		String userId = user.getId();
		
		LOG.info("当前登录用户IP：{}", HttpRequestUtils.getIpAddr(HttpRequestUtils.getRequest()));
		// 判断是否生成过token
		EntityWrapper<SysUserTokenEntity> ew = new EntityWrapper<SysUserTokenEntity>();
		ew.eq("user_id", userId);
		ew.eq("token_type", "0");
		SysUserTokenEntity tokenEntity = this.selectOne(ew);
		if (tokenEntity != null) {
			String oldToken = tokenEntity.getToken();
			redisUtils.delete(RedisKeys.getTokenKey(oldToken)); // 删除旧Token
		}
		if (tokenEntity == null) {
			tokenEntity = new SysUserTokenEntity();
		}
		
//		if (tokenEntity == null) {
//			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			// 生成一个token
			String token = TokenGenerator.generateValue();
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			tokenEntity.setTokenType("0");
			// 保存token
			this.insertOrUpdate(tokenEntity);
//		} else {
//			tokenEntity.setUpdateTime(now);
//			tokenEntity.setExpireTime(expireTime);
//			tokenEntity.setTokenType("0");
//			// 更新token
//			this.update(tokenEntity, ew);
//		}
		String newToken = tokenEntity.getToken();
		Map<String, Object> tokenMap = Maps.newLinkedHashMap();
		tokenMap.put("token", newToken);
		tokenMap.put("expire", EXPIRE);
		
		redisUtils.delete(RedisKeys.getUserKey(userId));
		redisUtils.set(RedisKeys.getTokenKey(newToken), tokenEntity, EXPIRE); // 将token信息放入缓存
		redisUtils.set(RedisKeys.getUserKey(userId), user, EXPIRE); // 将用户信息放入缓存
		
		if(Objects.equal("2", user.getMgrType())){
			EntityWrapper<SysCompanyEntity> comEw = new EntityWrapper<>();
			comEw.eq("corp_code", user.getCorpCode());
			List<SysCompanyEntity> companyList = sysCompanyService.selectList(comEw);
			tokenMap.put("company", companyList);
		}else{
			List<Map<String, Object>> companyList = sysCompanyService.selectByUserId(userId);
			tokenMap.put("company", companyList);
		}
		
		
		return Result.ok().put(tokenMap);
	}
	@Override
	public void logout(String userId) {
		// 生成一个token
		String token = TokenGenerator.generateValue();
		// 修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		tokenEntity.setTokenType("0");
		
		redisUtils.delete(RedisKeys.getUserKey(userId));
		redisUtils.delete(RedisKeys.getTokenKey(token));
		redisUtils.delete(ShiroUtils.getUserEntity().getId());
		this.updateById(tokenEntity);
		ShiroUtils.logout();
	}
	
	public static void main(String[] args) {
		try {
			String a = MD5Util.encode("032145");
			String salt = "DykxSiG4iSi7E58ZmvpH";
			String shaPwd = ShiroUtils.sha256(a, salt);
			System.out.println(shaPwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
