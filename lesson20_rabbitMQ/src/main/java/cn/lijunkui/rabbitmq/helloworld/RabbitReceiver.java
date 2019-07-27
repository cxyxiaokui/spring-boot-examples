package cn.lijunkui.rabbitmq.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitReceiver {
	private static Logger log = LoggerFactory.getLogger(RabbitReceiver.class);
	
	/**
	 * Direct 模式  交换机模式
	 * @param message
	 */
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void receive(String message) {
		log.info("receive message:"+message);
	}
	
	/**
	 * Direct 模式  交换机模式
	 * @param message
	 */
	@RabbitListener(queues=RabbitMQConfig.QUEUE)
	public void receive2(String message) {
		log.info("receive2 message:"+message);
	}
}
