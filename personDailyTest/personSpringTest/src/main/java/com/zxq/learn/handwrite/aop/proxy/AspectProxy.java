package com.zxq.learn.handwrite.aop.proxy;

import java.lang.reflect.Method;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 22:23
 **/
public abstract class AspectProxy implements MyProxy{


    @Override
    public Object doProxy(ProxyChain chain) throws Throwable {

        Class cls = chain.getTargetClass();
        Method method = chain.getTargetMethod();
        Object[] params = chain.getMethodParams();
        begin();
        Object result;
        try {
            if (intercept(method,params)){
                before(method,params);
                result = chain.doProxyChain();
                after(method,params);
            }else {
                result = chain.doProxyChain();
            }
        }catch (Exception e){
            error(method,params,e);
            throw e;
        }finally {
            end();
        }
        return result;
    }


    public void begin(){

    }

    /**
     * 切入点判断
     * @param method
     * @param params
     * @return
     */
    public boolean intercept(Method method,Object[] params) {
        return true;
    }

    public void before(Method method,Object[] params){

    }

    public void after(Method method,Object[] params){

    }

    public void error(Method method,Object[] params,Throwable t){

    }

    public void end(){

    }

}
