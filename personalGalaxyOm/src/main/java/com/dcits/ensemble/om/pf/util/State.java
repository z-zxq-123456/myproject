package com.dcits.ensemble.om.pf.util;

/**
 * @author maruie
 * @date   2016-01-12
 * StorageManager
 * file 操作
 */
public interface State {
	
	public boolean isSuccess();
	
	public void putInfo(String name, String val);
	
	public void putInfo(String name, long val);
	
	public String toJSONString();

}
