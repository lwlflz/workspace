package com.build.cloud.modules.job.utils;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.build.cloud.common.utils.SpringContextUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.job.bean.ScheduleJobBean;
import com.build.cloud.modules.job.entity.ScheduleJobLogEntity;
import com.build.cloud.modules.job.service.ScheduleJobLogService;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: ScheduleJob
 * @Description: 定时任务
 * @author: liutao
 * @date: 2018年3月16日 下午12:11:25
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	@Override
	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
		Object object = context.getMergedJobDataMap().get(ScheduleJobBean.JOB_PARAM_KEY);
		// ScheduleJobBean scheduleJob = (ScheduleJobBean)object;
		Map<String, Object> jobMap = StringUtil.objectToMap(object);
		ScheduleJobBean scheduleJob = null;
		try {
			scheduleJob = (ScheduleJobBean)StringUtil.convertMap(ScheduleJobBean.class, jobMap);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
		// 获取spring bean
		ScheduleJobLogService scheduleJobLogService =
			(ScheduleJobLogService)SpringContextUtils.getBean("scheduleJobLogService");
		// 数据库保存执行记录
		ScheduleJobLogEntity log = new ScheduleJobLogEntity();
		String id = MapUtil.getStr(jobMap, "id");
		log.setJobId(scheduleJob.getId());
		log.setBeanName(scheduleJob.getBeanName());
		log.setMethodName(scheduleJob.getMethodName());
		log.setParams(scheduleJob.getParams());
		log.setCreateTime(new Date());
		// 任务开始时间
		long startTime = System.currentTimeMillis();
		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + id);
			ScheduleRunnable task =
				new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
			Future<?> future = service.submit(task);
			future.get();
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(Convert.toInt(times));
			// 任务状态 0：成功 1：失败
			log.setStatus(0);
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(Convert.toInt(times));
			// 任务状态 0：成功 1：失败
			log.setStatus(1);
			log.setError(StrUtil.sub(e.toString(), 0, 2000));
		} finally {
			scheduleJobLogService.insert(log);
		}
	}
//	@Override
//	public void execute(JobExecutionContext context)
//		throws JobExecutionException {
//		executeInternal(context);
//	}
}
