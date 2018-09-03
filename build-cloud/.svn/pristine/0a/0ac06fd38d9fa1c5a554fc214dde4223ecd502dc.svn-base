package com.build.cloud.modules.sys.redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.modules.sys.entity.SysConfigEntity;
/**
 * @ClassName: SysConfigRedis
 * @Description: 系统配置Redis
 * @author: liutao
 * @date: 2018年3月16日 下午3:02:32
 */
@Component
public class SysConfigRedis {
	@Autowired
	private RedisUtils redisUtils;
	public void saveOrUpdate(SysConfigEntity config) {
		if (config == null) {
			return;
		}
		String key = RedisKeys.getSysConfigKey(config.getKey());
		redisUtils.set(key, config);
	}
	public void delete(String configKey) {
		String key = RedisKeys.getSysConfigKey(configKey);
		redisUtils.delete(key);
	}
	public SysConfigEntity get(String configKey) {
		String key = RedisKeys.getSysConfigKey(configKey);
		return redisUtils.get(key, SysConfigEntity.class);
	}
}
