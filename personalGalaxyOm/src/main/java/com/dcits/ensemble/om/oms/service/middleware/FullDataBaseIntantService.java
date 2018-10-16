package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.db.DBContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 全量DataBase实例部署Service* 
 * @author tangxlf
 * @date 2016-05-17
 */
@Service
public class FullDataBaseIntantService {
   
    
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	MiddlewareDBService middlewareDBService;
	@Resource
	MiddlewareInfoService middlewareInfoService;
	@Resource
	DBContainer dbContainer;
	/**
     * 全量部署时查询全量的DataBase集群和DataBase实例	
     * @param	  String userId  用户ID
     * @return	  List<EcmMidwareRedisint>   
     */    
	public List<EcmMidwareDbint> findFullDataBaseAndIntantList(String userId) {
		List<EcmMidwareInfo> redisGroupList = middlewareInfoService.findMidwareList(SysConfigConstants.DB_MIDWARE);
    	List<EcmMidwareDbint>  redisIntantList=findFullDataBaseIntant();
    	return mergeDataBaseAndIntant(redisGroupList,redisIntantList);
    }
	/**
     *查询所有DataBase实例	     
     * @return	  List<EcmMidwareRedisint>   
     */ 
	public List<EcmMidwareDbint>  findFullDataBaseIntant(){
		return middlewareDBService.findDbIntByMidwareId(new EcmMidwareDbint());
	}
	
	//合并DataBase集合和DataBaseIntant集合到一个集合中返回页面
	private List<EcmMidwareDbint> mergeDataBaseAndIntant(List<EcmMidwareInfo> redisGroupList,List<EcmMidwareDbint>  databaseIntantList){
		List<EcmMidwareDbint>  resultList = new ArrayList<EcmMidwareDbint>();
		for(EcmMidwareInfo group:redisGroupList){
			EcmMidwareDbint intant = new EcmMidwareDbint();
			intant.setMidwarePath(group.getMidwarePath());
			intant.setMidwareName(group.getMidwareName());
			resultList.add(intant);
			for(EcmMidwareDbint midwareDbint:databaseIntantList){
				if(midwareDbint.getMidwareId()==group.getMidwareId()){										
					dbContainer.checkContainerStatus(midwareDbint,null);
					//midwareDbint.setDbintCurrentStatusName(result.getCheckStatusName());
					//midwareDbint.setHealthMessage(result.getMessage());					
					midwareDbint.setMidwareName(null);	
					midwareDbint.setDbTypeName(paramComboxService.getParaName(midwareDbint.getDbType()));
					resultList.add(midwareDbint);
				}
			}
		}
		return  resultList;
	}
	
}
