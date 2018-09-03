package com.build.cloud.common.config;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.build.cloud.common.utils.cache.serializer.KryoRedisSerializer;
import com.build.cloud.common.utils.cache.serializer.StringRedisSerializer;
import com.google.common.collect.Maps;
/**
 * Redis配置
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisConfig extends CachingConfigurerSupport {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);
	public static final String CACHE_KEY_PREFIX = "DHJS:";
	@Autowired
	private RedisConnectionFactory connectionFactory;
	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			/** 重写生成key方法 */
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder(CACHE_KEY_PREFIX);
				CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
				Cacheable cacheable = method.getAnnotation(Cacheable.class);
				CachePut cachePut = method.getAnnotation(CachePut.class);
				CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
				if (cacheable != null) {
					String[] cacheNames = cacheable.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				} else if (cachePut != null) {
					String[] cacheNames = cachePut.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				} else if (cacheEvict != null) {
					String[] cacheNames = cacheEvict.value();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				}
				if (cacheConfig != null && sb.toString().equals(CACHE_KEY_PREFIX)) {
					String[] cacheNames = cacheConfig.cacheNames();
					if (ArrayUtils.isNotEmpty(cacheNames)) {
						sb.append(cacheNames[0]);
					}
				}
				if (sb.toString().equals(CACHE_KEY_PREFIX)) {
					sb.append(o.getClass().getName()).append(".").append(method.getName());
				}
				sb.append(":");
				if (objects != null) {
					for (Object object : objects) {
						sb.append(JSON.toJSONString(object));
					}
				}
				return sb.toString();
			}
		};
	}
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setUsePrefix(true);
		RedisCachePrefix cachePrefix = new RedisPrefix("prefix");
		cacheManager.setCachePrefix(cachePrefix);
		// 整体缓存过期时间 设置缓存默认过期时间（全局的）
		cacheManager.setDefaultExpiration(3600 * 24); // 60分钟
		// 设置缓存过期时间。key和缓存过期时间，单位秒
		 Map<String, Long> expiresMap = Maps.newHashMap();
		 expiresMap.put("user", 1000L);
		 expiresMap.put("menu", 1000L);
		 cacheManager.setExpires(expiresMap);
		return cacheManager;
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		// 建议使用这种方式，小范围指定白名单
		ParserConfig.getGlobalInstance().addAccept("com.build.cloud.");
		KryoRedisSerializer<Object> kryoRedisSerializer = new KryoRedisSerializer<>(Object.class);
		redisTemplate.setKeySerializer(kryoRedisSerializer);
		redisTemplate.setHashKeySerializer(kryoRedisSerializer);
		redisTemplate.setHashValueSerializer(kryoRedisSerializer);
		redisTemplate.setValueSerializer(kryoRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}
	@Bean
	public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue();
	}
	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}
	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}
	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}
	/**
	 * redis数据操作异常处理 这里的处理：在日志中打印出错误信息，但是放行 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
	 * @return
	 */
	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				LOGGER.error("redis异常：key=[{}]", key, e);
			}
			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				LOGGER.error("redis异常：key=[{}]", key, e);
			}
			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
				LOGGER.error("redis异常：key=[{}]", key, e);
			}
			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
				LOGGER.error("redis异常：", e);
			}
		};
		return cacheErrorHandler;
	}
}
