package com.company.dbManage;

import com.company.tools.ConfigUtils;
import java.sql.*;
import java.util.Properties;

/**
 * 数据连接单元
 */
public class DbConn implements ConfigUtils.Listener {

    private static String urlStr = null;
    private static DbConn dbConn = new DbConn();
    private static Properties properties;

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            getConnInfo(false);
            conn = DriverManager.getConnection(properties.getProperty("db1.jdbcUrl"),properties.getProperty("db1.username"),properties.getProperty("db1.password"));
            ConfigUtils.listeners.add(dbConn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement prepare(Connection connection,String sql){
        PreparedStatement pstmt = null;
        try {
            if (connection!=null){
                pstmt = connection.prepareStatement(sql);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pstmt;
    }


    public static Statement getStatement(Connection conn){
        Statement stmt = null;
        if (conn!=null){
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stmt;
    }


    public static ResultSet getResultSet(Statement stmt ,String sql){
        ResultSet result = null;

        if (stmt!=null){
            try {
                result = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void close(Connection connection){
        if (connection!=null){

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Properties onChange() {
        System.out.println("-----db config has changed!------");
        return getConnInfo(true);
    }

//    private static String getConnInfo(boolean change){
//        if (urlStr == null || change){
//            Properties properties = ConfigUtils.getDbProperties();
//            StringBuilder sb = new StringBuilder();
//            sb.append(properties.getProperty("db1.jdbcUrl"))
//                    .append("&username=")
//                    .append(properties.getProperty("db1.username"))
//                    .append("&password=")
//                    .append(properties.getProperty("db1.password"));
//            urlStr = sb.toString();
//            System.out.println("sql = " +urlStr);
//            return sb.toString();
//        }
//      return urlStr;
//    }

    private static Properties getConnInfo(boolean change){
        if (properties == null || change){
           properties =  ConfigUtils.getDbProperties();
        }
        return properties;
    }


}
