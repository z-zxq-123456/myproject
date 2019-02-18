package com.zxq.learn.dynamtic.jdk;


import com.zxq.learn.dynamtic.MyOperation;
import com.zxq.learn.dynamtic.Opertaion;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/6/15
 */
public class Test {
    public static void main(String []args)
          throws Exception{

        Class proxy = Proxy.getProxyClass(Opertaion.class.getClassLoader(),Opertaion.class);

/*        byte[] byteClassProxy = ProxyGenerator.generateProxyClass("$proxy0",new Class[]{Opertaion.class});
        recordIntoFile(byteClassProxy);*/

        Constructor constructor = proxy.getConstructor(InvocationHandler.class);


        Opertaion opertaion = (Opertaion) constructor.newInstance(new MyInvocationHandler(new MyOperation()));
        opertaion.add(1,2);

        /**********************************************************/

        Opertaion opertaion1 =
                (Opertaion) Proxy.newProxyInstance(Opertaion.class.getClassLoader(),
                                                    new Class[]{Opertaion.class},
                                                    new MyInvocationHandler(new MyOperation()));
        opertaion1.add(1,2);
    }

    private static void recordIntoFile(byte[] classData){
        String fileName = "$proxy0.class";
        try(
                FileOutputStream outputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ){
            objectOutputStream.write(classData);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
