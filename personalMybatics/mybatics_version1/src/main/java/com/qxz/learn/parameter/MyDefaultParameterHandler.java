package com.qxz.learn.parameter;

import java.sql.PreparedStatement;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyDefaultParameterHandler implements MyParameterHandler {

    @Override
    public Object getParamentObject() {
        return null;
    }

    @Override
    public void setParamentters(PreparedStatement pstmt) {

    }
}
