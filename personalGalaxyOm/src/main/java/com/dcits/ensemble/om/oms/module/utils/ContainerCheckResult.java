package com.dcits.ensemble.om.oms.module.utils;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * ContainerCheckResult* 
 * 容器检查返回结果
 * @author tangxlf
 * @date 2015-11-13
 */
public class ContainerCheckResult extends AbstractBean {
    // serialVersionUID
    private static final long serialVersionUID = 1L; 
    
	private   String    checkStatus;      //检查状态
	private   String    checkStatusName;  //检查状态名
    private   StringBuilder    messages = new StringBuilder();          //检查返回消息  
    private   int       resultNum = 0; //0成功  1失败
	
	public int getResultNum() {
		return resultNum;
	}
	public void addResultNum(int resultNum) {
		this.resultNum = this.resultNum+resultNum;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCheckStatusName() {
		return checkStatusName;
	}
	public void setCheckStatusName(String checkStatusName) {
		this.checkStatusName = checkStatusName;
	}
	public String getMessage() {
		return messages.toString();
	}
	public void addMessage(String message) {
		messages.append(message+"|");
	}
}
