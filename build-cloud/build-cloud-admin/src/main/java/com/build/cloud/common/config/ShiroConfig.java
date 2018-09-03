package com.build.cloud.common.config;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.build.cloud.common.listener.ShiroSessionListener;
import com.build.cloud.modules.sys.oauth2.OAuth2Filter;
import com.build.cloud.modules.sys.oauth2.OAuth2Realm;
import com.google.common.collect.Maps;
/**
 * @ClassName: ShiroConfig
 * @Description: Shiro的配置文件
 * @author: liutao
 * @date: 2018年3月16日 下午12:26:52
 */
@Configuration
public class ShiroConfig {
	@Bean("securityManager")
	public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(oAuth2Realm);
		securityManager.setSessionManager(sessionManager);
		// 注入记住我管理器;
		// securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		// Shiro的核心安全接口,这个属性是必须的
		shiroFilter.setSecurityManager(securityManager);
		// 自定义拦截器
		Map<String, Filter> filtersMap = Maps.newHashMap();
		// oauth过滤
		filtersMap.put("oauth2", new OAuth2Filter());
		shiroFilter.setFilters(filtersMap);
		Map<String, String> filterMap = Maps.newLinkedHashMap();
//		filterMap.put("/pp/**", "anon");
//		filterMap.put("/bs/**", "anon");
//		filterMap.put("/act/**", "anon");
//		filterMap.put("/bs/worker/v1/upload", "anon");
		filterMap.put("/v1/login", "anon");
		filterMap.put("/captcha.jpg", "anon");
		filterMap.put("/**", "oauth2");
		shiroFilter.setFilterChainDefinitionMap(filterMap);
		return shiroFilter;
	}
	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 * @return
	 */
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 * AOP式方法级权限检查
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	/**
	 * cookie对象;
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}
	/**
	 * cookie管理对象;记住我功能
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("OY//C4rhfwNxCQAQCrQQ1Q=="));
		return cookieRememberMeManager;
	}
	
	@Bean
	public SessionDAO sessionDAO() {
		MemorySessionDAO sessionDAO = new MemorySessionDAO();
		return sessionDAO;
	}
	
	@Bean
    public ShiroSessionListener sessionListener() {
        return new ShiroSessionListener();
    }
	
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDAO());
		sessionManager.getSessionListeners().add(sessionListener());
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setGlobalSessionTimeout(600000);
		sessionManager.setDeleteInvalidSessions(true);
		return sessionManager;
	}
}
