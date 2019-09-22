package cn.lijunkui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.exception.message.ReturnMessage;
import cn.lijunkui.exception.message.ReturnMessageUtil;

@RestController
public class IndexController {
	
	@GetMapping("index")
	public ReturnMessage index() {
		return ReturnMessageUtil.sucess();
	}
}
