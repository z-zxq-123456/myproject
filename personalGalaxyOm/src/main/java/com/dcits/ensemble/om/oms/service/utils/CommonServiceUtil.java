package com.dcits.ensemble.om.oms.service.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dcits.ensemble.om.oms.dao.utils.OMSPkCreateDao;


@Component
public class CommonServiceUtil {
	
	@Resource
	OMSPkCreateDao  dao;
	/**
     * 生成主键�?大�??   
     * @return int  生成的主�?     
     */
	public synchronized int getMaxReqID(int max_req_no,String tabName,String pkColName) {
		if(max_req_no==0){		
		   Map<String,String>  sqlMap =new HashMap<String,String>();
		   sqlMap.put("TABNAME",tabName);
		   sqlMap.put("PKCOLNAME",pkColName);
		   String obj = dao.createPk(sqlMap);			
	       if(obj!=null){		    	
	    	   max_req_no = Integer.parseInt(""+obj) + 1;
	    	   return max_req_no;
	       }
		}
		return ++max_req_no;
    }
	
	/**
     * 生成带条件的字段�?大�??   
     * @return int  生成的主�?     
     */
	public synchronized int getMaxByCon(String tabName,String colName,String tabCon) {				
		   Map<String,String>  sqlMap =new HashMap<String,String>();
		   sqlMap.put("TABNAME",tabName);
		   sqlMap.put("MAXCOLNAME",colName);
		   sqlMap.put("TABCON",tabCon);
		   String obj = dao.createMaxByCon(sqlMap);
		   if(obj==null){
			   return 1;
		   }
		   return Integer.parseInt(obj)+1;
    }
}
