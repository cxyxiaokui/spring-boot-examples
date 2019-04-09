package cn.lijunkui.jpa.model;

public class UserParam {
	
	private String name;
	private String address;
	private Integer age;
	private Integer maxage;
	private Integer minage;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMaxage() {
		return maxage;
	}
	public void setMaxage(Integer maxage) {
		this.maxage = maxage;
	}
	public Integer getMinage() {
		return minage;
	}
	public void setMinage(Integer minage) {
		this.minage = minage;
	}

	
}
