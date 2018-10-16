package com.dcits.ensemble.om.cmc.adapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baidu.ub.msoa.utils.NetUtils;
import com.baixin.infra.head.ResponseHead;
import com.baixin.infra.head.Result;
import com.dcits.baixin.msoa.Request;
import com.dcits.baixin.msoa.ServiceMapping;
import com.dcits.baixin.msoa.client.ProxyManager;
import com.dcits.baixin.msoa.support.ConvertUtils;
import com.dcits.baixin.msoa.support.MsoaConstants;
import com.dcits.ensemble.om.cmc.constant.CmcConstant;
import com.dcits.galaxy.adapter.Adapter;
import com.dcits.galaxy.base.GalaxyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/18
 */
public abstract class AbstractAdapter implements Adapter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAdapter.class);

    public AbstractAdapter() {
    }

    @Override
    public Object execute(Object o) {

        JSONObject inJson = JSON.parseObject((String) o);
        JSONObject sysHeadJson = inJson.getJSONObject(MsoaConstants.SYS_HEAD);
        String id = null;
        if (null != sysHeadJson){
            id = sysHeadJson.getString(MsoaConstants.ID.toUpperCase());
            if (null != id){
                return invokeOtherMsoaService(id, inJson);
            }
        }
        return invokeInternalService((String) o, inJson);
    }

    public String invokeOtherMsoaService(String id, JSONObject inMsg){
        try{
            //获取要调用的服务信息并组装请求报文
            JSONObject json = ConvertUtils.upper2hump(inMsg);
            ServiceMapping serviceMapping = ConvertUtils.getService(id);
            Class<? extends Request> clazz = serviceMapping.getRequestType();
            Method method = serviceMapping.getMethod();
            Object service = ProxyManager.getProxy(serviceMapping.getInterfaceType());
            JSONObject bodyJson = (JSONObject) json.remove(GalaxyConstants.BODY);
            if (bodyJson != null){
                json.putAll(bodyJson);
            }
            json.put(MsoaConstants.HEAD, json.getJSONObject(GalaxyConstants.SYS_HEAD));
            JSONObject head = json.getJSONObject(MsoaConstants.HEAD);
            head.put("serviceId", ConvertUtils.generateServiceId(id));
            head.put("sourceServiceId", CmcConstant.SOURCE_SERVICE_ID);
            head.put("sourceServerId", NetUtils.getLocalAddress().getHostName());
            Object request = JSON.toJavaObject(json, clazz);
            if (logger.isDebugEnabled()){
                logger.debug("sending message is: " + JSON.toJSONString(request,true));
            }

            //反射调用金融网关服务方法
            Object response = method.invoke(service, request);

            if (logger.isDebugEnabled()){
                logger.debug("response message is: " + JSON.toJSONString(response,true));
            }

            //处理调用返回的信息
            JSONObject responseJson = (JSONObject)JSON.toJSON(response);

            JSONObject jsonOutMsg = new JSONObject();
            JSONObject sysHeadJson = (JSONObject) responseJson.remove(MsoaConstants.HEAD);
            sysHeadJson.put(GalaxyConstants.RETS, sysHeadJson.remove(MsoaConstants.RET_RESULT));

            jsonOutMsg.put(GalaxyConstants.SYS_HEAD, sysHeadJson);
            jsonOutMsg.put(GalaxyConstants.APP_HEAD, responseJson.remove(GalaxyConstants.APP_HEAD));
            jsonOutMsg.put(GalaxyConstants.BODY, responseJson);

            String outMsg = JSON.toJSONString(jsonOutMsg, ConvertUtils.HUMP2UPPER_FILTER, SerializerFeature.DisableCircularReferenceDetect);

            if (logger.isDebugEnabled()){
                logger.debug("response message is: " + outMsg);
            }

            return outMsg;
        } catch (IllegalAccessException | IllegalArgumentException e){
            logger.error("exception when invoke service " + id, e);
            return handleException(e);
        } catch (InvocationTargetException e){
            logger.error("exception when invoke service " + id, e);
            return handleException(e.getTargetException());
        }
    }

    abstract String invokeInternalService(String inMsg,JSONObject request);

    /**
     * 异常处理
     * @param targetException
     * @return
     */
    private String handleException(Throwable targetException) {
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRetStatus("F");
        responseHead.setRetResult(new ArrayList());
        Result result = new Result("999999", targetException.getMessage());
        responseHead.getRetResult().add(result);
        JSONObject head = (JSONObject)JSON.toJSON(responseHead);
        head.put("ret", head.remove("retResult"));
        JSONObject retJson = new JSONObject();
        retJson.put("sysHead", head);
        return JSON.toJSONString(retJson, ConvertUtils.HUMP2UPPER_FILTER, new SerializerFeature[0]);
    }
}
