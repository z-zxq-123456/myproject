package com.qxz.learn.type;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyObjectTypeHandler extends MyBaseTypeHandler<Object>{

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, MyJdbcType jdbcType) throws SQLException {
        ps.setObject(i,parameter);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getObject(columnIndex);
    }
}
