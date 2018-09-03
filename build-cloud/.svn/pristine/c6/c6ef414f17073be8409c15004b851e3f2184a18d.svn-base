package com.build.cloud.modules.job.service.impl;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.job.dao.ScheduleJobLogDao;
import com.build.cloud.modules.job.entity.ScheduleJobLogEntity;
import com.build.cloud.modules.job.service.ScheduleJobLogService;

import cn.hutool.core.util.StrUtil;
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity>
		implements ScheduleJobLogService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String jobId = (String)params.get("id");
		Page<ScheduleJobLogEntity> page =
			this.selectPage(new Query<ScheduleJobLogEntity>(params).getPage(),
				new EntityWrapper<ScheduleJobLogEntity>().like(StrUtil.isNotBlank(jobId), "id", jobId));
		return new PageUtils(page);
	}
}
