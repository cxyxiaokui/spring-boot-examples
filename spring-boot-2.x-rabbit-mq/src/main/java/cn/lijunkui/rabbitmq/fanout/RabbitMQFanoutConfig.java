package cn.lijunkui.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQFanoutConfig {
	
	public static final String FANOUT_QUEUE = "fanout_queue";
	public static final String FANOUT_QUEUE2 = "fanout_queue2";
	
	public static final String FANOUT_EXCHANGE = "fanout_exchange";
	
	@Bean
	public Queue fanoutQueue() {
		return new Queue(FANOUT_QUEUE,true);
	}
	
	@Bean
	public Queue fanoutQueue2() {
		return new Queue(FANOUT_QUEUE2,true);
	}
	
	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
	@Bean
	public Binding FanoutBinding() {
		return BindingBuilder.bind(fanoutQueue()).to(fanoutExchage());
	}
	
	@Bean
	public Binding FanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchage());
	}
	
}
