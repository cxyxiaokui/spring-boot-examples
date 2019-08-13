package cn.lijunkui.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")//如果@Table(name="user") == @Table name值是数据库的表名 不设置默认和类名相同
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412772340721966016L;
	@Id
	/**
	 *  IDENTITY：ID自增长的方式来自增主键字段，Oracle 不支持。 
	    AUTO： 默认选项,自动选择合适的策略 == @GeneratedValue 
		SEQUENCE：通过序列产生主键，MySql不支持 
		TABLE：通过表产生主键
	 */
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * name:数据库表和name匹配的数据库自定名称 默认和属性名一致 如果不相同可以通过name的值来进行匹配
	 * nullable：是否允许为null 默认是true
	 * unique:是否是唯一标识默认为 false
	 * length: 字段的大小，仅对 String有效
	 */
	@Column(name = "name",nullable = false,length=32)
	private String name;
	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String sex;
	private String address;

	public User() {

	}

	public User(Long id, String name, Integer age, String sex, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.sex = sex;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
