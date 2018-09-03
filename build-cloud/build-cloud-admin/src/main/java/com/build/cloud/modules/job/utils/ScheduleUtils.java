package com.build.cloud.modules.job.utils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.modules.job.bean.ScheduleJobBean;
/**
 * @ClassName: ScheduleUtils
 * @Description: 定时任务工具类
 * @author: liutao
 * @date: 2018年3月16日 下午12:14:02
 */
public class ScheduleUtils {
	private final static String JOB_NAME = "TASK_";
	/**
	 * 获取触发器key
	 */
	public static TriggerKey getTriggerKey(String jobId) {
		return TriggerKey.triggerKey(JOB_NAME + jobId);
	}
	/**
	 * 获取jobKey
	 */
	public static JobKey getJobKey(String jobId) {
		return JobKey.jobKey(JOB_NAME + jobId);
	}
	/**
	 * 获取表达式触发器
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
		try {
			return (CronTrigger)scheduler.getTrigger(getTriggerKey(jobId));
		} catch (SchedulerException e) {
			throw new BusinessException("获取定时任务CronTrigger出现异常", e);
		}
	}
	/**
	 * 创建定时任务
	 */
	public static void createScheduleJob(Scheduler scheduler, ScheduleJobBean scheduleJobBean) {
		try {
			// 构建job信息
			JobDetail jobDetail =
				JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJobBean.getId())).build();
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder =
				CronScheduleBuilder.cronSchedule(scheduleJobBean.getCronExpression())
						.withMisfireHandlingInstructionDoNothing();
			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger =
				TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJobBean.getId()))
						.withSchedule(scheduleBuilder).build();
			// 放入参数，运行时的方法可以获取
			jobDetail.getJobDataMap().put(ScheduleJobBean.JOB_PARAM_KEY, scheduleJobBean);
			scheduler.scheduleJob(jobDetail, trigger);
			// 暂停任务
			if (scheduleJobBean.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()) {
				pauseJob(scheduler, scheduleJobBean.getId());
			}
		} catch (SchedulerException e) {
			throw new BusinessException("创建定时任务失败", e);
		}
	}
	/**
	 * 更新定时任务
	 */
	public static void updateScheduleJob(Scheduler scheduler, ScheduleJobBean scheduleJobBean) {
		try {
			TriggerKey triggerKey = getTriggerKey(scheduleJobBean.getId());
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder =
				CronScheduleBuilder.cronSchedule(scheduleJobBean.getCronExpression())
						.withMisfireHandlingInstructionDoNothing();
			CronTrigger trigger = getCronTrigger(scheduler, scheduleJobBean.getId());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 参数
			trigger.getJobDataMap().put(ScheduleJobBean.JOB_PARAM_KEY, scheduleJobBean);
			scheduler.rescheduleJob(triggerKey, trigger);
			// 暂停任务
			if (scheduleJobBean.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()) {
				pauseJob(scheduler, scheduleJobBean.getId());
			}
		} catch (SchedulerException e) {
			throw new BusinessException("更新定时任务失败", e);
		}
	}
	/**
	 * 立即执行任务
	 */
	public static void run(Scheduler scheduler, ScheduleJobBean scheduleJobBean) {
		try {
			// 参数
			JobDataMap dataMap = new JobDataMap();
			dataMap.put(ScheduleJobBean.JOB_PARAM_KEY, scheduleJobBean);
			scheduler.triggerJob(getJobKey(scheduleJobBean.getId()), dataMap);
		} catch (SchedulerException e) {
			throw new BusinessException("立即执行定时任务失败", e);
		}
	}
	/**
	 * 暂停任务
	 */
	public static void pauseJob(Scheduler scheduler, String jobId) {
		try {
			scheduler.pauseJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			throw new BusinessException("暂停定时任务失败", e);
		}
	}
	/**
	 * 恢复任务
	 */
	public static void resumeJob(Scheduler scheduler, String jobId) {
		try {
			scheduler.resumeJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			throw new BusinessException("暂停定时任务失败", e);
		}
	}
	/**
	 * 删除定时任务
	 */
	public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
		try {
			scheduler.deleteJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			throw new BusinessException("删除定时任务失败", e);
		}
	}
}
