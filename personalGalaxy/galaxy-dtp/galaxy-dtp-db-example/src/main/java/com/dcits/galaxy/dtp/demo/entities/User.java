package com.dcits.galaxy.dtp.demo.entities;

import java.io.Serializable;

/**
 * 用户
 * @author Yin.weicai
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -7945500297840187488L;

	private String userId = null;
	
	private String userName = null;
	
	private String remark = null;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "userId="+ userId +",userName=" + userName + ",remark=" + remark;
	}
}
