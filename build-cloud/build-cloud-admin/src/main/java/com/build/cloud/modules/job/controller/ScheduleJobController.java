package com.build.cloud.modules.job.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.modules.job.entity.ScheduleJobEntity;
import com.build.cloud.modules.job.service.ScheduleJobService;

import java.util.Map;
/**
 * @ClassName: ScheduleJobController
 * @Description: 定时任务
 * @author: liutao
 * @date: 2018年3月16日 下午2:44:45
 */
//@RestController
//@RequestMapping("/sys/schedule")
//public class ScheduleJobController {
//	@Autowired
//	private ScheduleJobService scheduleJobService;
//	/**
//	 * 定时任务列表
//	 */
//	@RequestMapping("/list")
//	@RequiresPermissions("sys:schedule:list")
//	public Result list(@RequestParam Map<String, Object> params) {
//		PageUtils page = scheduleJobService.queryPage(params);
//		return Result.ok().put("page", page);
//	}
//	/**
//	 * 定时任务信息
//	 */
//	@RequestMapping("/info/{id}")
//	@RequiresPermissions("sys:schedule:info")
//	public Result info(@PathVariable("id") String id) {
//		ScheduleJobEntity schedule = scheduleJobService.selectById(id);
//		return Result.ok().put("schedule", schedule);
//	}
//	/**
//	 * 保存定时任务
//	 */
//	@SysLog("保存定时任务")
//	@RequestMapping("/save")
//	@RequiresPermissions("sys:schedule:save")
//	public Result save(@RequestBody ScheduleJobEntity scheduleJob) {
//		ValidatorUtils.validateEntity(scheduleJob);
//		scheduleJobService.save(scheduleJob);
//		return Result.ok();
//	}
//	/**
//	 * 修改定时任务
//	 */
//	@SysLog("修改定时任务")
//	@RequestMapping("/update")
//	@RequiresPermissions("sys:schedule:update")
//	public Result update(@RequestBody ScheduleJobEntity scheduleJob) {
//		ValidatorUtils.validateEntity(scheduleJob);
//		scheduleJobService.update(scheduleJob);
//		return Result.ok();
//	}
//	/**
//	 * 删除定时任务
//	 */
//	@SysLog("删除定时任务")
//	@RequestMapping("/delete")
//	@RequiresPermissions("sys:schedule:delete")
//	public Result delete(@RequestBody String[] ids) {
//		scheduleJobService.deleteBatch(ids);
//		return Result.ok();
//	}
//	/**
//	 * 立即执行任务
//	 */
//	@SysLog("立即执行任务")
//	@RequestMapping("/run")
//	@RequiresPermissions("sys:schedule:run")
//	public Result run(@RequestBody String[] ids) {
//		scheduleJobService.run(ids);
//		return Result.ok();
//	}
//	/**
//	 * 暂停定时任务
//	 */
//	@SysLog("暂停定时任务")
//	@RequestMapping("/pause")
//	@RequiresPermissions("sys:schedule:pause")
//	public Result pause(@RequestBody String[] ids) {
//		scheduleJobService.pause(ids);
//		return Result.ok();
//	}
//	/**
//	 * 恢复定时任务
//	 */
//	@SysLog("恢复定时任务")
//	@RequestMapping("/resume")
//	@RequiresPermissions("sys:schedule:resume")
//	public Result resume(@RequestBody String[] ids) {
//		scheduleJobService.resume(ids);
//		return Result.ok();
//	}
//}
