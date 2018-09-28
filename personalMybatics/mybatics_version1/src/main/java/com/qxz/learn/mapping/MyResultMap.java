package com.qxz.learn.mapping;

import java.util.List;
import java.util.Set;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public class MyResultMap {

    private String id;
    private Class type;
    private Set<String> mappedColumns;
    private boolean hasNestedResultMaps;
    private boolean hasNestedQueries;
    private Boolean autoMapping;
    private List<MyResultMapping> resultMappings;
    private List<MyResultMapping> idResultMappings;
    private List<MyResultMapping> constructorResultMappings;
    private List<MyResultMapping> propertyResultMappings;


    public String getId() {
        return id;
    }

    public Class getType() {
        return type;
    }

    public Set<String> getMappedColumns() {
        return mappedColumns;
    }

    public boolean isHasNestedResultMaps() {
        return hasNestedResultMaps;
    }

    public boolean isHasNestedQueries() {
        return hasNestedQueries;
    }

    public Boolean getAutoMapping() {
        return autoMapping;
    }

    public List<MyResultMapping> getResultMappings() {
        return resultMappings;
    }

    public List<MyResultMapping> getIdResultMappings() {
        return idResultMappings;
    }

    public List<MyResultMapping> getConstructorResultMappings() {
        return constructorResultMappings;
    }

    public List<MyResultMapping> getPropertyResultMappings() {
        return propertyResultMappings;
    }
}
