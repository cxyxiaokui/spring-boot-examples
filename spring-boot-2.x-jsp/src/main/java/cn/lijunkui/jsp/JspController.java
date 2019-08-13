package cn.lijunkui.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class JspController {
	@RequestMapping("/jsp")
	public String toJps(Model model) {
		model.addAttribute("welcome", "不建议使用jsp");
		return "welcome";
	}
}
