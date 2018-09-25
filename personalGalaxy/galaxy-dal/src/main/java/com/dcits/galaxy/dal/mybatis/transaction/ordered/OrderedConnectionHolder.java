package com.dcits.galaxy.dal.mybatis.transaction.ordered;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedHashSet;

import javax.sql.DataSource;

import com.dcits.galaxy.dal.mybatis.parser.SQLParser;

public class OrderedConnectionHolder {
	
	private LinkedHashSet<DataSource> order  = new LinkedHashSet<>();
	
	public Connection getOrderedConnection(DataSource dataSource, Connection connection) {
		ClassLoader loader = connection.getClass().getClassLoader();
		Class<?>[] interfaces = new Class[] { Connection.class };

		return (Connection) Proxy.newProxyInstance(loader, interfaces,	new OrderedConnection(dataSource, connection, this));
	}
	
	public void addOrder(DataSource ds) {
		order.add(ds);
	}
	
	public LinkedHashSet<DataSource> getOrder(){
		return order;
	}
	
	public boolean exsit(DataSource dataSource){
		return order.contains(dataSource);
	}
	
	private static class OrderedConnection implements InvocationHandler {
		
		private DataSource dataSource;
		private Connection connection;
		private OrderedConnectionHolder holder;
		
		private OrderedConnection(DataSource dataSource, Connection connection, OrderedConnectionHolder holder) {
			this.dataSource = dataSource;
			this.connection = connection;
			this.holder = holder;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if(!holder.exsit(dataSource)){
				if(method.getName().equals("prepareStatement")){
					String sql = (String) args[0];
					if(isUpdateStatement(sql)){
						holder.addOrder(dataSource);
					}
				}
			}
			return method.invoke(connection, args);
		}
		
		private boolean isUpdateStatement(String sql){
			sql = sql.trim().toUpperCase();
			if(sql.isEmpty() || sql.startsWith(SQLParser.Const_Select)){
				return false;
			} else {
				return true;
			}
		}
	}
}
