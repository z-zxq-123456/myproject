package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MyExecutorException;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.factory.MyObjectFactory;
import com.qxz.learn.mapping.*;
import com.qxz.learn.parameter.MyParameterHandler;
import com.qxz.learn.result.MyDefaultResultHandler;
import com.qxz.learn.result.MyResultHandler;
import com.qxz.learn.session.MyDefaultResultContext;
import com.qxz.learn.type.MyTypeHandler;
import com.qxz.learn.type.MyTypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    private final MyTypeHandlerRegistry typeHandlerRegistry;



    private final Map<String, MyResultMapping> nextResultMaps = new HashMap<String, MyResultMapping>();


    public MyDefaultResultSetHandler(MyExecutor executor, MyMappedStatement mappedStatement, MyParameterHandler parameterHandler, MyResultHandler resultHandler, MyBoundSql boundSql) {
        this.executor = executor;
        this.configuration = mappedStatement.getConfiguration();
        this.mappedStatement = mappedStatement;
        this.parameterHandler = parameterHandler;
        this.resultHandler = resultHandler;
        this.boundSql = boundSql;
        this.objectFactory = configuration.getObjectFactory();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    @Override
    public List<Object> handleResultSets(Statement stmt) throws SQLException {

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

    private void handleResultSet(MyResultSetWrapper rsw, MyResultMap resultMap, List<Object> mulitResults, MyResultMapping parentMapping) throws SQLException{
      try {
          if (parentMapping != null){
              handlerRowValueSingle(rsw,resultMap,null,parentMapping);
          }else {
              if (resultHandler == null){
                  MyResultHandler myResultHandler = new MyDefaultResultHandler(objectFactory);
                  if (resultMap.isHasNestedResultMaps()){
                      // TODO ignored nested resultMap
                  }else {
                      handlerRowValueSingle(rsw,resultMap,myResultHandler,parentMapping);
                  }
                  mulitResults.add(((MyDefaultResultHandler) myResultHandler).getResultList());
              }else {
                  handlerRowValueSingle(rsw,resultMap,resultHandler,parentMapping);
              }
          }
      }finally {
          try {
              if (rsw.getResultSet() != null) {
                  (rsw.getResultSet()).close();
              }
          } catch (SQLException e) { }
      }
    }

    private void handlerRowValueSingle(MyResultSetWrapper rsw,MyResultMap resultMap,MyResultHandler resultHandler,MyResultMapping resultMapping)throws SQLException{
        MyDefaultResultContext resultContext = new MyDefaultResultContext();
        //TODO ignore skip rows
        while(rsw.getResultSet().next()){
            MyResultMap discriminatedResultMap = resolveDiscriminatedResultMap(rsw.getResultSet(),resultMap,null);
            Object rowValue = getRowValue(rsw,discriminatedResultMap);
            storeObject(resultHandler, resultContext, rowValue, resultMapping, rsw.getResultSet());
        }
    }

    private void storeObject(MyResultHandler<?> resultHandler, MyDefaultResultContext<Object> resultContext, Object rowValue, MyResultMapping parentMapping, ResultSet rs){
        if (parentMapping != null){
            // TODO ignored multiple result set
        }else {
            resultContext.nextResultObject(rowValue);
            ((MyResultHandler<Object>)resultHandler).handleResult(resultContext);
        }
    }

    private Object getRowValue(MyResultSetWrapper rsw,MyResultMap resultMap){
        //final ResultLoaderMap lazyLoader = new ResultLoaderMap();TODO ignored ResultLoaderMap
        Object resultObject = createResultObject(rsw, resultMap, null);
        //TODO ignored type handler
        return resultObject;
    }

    private Object createResultObject(MyResultSetWrapper rsw,MyResultMap resultMap,String columnPrefix){
        final List<Class<?>> constructorArgsTypes = new ArrayList<>();
        final List<Object> constructorArgs = new ArrayList<>();
        final Object resultObject = createResultObject(rsw,resultMap,constructorArgsTypes,constructorArgs,columnPrefix);
        // TODO ignored type handler and configuration proxyFactory
        return resultObject;
    }

    private Object createResultObject(MyResultSetWrapper rsw,MyResultMap resultMap,List<Class<?>> constructorArgsTypes,List<Object> constructorArgs,String columnPrefix)throws SQLException{
        final Class<?> resultType = resultMap.getType();
        // TODO ignored metaClass
        final List<MyResultMapping> constructorMappings = resultMap.getConstructorResultMappings();
        if (hasTypeHandlerForResultObject(rsw,resultType)){
            return createPrimitiveResultObject(rsw, resultMap, columnPrefix);
        }else if (!constructorMappings.isEmpty()){
            return createParameterizedResultObject(rsw, resultType, constructorMappings, constructorArgsTypes, constructorArgs, columnPrefix);
        }else if (resultType.isInterface()){
            return objectFactory.create(resultType);
        }else if (shouldApplyAutomaticMappings(resultMap, false)){
            return createByConstructorSignature(rsw, resultType, constructorArgsTypes, constructorArgs, columnPrefix);
        }
        throw new MyExecutorException("invalidate type " +resultType);
    }

    private boolean hasTypeHandlerForResultObject(MyResultSetWrapper rsw,Class resultType){
        if (rsw.getColumnNames().size() == 1){
            return typeHandlerRegistry.hasTypeHandler(resultType, rsw.getJdbcType(rsw.getColumnNames().get(0)));
        }
        return typeHandlerRegistry.hasTypeHandler(resultType);
    }

    private Object createPrimitiveResultObject(MyResultSetWrapper rsw,MyResultMap resultMap,String columnPrefix)throws SQLException {
        final Class<?> resultType = resultMap.getType();
        final String columnName;
        if (!resultMap.getResultMappings().isEmpty()) {
            final List<MyResultMapping> resultMappingList = resultMap.getResultMappings();
            final MyResultMapping mapping = resultMappingList.get(0);
            columnName = prependPrefix(mapping.getColumn(), columnPrefix);
        } else {
            columnName = rsw.getColumnNames().get(0);
        }
        final MyTypeHandler<?> typeHandler = rsw.getTypeHandler(resultType, columnName);
        return typeHandler.getResult(rsw.getResultSet(), columnName);
    }

    private Object createParameterizedResultObject(MyResultSetWrapper rsw,Class resultType,List<MyResultMapping> constructorMappings,List<Class<?>> constructorArgsTypes,List<Object> constructorArgs, String columnPrefix){

    }

    private boolean shouldApplyAutomaticMappings(MyResultMap resultMap,boolean isNested){

    }

    private Object createByConstructorSignature(MyResultSetWrapper rsw,Class resultType,List<Class<?>> constructorArgsTypes,List<Object> constructorArgs, String columnPrefix){

    }

    private MyResultMap resolveDiscriminatedResultMap(ResultSet rs,MyResultMap resultMap,String columnPrefix)throws SQLException{
        Set<String> pastDiscriminators = new HashSet<String>();
        MyDiscriminator discriminator = resultMap.getDiscriminator();
        while (discriminator != null) {
            final Object value = getDiscriminatorValue(rs, discriminator, columnPrefix);
            final String discriminatedMapId = discriminator.getMapIdFor(String.valueOf(value));
            if (configuration.hasResultMap(discriminatedMapId)) {
                resultMap = configuration.getResultMap(discriminatedMapId);
                MyDiscriminator lastDiscriminator = discriminator;
                discriminator = resultMap.getDiscriminator();
                if (discriminator == lastDiscriminator || !pastDiscriminators.add(discriminatedMapId)) {
                    break;
                }
            } else {
                break;
            }
        }
        return resultMap;
    }

    private Object getDiscriminatorValue(ResultSet rs, MyDiscriminator discriminator, String columnPrefix) throws SQLException {
        final MyResultMapping resultMapping = discriminator.getResultMapping();
        final MyTypeHandler<?> typeHandler = resultMapping.getTypeHandler();
        return typeHandler.getResult(rs, prependPrefix(resultMapping.getColumn(), columnPrefix));
    }

    private String prependPrefix(String column,String prefix){
        if (column == null || column.length() == 0 || prefix == null || prefix.length() == 0){
            return column;
        }
        return prefix+column;
    }

    private MyResultSetWrapper getNextResultSet(Statement statement)throws SQLException{
        if (statement.getConnection().getMetaData().supportsMultipleResultSets()){
            if (!(!statement.getMoreResults() && (statement.getUpdateCount() == -1))){
                ResultSet rs = statement.getResultSet();
                return rs != null ? new MyResultSetWrapper(configuration,rs) : null;
            }
        }
        return null;
    }

    private void cleanUpAfterHandlingResultSet(){

    }

    private List<Object> collapseSingleResultList(List multipleResults){
        return multipleResults.size() == 1 ? (List<Object>) multipleResults.get(0) : multipleResults;
    }
}
