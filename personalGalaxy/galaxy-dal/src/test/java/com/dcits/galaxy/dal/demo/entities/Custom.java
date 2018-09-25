package com.dcits.galaxy.dal.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Custom implements Serializable{

	private static final long serialVersionUID = 4421954258608423375L;
	
	private BigInteger customId;
	private String customName;
	private String customType;
	private Integer age;
	private BigDecimal amt;
	
	public BigInteger getCustomId() {
		return customId;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((amt == null) ? 0 : amt.hashCode());
		result = prime * result
				+ ((customId == null) ? 0 : customId.hashCode());
		result = prime * result
				+ ((customName == null) ? 0 : customName.hashCode());
		result = prime * result
				+ ((customType == null) ? 0 : customType.hashCode());
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
		Custom other = (Custom) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (amt == null) {
			if (other.amt != null)
				return false;
		} else if (!amt.equals(other.amt))
			return false;
		if (customId == null) {
			if (other.customId != null)
				return false;
		} else if (!customId.equals(other.customId))
			return false;
		if (customName == null) {
			if (other.customName != null)
				return false;
		} else if (!customName.equals(other.customName))
			return false;
		if (customType == null) {
			if (other.customType != null)
				return false;
		} else if (!customType.equals(other.customType))
			return false;
		return true;
	}



	public void setCustomId(BigInteger customId) {
		this.customId = customId;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Custom [customId=");
		builder.append(customId);
		builder.append(", customName=");
		builder.append(customName);
		builder.append(", customType=");
		builder.append(customType);
		builder.append(", age=");
		builder.append(age);
		builder.append(", amt=");
		builder.append(amt);
		builder.append("]");
		return builder.toString();
	}
}
