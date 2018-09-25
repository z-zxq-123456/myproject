package com.dcits.orion.scp.utils;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.serializer.JDKSerializer;
import com.dcits.galaxy.core.serializer.Serializer;
import com.dcits.galaxy.core.serializer.StringSerializer;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.scp.api.ISystem;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.mapping.DataMapping;
import com.dcits.orion.scp.mapping.entity.Mapper;
import com.dcits.orion.scp.model.ServiceNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class ScpUtils {

    private static final Logger log = LoggerFactory
            .getLogger(ScpUtils.class);
    public static void addCommittedService(ServiceNode serviceNode, Map cacheMap,int seqNo)
    {
        synchronized (cacheMap)
        {
            List committedServices = (List)cacheMap.get(ScpConstants.committedServices);
            if (committedServices == null)
            {
                committedServices = new ArrayList();
                cacheMap.put(ScpConstants.committedServices,committedServices);
            }
            Map reversalRequest = null;
            try {
                reversalRequest = ScpUtils.getArg(cacheMap,serviceNode.getFlowId(),serviceNode.getReversal());
            }
            catch (Exception e)
            {
                if (log.isWarnEnabled()) {
                    log.warn("服务：" + serviceNode.getNodeDesc() + "的冲正服务数据映射失败！");
                    log.warn(e.getMessage());
                    log.warn(ExceptionUtils.getStackTrace(e));
                }
            }
            if (reversalRequest != null)
            {
                ThreeTuple<ServiceNode,Map,Integer> commitService = new ThreeTuple(serviceNode,reversalRequest,seqNo);
                committedServices.add(commitService);
            }
        }
    }

    public static List<ThreeTuple<ServiceNode,Map,Integer>> getCommitServices(Map cacheMap)
    {
        return (List)cacheMap.get(ScpConstants.committedServices);
    }

    public static boolean hasCommittedService( Map cacheMap)
    {
        List committedServices = (List)cacheMap.get(ScpConstants.committedServices);
        if (committedServices == null || committedServices.isEmpty() )
            return false;
        else return true;
    }

    public static void addError(List<Map> errors,Map cacheMap)
    {
        if (errors == null || errors.isEmpty())
            return;
        synchronized (cacheMap)
        {
            List errorList = (List)cacheMap.get(ScpConstants.errorList);
            if (errorList == null)
            {
                errorList = new ArrayList();
                cacheMap.put(ScpConstants.errorList,errorList);
            }
            errorList.addAll(errors);
        }
    }

    public static void addError(Map error,Map cacheMap)
    {
        List arrayList = new ArrayList(1);
        arrayList.add(error);
        addError(arrayList,cacheMap);
    }
    public static void addError(UnknownException e,Map cacheMap)
    {
        addError(e.getRetCode(),e.getRetMsg(),cacheMap);
    }

    public static void addError(String errorCode,String errorMsg,Map cacheMap)
    {
        Map error = new HashMap();
        error.put(ScpConstants.retCode,errorCode);
        error.put(ScpConstants.retMsg,errorMsg);
        addError(error,cacheMap);
    }

    public static void addError(String errorCode,Map cacheMap)
    {
        Map error = new HashMap();
        error.put(ScpConstants.retCode,errorCode);
        error.put(ScpConstants.retMsg, ErrorUtils.getErrorDesc(errorCode));
        addError(error,cacheMap);
    }

    public static boolean isError(Map cacheMap)
    {
        List errorList = (List)cacheMap.get(ScpConstants.errorList);
        if (errorList == null || errorList.isEmpty())
            return false;
        else return true;
    }

    public static void addReversalFlag(Map cacheMap)
    {
        cacheMap.put(ScpConstants.toReversal,ScpConstants.toReversal);
    }
    public static boolean isReversal(Map cacheMap)
    {
        return cacheMap.containsKey(ScpConstants.toReversal);
    }



    public static Map errorReturn(Map cacheMap)
    {
        List<Map> errorList = (List) cacheMap.get(ScpConstants.errorList);
        Map sysHead = new HashMap();

        Map map = new HashMap();
        map.put("SYS_HEAD",sysHead);
        sysHead.put("RET",errorList);
        String retStatus = "O";
        for (Map error:errorList)
        {
            String retCode = (String)error.get("RET_CODE");
            if (!"102".equals(retCode))
                retStatus = "F";

        }
        sysHead.put("RET_STATUS",retStatus);
        return map;
    }

    public static Map errorReturn(String retCode,String retMsg)
    {
        List<Map> errorList = new ArrayList<>();
        Map ret = new HashMap();
        ret.put("RET_CODE",retCode);
        ret.put("RET_MSG",retMsg);
        errorList.add(ret);
        Map sysHead = new HashMap();
        Map map = new HashMap();
        map.put("SYS_HEAD",sysHead);
        if ("000000".equals(retCode))
            sysHead.put("RET_STATUS","S");
        else
            sysHead.put("RET_STATUS","F");
        sysHead.put("RET",errorList);
        return map;

    }

    public static Map getArg(Map cacheMap, String flowId, String argName){
        if (StringUtils.isBlank(argName))
            return null;
        DataMapping dataMapping = SpringApplicationContext.getContext().getBean(DataMapping.class);
        Map map = dataMapping.getMappingData(cacheMap,flowId,argName);
        return map;

    }
    public static String getExprString(Map map,String expr)
    {
        return getExprObject(map,expr,String.class);
    }

    public static <T> T getExprObject(Map map,String expr,Class<T> t)
    {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(map);
        Map variables = ScpUtils.getVariables(map);
        if (variables != null && !variables.isEmpty())
            context.setVariables(variables);
        try
        {
            return parser.parseExpression(expr).getValue(context,t);

        }
        catch (Exception e)
        {
            if (log.isErrorEnabled())
            {
                log.error("表达式处理失败，表达式为：" + expr);
            }
            throw e;
        }

    }

    public static boolean getExprBoolean(Map map,String expr)
    {
        boolean ret = getExprObject(map,expr,boolean.class);
        return ret;
    }

    public static String getString(Object map)
    {
        return JSON.toJSONString(map);
    }

    public static void setVariables(Map cacheMap,Map<String,Object> variables)
    {
        if (variables == null)
            return;
        Map<String,Object> map = (Map) cacheMap.get(ScpConstants.variables);
        if (map == null)
            cacheMap.put(ScpConstants.variables,variables);
        else map.putAll(variables);
    }
    public static void setVariable(Map cacheMap,String name,Object o)
    {
        Map<String,Object> map = (Map) cacheMap.get(ScpConstants.variables);
        if (map == null) {
            map = new HashMap<>();
            cacheMap.put(ScpConstants.variables,map);
        }
        map.put(name,o);
    }
    public static Map<String,Object> getVariables(Map cacheMap)
    {
        Map<String,Object> map = (Map) cacheMap.get(ScpConstants.variables);
        return map;
    }

    public static boolean existMapper(String flowId, String mapperName)
    {
        DataMapping dataMapping = SpringApplicationContext.getContext().getBean(DataMapping.class);
        if(dataMapping.getMapper(flowId,mapperName) != null)
            return true;
        else return false;
    }


    public static boolean isResume(Map cacheMap)
    {
        return (boolean)cacheMap.get(ScpConstants.isResume);
    }

    public static void addServiceNode(ServiceNode serviceNode)
    {
        ThreadLocalManager.put(ScpConstants.serviceNode,serviceNode);
    }

    public static ServiceNode getServiceNode()
    {
        return (ServiceNode)ThreadLocalManager.get(ScpConstants.serviceNode);
    }

    public static String getNodeId()
    {
        ServiceNode serviceNode = getServiceNode();
        if (serviceNode != null)
            return serviceNode.getNodeId();
        else return null;
    }


    public static void addNotReversalFlag(Map cacheMap)
    {
        cacheMap.put(ScpConstants.notReversalFlag, "Y");
    }

    public static boolean isNotReversal(Map cacheMap)
    {
        if (cacheMap.containsKey(ScpConstants.notReversalFlag))
            return true;
        else return false;
    }



}
