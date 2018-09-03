package com.build.cloud.common.aspect;
import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.HttpRequestUtils;
import com.build.cloud.core.log.LogManager;
import com.build.cloud.core.log.factory.LogTaskFactory;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.sunsine.common.util.DateUtil;
/**
 * @ClassName: SysLogAspect
 * @Description: 系统日志，切面处理类
 * @author: liutao
 * @date: 2018年3月16日 下午12:23:15
 */
@Aspect
@Component
public class SysLogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);
	private static final String PASSWORD = "password";
	private static final String PHONE = "phone";
	private static final String MOBILE = "mobile";
	private static final String SALT = "salt";
	@Pointcut("@annotation(com.build.cloud.common.annotation.SysLog)")
	public void logPointCut() {
	}
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point)
		throws Throwable {
		LOGGER.info("SysLogAspect.around()");
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}
	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog syslog = method.getAnnotation(SysLog.class);
		String operation = "";
		if (syslog != null) {
			// 注解上的描述
			operation = syslog.value();
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		String methods = className + "." + methodName + "()";
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		String params = "";
		try {
			params = JSON.toJSONString(args[0], SerializerFeature.WriteDateUseDateFormat);
			// if (StrUtil.isNotBlank(params)) {
			// JSONObject jsonObj = JSON.parseObject(params);
			// if (jsonObj.containsKey(PASSWORD)) {
			// jsonObj.replace(PASSWORD, AESUtil.encrypt(jsonObj.getString(PASSWORD)));
			// }
			// if (jsonObj.containsKey(PHONE)) {
			// jsonObj.replace(PHONE, StringUtil.phoneReplaceWithStar(jsonObj.getString(PHONE)));
			// }
			// if (jsonObj.containsKey(MOBILE)) {
			// jsonObj.replace(MOBILE, StringUtil.phoneReplaceWithStar(jsonObj.getString(MOBILE)));
			// }
			// if (jsonObj.containsKey(SALT)) {
			// jsonObj.replace(SALT, AESUtil.encrypt(jsonObj.getString(SALT)));
			// }
			// params = jsonObj.toJSONString();
			// } else {
			// params = StringUtil.EMPTY;
			// }
		} catch (Exception e) {
			throw new BusinessException("日志处理请求参数异常：", e);
		}
		// 用户名
		String username = "";
		if (ShiroUtils.getUserEntity() != null) {
			username = ShiroUtils.getUserEntity().getUserName();
		}
		// LogTaskFactory.saveLog(operation, methods, params, HttpRequestUtils.getIpAddr(request),
		// username, time);
		LogManager.me().executeLog(LogTaskFactory.sysLog(operation, methods, params,
			HttpRequestUtils.getIpAddr(HttpRequestUtils.getRequest()), username, time));
		LOGGER.info("方法[{}]执行时间为：{}", methodName, DateUtil.formatTime(time));
	}
}
