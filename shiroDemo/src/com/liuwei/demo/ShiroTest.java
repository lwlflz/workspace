package com.liuwei.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import junit.framework.Assert;

public class ShiroTest {

	@Test
	public void testHelloWorld() {
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager = securityManagerFactory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		session.setAttribute("someKey", "aValue");
		String attribute = (String) session.getAttribute("someKey");
		if(attribute.equals("aValue")) {
			System.out.println("someKey的值"+ attribute);
			
		}
		
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
		token.setRememberMe(true);
		
		try {
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(subject.isAuthenticated()) {
			System.out.println("用户"+subject.getPrincipal()+"登陆成功！");
		}else {
			System.out.println("用户"+subject.getPrincipal()+"登陆bu成功！");

		}
		
		System.out.println("是否拥有manager角色"+ subject.hasRole("manager"));
		System.out.println("是否拥有user.create权限"+subject.isPermitted("user.*"));
		
		subject.logout();
	}
}
