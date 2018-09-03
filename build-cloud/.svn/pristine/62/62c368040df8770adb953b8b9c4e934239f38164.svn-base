package com.build.cloud.modules.punch.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.entity.DevEmployeeEntity;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
import com.build.cloud.modules.punch.entity.StatCardEntity;
import com.build.cloud.modules.punch.service.AttendService;
import com.build.cloud.modules.punch.service.IStatCardService;
import com.build.cloud.modules.punch.service.ISyncService;
import com.build.cloud.modules.rabbitmq.sender.MQSender;
import com.google.common.collect.Maps;

import cn.hutool.core.collection.CollectionUtil;
@Service
public class SyncServiceImpl implements ISyncService {
	@Autowired
	private MQSender sender;
	@Override
	public void syncUser() {
		List<ProAttendUserEntity> attendUsers = AttendService.getService().getAttendUser();
		if (CollectionUtil.isEmpty(attendUsers)) {
			return;
		}
		sender.syncUser(JSON.toJSONString(attendUsers));
	}
	@Override
	public void syncDev() {
		List<DevDeviceEntity> list = AttendService.getService().getDevice();
		List<DevEmployeeEntity> emps = AttendService.getService().getDevEmp();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", list);
		map.put("emps", emps);
		sender.syncDev(JSON.toJSONString(map));
	}
	@Autowired
	private IStatCardService statCardService;
	@Override
	public void syncStatcard() {
		List<StatCardEntity> list = AttendService.getService().getStatCard();
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
//		sender.syncStatcard(JSON.toJSONString(list));
		statCardService.insertOrUpdateBatch(list);
	}
	@Override
	public void syncPunch() {
		sender.syncPunch();
	}
}
