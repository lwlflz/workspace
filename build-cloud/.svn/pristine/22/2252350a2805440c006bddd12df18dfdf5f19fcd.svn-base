package com.build.cloud.common.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;
@Component
@ConfigurationProperties(prefix = "hikari")
public class DBProperties {
	private HikariDataSource master;
	private HikariDataSource slave;
	public HikariDataSource getMaster() {
		return master;
	}
	public void setMaster(HikariDataSource master) {
		this.master = master;
	}
	public HikariDataSource getSlave() {
		return slave;
	}
	public void setSlave(HikariDataSource slave) {
		this.slave = slave;
	}
}