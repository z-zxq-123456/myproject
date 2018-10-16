package com.dcits.ensemble.om.oms.module.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * shell执行结果*
 * @author tangxlf
 * @date   2015-8-6
 */
public class ShellResult {
	
    private List<String> outList = new ArrayList<String>();//SHELL执行返回成功结果
	
	private List<String> errList = new ArrayList<String>();//SHELL执行返回失败结果
	
	private Integer  exitStatus = null;//SHELL执行返回状态

	@Override
	public String toString() {
		return "ShellInfo [outList=" + outList + ", errList=" + errList
				+ ", exitStatus=" + exitStatus + "]";
	}

	public List<String> getOutList() {
		return outList;
	}

	public void setOutList(List<String> outList) {
		this.outList = outList;
	}

	public List<String> getErrList() {
		return errList;
	}

	public void setErrList(List<String> errList) {
		this.errList = errList;
	}

	public Integer getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(Integer exitStatus) {
		this.exitStatus = exitStatus;
	}
	
	public void addOutLine(String outLine){
		outList.add(outLine);
	}
	
	public void addErrLine(String errLine){
		outList.add(errLine);
	}

}
