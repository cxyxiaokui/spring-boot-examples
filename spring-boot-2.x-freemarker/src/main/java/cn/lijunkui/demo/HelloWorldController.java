package cn.lijunkui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	@RequestMapping("/test")
    public String test(Model model){
		model.addAttribute("msg", "SpringBoot With Freemark hello world!");
        return "test/helloworld";
    }
}
