package com.build.cloud.modules.rabbitmq.config;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.build.cloud.common.constant.MqQueueConstant;
@Configuration
@ImportResource("classpath:rabbitmq-base-cfg.xml")
public class RabbitConfig {
	
	/**
     * 创建AMQP管理器,操作AMQP的基本设置
     */
    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // 增加失败重试机制，发送失败之后，会尝试重发三次，重发间隔(ms)为
    // 第一次 initialInterval
    // 此后: initialInterval * multiplier > maxInterval ? maxInterval : initialInterval * multiplier
    // 配合集群使用的时候, 当mq集群中一个down掉之后,重试机制尝试其他可用的mq
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(500);
        exponentialBackOffPolicy.setMaxInterval(5000);
        exponentialBackOffPolicy.setMultiplier(10.0);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
        return retryTemplate;
    }
	
	/**
	 * 初始化 log队列
	 * @return
	 */
	@Bean
	public Queue initLogQueue() {
		return new Queue(MqQueueConstant.LOG_QUEUE);
	}
	
	/**
	 * Direct模式 交换机Exchange
	 */
	@Bean
	public Queue attendQueue() {
		return new Queue(MqQueueConstant.ATTEND_QUEUE, true);
	}
	
	@Bean
	public Queue userQueue() {
		return new Queue(MqQueueConstant.USER_QUEUE, true);
	}
	@Bean
	public Queue devQueue() {
		return new Queue(MqQueueConstant.DEV_QUEUE, true);
	}
	
	@Bean
	public Queue statCardQueue() {
		return new Queue(MqQueueConstant.STATCARD_QUEUE, true);
	}
	
	@Bean
	public Queue statPunchQueue() {
		return new Queue(MqQueueConstant.PUNCH_QUEUE, true);
	}
	
}
