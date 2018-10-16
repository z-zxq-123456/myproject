package com.dcits.ensemble.om.pm.common.adapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baidu.ub.msoa.utils.NetUtils;
import com.baixin.infra.head.RequestHead;
import com.baixin.infra.head.ResponseHead;
import com.baixin.infra.head.Result;
import com.dcits.baixin.msoa.Request;
import com.dcits.baixin.msoa.ServiceMapping;
import com.dcits.baixin.msoa.client.ProxyManager;
import com.dcits.baixin.msoa.support.ConvertUtils;
import com.dcits.baixin.msoa.support.MsoaConstants;
import com.dcits.card.frame.api.api.CmcInternalServiceAPI;
import com.dcits.card.frame.api.model.request.CmcInternalServiceRequest;
import com.dcits.card.frame.api.model.response.CmcInternalServiceResponse;
import com.dcits.galaxy.adapter.Adapter;
import com.dcits.galaxy.base.GalaxyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CmcOMAdapter implements Adapter<String>{

    private static final Logger logger = LoggerFactory.getLogger(CmcOMAdapter.class);

    private String sourceServiceId;

    public CmcOMAdapter(){

    }

    @Override
    public String execute(String inMsg) {
        if (logger.isDebugEnabled()) {
            logger.debug("receive request message :" + inMsg);
        }

        JSONObject inJson = JSON.parseObject(inMsg);
        JSONObject sysHeadJson = inJson.getJSONObject(MsoaConstants.SYS_HEAD);
        String id = null;
        if (null != sysHeadJson){
            id = sysHeadJson.getString(MsoaConstants.ID.toUpperCase());
            if (null != id){
                return invokeOtherMsoaService(id, inJson);
            }
        }
        return invokeCmcInternalService(inMsg, inJson);
    }

    private String invokeOtherMsoaService(String id, JSONObject inJson){
        try{
            //获取要调用的服务信息并组装请求报文
            JSONObject json = ConvertUtils.upper2hump(inJson);
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
            //String sysDate = head.getString("sysDate");
            //String sysTimestamp = head.getString("sysTimestamp");
            //head.put("sysTimestamp", ConvertUtils.covertTimestamp(sysDate, sysTimestamp));
            head.put("serviceId", ConvertUtils.generateServiceId(id));
            head.put("sourceServiceId", sourceServiceId);
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

    private String invokeCmcInternalService(String inMsg, JSONObject inJson){
        CmcInternalServiceAPI internalService = ProxyManager.getProxy(CmcInternalServiceAPI.class);
        JSONObject body = inJson.getJSONObject(MsoaConstants.BODY);
        if (null == body){
            body = new JSONObject();
            inJson.put(MsoaConstants.BODY, body);
        }

        body.clear();
        body.put("message", inMsg);

        CmcInternalServiceRequest request = ConvertUtils.inMap2Request(inJson, CmcInternalServiceRequest.class);
        RequestHead head = request.getHead();
        head.setServiceId(ConvertUtils.generateServiceId(CmcInternalServiceAPI.SERVICE_NAME, internalService));
        head.setSourceServiceId(sourceServiceId);

        CmcInternalServiceResponse response = internalService.invoke(request);
        String responseJSON = response.getMessage();
        if (logger.isTraceEnabled()){
            logger.trace("convert response from {} to {}", JSON.toJSONString(response), responseJSON);
        }
        return responseJSON;
    }

    private String handleException(Throwable targetException){
        ResponseHead responseHead = new ResponseHead();
        responseHead.setRetStatus("F");
        responseHead.setRetResult(new ArrayList<Result>());

        Result result = new Result("999999", targetException.getMessage());
        responseHead.getRetResult().add(result);

        JSONObject head = (JSONObject) JSON.toJSON(responseHead);
        head.put(GalaxyConstants.RETS, head.remove(MsoaConstants.RET_RESULT));

        JSONObject retJson = new JSONObject();
        retJson.put(GalaxyConstants.SYS_HEAD, head);

        return JSON.toJSONString(retJson, ConvertUtils.HUMP2UPPER_FILTER);
    }

    public String getSourceServiceId() {
        return sourceServiceId;
    }

    public void setSourceServiceId(String sourceServiceId) {
        this.sourceServiceId = sourceServiceId;
    }
}
