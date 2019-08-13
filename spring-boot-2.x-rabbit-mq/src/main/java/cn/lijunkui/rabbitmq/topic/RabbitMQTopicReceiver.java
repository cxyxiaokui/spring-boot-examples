package cn.lijunkui.rabbitmq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTopicReceiver {
	private static Logger log = LoggerFactory.getLogger(RabbitMQTopicReceiver.class);
	
	@RabbitListener(queues=RabbitMQTopicConfig.TOPIC_QUEUE)
	public void receiveTopic(String message) {
		log.info(" rabbitMQ topic receive "+RabbitMQTopicConfig.TOPIC_QUEUE+" message:"+message);
	}
	
	@RabbitListener(queues=RabbitMQTopicConfig.TOPIC_QUEUE_ANOTHER)
	public void receiveTopicAnother(String message) {
		log.info(" rabbitMQ topic another receive "+RabbitMQTopicConfig.TOPIC_QUEUE_ANOTHER+" message:"+message);
	}
	
	
}
