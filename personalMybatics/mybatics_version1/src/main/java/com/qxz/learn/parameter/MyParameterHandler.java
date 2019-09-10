package com.qxz.learn.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyParameterHandler {

    Object getParamentObject();

    void setParamentters(PreparedStatement pstmt);
}
