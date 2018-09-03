package com.build.cloud.common.validator;
import com.build.cloud.common.exception.BusinessException;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: Assert
 * @Description: 数据校验
 * @author: liutao
 * @date: 2018年3月16日 上午11:56:08
 */
public abstract class Assert {
	public static void isBlank(String str, String message) {
		if (StrUtil.isBlank(str)) {
			throw new BusinessException(message);
		}
	}
	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new BusinessException(message);
		}
	}
}
