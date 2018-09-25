package com.dcits.orion.scp.handle;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.orion.api.Handler;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.factory.FlowFactory;
import com.dcits.orion.scp.factory.SystemFactory;
import com.dcits.orion.scp.model.Flow;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/17.
 */
@Repository
public class ScpHandle implements Handler{

    @Resource
    ScpEngine scpEngine;
    /**
     * 业务系统对外暴露的处理接口
     *
     * @param msg
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午1:42:45
     */
    @Override
    public String handle(String msg) {
        return null;
    }

    /**
     * 业务系统对外暴露的处理接口
     *
     * @param msg
     * @return
     */
    @Override
    public Map handle(Map msg){
        Flow flow = FlowFactory.getFlow(msg);
        if (flow == null)
        {
            return SystemFactory.getDefaultSystem().execute(msg);
        }
        else {
           return scpEngine.execute(msg,flow);
        }
    }
}
