package com.dcits.galaxy.dal;

public class Person {

	int id = 0;
	String name = null;
	String sex = null;
	String addr = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", addr=" + addr + "]";
	}
	public Person(String name, String sex, String addr) {
		super();
		this.name = name;
		this.sex = sex;
		this.addr = addr;
	}
	
	public Person() {
	}
	
	
}
