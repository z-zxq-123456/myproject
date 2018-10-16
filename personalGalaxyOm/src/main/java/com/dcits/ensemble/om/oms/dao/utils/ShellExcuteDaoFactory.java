package com.dcits.ensemble.om.oms.dao.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * shell调用辅助类*
 * @author tangxlf
 * @date   2015-8-26
 */
public class ShellExcuteDaoFactory {

	private static final Map<String,ShellExcuteDao>  shellDaoMap = new HashMap<String,ShellExcuteDao>();
	
	public static synchronized ShellExcuteDao getShellDao(String host,String user,String pwd){
		String mapKey = host+"<>"+user;
		if(shellDaoMap.get(mapKey)!=null){
			return shellDaoMap.get(mapKey);
		}
		ShellExcuteDao  shellDao= new ShellExcuteDao(host,user,pwd);
		shellDaoMap.put(mapKey, shellDao);
		return shellDao;
	}
}
