package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.type.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public class MyResultSetWrapper {

    private MyConfiguration configuration;
    private ResultSet resultSet;
    private final List<String> columnNames = new ArrayList<String>();
    private final List<String> classNames = new ArrayList<String>();
    private final List<MyJdbcType> jdbcTypes = new ArrayList<MyJdbcType>();
    private Map<String, List<String>> mappedColumnNamesMap = new HashMap<String, List<String>>();
    private Map<String, List<String>> unMappedColumnNamesMap = new HashMap<String, List<String>>();
    private final MyTypeHandlerRegistry typeHandlerRegistry;
    private final Map<String, Map<Class<?>, MyTypeHandler<?>>> typeHandlerMap = new HashMap<String, Map<Class<?>, MyTypeHandler<?>>>();



    public MyResultSetWrapper(MyConfiguration configuration, ResultSet resultSet) throws SQLException{
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        this.configuration = configuration;
        this.resultSet = resultSet;
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++){
            columnNames.add(configuration.isUseColumnLabel()?metaData.getColumnLabel(i):metaData.getColumnName(i));
            jdbcTypes.add(MyJdbcType.forceType(metaData.getColumnType(i)));
            classNames.add(metaData.getColumnClassName(i));
        }

    }

    public MyConfiguration getConfiguration() {
        return configuration;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public List<MyJdbcType> getJdbcTypes() {
        return jdbcTypes;
    }

    public Map<String, List<String>> getMappedColumnNamesMap() {
        return mappedColumnNamesMap;
    }

    public Map<String, List<String>> getUnMappedColumnNamesMap() {
        return unMappedColumnNamesMap;
    }

    public MyJdbcType getJdbcType(String columnName) {
        for (int i = 0 ; i < columnNames.size(); i++) {
            if (columnNames.get(i).equalsIgnoreCase(columnName)) {
                return jdbcTypes.get(i);
            }
        }
        return null;
    }

    public MyTypeHandler getTypeHandler(Class<?> propertyType,String columnName){
        MyTypeHandler<?> handler = null;
        Map<Class<?>, MyTypeHandler<?>> columnHandlers = typeHandlerMap.get(columnName);
        if (columnHandlers == null) {
            columnHandlers = new HashMap<Class<?>, MyTypeHandler<?>>();
            typeHandlerMap.put(columnName, columnHandlers);
        } else {
            handler = columnHandlers.get(propertyType);
        }
        if (handler == null) {
            MyJdbcType jdbcType = getJdbcType(columnName);
            handler = typeHandlerRegistry.getTypeHandler(propertyType, jdbcType);
            if (handler == null || handler instanceof MyUnknownTypeHandler) {
                final int index = columnNames.indexOf(columnName);
                final Class<?> javaType = resolveClass(classNames.get(index));
                if (javaType != null && jdbcType != null) {
                    handler = typeHandlerRegistry.getTypeHandler(javaType, jdbcType);
                } else if (javaType != null) {
                    handler = typeHandlerRegistry.getTypeHandler(javaType);
                } else if (jdbcType != null) {
                    handler = typeHandlerRegistry.getTypeHandler(jdbcType);
                }
            }
            if (handler == null || handler instanceof MyUnknownTypeHandler) {
                handler = new MyObjectTypeHandler();
            }
            columnHandlers.put(propertyType, handler);
        }
        return handler;
    }

    private <T>Class<T> resolveClass(String className){
        try {
            if (className != null) {
                return Resources.classForName(className);
            }
        } catch (ClassNotFoundException e) {
            // ignore
        }
        return null;
    }
}
