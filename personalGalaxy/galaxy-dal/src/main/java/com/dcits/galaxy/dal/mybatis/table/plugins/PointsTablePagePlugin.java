package com.dcits.galaxy.dal.mybatis.table.plugins;

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

import com.dcits.galaxy.dal.mybatis.plugins.PageSqlFactory;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.table.router.TableShardContext;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;

@Intercepts({@Signature(args = { Connection.class ,Integer.class}, method = "prepare", type = StatementHandler.class )} )
public class PointsTablePagePlugin implements Interceptor {
	
	/**
	 * 动态生成的代理类中的属性名
	 */
	private final static String Const_h = "h";
	
	/**
	 * Plugin类中被代理的对象对应的属性名
	 */
	private final static String Const_target = "target";
	
	/**
	 * RoutingStatementHandler类中被代理的StatementHandler实例对应的属性名
	 */
	private final static String Const_delegate = "delegate";
	
	/**
	 * BoundSql类中用来存储SQL参数的属性的属性名
	 */
	private final static String Const_boundSql_parameterObject = "delegate.boundSql.parameterObject";
	
	/**
	 * BoundSql类中用来存储SQL的属性的属性名
	 */
	private final static String Const_boundSql_sql = "sql";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
//		StatementHandler statementHandler = findStatementHandler(invocation.getTarget());
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

		
		BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaObject = MetaUtils.forObject(boundSql);
		String sql  = boundSql.getSql();
		
		//如果分表则替换实际操作的表名
		TableShard ts=TableShardContext.get();
		if(ts!=null){
			sql = sql.replaceAll("\\b" + ts.getLogicTable() + "\\b",
					ts.getExecuteTable());
			metaObject.setValue(Const_boundSql_sql, sql);
		}
		 
		RowArgs rowArgs = null;
		
		//获取分页参数
		Object p = metaObject.getValue( Const_boundSql_parameterObject );
		if( p != null && p instanceof ParameterWrapper){	
			ParameterWrapper paraWrapper = (ParameterWrapper)p;
			rowArgs = paraWrapper.getRowArgs();
			if( rowArgs != null ){
				Connection connection = (Connection) invocation.getArgs()[0];
				String databaseProductName = getDatabaseProductName(connection);
				String pageSql = PageSqlFactory.getPageSql(sql, rowArgs, databaseProductName);
				metaObject.setValue( Const_boundSql_sql, pageSql);
			}
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		
		if(TableShardContext.get() != null) {
			return Plugin.wrap(target, this);
		}
		
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
	
	@SuppressWarnings("unused")
	private StatementHandler findStatementHandler(Object object){
		MetaObject metaObject = MetaUtils.forObject(object);
		// 如果有多个插件，需要迭代找到真实的RoutingStatementHandler，而非代理
		while( metaObject.hasGetter(Const_h) ){
			object = metaObject.getValue(Const_h);
			metaObject = MetaUtils.forObject(object);
			if(metaObject.hasGetter(Const_target)){
				object = metaObject.getValue(Const_target);
				metaObject = MetaUtils.forObject(object);
			}
		}
		
		if(metaObject.hasGetter(Const_delegate)) {
			object = metaObject.getValue(Const_delegate);
		}
		return (StatementHandler) object;
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	public static String getDatabaseProductName(Connection connection) throws SQLException{
		return connection.getMetaData().getDatabaseProductName();
	}
}
