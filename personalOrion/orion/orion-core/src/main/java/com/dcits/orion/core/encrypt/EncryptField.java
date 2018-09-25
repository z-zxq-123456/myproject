package com.dcits.orion.core.encrypt;

/**
 * Created by lixbb on 2016/1/6.
 */
public class EncryptField {
    private String fieldName;
    private  String beanId;
    private String[] args;

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
