package com.dcits.galaxy.dal.demo1.entities;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = 4437449747750502756L;

	private Long sid;
	private String name;
	private String groupName;
	private int age;
	private String job;
	

	public Employee() {
	}

	public Employee(Long sid, String name, String groupName, int age, String job) {
		this.sid = sid;
		this.name = name;
		this.groupName = groupName;
		this.age = age;
		this.job = job;
	}
	
	public Long getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getAge() {
		return age;
	}

	public String getJob() {
		return job;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setJob(String job) {
		this.job = job;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [sid=");
		builder.append(sid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", age=");
		builder.append(age);
		builder.append(", job=");
		builder.append(job);
		builder.append("]");
		return builder.toString();
	}
	
}
