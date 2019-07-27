package cn.lijunkui.rabbitmq.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSend {
	private static Logger log = LoggerFactory.getLogger(RabbitMQSend.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate ;

	public void send(Object message) {
		amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
	}
}
