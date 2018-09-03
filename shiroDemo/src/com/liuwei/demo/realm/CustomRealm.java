package com.liuwei.demo.realm;

import java.util.ArrayList;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm{

//	 ⁄»®
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		ArrayList<String> permissions = new ArrayList<String>();
		
		permissions.add("user.*");
		permissions.add("department£∫*");
		  SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		  simpleAuthorizationInfo.addRole("manager");
		 
		 for (String permission : permissions) {
			simpleAuthorizationInfo.addStringPermission(permission);
		}
		  
		
		return simpleAuthorizationInfo;
	}

//	»œ÷§
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		
		if(!userName.equals("zhangsan")) {
			return null;
		}
		
		String dbPassword = "zhangsan";
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, dbPassword, getName());
		
		
		return simpleAuthenticationInfo;
	}

	


}
