package com.dcits.ensemble.om.pf.tools.metadata;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 2015/6/16.
 */
public class JavaTypeMapping {

    public static class JdbcTypeInformation {
        public JdbcTypeInformation(String jdbcType, String clazzName, String javaType) {
            this.jdbcType = jdbcType;
            this.clazzName = clazzName;
            this.javaType = javaType;
        }

        private String jdbcType;
        private String javaType;
        private String clazzName;

        public String getClazzName() {
            return clazzName;
        }

        public void setClazzName(String clazzName) {
            this.clazzName = clazzName;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getJdbcType() {
            return jdbcType;
        }

        public void setJdbcType(String jdbcType) {
            this.jdbcType = jdbcType;
        }
    }

    private static Map<Integer, JdbcTypeInformation> typeMap = new HashMap<Integer, JdbcTypeInformation>();

    static {
        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.DECIMAL, new JdbcTypeInformation("DECIMAL", BigDecimal.class.getName(), BigDecimal.class.getSimpleName()));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", Long.class.getName(), Long.class.getSimpleName()));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", null, "byte[]"));
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", Boolean.class.getName(), Boolean.class.getSimpleName()));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", null, "byte[]"));
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", Boolean.class.getName(), Boolean.class.getSimpleName()));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", Date.class.getName(), Date.class.getSimpleName()));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", Double.class.getName(), Double.class.getSimpleName()));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", Double.class.getName(), Double.class.getSimpleName()));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", Integer.class.getName(), Integer.class.getSimpleName()));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Jdbc4Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", null, "byte[]"));
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Jdbc4Types.NCHAR, new JdbcTypeInformation("NCHAR", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Jdbc4Types.NCLOB, new JdbcTypeInformation("NCLOB", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Jdbc4Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", String.class.getName(), String.class.getSimpleName()));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", Float.class.getName(), Float.class.getSimpleName()));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", Short.class.getName(), Short.class.getSimpleName()));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", Object.class.getName(), Object.class.getSimpleName()));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", Date.class.getName(), Date.class.getSimpleName()));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", Date.class.getName(), Date.class.getSimpleName()));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", Byte.class.getName(), Byte.class.getSimpleName()));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", null, "byte[]"));
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", String.class.getName(), String.class.getSimpleName()));
    }

    public static String getJdbcType(Integer type) {
        if (typeMap.containsKey(type))
            return ((JdbcTypeInformation) typeMap.get(type)).getJdbcType();
        else
            return null;
    }

    public static String getJavaType(Integer type) {
        if (typeMap.containsKey(type))
            return ((JdbcTypeInformation) typeMap.get(type)).getJavaType();
        else
            return null;
    }

    public static String getJavaClazzName(Integer type) {
        if (typeMap.containsKey(type))
            return ((JdbcTypeInformation) typeMap.get(type)).getClazzName();
        else
            return null;
    }

    public class Jdbc4Types {
        // these are added manually until we move to JDK 6
        // TODO - remove after JDK 6 and use the java.sql.Types constants instead
        public static final int LONGNVARCHAR = -16;
        public static final int NVARCHAR = -9;
        public static final int NCHAR = -15;
        public static final int NCLOB = 2011;
    }

}
