package com.qxz.learn.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DefaultParameterHandler implements ParameterHandler {

        private Object parameter;

    public DefaultParameterHandler(Object parameter) {
        this.parameter = parameter;
    }

    @Override
    public void setParameters(PreparedStatement ps) {
        try {
            if (ps != null){
                Object[] params = (Object[])parameter;
                if (ps.getClass().isArray()){
                    for (int i=0; i<params.length;i++){
                        ps.setObject(i+1,params[i]);
                    }
                }else {
                    ps.setObject(1,params[0]);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
