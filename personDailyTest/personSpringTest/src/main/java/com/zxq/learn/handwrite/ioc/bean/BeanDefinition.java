package com.zxq.learn.handwrite.ioc.bean;

import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:16
 **/
public class BeanDefinition {


    private String name;
    private String className;
    private String interfaceName;
    private List<ContructArg> contructArgs;
    private List<PropertyArg> propertyArgs;


    public List<ContructArg> getContructArgs() {
        return contructArgs;
    }

    public void setContructArgs(List<ContructArg> contructArgs) {
        this.contructArgs = contructArgs;
    }

    public List<PropertyArg> getPropertyArgs() {
        return propertyArgs;
    }

    public void setPropertyArgs(List<PropertyArg> propertyArgs) {
        this.propertyArgs = propertyArgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
