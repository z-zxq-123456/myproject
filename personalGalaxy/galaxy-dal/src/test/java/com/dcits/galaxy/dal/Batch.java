package com.dcits.galaxy.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch {

    private static String sql = "insert into \"person\" values(?,?,?,?)";

    public static void main(String[] args) {
        long start = System.nanoTime();
        try {
            batch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("batch: " + Double.toString((end - start) / 1000000000.0) + " 秒!");
    }

    public static void noBatch() throws SQLException {
        Connection conn = getConnection(false);
        Person person = new Person();
        person.setSex("a");
        person.setAddr("b");
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < 500; i++) {
            person.setId(i);
            person.setName("a" + i);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getSex());
            ps.setString(4, person.getAddr());
            ps.execute();
//			ps.close();
        }
        // modify for sonar
        ps.close();
        conn.commit();
        conn.close();
    }

    public static void batch() throws SQLException {
        Connection conn = getConnection(false);
        PreparedStatement ps = conn.prepareStatement(sql);
        Person person = new Person();
        person.setSex("a");
        person.setAddr("b");
        for (int i = 1; i <= 100000; i++) {
            person.setId(i);
            person.setName("a" + i);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getSex());
            ps.setString(4, person.getAddr());
            ps.addBatch();
            if (i % 100 == 0)
                ps.executeBatch();
        }
        ps.close();
        conn.commit();
        conn.close();
    }

    public static void forEach() throws SQLException {
        Connection conn = getConnection(false);
        Statement stat = conn.createStatement();
        Person person = new Person();
        person.setSex("a");
        person.setAddr("b");
        StringBuilder sb = null;
        for (int i = 1; i <= 100000; i++) {
            if (sb == null)
                sb = new StringBuilder("insert into person values");
            person.setId(i);
            person.setName("a" + i);
            sb.append("(");
            sb.append(person.getId());
            sb.append(",'");
            sb.append(person.getName());
            sb.append("','");
            sb.append(person.getSex());
            sb.append("','");
            sb.append(person.getAddr());
            sb.append("')");
            if (i % 500 == 0) {
                stat.execute(sb.toString());
                sb = null;
            } else {
                sb.append(",");
            }
        }
        stat.close();
        conn.commit();
        conn.close();
    }

    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@192.168.165.141:1521:GALAXY";
    private static String user = "GALAXY";
    private static String passwd = "GALAXY";

//	private static String driver = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://192.168.165.219:3306/test";
//	private static String user = "root";
//	private static String passwd = "123123";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
//			logger.error("load datasource driver: "+ driver +" failed!",e);
        }
    }

    public static Connection getConnection() {
        return getConnection(null, false);
    }

    public static Connection getConnection(boolean autoCommit) {
        return getConnection(null, autoCommit);
    }

    public static Connection getConnection(Object obj, boolean autoCommit) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
//			logger.error("can't getConnection! Cause:",e);
        }
        return conn;
    }
}
