package cn.lijunkui.unifiedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.lijunkui.unifiedException.customexception.SbException;
import cn.lijunkui.unifiedException.message.ReturnMessage;
import cn.lijunkui.unifiedException.message.ReturnMessageUtil;

@RestControllerAdvice
public class ExceptionHandle {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	@ExceptionHandler(value = Exception.class)
	//@ResponseBody
	public ReturnMessage<Object> handle(Exception exception) {
		if(exception instanceof SbException) {
			SbException sbexception = (SbException)exception;
			return ReturnMessageUtil.error(sbexception.getCode(), sbexception.getMessage());
		}else {
			logger.error("系统异常 {}",exception);
			return ReturnMessageUtil.error(-1, "未知异常"+exception.getMessage());
		}
	}
}
