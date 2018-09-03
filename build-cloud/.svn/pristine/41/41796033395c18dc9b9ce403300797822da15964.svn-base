package com.build.cloud.core.datasources;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.build.cloud.common.config.DBProperties;
import com.google.common.collect.Maps;
/**
 * @ClassName: DynamicDataSourceConfig
 * @Description: 配置多数据源
 * @author: liutao
 * @date: 2018年3月16日 下午12:32:50
 */
@Configuration
public class DynamicDataSourceConfig {
	@Autowired
	private DBProperties properties;
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		// 按照目标数据源名称和目标数据源对象的映射存放在Map中
		Map<String, DataSource> targetDataSources = Maps.newConcurrentMap();
		targetDataSources.put(DataSourceNames.MASTER, properties.getMaster());
		targetDataSources.put(DataSourceNames.SLAVE, properties.getSlave());
		// 采用是想AbstractRoutingDataSource的对象包装多数据源
		return new DynamicDataSource(properties.getMaster(), targetDataSources);
	}
	/**
	 * @Title: txManager @Description: Transaction 相关配置 @param @return 设定文件 @return
	 * PlatformTransactionManager 返回类型 @throws
	 */
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
