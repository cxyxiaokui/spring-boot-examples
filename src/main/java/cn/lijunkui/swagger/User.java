package cn.lijunkui.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="user对象",description="用户对象user")
public class User {
	@ApiModelProperty(value="用户名",name="name",example="xingguo")
    private String name;
	@ApiModelProperty(value="年龄1",name="age",required=true)
    private Integer age;
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
