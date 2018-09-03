package com.build.cloud.modules.job.task;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysUserService;
/**
 * 
 * @ClassName: TestTask   
 * @Description: 测试定时任务(演示Demo，可删除) testTask为spring bean的名称
 * @author: liutao
 * @date: 2018年3月16日 下午2:46:24
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ISysUserService sysUserService;
	public void test(String params) {
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SysUserEntity user = sysUserService.selectById(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));
	}
	public void test2() {
		logger.info("我是不带参数的test2方法，正在被执行");
	}
}
