package com.dcits.orion.api.model;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * Created by Tim on 2015/11/17.
 */
public class EventNode extends AbstractBean {

    private static final long serialVersionUID = -4303384982362338853L;

    /**
     * 别名
     */
    private String alias;

    /**
     * 事件实现类
     */
    private Class<?> eventClazz;

    /**
     * 方法
     */
    private String method;

    /**
     * 参数类型
     */
    private Class<?>[] argumentsClazz;

    /**
     * 参数对象
     */
    private String[] argumentsSPEL;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Class<?> getEventClazz() {
        return eventClazz;
    }

    public void setEventClazz(Class<?> eventClazz) {
        this.eventClazz = eventClazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?>[] getArgumentsClazz() {
        return argumentsClazz;
    }

    public void setArgumentsClazz(Class<?>[] argumentsClazz) {
        this.argumentsClazz = argumentsClazz;
    }

    public String[] getArgumentsSPEL() {
        return argumentsSPEL;
    }

    public void setArgumentsSPEL(String[] argumentsSPEL) {
        this.argumentsSPEL = argumentsSPEL;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("eventClazz:").append(eventClazz.getName()).append("\n")
                .append("method:").append(method).append("\n")
                .append("argumentsClazz:[");

        for (int i = 0; i < argumentsClazz.length; i++) {
            if (i == 0)
                sb.append(argumentsClazz[i].getName());
            else
                sb.append("|").append(argumentsClazz[i].getName());

        }
        sb.append("]").append("\n")
                .append("argumentsSPEL:[");
        for (int i = 0; i < argumentsSPEL.length; i++) {
            if (i == 0)
                sb.append(argumentsSPEL[i]);
            else
                sb.append("|").append(argumentsSPEL[i]);

        }
        sb.append("]").append("\n")
                .append("alias:").append(alias);
        return sb.toString();
    }
}
