package cn.lijunkui.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.Objects;
import cn.lijunkui.exception.message.CodeEnum;
import cn.lijunkui.exception.message.ReturnMessage;
import cn.lijunkui.exception.message.ReturnMessageUtil;
import cn.lijunkui.utils.JWTService;

@RestController
public class LoginController {
	Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private JWTService jwtService;
	
	@RequestMapping("/login")
	public ReturnMessage<Object> login(String loginName,String password,HttpServletRequest request) {
		if(valid(loginName,password)) {
			ReturnMessageUtil.error(CodeEnum.LOGINNAMEANDPWDERROR);
		}
		
		Map<String,String> userInfo = createUserInfoMap(loginName,password);
		String token = jwtService.createToken(userInfo, 1);
		
		ServletContext context = request.getServletContext();
		context.setAttribute(token, token);
		log.info("token:"+token);
		return ReturnMessageUtil.sucess(token);
	}
	
	@GetMapping("/logout")
	public ReturnMessage<?> logout(String token,String issuer,HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		context.removeAttribute(token);
		return ReturnMessageUtil.sucess();
	}
	

	private Map<String,String> createUserInfoMap(String loginName, String password) {
		Map<String,String> userInfo = new HashMap<String,String>();
		userInfo.put("loginName", loginName);
		userInfo.put("password", password);
		return userInfo;
	}

	private boolean valid(String loginName, String password) {
		if(Objects.equal("ljk", loginName) && Objects.equal("123456", password) ) {
			return true;
		}
		return false;
	}
	
	
} 
