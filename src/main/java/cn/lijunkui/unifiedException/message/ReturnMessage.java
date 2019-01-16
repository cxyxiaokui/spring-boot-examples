package cn.lijunkui.unifiedException.message;

public class ReturnMessage<T> {
	private Integer code;//错误码
	private String message;//提示信息
	private T date;//返回具体内容
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public ReturnMessage(Integer code, String message, T date) {
		super();
		this.code = code;
		this.message = message;
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getDate() {
		return date;
	}
	public void setDate(T date) {
		this.date = date;
	}
}
