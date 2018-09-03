package com.build.cloud.common.config;
import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.build.cloud.common.xss.XssFilter;
/**
 * @ClassName: FilterConfig
 * @Description: Filter配置
 * @author: liutao
 * @date: 2018年3月16日 下午2:54:49
 */
@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean shiroFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetBeanName("shiroFilter");
		registration.setFilter(proxy);
		// 该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
		registration.addInitParameter("targetFilterLifecycle", "true");
		registration.setEnabled(true);
		registration.setAsyncSupported(true);
		registration.setOrder(Integer.MAX_VALUE - 1);
		registration.addUrlPatterns("/*");
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC,
			DispatcherType.INCLUDE, DispatcherType.ERROR);
		return registration;
	}
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new XssFilter());
		registration.addUrlPatterns("/*");
		registration.setName("xssFilter");
		registration.setOrder(Integer.MAX_VALUE);
		return registration;
	}
	// @Bean
	// public ObjectMapper getObjectMapper() {
	// ObjectMapper mapper = new ObjectMapper();
	// mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	// return mapper;
	// }
}
