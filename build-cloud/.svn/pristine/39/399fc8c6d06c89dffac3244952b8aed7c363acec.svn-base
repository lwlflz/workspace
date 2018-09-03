package com.build.cloud.modules.punch.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.punch.dao.DevDeviceDao;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.entity.DevEmployeeEntity;
import com.build.cloud.modules.punch.service.IDevDeviceService;
import com.build.cloud.modules.punch.service.IDevEmployeeService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
@Service("devDeviceService")
public class DevDeviceServiceImpl extends ServiceImpl<DevDeviceDao, DevDeviceEntity> implements IDevDeviceService {
	
	@Autowired
	private IDevEmployeeService devEmployeeService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<DevDeviceEntity> page =
			this.selectPage(new Query<DevDeviceEntity>(params).getPage(), new EntityWrapper<DevDeviceEntity>());
		return new PageUtils(page);
	}
	@Transactional
	@Override
	public void syncDev(Map<String, Object> params) {
		List<DevDeviceEntity> list = JSON.parseArray(MapUtil.getStr(params, "list"), DevDeviceEntity.class);
		List<DevEmployeeEntity> emps = JSON.parseArray(MapUtil.getStr(params, "emps"), DevEmployeeEntity.class);
		if (CollectionUtil.isNotEmpty(list)) {
			insertOrUpdateBatch(list);
		}
		if (CollectionUtil.isNotEmpty(emps)) {
			devEmployeeService.insertOrUpdateBatch(emps);
		}
	}
	@Override
	public List<Map<String, Object>> queryDevEmp(Map<String, Object> params) {
		return baseMapper.queryDevEmp(params);
	}
	@Override
	public List<String> selectAllProject() {
		return baseMapper.selectAllProject();
	}
}
