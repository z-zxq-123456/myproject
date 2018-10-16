package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.fullgalaxy.EcmServerIndex;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FullServerMonitorService*
 * @author luolang
 * @date 2017-1-9
 */
@Service
public class FullServerMonitorService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	IService omsBaseService;
	@Resource
	FullServerIndexService fullServerIndexService;

    public static Integer serMoniId = 0;

	public EcmServerIndex getLastServerMonitorInfo(String serId) {
		fullServerIndexService.startCollSeverIndex();
		Map<String ,Object> serMap = new HashMap<String , Object >();
		serMap.put("serId",serId);
		List<EcmServerIndex > serverIndexList = omsBaseService.findListByCond(new EcmServerIndex(),serMap);
        if(serverIndexList.size()>0) {
			if (serMoniId!= serverIndexList.get(serverIndexList.size()-1).getSerMoniId()) {
				serMoniId = serverIndexList.get(serverIndexList.size()-1).getSerMoniId();
				return serverIndexList.get(serverIndexList.size()-1);
			} else {
                return null;
			}
		}else {
			return null;
		}

	}

}
