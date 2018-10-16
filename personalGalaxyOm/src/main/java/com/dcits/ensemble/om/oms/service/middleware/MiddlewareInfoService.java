package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.action.middleware.EcmMidewareInfoAction;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
/**
 * MiddlewareInfoService* 
 * @author LuoLang
 * @createdate 2016-3-7
 */
@Service
public class MiddlewareInfoService {	
	
	private static final String  FIND_COMB_LIST="findCombList";
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	EcmMidewareInfoAction midewareInfoAction;
	@Resource
	PkServiceUtil  serviceUtil;
	public static Map<String,EcmMidwareInfo> DefaultMidwareVerMap = new HashMap<String,EcmMidwareInfo>();//默认版本集合，K为中间件类型,V节点中间件对象。
	private int max_req_no = 0; 
	/**
     * 修改DefaultMidwareVerMap，并更新数据库
     * @param	  EcmMidwareInfo midware     中间件对象 
     */  
	@Transactional
	public void save(EcmMidwareInfo midware) {
		 int midwareId=serviceUtil.getMaxReqID(max_req_no,"ecm_midware_info","MIDWARE_ID"); //获取当前列表数据最大ID
		    midware.setMidwareId(midwareId);
		    if(!getMap().containsKey(midware.getMidwareType())&&midware.getIsDefault().equals(SysConfigConstants.IS_DEFAULT)) {//注意DB
		    	DefaultMidwareVerMap.put(midware.getMidwareType(), midware);
		    }else {
		    if(getMap().containsKey(midware.getMidwareType())&&midware.getIsDefault().equals(SysConfigConstants.IS_DEFAULT)){
		    	EcmMidwareInfo lastMidware = DefaultMidwareVerMap.get(midware.getMidwareType());
		    	lastMidware.setIsDefault(SysConfigConstants.NOT_DEFAULT);
		    	omsBaseDao.updateByPrimaryKey(lastMidware);
		    	DefaultMidwareVerMap.put(midware.getMidwareType(), midware);
		    }
		    }
		    omsBaseDao.insert(midware);    
    }
	
	/**
     * DefaultMidwareVerMap初始化
     */  
	public Map<String,EcmMidwareInfo> getMap() {
		DefaultMidwareVerMap.clear();
		Map<String,Object> midware = new HashMap<String,Object>();
		midware.put("isDefault", SysConfigConstants.IS_DEFAULT);
		List<EcmMidwareInfo> infoList = omsBaseDao.findListByCond(new EcmMidwareInfo(), "findIsDefaultList", midware);
		for(EcmMidwareInfo midwareInfo : infoList) {
				DefaultMidwareVerMap.put(midwareInfo.getMidwareType(), midwareInfo);
		}
		return DefaultMidwareVerMap;
	}
	/**
     * DefaultMidwareVerMap更新
     * @param	  EcmMidwareInfo midware     中间件对象 
     */  
	@Transactional
	public void update(EcmMidwareInfo midware) {
		 if(midware.getIsDefault().equals(SysConfigConstants.IS_DEFAULT)) {
		    	if(!getMap().containsKey(midware.getMidwareType())) {
		    		DefaultMidwareVerMap.put(midware.getMidwareType(), midware);
		    	}else { 
		    		if(getMap().get(midware.getMidwareType()).getMidwareId()==midware.getMidwareId()) {
		    	    }else {
		    	EcmMidwareInfo lastMidwareInfo = getMap().get(midware.getMidwareType());
		    	lastMidwareInfo.setIsDefault(SysConfigConstants.NOT_DEFAULT);
		    	omsBaseDao.updateByPrimaryKey(lastMidwareInfo);
		    	DefaultMidwareVerMap.put(midware.getMidwareType(), midware);
		    	          }
		    	}
	     	    } else {
	     		   if(getMap().containsKey(midware.getMidwareType())) {
		    		if(getMap().get(midware.getMidwareType()).getMidwareId()==midware.getMidwareId()) {
		    		DefaultMidwareVerMap.remove(midware.getMidwareType());
		    	   }
	     	   }
		}
		 omsBaseDao.updateByPrimaryKey(midware); 		    
	} 
	/**
     * 获取中间件指定类型的所有记录	
     * @param	  String                midwareType     中间件类型  
     * @return	  List<EcmMidwareInfo>   中间件指定类型的所有记录
     */    
	public List<EcmMidwareInfo> findMidwareList(String midwareType){
		Map<String,Object> midware = new HashMap<String,Object>();
		midware.put("midwareType",midwareType);
		return omsBaseDao.findListByCond(new EcmMidwareInfo(), FIND_COMB_LIST, midware);
	}
}