package com.build.cloud.modules.sys.service.impl;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.sys.dao.SysPostDao;
import com.build.cloud.modules.sys.entity.SysPostEntity;
import com.build.cloud.modules.sys.service.ISysPostService;
@Service("sysPostService")
public class SysPostServiceImpl extends ServiceImpl<SysPostDao, SysPostEntity> implements ISysPostService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<SysPostEntity> page =
			this.selectPage(new Query<SysPostEntity>(params).getPage(), new EntityWrapper<SysPostEntity>());
		return new PageUtils(page);
	}
}
