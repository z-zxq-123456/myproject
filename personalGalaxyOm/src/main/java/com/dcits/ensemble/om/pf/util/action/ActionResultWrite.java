package com.dcits.ensemble.om.pf.util.action;

import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangxlf
 * @date   2015-06-17
 * 
 */

public class ActionResultWrite {

	public static void setExitResult(PrintWriter printWriter){
		Map<String, Object> result = new HashMap<String, Object>();
		List  infoList = new ArrayList();
		result.put("total",0);
		result.put("rows", infoList);
		result.put("errorMsg","session过期或尚未登陆，请退出重新登陆!");
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();			
	}
	
	public static void setErrorResult(PrintWriter printWriter,String errorMsg){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errorMsg",errorMsg);
		result.put("data", "");
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();			
	}
	
	public static void setReadResult(PrintWriter printWriter,List infoList){		
		Map<String, Object> result = new HashMap<String, Object>();		
		result.put("data", infoList);
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);		
		printWriter.flush();
		printWriter.close();			
	}
	
	
	
	public static void setPageReadResult(PrintWriter printWriter,List infoList,String pageNum){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("option", pageNum);
		result.put("id", infoList.size());
		result.put("data", infoList);
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);		
		printWriter.flush();
		printWriter.close();			
	}
	
	public static void setSuccedResult(PrintWriter printWriter){		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success","success");
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();		
	}
}
