package cn.lijunkui.rabbitmq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTopicSend {
	private static Logger log = LoggerFactory.getLogger(RabbitMQTopicSend.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate ;

	public void sendTopic(Object message) {
		log.info("send topic message:"+message);
		amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.key","topic.key" + message);
		
	}
	
	public void sendTopicAnother(Object message) {
		log.info("send topic another message:"+message);
		amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.another","topic.another" + message);
	}
	
	public void sendTopicAll(Object message) {
		log.info("send topic all message:"+message);
		amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.key","topic.key" + message);
		//amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.anotherkey","topic.anotherkey" + message);
		amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.1","topic.1" + message);
	}
}
