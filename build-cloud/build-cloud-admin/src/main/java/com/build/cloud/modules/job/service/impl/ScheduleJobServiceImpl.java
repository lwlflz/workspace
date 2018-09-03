package com.build.cloud.modules.job.service.impl;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.job.bean.ScheduleJobBean;
import com.build.cloud.modules.job.dao.ScheduleJobDao;
import com.build.cloud.modules.job.entity.ScheduleJobEntity;
import com.build.cloud.modules.job.service.ScheduleJobService;
import com.build.cloud.modules.job.utils.ScheduleUtils;
import com.google.common.collect.Maps;

import cn.hutool.core.util.StrUtil;
//@Service("scheduleJobService")
//public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity>
//		implements ScheduleJobService {
//	@Autowired
//	private Scheduler scheduler;
//	/**
//	 * 项目启动时，初始化定时器
//	 */
//	@PostConstruct
//	public void init() {
//		List<ScheduleJobEntity> scheduleJobList = this.selectList(null);
//		for (ScheduleJobEntity scheduleJobEntity : scheduleJobList) {
//			ScheduleJobBean scheduleJobBean = new ScheduleJobBean();
//			BeanUtils.copyProperties(scheduleJobEntity, scheduleJobBean);
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJobBean.getId());
//			// 如果不存在，则创建
//			if (cronTrigger == null) {
//				ScheduleUtils.createScheduleJob(scheduler, scheduleJobBean);
//			} else {
//				ScheduleUtils.updateScheduleJob(scheduler, scheduleJobBean);
//			}
//		}
//	}
//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		String beanName = (String)params.get("beanName");
//		Page<ScheduleJobEntity> page =
//			this.selectPage(new Query<ScheduleJobEntity>(params).getPage(),
//				new EntityWrapper<ScheduleJobEntity>().like(StrUtil.isNotBlank(beanName), "bean_name", beanName));
//		return new PageUtils(page);
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void save(ScheduleJobEntity scheduleJobEntity) {
//		scheduleJobEntity.setCreateTime(new Date());
//		scheduleJobEntity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
//		this.insert(scheduleJobEntity);
//		ScheduleJobBean scheduleJobBean = new ScheduleJobBean();
//		BeanUtils.copyProperties(scheduleJobEntity, scheduleJobBean);
//		ScheduleUtils.createScheduleJob(scheduler, scheduleJobBean);
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void update(ScheduleJobEntity scheduleJobEntity) {
//		ScheduleJobBean scheduleJobBean = new ScheduleJobBean();
//		BeanUtils.copyProperties(scheduleJobEntity, scheduleJobBean);
//		ScheduleUtils.updateScheduleJob(scheduler, scheduleJobBean);
//		this.updateById(scheduleJobEntity);
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteBatch(String[] ids) {
//		for (String id : ids) {
//			ScheduleUtils.deleteScheduleJob(scheduler, id);
//		}
//		// 删除数据
//		this.deleteBatchIds(Arrays.asList(ids));
//	}
//	@Override
//	public int updateBatch(String[] ids, int status) {
//		Map<String, Object> map = Maps.newHashMap();
//		map.put("list", Arrays.asList(ids));
//		map.put("status", status);
//		return baseMapper.updateBatch(map);
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void run(String[] ids) {
//		for (String id : ids) {
//			ScheduleJobEntity scheduleJobEntity = this.selectById(id);
//			ScheduleJobBean scheduleJobBean = new ScheduleJobBean();
//			BeanUtils.copyProperties(scheduleJobEntity, scheduleJobBean);
//			ScheduleUtils.run(scheduler, scheduleJobBean);
//		}
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void pause(String[] ids) {
//		for (String id : ids) {
//			ScheduleUtils.pauseJob(scheduler, id);
//		}
//		updateBatch(ids, Constant.ScheduleStatus.PAUSE.getValue());
//	}
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void resume(String[] ids) {
//		for (String id : ids) {
//			ScheduleUtils.resumeJob(scheduler, id);
//		}
//		updateBatch(ids, Constant.ScheduleStatus.NORMAL.getValue());
//	}
//}
