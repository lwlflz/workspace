package com.build.cloud.common.listener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.build.cloud.modules.sys.service.ISysDictService;
/**
 * @ClassName: WebAppListener
 * @Description: 项目启动，初始化相关信息
 * @author: liutao
 * @date: 2018年3月16日 下午8:53:31
 */
@Service
public class WebAppListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(WebAppListener.class);
	ExecutorService executorService = Executors.newCachedThreadPool();
	@Autowired
	private ISysDictService sysDictService;
	/**
	 * 实现EnvironmentAware接口，初始化系统数据。
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		dictCache();
		logger.info("-------------bean初始化完毕-------------");
		/**
		 * 通过线程开启数据库中已经开启的定时任务 灵感来自spring spring boot继续执行 mythread开辟线程，延迟后执行
		 */
		// DataSourceJobThread myThread =
		// (DataSourceJobThread)event.getApplicationContext().getBean("dataSourceJobThread");
		// executorService.execute(myThread);
	}
	/**
	 * 缓存全部数据字典
	 */
	private void dictCache() {
		sysDictService.selectAll();
		logger.info("执行dictCache...");
	}
}
