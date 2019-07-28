package cn.lijunkui.rabbitmq.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQFanoutReceiver {
	private static Logger log = LoggerFactory.getLogger(RabbitMQFanoutReceiver.class);
	
	@RabbitListener(queues=RabbitMQFanoutConfig.FANOUT_QUEUE)
	public void receiveFanout(String message) {
		log.info(" rabbitMQ Fanout receive "+RabbitMQFanoutConfig.FANOUT_QUEUE+" message:"+message);
	}
	@RabbitListener(queues=RabbitMQFanoutConfig.FANOUT_QUEUE2)
	public void receiveFanout2(String message) {
		log.info(" rabbitMQ Fanout receive "+RabbitMQFanoutConfig.FANOUT_QUEUE2+" message:"+message);
	}
}
