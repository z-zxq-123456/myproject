package com.dcits.ensemble.om.oms.service.log;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.log.EcmLogconfInfo;
import com.dcits.ensemble.om.oms.module.log.EcmTraceAnnot;
import com.dcits.ensemble.om.oms.module.log.EcmTraceCircle;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmLogInfoService*
 * @ author luolang
 * @ date 2016-1-13
 */
@Service
public class EcmLogInfoService {


    @Resource
    IService  omsBaseService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());
    @Transactional
    public List<EcmTraceAnnot> getLogInfo(String traceId) {
        List<EcmTraceAnnot> loginfo = new ArrayList<EcmTraceAnnot>();
        Map<String ,Object> map = new HashMap<>();
        map.put("traceId", traceId);
        List<EcmTraceCircle> circleList = omsBaseService.findListByCond(new EcmTraceCircle(), "findTcecirList", map);
        for(EcmTraceCircle traceCircle : circleList) {
            Map<String ,Object> criMap = new HashMap<String,Object>();
            criMap.put("cirId",traceCircle.getCirId());
            List<EcmTraceAnnot> busList = omsBaseService.findListByCond(new EcmTraceAnnot(),"findTraceAnnotList",criMap);
            loginfo.addAll(busList);
        }
        return loginfo;
    }
}
