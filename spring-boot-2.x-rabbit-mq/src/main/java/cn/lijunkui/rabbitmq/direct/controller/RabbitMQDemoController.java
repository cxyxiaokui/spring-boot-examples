package cn.lijunkui.rabbitmq.direct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.rabbitmq.direct.RabbitMQSend;

@RestController
@RequestMapping("/rabbitMQDemo")
public class RabbitMQDemoController {
	
	@Autowired
	private RabbitMQSend send;
	
	@RequestMapping("/helloworld")
	public String helloword(@RequestParam("msg") String msg) {
		send.send(msg);
		return "发送成功！";
	}
}
