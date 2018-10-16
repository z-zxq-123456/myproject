package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用路径处理类* 
 * @author tangxlf
 * @date 2015-11-25 
 */
@Component
public class AppPathHandler {
	@Resource
	PathCreateHelp pathCreateHelp;
	@Resource
	private OMSIDao omsBaseDao;
	private static final String APP_PATH_MARK = "app_";
	private static final Map<String,String> isCreatPathMap = new HashMap<String,String>();//目录是否创建集合
   /**
    * 处理应用安装路径
    * @param  EcmAppIntant        intant              应用实例对象       
    */ 
	public void handlerOsPath(EcmAppIntant intant){
		   if(isCreatPathMap.get(APP_PATH_MARK+intant.getAppIntantId())==null){
			   pathCreateHelp.createOsPath(intant,intant.getAppPath());
			   isCreatPathMap.put(APP_PATH_MARK+intant.getAppIntantId(),""+intant.getAppIntantId());
		   }
	}
	
  
   /**
	* 删除该应用所有应用实例已创建路径缓存
	* @param  EcmAppInfo app              应用对象     
	*/  	
	public void deleteAppPathIsCreatedCache(EcmAppInfo app){
		EcmAppIntant intant =new EcmAppIntant();
		intant.setAppId(app.getAppId());
		List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(intant);
    	for(EcmAppIntant rowIntant:intantList){
    			isCreatPathMap.remove(APP_PATH_MARK+rowIntant.getAppIntantId());         
    	}
		
	}
	

	
}
