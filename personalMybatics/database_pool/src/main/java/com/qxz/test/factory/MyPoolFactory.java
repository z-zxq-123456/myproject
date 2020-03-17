package com.qxz.test.factory;

import com.qxz.test.pool.IPool;
import com.qxz.test.pool.MyDbPool;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-17 17:34
 **/
public class MyPoolFactory {

    private static class CreatePool{
        private static IPool myDbPool = new MyDbPool();
    }

    public static IPool getInstance(){

        return CreatePool.myDbPool;
    }
}
