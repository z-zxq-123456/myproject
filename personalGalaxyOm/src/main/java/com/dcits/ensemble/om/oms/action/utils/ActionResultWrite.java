package com.dcits.ensemble.om.oms.action.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActionResultWrite {

	public static void setExitResult(PrintWriter printWriter){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Object>  infoList = new ArrayList<Object>();
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
		List<Object>  infoList = new ArrayList<Object>();
		result.put("total",0);
		result.put("rows", infoList);
		result.put("errorMsg", errorMsg);
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();			
	}
	
	public static void setReadResult(PrintWriter printWriter,Object infoList){
		System.out.println("infoList =" +infoList);
		String jsonStr = JSON.toJSONString(infoList);
		System.out.println("jsonStr =" +jsonStr);
		printWriter.print(jsonStr);		
		printWriter.flush();
		printWriter.close();			
	}
	
	
	
	public static void setPageReadResult(PrintWriter printWriter,List<?> infoList,int total){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infoList);
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
	
	public static void setMapResult(PrintWriter printWriter,Map<String, Object> result){		
		String jsonStr = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();		
	}
	
	
}
