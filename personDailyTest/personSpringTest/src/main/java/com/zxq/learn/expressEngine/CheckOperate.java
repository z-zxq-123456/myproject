package com.zxq.learn.expressEngine;

import com.ql.util.express.Operator;

/**
 * Created{ by zhouxqh} on 2018/3/15.
 */

public class CheckOperate extends Operator {

    public CheckOperate(String name) {
        this.name = name;
    }

    @Override
    public Object executeInner(Object[] objects) throws Exception {
        String str1 = (String) objects[0];
        String str2 = (String) objects[1];
        return str1.equals(str2);
    }
}

