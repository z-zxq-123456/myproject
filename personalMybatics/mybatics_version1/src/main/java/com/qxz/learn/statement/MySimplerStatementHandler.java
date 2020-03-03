package com.qxz.learn.statement;


import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.parameter.MyParameterHandler;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MySimplerStatementHandler implements MyStatementHandler {


    private MyMappedStatement mappedStatement;
    private static Pattern param_pattern = Pattern.compile("#\\{([^\\{\\}]*)\\}");

    public MySimplerStatementHandler(MyMappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public PreparedStatement prepare(Connection connection, Integer timeout) throws SQLException {

        String oriSql =  mappedStatement.getSql();
        if (oriSql != null){
            Matcher matcher = param_pattern.matcher(oriSql);
            return connection.prepareStatement(matcher.replaceAll("?"));
        }else {
            //exception sql is null
        }
        return null;
    }

    @Override
    public int update(PreparedStatement statement) throws SQLException {
       return statement.executeUpdate();
    }

    @Override
    public ResultSet query(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    @Override
    public MyBoundSql getBoundSql() {
        return null;
    }

    @Override
    public MyParameterHandler getParamenterHandler() {
        return null;
    }

    @Override
    public void parameterize(Statement statement) {

    }
}
