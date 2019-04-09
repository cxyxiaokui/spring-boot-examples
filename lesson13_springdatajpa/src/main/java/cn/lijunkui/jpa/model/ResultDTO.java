package cn.lijunkui.jpa.model;

public class ResultDTO {
	private String address;
	private Long count;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ResultDTO(String address, Long count) {
		super();
		this.address = address;
		this.count = count;
	}
	
}
