package com.dcits.orion.scp.factory;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.scp.dao.ScpDao;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.utils.ScpUtils;

import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class FlowFactory {
    public static Flow getFlow(Map request)
    {
        String expr = "[SYS_HEAD][SERVICE_CODE]+'-'+[SYS_HEAD][MESSAGE_TYPE]+'-'+[SYS_HEAD][MESSAGE_CODE]";
        String flowID = ScpUtils.getExprString(request,expr);
        ScpDao scpDao = SpringApplicationContext.getContext().getBean(ScpDao.class);
        return scpDao.getFlow(flowID);
    }

}
