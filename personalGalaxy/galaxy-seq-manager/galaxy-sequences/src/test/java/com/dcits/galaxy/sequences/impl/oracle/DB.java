/**
 * Title: Galaxy(Distributed service platform)
 * File: DB.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.sequences.impl.oracle;

import java.sql.*;

/**
 * <p>Created on 2017/5/4.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class DB {
    public static int get() {
        Connection conn = getConnection();
        if (conn == null) {
            return 0;
        }
        Statement stmt = null;
        ResultSet rset = null;
        try {
            String sql = "select TEST1.nextval id from dual";
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);
            if (rset.next()) {
                return rset.getInt("id");
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            closeDataBaseConnection(conn, stmt, rset);
        }
        return 0;
    }
    public static void main(String arg[]){
        System.out.println(DB.get());
    }

    private static void closeDataBaseConnection(Connection conn, Statement stmt, ResultSet rset) {
        if (null != rset) {
            try {
                rset.close();
            } catch (SQLException e) {
            }
        }
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public static Connection getConnection() {
        String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
        String jdbc_url = "jdbc:oracle:thin:@192.168.164.176:1521:GALAXY";
        try {
            Class.forName(jdbc_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbc_url, "ENSEMBLE_DEMO", "ENSEMBLE_DEMO");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
