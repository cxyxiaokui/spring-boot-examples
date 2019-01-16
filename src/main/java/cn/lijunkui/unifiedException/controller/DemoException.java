package cn.lijunkui.unifiedException.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.unifiedException.customexception.SbException;
@RestController
@RequestMapping("/error")
public class DemoException {
	@GetMapping(value = "custome")
	public void customException() {
		SbException sbe = new SbException(100, "这个是自定义异常！");
		throw sbe;
	}
	@GetMapping(value = "unknown")
	public void unknownException() {
		int i = 0;
		int b = 1/i;
	}
}
