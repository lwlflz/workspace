package com.build.cloud.core.datasources;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.google.common.collect.Maps;
/**
 * @ClassName: DynamicDataSource
 * @Description: 动态数据源
 * @author: liutao
 * @date: 2018年3月16日 下午2:59:35
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(Maps.newHashMap(targetDataSources));
		super.afterPropertiesSet();
	}
	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}
	public static String getDataSource() {
		return contextHolder.get();
	}
	public static void clearDataSource() {
		contextHolder.remove();
	}
}
