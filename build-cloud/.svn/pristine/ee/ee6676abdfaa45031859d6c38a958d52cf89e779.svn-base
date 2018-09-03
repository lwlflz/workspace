package com.build.cloud.common.config;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunsine.fastdfs.FastDFSTemplate;
import com.sunsine.fastdfs.FastDFSTemplateFactory;

@Configuration
public class FastDFSConfig {
	
	public static final String ROOT_CONFIG_PREFIX = "fastdfs";
	
	@Resource
    private FastDFSTemplateFactory fastDFSFactory;

    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = ROOT_CONFIG_PREFIX)
    public FastDFSTemplateFactory fastDFSFactory() {
        return new FastDFSTemplateFactory();
    }

    @Bean
    public FastDFSTemplate fastDFSTemplate() {
        return new FastDFSTemplate(fastDFSFactory);
    }
}
