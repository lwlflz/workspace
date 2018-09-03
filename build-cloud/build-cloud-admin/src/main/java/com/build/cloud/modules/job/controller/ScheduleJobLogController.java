package com.build.cloud.modules.job.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.job.entity.ScheduleJobLogEntity;
import com.build.cloud.modules.job.service.ScheduleJobLogService;

import java.util.Map;
/**
 * @ClassName: ScheduleJobLogController
 * @Description: 定时任务日志
 * @author: liutao
 * @date: 2018年3月16日 下午2:45:03
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = scheduleJobLogService.queryPage(params);
		return Result.ok().put("page", page);
	}
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{id}")
	public Result info(@PathVariable("id") Long id) {
		ScheduleJobLogEntity log = scheduleJobLogService.selectById(id);
		return Result.ok().put("log", log);
	}
}
