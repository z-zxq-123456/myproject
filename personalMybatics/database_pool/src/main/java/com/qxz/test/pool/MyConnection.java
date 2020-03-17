package com.qxz.test.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-10 22:34
 **/
public class MyConnection {

    private Connection connection;
    private boolean isBusy;

    public MyConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }


    public void close(){

        this.isBusy = false;
    }


    public boolean isBusy(){

        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet query(String sql){

        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
