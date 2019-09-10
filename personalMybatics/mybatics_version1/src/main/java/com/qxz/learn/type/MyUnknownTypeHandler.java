package com.qxz.learn.type;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUnknownTypeHandler implements MyTypeHandler {

    private MyTypeHandlerRegistry typeHandlerRegistry;

    public MyUnknownTypeHandler(MyTypeHandlerRegistry typeHandlerRegistry) {
        this.typeHandlerRegistry = typeHandlerRegistry;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, MyJdbcType jdbcType) throws SQLException {

    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
