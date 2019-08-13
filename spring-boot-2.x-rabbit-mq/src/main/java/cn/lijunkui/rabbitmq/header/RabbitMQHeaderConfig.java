package cn.lijunkui.rabbitmq.header;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQHeaderConfig {
	public static final String HEAD_QUEUE = "head_queue";
	public static final String HEAD_EXCHAGE = "head_queue_exchage";
	@Bean
	public Queue headQueue() {
		return new Queue(HEAD_QUEUE,true);
	}
	@Bean
	public HeadersExchange headExchage() {
		return new HeadersExchange(HEAD_EXCHAGE);
	}
	@Bean
	public Binding headBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", "abc123");
		return BindingBuilder.bind(headQueue()).to(headExchage()).whereAll(map).match();
	}
}
