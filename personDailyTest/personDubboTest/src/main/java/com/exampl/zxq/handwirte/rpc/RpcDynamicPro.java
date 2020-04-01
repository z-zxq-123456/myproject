package com.exampl.zxq.handwirte.rpc;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 23:21
 **/
@Component
public class RpcDynamicPro implements InvocationHandler {


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String requestJson = objectToJson(method, args);
        Socket socket = new Socket("127.0.0.1",1000);
        socket.setSoTimeout(10000);
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printStream.println(requestJson);
        Response response;
        String responsJson = bufferedReader.readLine();
        response = fromJson(responsJson);
        if (socket != null){
            socket.close();
        }
        return response.getResult();
    }

    private String objectToJson(Method method,Object[] args){
        Request request = new Request();
        String methodName = method.getName();
        Class<?> [] paramterTypes = method.getParameterTypes();
        String className = method.getDeclaringClass().getName();
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParamters(paramterTypes);
        request.setParamters(args);
        return "";
    }

    private Response fromJson(String json){
        return new Response();
    }
}
