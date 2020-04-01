package com.zxq.learn.handwrite.mvc;

import com.zxq.learn.handwrite.mvc.annotation.RequestMapping;
import com.zxq.learn.handwrite.mvc.handler.Handler;
import com.zxq.learn.handwrite.mvc.model.Request;
import com.zxq.learn.handwrite.utils.ClassHelper;
import com.zxq.learn.handwrite.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 17:26
 **/
public class ControlHelper {

    private static final Map<Request, Handler> requestMapper = new HashMap<>();

    static {
        Set<Class<?>> contrlsClass = ClassHelper.getBeanClassSet();
        for (Class cls:contrlsClass){
            Method[] methods = cls.getDeclaredMethods();
            for (Method mth:methods){
                if (mth.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping mapping = mth.getAnnotation(RequestMapping.class);
                    String requestPth = mapping.value();
                    String requestMth = mapping.method();
                    Request request = new Request(requestMth,requestPth);
                    Handler handler = new Handler(cls,mth);
                    requestMapper.put(request,handler);
                }
            }
        }
    }

    public static Handler getHandler(String reqMethod,String requestPath){
        Request rq = new Request(reqMethod,requestPath);
        return requestMapper.get(rq);
    }
}
