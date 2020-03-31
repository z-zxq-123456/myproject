package com.zxq.learn.handwrite.utils;

import com.zxq.learn.handwrite.aop.annotation.MyAspect;
import com.zxq.learn.handwrite.aop.factory.ProxyFactory;
import com.zxq.learn.handwrite.aop.proxy.AspectProxy;
import com.zxq.learn.handwrite.aop.proxy.MyProxy;

import java.util.*;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 22:54
 **/
public class AopHelper {

    static {
        Map<Class<?>, Set<Class<?>>> aspect = createAspectMap();
        Map<Class<?>, List<MyProxy>> targetMap = null;
        try {
            targetMap = createTargetMap(aspect);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Class<?>,List<MyProxy>> target:targetMap.entrySet()){
            Class<?> targetClass = target.getKey();
            List<MyProxy> proxyList = target.getValue();
            Object proxy = ProxyFactory.createProxy(targetClass,proxyList);
            BeanHelper.setBean(targetClass,proxy);
        }
    }

    private static Map<Class<?>,Set<Class<?>>> createAspectMap(){

        Map<Class<?>,Set<Class<?>>> aspectMap = new HashMap<>();
        addAspectMap(aspectMap);
        return aspectMap;
    }

    /**
     * 获取普通切面类
     * @param aspectMap
     */
    private static void addAspectMap(Map<Class<?>,Set<Class<?>>> aspectMap){

        Set<Class<?>> aspectClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> clz :aspectClassSet){
            if (clz.isAnnotationPresent(MyAspect.class)){
                MyAspect aspect = clz.getAnnotation(MyAspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                aspectMap.put(clz,targetClassSet);
            }
        }
    }

    private static Set<Class<?>> createTargetClassSet(MyAspect aspect){
       Set<Class<?>> targetSet = new HashSet<>();
       String packageName = aspect.pkg();
       String cls = aspect.cls();
       if (!packageName.equals("") && !cls.equals("")){
           try {
               targetSet.add(Class.forName(packageName+"."+cls));
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       }else if (!packageName.equals("")){
           targetSet.addAll(ClassUtils.getClasses(packageName));
       }
       return targetSet;
    }

    private static Map<Class<?>,List<MyProxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> aspectMap)
            throws IllegalAccessException, InstantiationException {

        Map<Class<?>, List<MyProxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : aspectMap.entrySet()) {
            //切面类
            Class<?> aspectClass = proxyEntry.getKey();
            //目标类集合
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            //创建目标类-切面对象列表的映射关系
            for (Class<?> targetClass : targetClassSet) {
                //切面对象
                MyProxy aspect = (MyProxy) aspectClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(aspect);
                } else {
                    //切面对象列表
                    List<MyProxy> aspectList = new ArrayList<MyProxy>();
                    aspectList.add(aspect);
                    targetMap.put(targetClass, aspectList);
                }
            }
        }
        return targetMap;
    }

}
