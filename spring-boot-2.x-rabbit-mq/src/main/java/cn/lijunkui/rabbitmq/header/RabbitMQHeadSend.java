package cn.lijunkui.rabbitmq.header;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQHeadSend {
	private static Logger log = LoggerFactory.getLogger(RabbitMQHeadSend.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate ;

	public void send(String message) {
		MessageProperties properties = new MessageProperties();
		properties.setHeader("token", "abc123");
		Message amqpMessage = new Message(message.getBytes(), properties);
		amqpTemplate.convertAndSend(RabbitMQHeaderConfig.HEAD_EXCHAGE, "", amqpMessage);
	}
	

}
