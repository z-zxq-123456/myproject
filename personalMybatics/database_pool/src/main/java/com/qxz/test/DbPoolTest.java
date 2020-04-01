package com.qxz.test;

import com.qxz.test.factory.MyPoolFactory;
import com.qxz.test.pool.IPool;
import com.qxz.test.pool.MyConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-10 22:33
 **/
public class DbPoolTest {


    public static void main(String[] args) throws SQLException {

        IPool pool = MyPoolFactory.getInstance();
        for (int i = 0; i < 100; i++){
            MyConnection connection = pool.getConnectionFromPool();
            ResultSet resultSet = connection.query("select * from user");

            while (resultSet.next()){

                System.out.println(resultSet.getString("userName"));
            }
            connection.close();
        }
    }
}
