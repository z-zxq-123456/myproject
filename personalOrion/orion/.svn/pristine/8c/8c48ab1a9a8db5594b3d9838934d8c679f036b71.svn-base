package com.dcits.orion.base.file;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.api.IFileParse;
import com.dcits.orion.api.IParseString;
import com.dcits.orion.api.model.FileData;
import com.dcits.orion.base.model.Field;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/3/9.
 */
public abstract class AbstractFileParse implements IFileParse {
    protected void setValue(Object o, Field field, String strValue) {
        if ("Y".equals(field.getTrim())) {
            if (strValue != null)
                strValue = strValue.trim();
        }
        //字段非空检查
        if ("N".equals(field.getIsNull()))
        {
            if (StringUtils.isBlank(strValue)) {
                String fieldDesc=null;
                if (!StringUtils.isBlank(field.getDesc()))
                    fieldDesc = field.getDesc();
                else fieldDesc = field.getName();
                throw new GalaxyException(fieldDesc + "不能为空");
            }

        }
        if (o instanceof Map) {
            if ("String".equals(field.getType())) {
                ((Map) o).put(field.getName(), strValue);
            } else {
                IParseString iParseString = (IParseString) SpringApplicationContext.getContext().getBean(field.getParseBeanId());
                Class clazz = getClazz(field.getType());
                if (clazz == null) {
                    try {
                        clazz = ClassLoaderUtils.loadClass(field.getType());
                    } catch (ClassNotFoundException e) {
                        throw new GalaxyException("类型不存在");
                    }
                }
                ((Map) o).put(field.getName(), iParseString.parse(clazz, strValue));
            }
        } else if (o instanceof AbstractBean) {
            try {
                IParseString iParseString = (IParseString) SpringApplicationContext.getContext().getBean(field.getParseBeanId());
                Object value = iParseString.parse(((AbstractBean) o).fieldType(field.getName()), strValue);
                ((AbstractBean) o).writeValue(field.getName(), value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new GalaxyException("clazz必须为Map或AbstractBean");
        }
    }

    //获取字段对应的STRING
    protected String getValue(Object object, Field field) {
        Object value = null;
        if (object instanceof Map) {
            value = ((Map) object).get(field.getName());
        } else if (object instanceof AbstractBean) {
            try {
                value = ((AbstractBean) object).readValue(field.getName());
            } catch (Exception e) {
                throw new GalaxyException(e);
            }
        }
        if (value == null) {
            return "";
        } else if (value instanceof String)
            return (String) value;
        else {
            IParseString iParseString = (IParseString) SpringApplicationContext.getContext().getBean(field.getParseBeanId());
            return iParseString.getString(value);
        }
    }

    protected Class getClazz(String clazz) {
        switch (clazz) {
            case "String":
                return String.class;
            case "string":
                return String.class;
            case "double":
                return Double.TYPE;
            case "Double":
                return Double.class;
            case "Long":
                return Long.class;
            case "long":
                return Long.TYPE;
            case "BigDecimal":
                return BigDecimal.class;
            case "BigInteger":
                return BigInteger.class;
            case "Float":
                return Float.class;
            case "float":
                return Float.TYPE;
            case "int":
                return Integer.TYPE;
            case "Integer":
                return Integer.class;
            case "Short":
                return Short.class;
            case "short":
                return Short.TYPE;
            case "Boolean":
                return Boolean.class;
            case "boolean":
                return Boolean.TYPE;
            case "Byte":
                return Byte.class;
            case "byte":
                return Byte.TYPE;
        }
        return null;
    }

    protected Object newBean(String clazzName) {
        Object o;
        try {
            Class clazz = ClassLoaderUtils.loadClass(clazzName);
            if (clazz == Map.class) {
                clazz = HashMap.class;
            }
            try {
                o = clazz.newInstance();
            } catch (Exception e) {
                throw new GalaxyException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new GalaxyException(clazzName + "类不存在");
        }
        return o;
    }

    /**
     * 写指定格式文件,只写body
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param body
     */
    @Override
    public void writeBody(String path, String fileName, String convertName, List body) {
        FileData fileData = new FileData();
        fileData.setBodyData(body);
        mkdir(path);
        writeFile(path, fileName, convertName, fileData);
    }

    @Override
    public void writeBody(String path, String fileName, String convertName, List body, boolean append) {
        FileData fileData = new FileData();
        fileData.setBodyData(body);
        mkdir(path);
        writeFile(path,fileName,convertName,fileData,append);
    }

    /**
     * 写文件头
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param head
     */
    @Override
    public void writeHead(String path, String fileName, String convertName, Object head) {
        FileData fileData = new FileData();
        fileData.setHeadData(head);
        mkdir(path);
        writeFile(path, fileName, convertName, fileData);

    }

    /**
     * 写文件尾
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param tail
     */
    @Override
    public void writeTail(String path, String fileName, String convertName, Object tail) {
        FileData fileData = new FileData();
        fileData.setTailData(tail);
        mkdir(path);
        writeFile(path, fileName, convertName, fileData);

    }
    public void mkdir(String path)
    {
        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdir();
    }
}
