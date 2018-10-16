package com.dcits.ensemble.om.oms.module.utils;

import com.dcits.ensemble.om.oms.module.middleware.ServerModel;

import java.util.Properties;

public class PropToModel {	
	public static ServerModel propPutModel(String server,Properties prop){
		ServerModel model = new ServerModel();
		model.setKeyNum(handleKeyNum(prop.getProperty("db0")));
		model.setServer(server);
		model.setUseMemory(prop.getProperty("used_memory_human"));
		return model;
	}
	
	private static String handleKeyNum(String dataStr){
		if(dataStr==null||dataStr.trim().equals("")){
			return "";
		}
		return dataStr.split(",")[0].split("=")[1];
	}

}
