package com.dcits.ensemble.om.oms.module.utils;

import java.util.ArrayList;
import java.util.List;

public class PageData<T> {
	
	int  totalNum =0;
	
	List<T> list= new ArrayList<T>();

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
