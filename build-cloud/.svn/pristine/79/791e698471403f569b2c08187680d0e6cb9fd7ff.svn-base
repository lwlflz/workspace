package com.build.cloud.common.utils.cache;
/**
 * @ClassName: RedisKeys
 * @Description: Redis所有Keys
 * @author: liutao
 * @date: 2018年3月16日 下午2:27:50
 */
public class RedisKeys {
	public static String getSysConfigKey(String key) {
		return "sys:config:" + key;
	}
	public static String getShiroSessionKey(String key) {
		return "shiro_redis_sessionid:" + key;
	}
	/**
	 * 数据字典缓存
	 */
	public static String getDictKey(Object key) {
		return "sys:dict:" + key;
	}
	
	/**
	 * 
	 * @Title: getUserKey   
	 * @Description: 登录用户信息KEY
	 * @param @param key
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	public static String getUserKey(Object key) {
		return "login:user:" + key;
	}
	public static String getTokenKey(Object key) {
		return "login:token:" + key;
	}
	public static String getMenuKey(String key) {
		return "sys:menu:" + key;
	}
	public static String getWorkerKey(String key) {
		return "sys:worker:" + key;
	}
	public static String getUserPermissions(String key) {
		return "current:permiss:" + key;
	}
	public static String getDefCom(String key) {
		return "def:com:" + key;
	}
}
