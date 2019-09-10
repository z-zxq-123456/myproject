package com.zxq.learn.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/7/16
 */
public class ThreadLoacalManager {

public static ThreadLocal<Map<String,Object>> LOCAL = new ThreadLocal<Map<String,Object>>(){
    @Override
    protected Map<String, Object> initialValue() {
        return new HashMap();
    }
};

    public static <T> void setTranContext(T tranContext) {
        ((Map)LOCAL.get()).put("TRAN_CONTEXT", tranContext);
    }

    public static <T> T getTranContext() {
        return (T)((Map)LOCAL.get()).get("TRAN_CONTEXT");
    }


    public static void remove() {
        LOCAL.remove();
    }

}
