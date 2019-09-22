package cn.lijunkui.exception.customexception;

import cn.lijunkui.exception.message.CodeEnum;

public class JKException extends RuntimeException{
	private static final long serialVersionUID = -8201518085425482189L;
	 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	private Integer code;
	
	public JKException(Integer code,String message) {
		super(message);
		this.code = code;
	}
	public JKException(CodeEnum codeEnum) {
		super(codeEnum.getMsg());
		this.code = codeEnum.getCode();
	}
}
