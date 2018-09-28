package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.factory.MyObjectFactory;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.mapping.MyResultMap;
import com.qxz.learn.mapping.MyResultMapping;
import com.qxz.learn.parameter.MyParameterHandler;
import com.qxz.learn.result.MyDefaultResultHandler;
import com.qxz.learn.result.MyResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyDefaultResultSetHandler implements MyResultSetHandler {

    private final MyExecutor executor;
    private final MyConfiguration configuration;
    private final MyMappedStatement mappedStatement;
    private final MyParameterHandler parameterHandler;
    private final MyResultHandler resultHandler;
    private final MyBoundSql boundSql;
    private final MyObjectFactory objectFactory;


    private final Map<String, MyResultMapping> nextResultMaps = new HashMap<String, MyResultMapping>();


    public MyDefaultResultSetHandler(MyExecutor executor, MyMappedStatement mappedStatement, MyParameterHandler parameterHandler, MyResultHandler resultHandler, MyBoundSql boundSql) {
        this.executor = executor;
        this.configuration = mappedStatement.getConfiguration();
        this.mappedStatement = mappedStatement;
        this.parameterHandler = parameterHandler;
        this.resultHandler = resultHandler;
        this.boundSql = boundSql;
        this.objectFactory = configuration.getObjectFactory();
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {

        MyErrorContext.instance().activity("handler result").object(mappedStatement.getId());
        final List<Object> multipleResults = new ArrayList<>();
        int resultSetCount = 0;
        MyResultSetWrapper rsw = getFirstResultSet(stmt);
        List<MyResultMap> resultMaps = mappedStatement.getResultMaps();
        int resultMapCount = resultMaps.size();

        while (rsw != null && resultMapCount > resultSetCount) {
            MyResultMap resultMap = resultMaps.get(resultSetCount);
            handleResultSet(rsw, resultMap, multipleResults, null);
            rsw = getNextResultSet(stmt);
            cleanUpAfterHandlingResultSet();
            resultSetCount++;
        }

        String[] resultSets = mappedStatement.getResultSet();
        if (resultSets != null) {
            while (rsw != null && resultSetCount < resultSets.length) {
                MyResultMapping parentMapping = nextResultMaps.get(resultSets[resultSetCount]);
                if (parentMapping != null) {
                    String nestedResultMapId = parentMapping.getNestedResultMapId();
                    MyResultMap resultMap = configuration.getResultMap(nestedResultMapId);
                    handleResultSet(rsw, resultMap, null, parentMapping);
                }
                rsw = getNextResultSet(stmt);
                cleanUpAfterHandlingResultSet();
                resultSetCount++;
            }
        }

        return collapseSingleResultList(multipleResults);
    }

    private MyResultSetWrapper getFirstResultSet(Statement stmt)throws SQLException{
        ResultSet rs = stmt.getResultSet();
        while (rs == null){
            if (stmt.getMoreResults()){
                rs = stmt.getResultSet();
            }else {
                if (stmt.getUpdateCount() == -1){
                    break;
                }
            }
        }
        return rs != null ? new MyResultSetWrapper(configuration,rs):null;
    }

    private void handleResultSet(MyResultSetWrapper rsw, MyResultMap resultMap, List mulitResults, MyResultMapping parentMapping) {
        if (resultHandler == null){
            MyResultHandler myResultHandler = new MyDefaultResultHandler(objectFactory);
        }
    }

    private MyResultSetWrapper getNextResultSet(Statement statement){

    }

    private void cleanUpAfterHandlingResultSet(){

    }

    private <E> List<E> collapseSingleResultList(List list){

    }
}
