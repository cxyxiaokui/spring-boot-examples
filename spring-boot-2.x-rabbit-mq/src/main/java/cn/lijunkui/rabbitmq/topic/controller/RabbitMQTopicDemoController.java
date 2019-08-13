package cn.lijunkui.rabbitmq.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lijunkui.rabbitmq.topic.RabbitMQTopicSend;

@RestController
@RequestMapping("/rabbitMQ/topic")
public class RabbitMQTopicDemoController {
	
	@Autowired
	private RabbitMQTopicSend send;
	
	@RequestMapping("/send")
	public String send(@RequestParam("msg") String message) {
		send.sendTopic(message);
		return "发送成功！";
	}
	
	@RequestMapping("/send/another")
	public String sendAnother(@RequestParam("msg") String message) {
		send.sendTopicAnother(message);
		return "发送成功！";
	}
	
	@RequestMapping("/send/all")
	public String sendAll(@RequestParam("msg") String message) {
		send.sendTopicAll(message);
		return "发送成功！";
	}
}
