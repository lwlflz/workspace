package com.build.cloud.common.config;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
@EnableAsync
public class AsyncConfig {
	private int corePoolSize = 10;
	private int maxPoolSize = 200;
	private int queueCapacity = 10;
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);//当前线程数
		executor.setMaxPoolSize(maxPoolSize);// 最大线程数
		executor.setQueueCapacity(queueCapacity);//线程池所使用的缓冲队列
		executor.setThreadNamePrefix("DhjsAsync-");//  线程名称前缀
		executor.initialize();
		return executor;
	}
}