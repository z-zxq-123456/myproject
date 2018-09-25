package com.dcits.galaxy.dal.mybatis.plugins;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;

import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;

@Intercepts({@Signature(args = { Connection.class ,Integer.class}, method = "prepare", type = StatementHandler.class )} )
public class PagePlugin implements Interceptor {
	
	/**
	 * 动态生成的代理类中的属性名
	 */
	private final static String CONST_H = "h";
	
	/**
	 * Plugin类中被代理的对象对应的属性名
	 */
	private final static String CONST_TARGET = "target";
	
	/**
	 * RoutingStatementHandler类中被代理的StatementHandler实例对应的属性名
	 */
	private final static String CONST_DELEGATE = "delegate";
	
	/**
	 * BoundSql类中用来存储SQL的属性的属性名
	 */
	private final static String CONST_BOUNDSQL_SQL = "sql";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
//		StatementHandler statementHandler = findStatementHandler(invocation.getTarget());
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
		BoundSql boundSql = statementHandler.getBoundSql();
		String sql  = boundSql.getSql();
		
		//获取分页参数
		ParameterWrapper paraWrapper = (ParameterWrapper) boundSql.getParameterObject();
		RowArgs rowArgs = paraWrapper.getRowArgs();
		
		Connection connection = (Connection) invocation.getArgs()[0];
		String databaseProductName = getDatabaseProductName(connection);
		String pageSql = PageSqlFactory.getPageSql(sql, rowArgs, databaseProductName);
		MetaObject metaObject = MetaUtils.forObject(boundSql);
		metaObject.setValue( CONST_BOUNDSQL_SQL, pageSql);
		
		return invocation.proceed();
	}
	
	@SuppressWarnings("unused")
	private StatementHandler findStatementHandler(Object object){
		MetaObject metaObject = MetaUtils.forObject(object);
		// 如果有多个插件，需要迭代找到真实的RoutingStatementHandler，而非代理
		while( metaObject.hasGetter(CONST_H) ){
			object = metaObject.getValue(CONST_H);
			metaObject = MetaUtils.forObject(object);
			if(metaObject.hasGetter(CONST_TARGET)){
				object = metaObject.getValue(CONST_TARGET);
				metaObject = MetaUtils.forObject(object);
			}
		}
		
		if(metaObject.hasGetter(CONST_DELEGATE)) {
			object = metaObject.getValue(CONST_DELEGATE);
		}
		return (StatementHandler) object;
	}

	@Override
	public Object plugin(Object target) {
		
		if(!(target instanceof StatementHandler)) {
			return target;
		}
		
		StatementHandler statementHandler = (StatementHandler) target;
		Object p = statementHandler.getBoundSql().getParameterObject();
		if(p == null || !(p instanceof ParameterWrapper)){
			return target;
		}
		
		ParameterWrapper paraWrapper = (ParameterWrapper) p;
		RowArgs rowArgs = paraWrapper.getRowArgs();
		if(rowArgs == null) {
			return target;
		}
		
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	public static String getDatabaseProductName(Connection connection) throws SQLException{
		return connection.getMetaData().getDatabaseProductName();
	}
}
