package com.build.cloud.modules.sys.oauth2;
import java.util.UUID;

import com.build.cloud.common.exception.BusinessException;
import com.sunsine.common.util.encrypt.MD5Util;
/**
 * 
 * @ClassName: TokenGenerator   
 * @Description: 生成token
 * @author: liutao
 * @date: 2018年3月31日 下午3:51:52
 */
public class TokenGenerator {
	public static String generateValue() {
		return generateValue(UUID.randomUUID().toString());
	}
	public static String generateValue(String param) {
		try {
			return MD5Util.encode(param);
		} catch (Exception e) {
			throw new BusinessException("生成Token失败", e);
		}
	}
}
