package com.exampl.zxq.handwirte.rpc;

import java.io.Serializable;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 22:39
 **/
public class Request implements Serializable {

    private static final long serialVersionUID = 3933918042687238629L;

    private String className;

    private String methodName;

    private Class<?> [] paramTypes;

    private Object[] paramters;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParamters() {
        return paramters;
    }

    public void setParamters(Object[] paramters) {
        this.paramters = paramters;
    }
}
