package com.build.cloud.modules.rabbitmq.receiver;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.build.cloud.common.constant.MqQueueConstant;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
import com.build.cloud.modules.punch.entity.StatCardEntity;
import com.build.cloud.modules.punch.service.IDevDeviceService;
import com.build.cloud.modules.punch.service.IProAttendUserService;
import com.build.cloud.modules.punch.service.IPunchService;
import com.build.cloud.modules.punch.service.IStatCardService;
import com.google.common.collect.Maps;
@Service
public class MQReceiver {
	private static final Logger logger = LoggerFactory.getLogger(MQReceiver.class);
	@Autowired
	private IProAttendUserService proAttendUserService;
	@Autowired
	private IDevDeviceService devDeviceService;
	@Autowired
	private IStatCardService statCardService;
	@Autowired
	private IPunchService punchService;
	
	@Autowired
	private org.apache.shiro.mgt.SecurityManager securityManager;
	private void init() {
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	@RabbitListener(queues = MqQueueConstant.ATTEND_QUEUE)
	public void receive(String message) {
		logger.info("receive message:" + message);
	}
	@RabbitListener(queues = MqQueueConstant.USER_QUEUE)
	public void syncUserReceive(String user) {
		logger.info("syncUser user:" + user);
		init();
		List<ProAttendUserEntity> list = JSON.parseArray(user, ProAttendUserEntity.class);
		proAttendUserService.insertOrUpdateBatch(list);
	}
	@RabbitListener(queues = MqQueueConstant.DEV_QUEUE)
	public void syncDevReceive(String dev) {
		logger.info("syncDevReceive dev:" + dev);
		init();
		Map<String, Object> map = JSON.parseObject(dev, Map.class);
		devDeviceService.syncDev(map);
	}
	@RabbitListener(queues = MqQueueConstant.STATCARD_QUEUE)
	public void syncStatCardReceive(String statCard) {
		logger.info("syncStatCardReceive statCard:" + statCard);
		init();
		List<StatCardEntity> list = JSON.parseArray(statCard, StatCardEntity.class);
		statCardService.insertOrUpdateBatch(list);
	}
	@RabbitListener(queues = MqQueueConstant.PUNCH_QUEUE)
	public void syncPunch() {
		logger.info("syncPunch");
		init();
		punchService.queryPunch(Maps.newHashMap());
	}
}
