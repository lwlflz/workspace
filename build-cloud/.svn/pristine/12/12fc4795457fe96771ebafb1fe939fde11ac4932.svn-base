package com.build.cloud.modules.rabbitmq.sender;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.build.cloud.common.constant.MqQueueConstant;
/**
 * @ClassName: MQSender
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: liutao
 * @date: 2018年5月11日 上午12:38:39
 */
@Service
public class MQSender {
	private static final Logger logger = LoggerFactory.getLogger(MQSender.class);
	@Autowired
	private AmqpTemplate amqpTemplate;
	public void sendPunchMessage(Map<String, Object> params) {
		String msg = JSON.toJSONString(params);
		logger.info("send message:" + msg);
		amqpTemplate.convertAndSend(MqQueueConstant.ATTEND_QUEUE, msg);
	}
	
	public void sendMsg(String routingKey, String message) {
		amqpTemplate.convertAndSend(routingKey, message);
	}
	
	public void syncUser(String user) {
//		sendMsg(MqQueueConstant.USER_QUEUE, user);
		amqpTemplate.convertAndSend(MqQueueConstant.USER_QUEUE, user);
	}
	public void syncDev(String dev) {
		sendMsg(MqQueueConstant.DEV_QUEUE, dev);
	}
	public void syncStatcard(String m) {
		sendMsg(MqQueueConstant.STATCARD_QUEUE, m);
	}
	public void syncPunch() {
		amqpTemplate.convertAndSend(MqQueueConstant.PUNCH_QUEUE);
	}
}
