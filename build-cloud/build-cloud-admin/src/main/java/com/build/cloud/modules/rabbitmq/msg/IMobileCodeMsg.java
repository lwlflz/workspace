package com.build.cloud.modules.rabbitmq.msg;

import com.build.cloud.common.constant.MqQueueConstant;
import com.build.cloud.modules.rabbitmq.common.ISendMsg;
import com.build.cloud.modules.rabbitmq.common.RPCQueueName;


@RPCQueueName(MqQueueConstant.MOBILE_CODE_QUEUE)
public interface IMobileCodeMsg extends ISendMsg {
}
