package com.build.cloud.common.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * <p>
 * SpringContextUtils.java
 * </p>
 * <p>
 * Spring Context 工具类
 * </p>
 * <Detail Description>
 * @author LiuTao
 * @since 2017年6月1日 上午1:00:13
 */
@Component
public class SpringContextUtils implements ApplicationContextAware, DisposableBean  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextUtils.class);
	public static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
		throws BeansException {
		if (SpringContextUtils.applicationContext == null) {
			SpringContextUtils.applicationContext = applicationContext;
		}
		LOGGER.info("注入ApplicationContext到SpringContextHolder：{}" + applicationContext);
	}
	public static Object getBean(String name) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return applicationContext.getBean(name);
	}
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return applicationContext.getBean(requiredType);
	}
	public static <T> T getBean(String name, Class<T> requiredType) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return applicationContext.getBean(name, requiredType);
	}
	public static boolean containsBean(String name) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		return applicationContext.containsBean(name);
	}
	public static boolean isSingleton(String name) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		return applicationContext.isSingleton(name);
	}
	public static Class<? extends Object> getType(String name) {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return applicationContext.getType(name);
	}
	// 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		try {
            assertContextInjected();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return applicationContext;
	}
	/**
     *清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder () {
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("清除SpringContext中的ApplicationContext："+ applicationContext);
        }
        applicationContext = null;
    }

    /**
     *实现ApplicationContextAware接口, 注入Context到静态变量中.
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        SpringContextUtils.clearHolder();
    }
	 /**
     * 检查ApplicationContext不为空
     */
    private static void assertContextInjected () throws Exception {
        if (applicationContext == null) {
            throw new Exception("applicaitonContext属性未注入.");
        }
    }
}