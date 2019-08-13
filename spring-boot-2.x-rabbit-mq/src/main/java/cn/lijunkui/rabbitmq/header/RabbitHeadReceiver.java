package cn.lijunkui.rabbitmq.header;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitHeadReceiver {
	private static Logger log = LoggerFactory.getLogger(RabbitHeadReceiver.class);
	
	/**
	 * 
	 * @param message
	 */
	@RabbitListener(queues=RabbitMQHeaderConfig.HEAD_QUEUE)
	public void receive(byte[] message) {
		log.info("receive head message:"+new String(message));
	}
	
}
