package com.dcits.orion.batch.common.bean;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.dal.mybatis.plugins.PageSqlFactory;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lixbb on 2016/2/18.
 */

public abstract class AbstractTableObj extends HashMap implements Serializable {
    private static final long serialVersionUID = -5521527686619235184L;
    public final static String DatabaseProductName_MySQL = "MySQL";

    public final static String DatabaseProductName_Oracle = "Oracle";

    public final static String DatabaseProductName_SQLite = "SQLite";

    private Map<String, Object> fieldValue;
    private Map<String, Object> conditionValue;
    private List args = new ArrayList();

    public void clear() {
        if (fieldValue != null)
            fieldValue.clear();
        if (conditionValue != null)
            conditionValue.clear();
        if (args != null)
            args.clear();
        super.clear();
    }


    private String keyField;


    private TwoTuple range;


    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    public String getKeyField() {
        return this.keyField;
    }


    public void setRange(Object startKey, Object endKey) {
        range = new TwoTuple(startKey, endKey);
    }


    public List getArgs() {
        return args;
    }

    public void setArgs(List args) {
        this.args = args;
    }


    private String whereSql;


    public void setCondition(String fieldName, Object fieldValue) {
        if (conditionValue == null)
            conditionValue = new HashMap();
        conditionValue.put(fieldName, fieldValue);
    }

    public void setField(String fieldName, Object field) {
        if (fieldValue == null)
            fieldValue = new HashMap<>();
        fieldValue.put(fieldName, field);
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    private List records;


    public abstract String tableName();


    public String insertSql(List<String> columns) {

        args = new ArrayList();
        Collection<String> columnNames;

        if (columns == null || columns.size() == 0) {
            columnNames = fieldValue.keySet();
        } else {
            columnNames = columns;
        }
        StringBuffer tableSql = new StringBuffer();
        StringBuffer valueSql = new StringBuffer();
        String tableName = this.tableName();
        tableSql.append("insert into ").append(tableName).append("(");
        valueSql.append("values(");
        for (String fieldName : columnNames) {
            Object value = fieldValue.get(fieldName);
            if (value == null) {
                continue;
            }
            tableSql.append(fieldName).append(",");
            valueSql.append("?,");
            args.add(value);

        }
        tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
        valueSql.delete(valueSql.lastIndexOf(","), valueSql.lastIndexOf(",") + 1);
        return tableSql.append(") ").append(valueSql).append(")").toString();

    }

    public String insertSql() {
        return insertSql(null);
    }


    private Map<String, Object> beanToMap(Object obj) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(obj, new Object[0]);
                if (result != null) {
                    returnMap.put(JsonUtils.convertUpperCase(propertyName), result);
                }
            }
        }
        return returnMap;
    }

    private List getMapList(List list) {
        if (list == null || list.size() == 0)
            return list;
        Object first = list.get(0);
        if (first instanceof Map)
            return list;
        List<Map> mapList = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = null;
            try {
                map = beanToMap(object);
            } catch (Exception e) {
                throw new GalaxyException(e);
            }
            mapList.add(map);
        }
        return mapList;
    }

    public String batchDeleteSql() {
        List<Map> mapList = getMapList(records);
        Collection<String> conColumnNames = conditionValue.keySet();
        StringBuffer tableSql = new StringBuffer();
        tableSql.append("delete from ").append(this.tableName()).append(" where 1=1 ");
        for (String conFieldName : conColumnNames) {
            tableSql.append(" and ").append(conFieldName).append("=?");
        }
        this.args = new ArrayList();
        for (Map map : mapList) {
            List colValues = new ArrayList();
            for (String conFieldName : conColumnNames) {
                colValues.add(map.get(conFieldName));
            }
            this.args.add(colValues.toArray());
        }
        return tableSql.toString();
    }

    public String batchUpdateSql() {
        List<Map> mapList = getMapList(records);
        Collection<String> columnNames = fieldValue.keySet();
        Collection<String> conColumnNames = conditionValue.keySet();
        StringBuffer tableSql = new StringBuffer();
        tableSql.append("update ").append(this.tableName()).append(" set ");
        for (String fieldName : columnNames) {
            tableSql.append(fieldName).append("= ? ,");
        }
        tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
        tableSql.append(" where 1=1 ");
        for (String conFieldName : conColumnNames) {
            tableSql.append(" and ").append(conFieldName).append("=?");
        }
        this.args = new ArrayList();
        for (Map map : mapList) {
            List colValues = new ArrayList();
            for (String fieldName : columnNames) {
                colValues.add(map.get(fieldName));
            }
            for (String conFieldName : conColumnNames) {
                colValues.add(map.get(conFieldName));
            }
            this.args.add(colValues.toArray());
        }
        return tableSql.toString();
    }

    public String insertBatchSql(List<String> columns) {

        List<Map> mapList = getMapList(records);
        Collection<String> columnNames;
        if (columns == null || columns.size() == 0) {
            Map columnMap = new HashMap();
            for (Map map : mapList) {
                columnMap.putAll(map);
            }
            columnMap.remove("R");//oracle分段产生的R
            columnNames = columnMap.keySet();
        } else columnNames = columns;
        StringBuffer tableSql = new StringBuffer();
        String tableName = this.tableName();
        tableSql.append("insert into ").append(tableName);
        tableSql.append("(");
        for (String fieldName : columnNames) {
            tableSql.append(fieldName).append(",");
        }
        tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
        tableSql.append(") ");
        tableSql.append(" values(");
        int colSize = columnNames.size();
        for (int i = 0; i < colSize; i++) {
            if (i == colSize - 1)
                tableSql.append("?");
            else tableSql.append("?,");
        }
        tableSql.append(")");
        this.args = new ArrayList();
        for (Map map : mapList) {
            List colValues = new ArrayList();
            for (String fieldName : columnNames) {
                colValues.add(map.get(fieldName));
            }
            this.args.add(colValues.toArray());
        }
        return tableSql.toString();

    }

    public String getSplitByKeySql(String databaseProductName) {
        args = new ArrayList();
        StringBuffer inerSql = new StringBuffer();
        if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
            inerSql.append("select ").append(keyField).append(" from ").append(tableName());
        } else if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
            inerSql.append("select ").append(keyField).append(",@rownum:=@rownum+1 as rownum from ").append(tableName()).append(" ,(select @rownum := 0) tt");

        }

        String whereSql = getWhereSql();
        if (whereSql != null)
            inerSql.append(" ").append(whereSql);
        inerSql.append(" order by ").append(keyField);

        StringBuffer sql = new StringBuffer();
        if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
            sql.append("select  MIN (").append(keyField).append(") START_KEY, MAX (").append(keyField).append(") END_KEY FROM ( ")
                    .append(inerSql).append(") GROUP BY TRUNC ( (ROWNUM - 1) /?) ORDER BY START_KEY");

        } else if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
            sql.append("select  MIN(").append(keyField).append(") START_KEY, MAX(").append(keyField).append(") END_KEY FROM ( ")
                    .append(inerSql).append(" ) as temp GROUP BY FLOOR ( (temp.ROWNUM - 1) /?) ORDER BY START_KEY");

        }
        args.add(get("maxPerCount"));
        return sql.toString();
    }


    public String getRangeSql() {
        args = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(tableName());
        String whereSql = getWhereSql();
        if (whereSql != null)
            sql.append(" ").append(whereSql).append(" and ");
        else sql.append(" where ");
        sql.append(keyField).append(" between ? and ?");
        this.args.add(range.first);
        this.args.add(range.second);
        return sql.toString();

    }

    public String batchInsertSql(String databaseProductName, List<String> columns) {
        args = new ArrayList();
        List<Map> mapList = getMapList(records);
        if (mapList == null || mapList.size() == 0)
            return "";

        Collection<String> columnNames;
        if (columns == null || columns.size() == 0) {
            Map columnMap = new HashMap();
            for (Map map : mapList) {
                columnMap.putAll(map);
            }
            columnMap.remove("R");//oracle分段产生的R
            columnNames = columnMap.keySet();
        } else columnNames = columns;
        StringBuffer tableSql = new StringBuffer();
        StringBuffer valueSql = new StringBuffer();
        String tableName = this.tableName();
        tableSql.append("insert into ").append(tableName);
        if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
            valueSql.append(" values ");
        } else if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
            valueSql.append("  ");
        } else if (DatabaseProductName_SQLite.equalsIgnoreCase(databaseProductName)) {
            valueSql.append("values(");
        }


        if (columns == null || columns.size() == 0) {
            tableSql.append("(");
            for (String fieldName : columnNames) {
                tableSql.append(fieldName).append(",");
            }
            tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
            tableSql.append(") ");

        }

        for (int i = 0; i < mapList.size(); i++) {
            if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
                valueSql.append("(");
            } else if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
                valueSql.append("select ");
            } else if (DatabaseProductName_SQLite.equalsIgnoreCase(databaseProductName)) {
                valueSql.append("(");
            }


            Map map = mapList.get(i);
            for (String fieldName : columnNames) {
                String listFieldName = getListFieldName(i, fieldName);
                Object value = map.get(fieldName);

                if (value == null) {
                    valueSql.append("NULL,");

                } else {

                    valueSql.append("?,");
                    args.add(value);


                }
            }
            valueSql.delete(valueSql.lastIndexOf(","), valueSql.lastIndexOf(",") + 1);
            if (i < mapList.size() - 1) {
                if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append("),");
                } else if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append(" from dual union all ");
                } else if (DatabaseProductName_SQLite.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append("),");
                }

            } else {
                if (DatabaseProductName_MySQL.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append(")");
                } else if (DatabaseProductName_Oracle.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append(" from dual");
                } else if (DatabaseProductName_SQLite.equalsIgnoreCase(databaseProductName)) {
                    valueSql.append("),");
                }

            }


        }

        return tableSql.append(valueSql).toString();

    }

    public String batchInsertSql(String databaseProductName) {
        return batchInsertSql(databaseProductName, null);

    }

    private String getListFieldName(int index, String fieldName) {
        return "item" + index + "_" + fieldName;
    }

    public String updateSql() {

        args = new ArrayList();
        StringBuffer updateSql = new StringBuffer();
        updateSql.append("update ").append(tableName()).append(" set ");


        for (String fieldName : fieldValue.keySet()) {
            Object value = fieldValue.get(fieldName);
            if (value == null) {
                updateSql.append(fieldName).append("=NULL,");
            } else {
                updateSql.append(fieldName).append("=?,");
                args.add(value);
            }
        }
        updateSql.delete(updateSql.lastIndexOf(","), updateSql.lastIndexOf(",") + 1);
        String whereSql = getWhereSql();
        if (whereSql != null)
            updateSql.append(" ").append(whereSql);
        return updateSql.toString();
    }

    public String selectSqlByPage(String databaseProductName, RowArgs rowArgs) {
        args = new ArrayList();
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select * from ").append(tableName());
        String whereSql = getWhereSql();
        if (whereSql != null) {
            selectSql.append(" ").append(whereSql);
        } else {
            StringBuffer conSql = new StringBuffer();
            if (conditionValue != null && conditionValue.size() > 0) {
                conSql.append(" where 1 = 1 ");
                for (String fieldName : conditionValue.keySet()) {
                    Object value = conditionValue.get(fieldName);
                    if (value == null)
                        continue;
                    conSql.append(" and ").
                            append(fieldName).
                            append("=?");
                    args.add(value);
                }
            }
            selectSql.append(conSql);
        }
        return PageSqlFactory.getPageSql(selectSql.toString(), rowArgs, databaseProductName);
    }

    public String selectSql() {
        args = new ArrayList();
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select * from ").append(tableName());
        String whereSql = getWhereSql();
        if (whereSql != null) {
            selectSql.append(" ").append(whereSql);
        }
        return selectSql.toString();
    }

    public String selectCountSql() {
        args = new ArrayList();
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select count(1) ROW_COUNT from ").append(tableName());
        String whereSql = getWhereSql();
        if (whereSql != null) {
            selectSql.append(" ").append(whereSql);
        }
        return selectSql.toString();
    }

    public String deleteSql() {
        args = new ArrayList();

        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("delete from ").append(tableName());
        String whereSql = getWhereSql();
        if (whereSql != null) {
            deleteSql.append(" ").append(whereSql);
        }
        return deleteSql.toString();
    }


    public void setWhereSql(String whereSql) {

        if (whereSql != null && whereSql.trim().length() > 0) {
            this.whereSql = whereSql;

        }

    }

    private String getWhereSql() {
        if (whereSql != null && whereSql.trim().length() > 0) {
            TextSqlNode node = new TextSqlNode(whereSql);
            DynamicSqlSource s = new DynamicSqlSource(new Configuration(), node);
            BoundSql boundSql = s.getBoundSql(this);
            whereSql = boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterMappings != null && !parameterMappings.isEmpty()) {
                List argsArray = new ArrayList();
                for (ParameterMapping parameterMapping : parameterMappings) {
                    args.add(get(parameterMapping.getProperty()));
                }
            }
            return whereSql;
        } else {
            StringBuffer conSql = new StringBuffer();
            if (conditionValue != null && conditionValue.size() > 0) {
                if (args == null)
                    args = new ArrayList();
                conSql.append(" where 1 = 1 ");
                for (String fieldName : conditionValue.keySet()) {
                    Object value = conditionValue.get(fieldName);
                    if (value == null)
                        continue;
                    conSql.append(" and ").
                            append(fieldName).
                            append("=?");
                    args.add(value);
                }
                return conSql.toString();
            } else
                return null;
        }

    }

    public Map getConditionValue() {
        return conditionValue;
    }


    public void setConditionValue(Map conditionValue) {
        this.conditionValue = conditionValue;
    }

    public Map getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Map fieldValue) {
        this.fieldValue = fieldValue;
    }
}
