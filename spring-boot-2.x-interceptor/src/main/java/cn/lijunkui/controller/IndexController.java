package cn.lijunkui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index(ModelAndView modelAndView){
		
		return "index";
	}
}
