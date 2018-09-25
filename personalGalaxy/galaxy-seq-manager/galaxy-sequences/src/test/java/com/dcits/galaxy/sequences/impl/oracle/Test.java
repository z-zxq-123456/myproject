/**
 * Title: Galaxy(Distributed service platform)
 * File: Test.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.sequences.impl.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * <p>Created on 2017/5/5.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class Test implements Callable {

    private String name;

    private List<String> list;

    public Test(String name, List<String> list) {
        this.name = name;
        this.list = list;
    }

    public Object call() {
        String sql = "select Test1.nextval id from dual connect by level <= 10 for update";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(name + "_" + rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public static void test() throws Exception {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        List<FutureTask> futureTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Test test = new Test("Thread" + String.valueOf(i), list);
            FutureTask jobTask = new FutureTask(test);
            futureTasks.add(jobTask);
            Thread t = new Thread(jobTask);
            t.start();
        }
        for (FutureTask f : futureTasks) {
            System.out.println(f.get());
        }
        System.out.println("===========================");
        Collections.sort(list);
        for (String value : list) {
            System.out.println(value);
        }
    }

    public static void main(String arg[]) throws Exception {
        Test.test();
    }

}
