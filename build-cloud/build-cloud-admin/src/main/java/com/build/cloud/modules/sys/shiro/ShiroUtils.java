package com.build.cloud.modules.sys.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.modules.sys.entity.SysUserEntity;

/**
 * 
 * @ClassName: ShiroUtils
 * @Description: Shiro工具类
 * @author: liutao
 * @date: 2018年3月16日 下午12:20:41
 */
public class ShiroUtils {
	/** 加密算法 */
	public final static String hashAlgorithmName = "SHA-256";
	/** 循环次数 */
	public final static int hashIterations = 16;

	public static String sha256(String password, String salt) {
		return new SimpleHash(hashAlgorithmName, password.toLowerCase(), salt, hashIterations).toString();
	}

	public static Session getSession() {
		return getSubject().getSession();
	}
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static SysUserEntity getUserEntity() {
		return (SysUserEntity) getPrincipal();
	}

	public static Object getPrincipal() {
		return getSubject().getPrincipal();
	}

	public static String getUserId() {
		String userId = "0";
		if (getUserEntity() != null) {
			userId = getUserEntity().getId();
		}
		return userId;
	}
	public static String getLoginName() {
		String userId = "a";
		if (getUserEntity() != null) {
			userId = getUserEntity().getLoginName();
		}
		return userId;
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return getPrincipal() != null;
	}

	public static void logout() {
		getSubject().logout();
	}

	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if (kaptcha == null) {
			throw new BusinessException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}
}
