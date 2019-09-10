package com.qxz.learn.type;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MyTypeException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/30
 */
public abstract class MyBaseTypeHandler<T> extends MyTypeReference<T> implements MyTypeHandler<T>{

   private MyConfiguration configuration;

    public void setConfiguration(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, MyJdbcType jdbcType) throws SQLException {
        if (parameter == null){
            if (jdbcType == null){
                throw new MyTypeException("jdbc requires that the JdbcType must be specified for all nullable parameters");
            }
            try {
                ps.setNull(i, jdbcType.type_code);
            } catch (SQLException e) {
                throw new MyTypeException("Error setting null for parameter #" + i + " with JdbcType " + jdbcType + " . " +
                        "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. " +
                        "Cause: " + e);
            }
        }else {
            try {
                setNonNullParameter(ps, i, parameter, jdbcType);
            } catch (Exception e) {
                throw new MyTypeException("Error setting non null for parameter #" + i + " with JdbcType " + jdbcType + " . " +
                        "Try setting a different JdbcType for this parameter or a different configuration property. " +
                        "Cause: " + e);
            }
        }
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        T result;
        try {
            result = getNullableResult(rs, columnName);
        } catch (Exception e) {
            throw new RuntimeException("Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
        }
        if (rs.wasNull()) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public T getResult(ResultSet rs, int columnIndex) throws SQLException {
        T result;
        try {
            result = getNullableResult(rs, columnIndex);
        } catch (Exception e) {
            throw new RuntimeException("Error attempting to get column #" + columnIndex+ " from result set.  Cause: " + e, e);
        }
        if (rs.wasNull()) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    public abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, MyJdbcType jdbcType) throws SQLException;

    public abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

    public abstract T getNullableResult(ResultSet rs, int columnIndex) throws SQLException;

}
