package cn.lijunkui.unifiedException.customexception;

public class SbException extends RuntimeException{
	private static final long serialVersionUID = -8201518085425482189L;
	 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	private Integer code;
	
	public SbException(Integer code,String message) {
		super(message);
		this.code = code;
	}
}
