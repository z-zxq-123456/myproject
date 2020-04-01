package com.example.zxq.dubbo.test;

import com.exampl.zxq.dubbo.adaptiveMethod.SimpleEx;
import com.exampl.zxq.dubbo.extension.ExtensionLoader;
import com.exampl.zxq.dubbo.logUtil.Log4JLog;
import com.exampl.zxq.dubbo.logUtil.LogAdapter;
import com.exampl.zxq.dubbo.proxytool.IMethod;
import com.exampl.zxq.dubbo.proxytool.PrintUtil;
import com.exampl.zxq.dubbo.proxytool.cglib.CGLibProxy;
import com.exampl.zxq.dubbo.proxytool.javasist.JavasistProxy;
import com.exampl.zxq.dubbo.proxytool.jdkProxy.JDKProxy;
import com.exampl.zxq.dubbo.proxytool.jdkProxy.JdkInvocationHandler;
import com.exampl.zxq.dubbo.utils.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理测试
 */
public class TestMain {


    public static void main(String[] args) {

        /*测试jdk代理*/
//        testJDKProxy();

        /*测试javasist代理*/
//        testJavasist();

        /*测试cglib代理*/
      //  testCgilb();

        testExtensionLoader2();
    }

    /**
     * testJDKProxy
     */
    private static void testJDKProxy(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");//生成代理类  路劲com/sun/proxy
        IMethod method = (IMethod)JDKProxy.createProxy(PrintUtil.class.getClassLoader(),new Class[]{IMethod.class}, new JdkInvocationHandler(new PrintUtil()));
        method.pringLog();
    }

    /**
     * testJavasist
     */
    private static void testJavasist(){
        JavasistProxy.javasistMake();
    }

    /**
     * testCgilb
     */
    private static void testCgilb(){
//        CGLibProxy.cglibProxy();
        CGLibProxy.cglibProxyFillter();

    }


    private static void testExtensionLoader1(){

        LogAdapter logAdapter = ExtensionLoader.getExtensionLoader(LogAdapter.class).getAdaptiveExtension();
        System.out.println(logAdapter instanceof Log4JLog);

        LogAdapter logAdapter2 = ExtensionLoader.getExtensionLoader(LogAdapter.class).getExtension("log4j");
        System.out.println(logAdapter2 instanceof Log4JLog);
    }


    private static void testExtensionLoader2(){

       ExtensionLoader extensionLoader  = ExtensionLoader.getExtensionLoader(SimpleEx.class);
       SimpleEx simpleEx = (SimpleEx)extensionLoader.getAdaptiveExtension();

       Map<String, String> map = new HashMap<String, String>();

       URL url = new URL("p1", "1.2.3.4", 1010, "path1", map);
       simpleEx.echo(url, "haha");
    }



}
