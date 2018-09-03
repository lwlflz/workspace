package com.build.cloud.core.log;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.sunsine.common.util.ThreadUtils;
/**
 * @ClassName: LogManager
 * @Description:日志管理器
 * @author: 刘滔
 * @date: 2017年7月7日 下午2:22:37
 */
public class LogManager {
	// 日志记录操作延时
	private final int OPERATE_DELAY_TIME = 10;
	private static final int POOL_SIZE = 10;
	private LogManager() {
	}
	private static class instance{
		public static LogManager logManager = new LogManager();
	}
	
	public static LogManager me() {
		return instance.logManager;
	}
	public void executeLog(TimerTask task) {
		ThreadUtils.getScheduledExecutorService(POOL_SIZE).schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}
}
