package com.qxz.learn.executor.parameter;

import java.sql.PreparedStatement;

public interface ParameterHandler {

    void setParameters(PreparedStatement preparedStatement);
}
