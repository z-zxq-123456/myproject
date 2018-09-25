package com.dcits.galaxy.example.exp1.api;

public interface DemoService {

	public String sayHello(String name);

	public User getUser(String name);

	public void setUser(User user);

	public User updateUser(int age, User user);

}