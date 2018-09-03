package com.build.cloud.common.xss;
import com.build.cloud.common.exception.BusinessException;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SQLFilter
 * @Description: SQL过滤
 * @author: liutao
 * @date: 2018年3月16日 上午11:53:19
 */
public class SQLFilter {
	/**
	 * SQL注入过滤
	 * @param str 待验证的字符串
	 */
	public static String sqlInject(String str) {
		if (StrUtil.isBlank(str)) {
			return null;
		}
		// 去掉'|"|;|\字符
		str = StrUtil.replace(str, "'", "");
		str = StrUtil.replace(str, "\"", "");
		str = StrUtil.replace(str, ";", "");
		str = StrUtil.replace(str, "\\", "");
		// 转换成小写
		// str = str.toLowerCase();
		// 非法字符
		String[] keywords =
			{ "master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop" };
		// 判断是否包含非法字符
		for (String keyword : keywords) {
			if (str.toLowerCase().indexOf(keyword) != -1) {
				throw new BusinessException("包含非法字符");
			}
		}
		return str;
	}
}
