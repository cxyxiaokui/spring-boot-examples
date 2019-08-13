package cn.lijunkui.resttemplate.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private String name;
    private Integer age;
	private String addr;
	
    public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
}
