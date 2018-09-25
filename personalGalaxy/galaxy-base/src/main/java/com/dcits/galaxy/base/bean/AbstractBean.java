package com.dcits.galaxy.base.bean;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.BeanUtils;

import java.io.Serializable;

public abstract class AbstractBean implements Serializable, IBean {

    private static final long serialVersionUID = -5521527686619235184L;

    public String toString() {
        return BeanUtils.getString(this);
    }

    private String firstUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public Object readValue(String fieldName) throws Exception {
        if (fieldName.indexOf(".") < 0) {
            return BeanUtils.getProperty(this, fieldName);
        } else {
            String[] field = fieldName.split("\\.", 2);
            return BeanUtils.getFieldValue(BeanUtils.getProperty(this, field[0]), field[1]);
        }
    }

    public void writeValue(String fieldName, Object fieldValue) throws Exception {
        if (fieldName.indexOf(".") < 0) {
            BeanUtils.setProperty(this, fieldName, fieldValue);
        } else {
            String[] field = fieldName.split("\\.", 2);
            BeanUtils.setFieldValue(BeanUtils.getProperty(this, field[0]), field[1], fieldValue);
        }
    }

    public Class fieldType(String fieldName) throws Exception {
        return BeanUtils.getPropertyDescriptor(this, fieldName).getPropertyType();
    }

    public <T> T fieldTypeNewInstance(String fieldName)
    {
        try {
            return (T)BeanUtils.getPropertyDescriptor(this, fieldName).getPropertyType().newInstance();
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }

}
