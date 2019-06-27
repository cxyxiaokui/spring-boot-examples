package cn.lijunkui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SpringSessionTestController {

	@RequestMapping("/get")
	public String getSesseion(HttpServletRequest request){
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute("name");
		return "sessionId:"+session.getId()+" value:"+value;
	}
}
