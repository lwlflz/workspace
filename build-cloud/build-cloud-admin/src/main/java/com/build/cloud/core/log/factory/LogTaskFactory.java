package com.build.cloud.core.log.factory;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import com.build.cloud.common.constant.MqQueueConstant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.SpringContextUtils;
import com.build.cloud.modules.sys.entity.SysLogEntity;
import com.build.cloud.modules.sys.service.ISysLogService;
/**
 * @ClassName: LogTaskFactory
 * @Description:日志操作任务创建工厂
 * @author: 刘滔
 * @date: 2017年7月7日 下午2:31:54
 */
public class LogTaskFactory {
	private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static ISysLogService apiLogService = SpringContextUtils.getBean(ISysLogService.class);
	private static AmqpTemplate rabbitTemplate = SpringContextUtils.getBean(AmqpTemplate.class);
	public static TimerTask sysLog(final String operation, final String method, final String params, final String ip,
			final String username, final Long time) {
		return new TimerTask() {
			@Override
			public void run() {
				SysLogEntity sysLogEntity = LogFactory.createApiLog(operation, method, params, ip, username, time);
				try {
					logger.info("LogTaskFactory saveLog params:{}", params);
					// 保存系统日志
					apiLogService.insert(sysLogEntity);
				} catch (Exception e) {
					logger.error("创建业务日志异常!", e);
					throw new BusinessException("创建业务日志异常!", e);
				}
			}
		};
	}
	public static void saveLog(String operation, String method, String params, String ip, String username, Long time) {
		SysLogEntity sysLogEntity = LogFactory.createApiLog(operation, method, params, ip, username, time);
		rabbitTemplate.convertAndSend(MqQueueConstant.LOG_QUEUE, sysLogEntity);
	}
}
