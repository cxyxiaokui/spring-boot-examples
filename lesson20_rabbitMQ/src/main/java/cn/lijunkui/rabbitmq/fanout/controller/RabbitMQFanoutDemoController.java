package cn.lijunkui.rabbitmq.fanout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.rabbitmq.direct.RabbitMQSend;
import cn.lijunkui.rabbitmq.fanout.RabbitMQFanoutSend;
import cn.lijunkui.rabbitmq.topic.RabbitMQTopicSend;

@RestController
@RequestMapping("/rabbitMQ/fanout")
public class RabbitMQFanoutDemoController {
	
	@Autowired
	private RabbitMQFanoutSend send;
	
	@RequestMapping("/send")
	public String send(@RequestParam("msg") String message) {
		send.sendFanout(message);
		return "发送成功！";
	}
	
	
}
