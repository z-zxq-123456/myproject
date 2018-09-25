package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLogGroupHolderUtil.SqlLogGroupHolder;

/**
 * 用于生成记录sql及其对应的数据库信息
 * @author huangzhec
 *
 */
public class SqlLogConnection implements InvocationHandler {
	
	private Connection connection = null;
	
	private DataSource dataSource = null;
	
	private SqlLogGroupHolder sqlLogGroupHolder;
	
	public SqlLogConnection() {
	}

	public SqlLogConnection(Connection connection, DataSource dataSource, SqlLogGroupHolder sqlLogGroupHolder) {
		this.connection = connection;
		this.dataSource = dataSource;
		this.sqlLogGroupHolder = sqlLogGroupHolder;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if("prepareStatement".equals(method.getName())){
			String sourceSql = (String) args[0];
			if(isSelectSql(sourceSql)){
				return method.invoke(connection, args);
			}
			PreparedStatement stmt = (PreparedStatement) method.invoke(connection, args);
			SqlLogGroup group = sqlLogGroupHolder.getSqlLogGroup(dataSource);
			SqlLog sqlLog = new SqlLog(sourceSql);
			group.addSqlLog(sqlLog);
			return SqlLogPreparedStatement.newInstance(stmt, sqlLog);
		}
		return method.invoke(connection, args);
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public SqlLogGroupHolder getSqlLogGroupHolder() {
		return sqlLogGroupHolder;
	}

	public void setSqlLogGroupHolder(SqlLogGroupHolder sqlLogGroupHolder) {
		this.sqlLogGroupHolder = sqlLogGroupHolder;
	}

	private boolean isSelectSql(String sourceSql){
		boolean result = true;
		if(sourceSql!=null){
			String sql = sourceSql.toUpperCase().trim();
			if(!sql.startsWith("SELECT")){
				result = false;
			}
		}
		return result;
	}
	
	public static Connection newInstance(DataSource dataSource,Connection con) {
		SqlLogConnection handler = new SqlLogConnection(con, dataSource, SqlLogGroupHolderUtil.getSqlLogGroupHolder());
		ClassLoader cl = PreparedStatement.class.getClassLoader();
		return (Connection) Proxy.newProxyInstance(cl, new Class[] {Connection.class}, handler);
	}
	
}
