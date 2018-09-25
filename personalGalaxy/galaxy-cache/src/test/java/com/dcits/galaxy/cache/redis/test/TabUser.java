package com.dcits.galaxy.cache.redis.test;

import java.math.BigDecimal;
import java.util.Date;

public class TabUser{
	String userName;
	String userAge;
	String userId;
	BigDecimal userSalary;
	Date  userDate;
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(BigDecimal userSalary) {
		this.userSalary = userSalary;
	}
	
	
}
