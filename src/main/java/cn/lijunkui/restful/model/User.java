package cn.lijunkui.restful.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class User {
	@NotEmpty(message="请输入您的名称！")
	private String name;
	@Max(value = 100, message = "年龄必须在20-100之间！")
	@Min(value= 20 ,message= "年龄必须在20-100之间！" )
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
