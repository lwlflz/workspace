package com.build.cloud.core.log.factory;
import java.util.Date;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.build.cloud.modules.sys.entity.SysLogEntity;
/**
 * @ClassName: LogFactory
 * @Description:日志对象创建工厂
 * @author: 刘滔
 * @date: 2017年7月7日 下午2:24:19
 */
public class LogFactory {
	/**
	 * 创建API操作日志
	 */
	public static SysLogEntity createApiLog(String operation, String method, String params, String ip, String username,
			Long time) {
		SysLogEntity apiLogEntity = new SysLogEntity();
		apiLogEntity.setId(IdWorker.getIdStr());
		apiLogEntity.setOperation(operation);
		apiLogEntity.setMethod(method);
		apiLogEntity.setParams(params);
		apiLogEntity.setIp(ip);
		apiLogEntity.setTime(time);
		if (username != null) {
			apiLogEntity.setUsername(username);
		}
		apiLogEntity.setCreateDate(new Date());
		return apiLogEntity;
	}
}
