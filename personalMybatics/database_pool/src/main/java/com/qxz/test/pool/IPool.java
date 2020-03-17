package com.qxz.test.pool;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-10 22:35
 **/
public interface IPool {

    public MyConnection getConnectionFromPool();

    public void createConnection(int count);

}
