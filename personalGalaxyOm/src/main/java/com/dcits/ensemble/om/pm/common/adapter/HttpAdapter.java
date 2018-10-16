package com.dcits.ensemble.om.pm.common.adapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.adapter.Adapter;

public class HttpAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpAdapter.class);
    
    private static Map<String, Adapter<String>> adapterManager = new ConcurrentHashMap<>();
    
    private static Adapter<String> getAdapter(String url){
    	if(!adapterManager.containsKey(url)){
    		try {
                CmcOMAdapter httpAdapter = new CmcOMAdapter();
//    			httpAdapter.setUrl(url);
    			adapterManager.put(url, httpAdapter);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
    	}
		return adapterManager.get(url);
    }

    /**
     * http通讯接出
     *
     * @param str json格式字符串
     * @return
     */
    public static String doSubmit(String url, String str) {
        Adapter<String> adapter = getAdapter(url);
        return adapter.execute(str);
    }


    /**
     * http通讯接出
     *
     * @param str json格式字符串
     * @return
     */
    public static String doPostMsg(String url, String str) {
        Adapter<String> adapter = getAdapter(url);
        try {
        	return adapter.execute(str);
		} catch (Exception e) {
			logger.warn("fail send request to " + url, e);
			return "ERROR:" + e.getMessage();
		}
    }
    
    public static void addAdapter(String url, Adapter<String> adapter){
    	adapterManager.put(url, adapter);
    }
    
    public void setAdapters(Map<String, Adapter<String>> adapters){
    	adapterManager.putAll(adapters);
    }
}
