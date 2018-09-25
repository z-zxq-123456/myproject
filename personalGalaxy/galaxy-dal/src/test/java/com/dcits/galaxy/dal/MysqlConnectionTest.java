package com.dcits.galaxy.dal;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.MySQLConnection;

import junit.framework.TestCase;

public class MysqlConnectionTest extends TestCase {
	
	private String url = "jdbc:mysql://127.0.0.1:3306/benchmark_db1";
	private String user = "root";
	private String passwd = "1111";
	
	public void testTimeout(){
		try(Connection conn = DriverManager.getConnection(url, user, passwd)){
			conn.setAutoCommit(false);
			MySQLConnection myConn = (MySQLConnection) conn;
			boolean isInteractiveClient = myConn.getInteractiveClient();
			long serverTimeoutSeconds = -1;
			String serverTimeoutSecondsStr = null;

			if (isInteractiveClient) {
				serverTimeoutSecondsStr = myConn.getServerVariable("interactive_timeout");
			} else {
				serverTimeoutSecondsStr = myConn.getServerVariable("wait_timeout");
			}

			if (serverTimeoutSecondsStr != null) {
				try {
					serverTimeoutSeconds = Long.parseLong(serverTimeoutSecondsStr);
				} catch (NumberFormatException nfe) {
					serverTimeoutSeconds = 0;
				}
			}
			
			System.out.println(isInteractiveClient);
			System.out.println(serverTimeoutSeconds);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
