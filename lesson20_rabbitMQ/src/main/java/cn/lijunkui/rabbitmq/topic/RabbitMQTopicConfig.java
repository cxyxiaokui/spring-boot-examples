package cn.lijunkui.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQTopicConfig {
	public static final String TOPIC_QUEUE = "topic_queue";
	public static final String TOPIC_QUEUE_ANOTHER = "topic_queue_another";
	
	public static final String TOPIC_EXCHANGE = "topic_exchange";
	
	@Bean
	public Queue topicQueue() {
		return new Queue(TOPIC_QUEUE,true);
	}
	
	@Bean
	public Queue topicQueueAnother() {
		return new Queue(TOPIC_QUEUE_ANOTHER,true);
	}
	
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	
	
	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicQueue()).to(topicExchage()).with("topic.key");
	}
	
	/*@Bean
	public Binding topicAnotherBinding() {
		return BindingBuilder.bind(topicQueueAnother()).to(topicExchage()).with("topic.another");
	}*/
	
	@Bean
	public Binding topicAllBinding() {
		return BindingBuilder.bind(topicQueueAnother()).to(topicExchage()).with("topic.#");
	}
}
