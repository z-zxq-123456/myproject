package com.dcits.ensemble.om.oms.manager.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 数据库查询结果* 
 * @author tangxlf
 * @date 2016-05-03
 */
public class DBSqlResultInfo {
	
	private String[] colsName = null;//字段名数组
	private List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();//查询结果
	private int totalNum = 0;//查询结果总数
	private long consumerTime = 0;//消费时间 单位毫秒	
	public String[] getColsName() {
		return colsName;
	}
	public void setColsName(String[] colsName) {
		this.colsName = colsName;
	}
	public List<Map<String, String>> getResultList() {
		return resultList;
	}
	public void setResultList(List<Map<String, String>> resultList) {
		this.resultList = resultList;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public long getConsumerTime() {
		return consumerTime;
	}
	public void setConsumerTime(long consumerTime) {
		this.consumerTime = consumerTime;
	}

	public String toString() {
		   System.out.println("colsName="+colsName+",resultList="+resultList+",totalNum="+totalNum+",consumerTime="+consumerTime);
		return  "";
	}
	
}
