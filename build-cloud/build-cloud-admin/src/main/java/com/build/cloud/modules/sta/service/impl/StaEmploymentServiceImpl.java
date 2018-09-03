package com.build.cloud.modules.sta.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.modules.sta.StaEmploymentBean;
import com.build.cloud.modules.sta.dao.StaEmploymentDao;
import com.build.cloud.modules.sta.entity.StaEmploymentEntity;
import com.build.cloud.modules.sta.service.IStaEmploymentService;

import cn.hutool.core.thread.ThreadUtil;
@Service
public class StaEmploymentServiceImpl extends ServiceImpl<StaEmploymentDao, StaEmploymentEntity>
		implements IStaEmploymentService {
	@Override
	public void syncWorkerRecord() {
		ThreadUtil.execAsync(new Runnable() {
			@Override
			public void run() {
				List<StaEmploymentEntity> list = baseMapper.selectWorkerRecord();
				insertOrUpdateAllColumnBatch(list);
			}
		});
	}

	@Override
	public List<StaEmploymentBean> selectStaEmployment(Map<String, Object> params) {
		return baseMapper.selectStaEmployment(params);
	}

	@Override
	public List<Map<String, Object>> selectByDay(Map<String, Object> params) {
		return baseMapper.selectByDay(params);
	}
}
