package cn.lijunkui.rabbitmq.header.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lijunkui.rabbitmq.header.RabbitMQHeadSend;

@RestController
@RequestMapping("/rabbitMQ/head")
public class RabbitMQHeadDemoController {
	
	@Autowired
	private RabbitMQHeadSend send;
	
	@RequestMapping("/send")
	public String send(@RequestParam("msg") String message) {
		send.send(message);
		return "发送成功！";
	}
	
	
}
