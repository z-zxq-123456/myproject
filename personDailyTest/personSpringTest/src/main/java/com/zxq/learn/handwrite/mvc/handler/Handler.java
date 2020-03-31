package com.zxq.learn.handwrite.mvc.handler;

import java.lang.reflect.Method;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 17:24
 **/
public class Handler {

    private Class<?> controller;

    private Method controlMethod;

    public Handler(Class<?> controller, Method controlMethod) {
        this.controller = controller;
        this.controlMethod = controlMethod;
    }

    public Class<?> getController() {
        return controller;
    }

    public void setController(Class<?> controller) {
        this.controller = controller;
    }

    public Method getControlMethod() {
        return controlMethod;
    }

    public void setControlMethod(Method controlMethod) {
        this.controlMethod = controlMethod;
    }
}
