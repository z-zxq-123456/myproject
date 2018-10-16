package com.dcits.ensemble.om.oms.service.shshpoc;

import java.util.ArrayList;
import java.util.List;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndexRec;

/**
 * 指标查询结果* 
 * @author tangxlf
 * @date 2016-07-27
 */
public class AppIndexResultInfo {
	
	private List<ColInfo> colsName = new ArrayList<ColInfo>();//字段名List
	private List<EcmAppmoniIndexRec> rows = new ArrayList<EcmAppmoniIndexRec>();//查询结果
	private Integer  total = 0;
	public List<ColInfo> getColsName() {
		return colsName;
	}
	public void setColsName(List<ColInfo> colsName) {
		this.colsName = colsName;
	}
	public List<EcmAppmoniIndexRec> getRows() {
		return rows;
	}
	public void setRows(List<EcmAppmoniIndexRec> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public void addCol(String name,String code,String length){
		colsName.add(new ColInfo(name,code,length));
	}
	public class ColInfo{
		private String name;
		private String code;
		private String length;
		
		ColInfo(){}
		
		ColInfo(String name,String code,String length){
			this.name = name;
			this.code = code;
			this.length = length;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

		public String getLength() {
			return length;
		}

		public void setLength(String length) {
			this.length = length;
		}
		
	}
}
