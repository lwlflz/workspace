package com.build.cloud.common.listener;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.build.cloud.common.constant.Constant;

public class ShiroSessionListener implements SessionListener {
	private static final Logger LOG = LoggerFactory.getLogger(ShiroSessionListener.class);
	@Autowired
	private RedisTemplate redisTemplate;
	private final AtomicInteger sessionCount = new AtomicInteger(0);
	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
		LOG.info("创建了一个Session连接:[" + session.getId() + "]");
		LOG.info("在线用户:[{}]", sessionCount.intValue());
		redisTemplate.opsForSet().add(Constant.ALLUSER_NUMBER, session.getId());
	}
	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
		if (getAllUserNumber() > 0) {
			LOG.info("销毁了一个Session连接:[" + session.getId() + "]");
		}
		redisTemplate.opsForSet().remove(Constant.ALLUSER_NUMBER, session.getId());
	}
	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();
		onStop(session);
	}
	/** 获取在线用户数量 */
	public Integer getAllUserNumber() {
		return redisTemplate.opsForSet().size(Constant.ALLUSER_NUMBER).intValue();
	}
}