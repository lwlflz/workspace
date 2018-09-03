package com.build.cloud.modules.sys.oauth2;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.HttpContextUtils;
import com.build.cloud.core.base.controller.Result;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: OAuth2Filter
 * @Description: oauth2过滤器
 * @author: liutao
 * @date: 2018年3月31日 下午3:43:17
 */
public class OAuth2Filter extends AuthenticatingFilter {
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response)
		throws Exception {
		// 获取请求token
		String token = getRequestToken((HttpServletRequest)request);
		if (StrUtil.isBlank(token)) {
			return null;
		}
		return new OAuth2Token(token);// 用auth code创建auth2Token
	}
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (((HttpServletRequest)request).getMethod().equals(RequestMethod.OPTIONS.name())) {
			return true;
		}
		return false;
	}
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
		throws Exception {
		// 获取请求token，如果token不存在，直接返回401
		String token = getRequestToken((HttpServletRequest)request);
		if (StrUtil.isBlank(token)) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
			String json = JSON.toJSONString(Result.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
			httpResponse.getWriter().print(json);
			return false;
		}
		return executeLogin(request, response); // 执行父类的登录逻辑，调用Subject.login登录
	}
	/**
	 * 登录失败后的回调方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setContentType("application/json;charset=utf-8");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
		try {
			// 处理登录失败的异常
			Throwable throwable = e.getCause() == null ? e : e.getCause();
			Result r = Result.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
			String json = JSON.toJSONString(r);
			httpResponse.getWriter().print(json);
		} catch (IOException e1) {
			throw new BusinessException(e1.getMessage());
		}
		return false;
	}
	/**
	 * 获取请求的token
	 */
	private String getRequestToken(HttpServletRequest httpRequest) {
		// 从header中获取token
		String token = httpRequest.getHeader("token");
		// 如果header中不存在token，则从参数中获取token
		if (StrUtil.isBlank(token)) {
			token = httpRequest.getParameter("token");
		}
		return token;
	}
}
