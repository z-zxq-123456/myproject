package com.qxz.test.pool;

import com.qxz.test.config.DbPoolConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-10 22:36
 **/
public class MyDbPool implements IPool {

    private Vector<MyConnection> connections = new Vector<>();

    private String url;
    private String username;
    private String password;
    private int count;
    private int max;
    private int step;

    public MyDbPool() {
        init();
        try {
            Class.forName(DbPoolConfig.jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        createConnection(count);
    }


    private void init(){
        url = DbPoolConfig.url;
        username = DbPoolConfig.username;
        password = DbPoolConfig.password;
        count = DbPoolConfig.count;
        max = DbPoolConfig.max;
        step = DbPoolConfig.step;
    }

    @Override
    public MyConnection getConnectionFromPool() {

        if (connections.size() == 0){
            throw new IllegalArgumentException("the db pool size is not effected!");
        }

        MyConnection myConnection = null;
        try {
            myConnection = getConnection();
            if (myConnection == null){
                createConnection(step);
                myConnection = getConnection();
                return myConnection;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return myConnection;
    }

    private MyConnection getConnection() throws SQLException {

        for (MyConnection connection:connections){
            if (connection.isBusy()){
                Connection jdbcConnection = DriverManager.getConnection(url,username,password);
                connection.setConnection(jdbcConnection);
                connection.setBusy(true);
                return connection;
            }else {
                if (connection.getConnection().isValid(300)){
                    connection.setBusy(true);
                    return connection;
                }
            }
        }
        return null;
    }

    @Override
    public void createConnection(int count) {

        if (connections.size() > max || connections.size()+count> max){
            throw new RuntimeException("the pool size is full!");
        }

        for (int i = 0; i < count; i++){
            try {
                Connection connection = DriverManager.getConnection(url,username,password);
                MyConnection myConnection = new MyConnection(connection,false);
                connections.add(myConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
