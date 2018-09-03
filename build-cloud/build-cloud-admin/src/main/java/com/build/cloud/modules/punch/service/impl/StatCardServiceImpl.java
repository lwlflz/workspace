package com.build.cloud.modules.punch.service.impl;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.punch.dao.StatCardDao;
import com.build.cloud.modules.punch.entity.StatCardEntity;
import com.build.cloud.modules.punch.service.IStatCardService;
@Service("statCardService")
public class StatCardServiceImpl extends ServiceImpl<StatCardDao, StatCardEntity> implements IStatCardService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<StatCardEntity> page =
			this.selectPage(new Query<StatCardEntity>(params).getPage(), new EntityWrapper<StatCardEntity>());
		return new PageUtils(page);
	}

	@Override
	public Integer selectWorkerStatCard(Map<String, Object> params) {
		return baseMapper.selectWorkerStatCard(params);
	}
}
