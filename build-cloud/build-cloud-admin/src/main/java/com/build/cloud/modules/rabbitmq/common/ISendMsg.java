package com.build.cloud.modules.rabbitmq.common;

import java.util.Map;

public interface ISendMsg {
	void sendMsg(Map<String, Object> params);
}
