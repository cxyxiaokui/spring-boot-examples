package cn.lijunkui.layout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class LayoutController {
	@RequestMapping("/index")
	public String index(Model model) {
		return "/layout/index";
	}
	
	@RequestMapping("/index2")
	public String index2(Model model) {
		return "/layout/index2";
	}
}
