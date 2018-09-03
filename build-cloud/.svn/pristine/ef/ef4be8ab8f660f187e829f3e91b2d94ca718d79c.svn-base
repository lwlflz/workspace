package com.build.cloud.dbutils;
import java.util.List;

import org.junit.Test;

import com.build.cloud.common.dbutils.DatabaseHelper;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.service.AttendService;
public class DbUtilsTest {
	@Test
	public void jdbcTest() {
		System.out.println(DatabaseHelper.getConnection());
	}
	@Test
	public void findAllTest() {
	}
	@Test
	public void getDeviceTest() {
		List<DevDeviceEntity> list = AttendService.getService().getDevice();
		for (DevDeviceEntity entity : list) {
			System.out.println(entity.toString());
		}
	}
}
