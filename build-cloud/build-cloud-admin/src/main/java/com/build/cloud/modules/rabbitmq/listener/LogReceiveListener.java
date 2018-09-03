package com.build.cloud.modules.rabbitmq.listener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.build.cloud.common.constant.MqQueueConstant;
import com.build.cloud.modules.sys.entity.SysLogEntity;
import com.build.cloud.modules.sys.service.ISysLogService;
/**
 * @ClassName: LogReceiveListener
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: liutao
 * @date: 2018年4月10日 下午2:24:52
 */
@Component
@RabbitListener(queues = MqQueueConstant.LOG_QUEUE)
public class LogReceiveListener {
	@Autowired
	private ISysLogService sysLogService;
	@RabbitHandler
	public void receive(SysLogEntity log) {
		System.out.println("============================receive===========================");
//		sysLogService.insert(log);
	}
}