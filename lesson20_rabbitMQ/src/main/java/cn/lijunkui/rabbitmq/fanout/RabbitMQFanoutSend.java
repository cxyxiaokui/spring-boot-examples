package cn.lijunkui.rabbitmq.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQFanoutSend {
	private static Logger log = LoggerFactory.getLogger(RabbitMQFanoutSend.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate ;

	public void sendFanout(Object message) {
		log.info("send topic message:"+message);
		amqpTemplate.convertAndSend(RabbitMQFanoutConfig.FANOUT_EXCHANGE, "", message);
		
	}
}
