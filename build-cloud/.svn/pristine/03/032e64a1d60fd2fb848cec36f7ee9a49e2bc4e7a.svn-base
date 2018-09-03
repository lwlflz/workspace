package com.build.cloud.common.listener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.modules.job.entity.ScheduleJobEntity;
import com.build.cloud.modules.job.service.ScheduleJobService;

import cn.hutool.core.collection.CollectionUtil;
//@Configuration
//public class DataSourceJobThread implements Runnable {
//	private static final Logger log = LoggerFactory.getLogger(DataSourceJobThread.class);
//	@Autowired
//	ScheduleJobService jobService;
//	@Override
//	public void run() {
//		try {
//			Thread.sleep(1000);
//			log.info("---------线程启动---------");
//			EntityWrapper<ScheduleJobEntity> wrapper = new EntityWrapper<ScheduleJobEntity>();
//			wrapper.eq("status", 0);
//			List<ScheduleJobEntity> list = jobService.selectList(wrapper);
//			if (CollectionUtil.isEmpty(list)) {
//				log.info("---数据库暂无启动的任务---------");
//			}
//			int size = list.size();
//			String[] jobIds = new String[size];
//			// 开启任务
//			for (int i = 0; i < size; i++) {
//				jobIds[i] = list.get(i).getId();
//			}
//			jobService.run(jobIds);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}