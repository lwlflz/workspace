package com.build.cloud.common.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtils {

	public static String getDomain(){
		HttpServletRequest request = HttpRequestUtils.getRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getOrigin(){
		HttpServletRequest request = HttpRequestUtils.getRequest();
		return request.getHeader("Origin");
	}
}
