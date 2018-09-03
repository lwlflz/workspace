package com.build.cloud.modules.rabbitmq;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunsine.common.util.encrypt.AESUtil;
public class MQMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(MQMessage.class);
	// 是否加密
	private boolean encrypt = false;
	private String body;
	@Override
	public String toString() {
		try {
			if (body != null) {
				if (encrypt) {
					return "MQMessage [ body=" + AESUtil.encrypt(body) + "]";
				} else {
					return "MQMessage [ body=" + body + "]";
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "MQMessage [ null ]";
	}
	public boolean isEncrypt() {
		return encrypt;
	}
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
	public String getBody() {
		if (encrypt) {
			try {
				this.body = AESUtil.decrypt(body);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return body;
	}
	public void setBody(String body) {
		if (encrypt) {
			try {
				this.body = AESUtil.encrypt(body);
			} catch (Exception e) {
				logger.error("", e);
			}
		} else {
			this.body = body;
		}
	}
}
