package com.build.cloud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@MapperScan(basePackages = { "com.build.cloud.modules.*.dao" })
@ServletComponentScan
@EnableAsync(proxyTargetClass = true)
@EnableEncryptableProperties
public class AdminApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminApplication.class);
	}
}
