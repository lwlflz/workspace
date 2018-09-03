package com.build.cloud.common.config;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @ClassName: RedisPrefix
 * @Description: redis前缀配置，有时候多个工程共用一个db需要区分
 * @author: 刘滔
 * @date: 2018年2月3日 上午9:49:29
 */
public class RedisPrefix implements RedisCachePrefix {
	private final RedisSerializer<String> serializer;
	private final String delimiter;
	public RedisPrefix() {
		this(":");
	}
	public RedisPrefix(String delimiter) {
		this.serializer = new StringRedisSerializer();
		this.delimiter = delimiter;
	}
	@Override
	public byte[] prefix(String cacheName) {
		return this.serializer.serialize(
			this.delimiter != null ? this.delimiter.concat(":").concat(cacheName).concat(":") : cacheName.concat(":"));
	}
}