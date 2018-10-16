package com.dcits.ensemble.om.cmc.adapter;

import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pm.common.adapter.CmcOMAdapter;
import com.dcits.galaxy.adapter.Adapter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/26
 */
public class CmcHttpAdapter extends AbstractAdapter {

    private static Map<String, Adapter<String>> adapterManager = new ConcurrentHashMap<>();

    @Override
    String invokeInternalService(String inMsg, JSONObject request) {
        return null;
    }

    private static Adapter<String> getAdapter(String url){

        if(!adapterManager.containsKey(url)){
            CmcOMAdapter httpAdapter = new CmcOMAdapter();
            adapterManager.put(url, httpAdapter);
        }
        return adapterManager.get(url);
    }

    /**
     * http通讯接出
     *
     * @param str json格式字符串
     * @return
     */
    public static String doPostMsg(String url, String str) {
        Adapter<String> adapter = getAdapter(url);
        return adapter.execute(str);
    }

    public static void addAdapter(String url, Adapter<String> adapter){
        adapterManager.put(url, adapter);
    }

    public void setAdapters(Map<String, Adapter<String>> adapters){
        adapterManager.putAll(adapters);
    }
}
