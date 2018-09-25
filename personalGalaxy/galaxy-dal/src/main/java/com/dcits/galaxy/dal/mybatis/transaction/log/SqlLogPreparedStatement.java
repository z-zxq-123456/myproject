package com.dcits.galaxy.dal.mybatis.transaction.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于生成记录参数的PreparedStatement动态代理
 * @author huangzhec
 *
 */

public class SqlLogPreparedStatement implements InvocationHandler {
	
	private static final Set<String> SET_METHODS = new HashSet<String>();
	private static final Set<String> EXECUTE_METHODS = new HashSet<String>();

	static {
		SET_METHODS.add("setString");
		SET_METHODS.add("setNString");
		SET_METHODS.add("setInt");
		SET_METHODS.add("setByte");
		SET_METHODS.add("setShort");
		SET_METHODS.add("setLong");
		SET_METHODS.add("setDouble");
		SET_METHODS.add("setFloat");
		SET_METHODS.add("setTimestamp");
		SET_METHODS.add("setDate");
		SET_METHODS.add("setTime");
		SET_METHODS.add("setArray");
		SET_METHODS.add("setBigDecimal");
		SET_METHODS.add("setAsciiStream");
		SET_METHODS.add("setBinaryStream");
		SET_METHODS.add("setBlob");
		SET_METHODS.add("setBoolean");
		SET_METHODS.add("setBytes");
		SET_METHODS.add("setCharacterStream");
		SET_METHODS.add("setNCharacterStream");
		SET_METHODS.add("setClob");
		SET_METHODS.add("setNClob");
		SET_METHODS.add("setObject");
		SET_METHODS.add("setNull");

		EXECUTE_METHODS.add("execute");
		EXECUTE_METHODS.add("executeUpdate");
//		EXECUTE_METHODS.add("executeQuery");
		EXECUTE_METHODS.add("addBatch");
	}

	private PreparedStatement statement;
	
	private SqlLog sqlLog;

	private SqlLogPreparedStatement(PreparedStatement stmt,SqlLog sqlInfo) {
		this.statement = stmt;
		this.sqlLog = sqlInfo;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		try {
			if (Object.class.equals(method.getDeclaringClass())) {
				return method.invoke(this, params);
			}
			if (EXECUTE_METHODS.contains(method.getName())) {
			    sqlLog.setCreateParameterValue(true);
				return method.invoke(statement, params);
			}
			if (SET_METHODS.contains(method.getName())) {
				if ("setNull".equals(method.getName())) {
					sqlLog.addValue(null);;
				} else {
					sqlLog.addValue(params[1]);
				}
				return method.invoke(statement, params);
			}
			return method.invoke(statement, params);
		} catch (Throwable t) {
			throw t;
		}
	}
	
	public static PreparedStatement newInstance(PreparedStatement stmt,SqlLog sqlLog) {
		InvocationHandler handler = new SqlLogPreparedStatement(stmt,sqlLog);
		ClassLoader cl = PreparedStatement.class.getClassLoader();
		return (PreparedStatement) Proxy.newProxyInstance(cl, new Class[] {PreparedStatement.class, CallableStatement.class }, handler);
	}

	public PreparedStatement getPreparedStatement() {
		return statement;
	}
	
}
