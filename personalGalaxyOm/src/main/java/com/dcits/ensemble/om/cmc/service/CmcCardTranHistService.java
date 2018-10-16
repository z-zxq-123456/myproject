package com.dcits.ensemble.om.cmc.service;

import com.alibaba.fastjson.JSONObject;
import com.dcits.baixin.msoa.support.MsoaConstants;
import com.dcits.ensemble.om.cmc.constant.CmcConstant;
import com.dcits.ensemble.om.cmc.model.CmcTranInfo;
import com.dcits.ensemble.om.cmc.util.BeanConvertUtils;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.pm.common.adapter.HttpAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dangyk on 2018/4/20.
 */
@Service
public class CmcCardTranHistService {

    private static Logger logger = LoggerFactory.getLogger(CmcCardTranHistService.class);


    public CmcTranInfo getCmcTranHist(Map requestMapping){

        JSONObject request = new JSONObject();
        request.putAll(requestMapping);
        DataPostUtil.genCommonArgs(request);
        DataPostUtil.genMosaHead(request,"0304","1400","MbsdCard","QryCardTranHistAPI.qryCardTranHist");
        //请求服务
        String result = sendToServer(CmcConstant.ADAPTER_CACHE_KEY_PM,request);
        //结果响应
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONObject resSysHead = resultJson.getJSONObject(MsoaConstants.SYS_HEAD);
        JSONObject resBody = resultJson.getJSONObject(MsoaConstants.BODY);
        DataPostUtil.dealWithResultStatus(resSysHead);
        return (CmcTranInfo) BeanConvertUtils.getInstance().convertToResult(resBody,CmcTranInfo.class);
    }

    /**
     * 发布到服务器
     * @param url
     * @param requestMapping
     * @return
     */
    private String sendToServer(String url, JSONObject requestMapping){

        return HttpAdapter.doPostMsg(url,JSONObject.toJSONString(requestMapping));
    }

}
