package cn.lijunkui.helloword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class ThymeleafHelloWrodController {
	
	@RequestMapping("/thymeleaf")
	public String helloThymeleaf(Model model){
		model.addAttribute("hello","hello Thymeleaf！");
		return "hello/index";
	}
}
