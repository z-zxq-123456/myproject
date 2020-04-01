package com.exampl.zxq.handwirte.rpc.Server;

import com.exampl.zxq.handwirte.rpc.Request;
import com.exampl.zxq.handwirte.rpc.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 11:16
 **/
@Component
public class InitConfig {

    @Autowired
    ApplicationContext context;

    public static final   Map<Class<?>,Object> rpcMap = new HashMap<>();

    public void run(){

        Map<String ,Object> beans = context.getBeansWithAnnotation(Service.class);
        for (Object b:beans.values()){

            Class<?> clz = b.getClass();
            Class<?>[] interfaces = clz.getInterfaces();
            for (Class inter:interfaces){
                rpcMap.put(inter,b);
            }
        }
    }

    public Response invokeMethod(Request request){

        String className = request.getClassName();
        String methodName = request.getMethodName();
        Object[] parameters = request.getParamters();
        Class<?>[] parameTypes = request.getParamTypes();
        Object o = InitConfig.rpcMap.get(className);
        Response response = new Response();
        try {
            Method method = o.getClass().getDeclaredMethod(methodName, parameTypes);
            Object invokeMethod = method.invoke(o, parameters);
            response.setResult(invokeMethod);
        } catch (NoSuchMethodException e) {
//            log.info("没有找到"+methodName);
        } catch (IllegalAccessException e) {
//            log.info("执行错误"+parameters);
        } catch (InvocationTargetException e) {
//            log.info("执行错误"+parameters);
        }
        return response;
    }


}
