package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.type.MyJdbcType;
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


    public MyResultSetWrapper(MyConfiguration configuration, ResultSet resultSet) throws SQLException{
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
}
