package com.build.cloud.common.utils;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
/**
 * @ClassName: RedisUtils
 * @Description:Redis工具类
 * @author: 刘滔
 * @date: 2017年8月9日 下午1:12:24
 */
@Component
public class RedisUtils {
	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, Object> hashOperations;
	@Resource(name = "redisTemplate")
	private ListOperations<String, Object> listOperations;
	@Resource(name = "redisTemplate")
	private SetOperations<String, Object> setOperations;
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, Object> zSetOperations;
	/** 默认过期时长，单位：秒 */
	public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
	/** 不设置过期时长 */
	public final static long NOT_EXPIRE = -1;
	public void set(String key, Object value, long expire) {
		valueOperations.set(key, toJson(value));
		if (expire != NOT_EXPIRE) {
			expire(key, expire);
		}
	}
	public void set(String key, Object value) {
		set(key, value, DEFAULT_EXPIRE);
	}
	public <T> T get(String key, Class<T> clazz, long expire) {
		String value = valueOperations.get(key);
		if (expire != NOT_EXPIRE) {
			expire(key, expire);
		}
		return value == null ? null : fromJson(value, clazz);
	}
	public <T> T get(String key, Class<T> clazz) {
		return get(key, clazz, NOT_EXPIRE);
	}
	public String get(String key, long expire) {
		String value = valueOperations.get(key);
		if (expire != NOT_EXPIRE) {
			expire(key, expire);
		}
		return value;
	}
	public String get(String key) {
		return get(key, NOT_EXPIRE);
	}
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	public void delete(Set<String> keys) {
		redisTemplate.delete(keys);
	}
	/** 
     * 模糊查询keys 
     * @param pattern 
     * @return 
     */  
    public Set<String> keys(String pattern){  
        return redisTemplate.keys(pattern);   
    }  
	/**
	 * 指定缓存的失效时间
	 * @author FangJun
	 * @date 2016年8月14日
	 * @param key 缓存KEY
	 * @param time 失效时间(秒)
	 */
	public void expire(String key, long expire) {
		if (expire > 0) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}
	/**
	 * Object转成JSON数据
	 */
	private String toJson(Object object) {
		if (object instanceof Integer
			|| object instanceof Long
			|| object instanceof Float
			|| object instanceof Double
			|| object instanceof Boolean
			|| object instanceof String) {
			return String.valueOf(object);
		}
		return JSON.toJSONString(object);
	}
	/**
	 * JSON数据，转成Object
	 */
	private <T> T fromJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
}
