package com.dcits.galaxy.cache.base;
import java.util.TreeMap;

/**
 * 封装redis key * 
 * @author tangxlf
 * @date   2014-12-15
 * 
 */
public class CKey {
  
	private String businessMark;//业务标志
	private TreeMap<String,String>  keyMap = new TreeMap<String,String>();//主键Map
	
	//构造器
	public CKey(String mark){
		this.businessMark = mark;
	}
	
	//设置主键
	public void setKey(String key,String value){
		keyMap.put(key, value);
	}
	
	//生成Ckey
	public String createCkey(){
		StringBuilder sb = new StringBuilder(businessMark);	
		Object[] values = keyMap.values().toArray();
		for(int i=0;i<values.length;i++){
			sb.append(":"+values[i]);
		}		
		return sb.toString();
	}
}
