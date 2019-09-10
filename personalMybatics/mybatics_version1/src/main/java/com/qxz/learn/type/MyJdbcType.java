package com.qxz.learn.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public enum MyJdbcType {
    ARRAY(Types.ARRAY),
    BIT(Types.BIT),
    TINYINT(Types.TINYINT),
    SMALLINT(Types.SMALLINT),
    INTEGER(Types.INTEGER),
    BIGINT(Types.BIGINT),
    FLOAT(Types.FLOAT),
    REAL(Types.REAL),
    DOUBLE(Types.DOUBLE),
    NUMERIC(Types.NUMERIC),
    DECIMAL(Types.DECIMAL),
    CHAR(Types.CHAR),
    VARCHAR(Types.VARCHAR),
    LONGVARCHAR(Types.LONGVARCHAR),
    DATE(Types.DATE),
    TIME(Types.TIME),
    TIMESTAMP(Types.TIMESTAMP),
    BINARY(Types.BINARY),
    VARBINARY(Types.VARBINARY),
    LONGVARBINARY(Types.LONGVARBINARY),
    NULL(Types.NULL),
    OTHER(Types.OTHER),
    BLOB(Types.BLOB),
    CLOB(Types.CLOB),
    BOOLEAN(Types.BOOLEAN),
    CURSOR(-10), // Oracle
    UNDEFINED(Integer.MIN_VALUE + 1000),
    NVARCHAR(Types.NVARCHAR), // JDK6
    NCHAR(Types.NCHAR), // JDK6
    NCLOB(Types.NCLOB), // JDK6
    STRUCT(Types.STRUCT),
    JAVA_OBJECT(Types.JAVA_OBJECT),
    DISTINCT(Types.DISTINCT),
    REF(Types.REF),
    DATALINK(Types.DATALINK),
    ROWID(Types.ROWID), // JDK6
    LONGNVARCHAR(Types.LONGNVARCHAR), // JDK6
    SQLXML(Types.SQLXML), // JDK6
    DATETIMEOFFSET(-155); // SQL Server 2008

    public int type_code = 0;
    private static Map<Integer,MyJdbcType> codeLookup = new HashMap<>();

    static {
        for (MyJdbcType type:MyJdbcType.values()){
            codeLookup.put(type.type_code,type);
        }
    }

    MyJdbcType(int type_code) {
        this.type_code = type_code;
    }

    public static MyJdbcType forceType(int type_code){
        return codeLookup.get(type_code);
    }
}
