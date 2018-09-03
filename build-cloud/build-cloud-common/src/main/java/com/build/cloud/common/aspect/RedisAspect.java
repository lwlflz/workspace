package com.build.cloud.common.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.build.cloud.common.exception.BusinessException;
/**
 * 
 * @ClassName: RedisAspect   
 * @Description: Redis切面处理类
 * @author: liutao
 * @date: 2018年3月16日 上午11:55:39
 */
@Aspect
@Component
public class RedisAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 是否开启redis缓存 true开启 false关闭
	@Value("${spring.redis.open: false}")
	private boolean open;
	@Around("execution(* com.build.cloud.common.utils.RedisUtils.*(..))")
	public Object around(ProceedingJoinPoint point)
		throws Throwable {
		Object result = null;
		if (open) {
			try {
				result = point.proceed();
			} catch (Exception e) {
				logger.error("redis error", e);
				throw new BusinessException("Redis服务异常");
			}
		}
		return result;
	}
}
