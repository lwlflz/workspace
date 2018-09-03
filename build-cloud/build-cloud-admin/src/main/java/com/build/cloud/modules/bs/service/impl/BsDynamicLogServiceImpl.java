package com.build.cloud.modules.bs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.core.base.service.impl.BaseServiceImpl;
import com.build.cloud.modules.bs.dao.BsDynamicLogDao;
import com.build.cloud.modules.bs.entity.BsDynamicLogEntity;
import com.build.cloud.modules.bs.service.IBsDynamicLogService;
@Service
public class BsDynamicLogServiceImpl extends BaseServiceImpl<BsDynamicLogDao, BsDynamicLogEntity> implements IBsDynamicLogService{

	@Override
	public List<BsDynamicLogEntity> findBsDynamicLogList(String taskId) {
		return baseMapper.selectList(new EntityWrapper<BsDynamicLogEntity>().eq("task_id", taskId));
	}

}
