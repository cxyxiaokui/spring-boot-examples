package cn.lijunkui.helloworld.springmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/mvc")
public class HelloWordMvcController {
	
	@RequestMapping("/helloworld")
	public String helloWord(){
		return "hello mvc!"; 
	}
}
