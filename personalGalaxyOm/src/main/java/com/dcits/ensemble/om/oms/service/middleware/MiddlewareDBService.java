package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.db.DBContainer;
import com.dcits.ensemble.om.oms.manager.db.DBSqlResultInfo;
import com.dcits.ensemble.om.oms.manager.db.DBSqlSelect;
import com.dcits.ensemble.om.oms.manager.db.DbConfUpdate;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中间件-DB Service* 
 * @author tangxlf
 * @date 2016-05-03
 */
@Service
public class MiddlewareDBService {	
	private int max_req_no = 0;
	private static final String FIND_DBINT_BY_MIDWAREID ="findListByMidwareId";
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	DBContainer dbContainer;
	@Resource
	DBSqlSelect dbSqlSelect;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService omsBaseService;
	@Resource
	DbConfUpdate dbConfUpdate;
	/**
	 * 监测指定DB集群中间件ID所属DB实例信息
	 * @param    EcmMidwareDbint midwareDbint    db实例对象		 
	 * @return   List<EcmMidwareDbint>            db实例对象列表
	 */
	public  List<EcmMidwareDbint> monitorDbInt(EcmMidwareDbint midwareDbint) {
        return magicDbStatusList(findDbIntByMidwareId(midwareDbint));
	}
	
	/**
	 * 按照SQL查询指定数据库实例
	 * @param    EcmMidwareDbint         midwareDbint    db实例对象		 
	 * @param    String                  sql             查询SQL  		 
	 * @return   DBSqlResultInfo                  数据库查询结果
	 */
	public  DBSqlResultInfo queryDbInt(EcmMidwareDbint midwareDbint,String sql) {
		List<EcmMidwareDbint>  dbIntList = findDbIntByMidwareId(midwareDbint);
		DBSqlResultInfo rsInfo = new DBSqlResultInfo();
		long startTime = System.currentTimeMillis();
		for(EcmMidwareDbint dbInt : dbIntList){
			dbSqlSelect.selectDBint(dbInt,sql,rsInfo);
		}
		long endTime = System.currentTimeMillis();
		rsInfo.setConsumerTime(endTime-startTime);
		rsInfo.setTotalNum(rsInfo.getResultList().size());
		if(rsInfo.getResultList().size()>500){
			rsInfo.setResultList(rsInfo.getResultList().subList(0,500));
		}
		System.out.print("rsInfo="+rsInfo);
        return rsInfo;
	}
	//根据指定DB集群中间件ID查询DB实例列表
	public List<EcmMidwareDbint> findDbIntByMidwareId(EcmMidwareDbint midwareDbint){
		Map<String,Object>  queryMap = new HashMap<String,Object>();
		if(midwareDbint!=null){
			queryMap.put("midwareId",midwareDbint.getMidwareId());
		}
        return omsBaseDao.findListByCond(midwareDbint, FIND_DBINT_BY_MIDWAREID, queryMap);
	}
	//转换每个实例的中间件类型名称及健康信息
	private List<EcmMidwareDbint> magicDbStatusList(List<EcmMidwareDbint> list) {
		for (EcmMidwareDbint midwareDbint : list) {
			midwareDbint.setDbTypeName(paramComboxService.getParaName(midwareDbint.getDbType()));
			dbContainer.checkContainerStatus(midwareDbint,null);			
		}
		return list;
	}
	/**
	 * 判断该集群下的应用实例是否启动,
	 * @param    Integer         midwareId    db实例对象的中间件ID		 
	 * @return   boolean         false 未启动 ；      true  启动 
	 */
  	public  boolean isStartAppIntant(Integer   midwareId){	
  		log.info("midwareId="+midwareId);
  		List <EcmAppIntant> appInfoLint  = findByMidwareDBId(midwareId);//查找出对应的所有应用实例
  		if(appInfoLint.size()==0){//该数据库集群没有配到任何一个应用集群中，所以根本不用考虑实例的启动，
  			return false;
  		}else{
  	       for(EcmAppIntant temp :appInfoLint){//如果实例是启动的则实例的有些属性是不能修改的
  	    		if(temp.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)){
  	    			return true;
  	    		}
	  	    }
  	     }
  		 return false;
  	}
  	/**
	 * 判断是否修改了db实例的有些实例属性  
	 * @param    EcmMidwareDbint  midwareDbint    db实例对象		 
	 * @return   boolean        false 未修改                       true  修改
	 */
  	public  boolean isUpdateDb(EcmMidwareDbint midwareDbint){
	    Map<String,Object> conMap = new HashMap<String,Object>();
		conMap.put("dbintId",midwareDbint.getDbintId());
		EcmMidwareDbint oldDbint=omsBaseDao.findListByCond(new EcmMidwareDbint(),"findByDbId",conMap).get(0);//通过主键查不出所有信息，所以调用该查询方法	=omsBaseDao.selectByPrimaryKey(midwareDbint);
		log.info("oldDbint="+oldDbint);
		if(!midwareDbint.getMidwareId().equals(oldDbint.getMidwareId())){
			return true;
		}
		if(!midwareDbint.getSerId().equals(oldDbint.getSerId())){
			return true;
		}
		if(!midwareDbint.getDbType().equals(oldDbint.getDbType())){
			return true;
		}
		if(!midwareDbint.getDbintUserName().equals(oldDbint.getDbintUserName())){
			return true;	
		}
		if(!midwareDbint.getDbintUserPwd().equals(oldDbint.getDbintUserPwd())){
			return true;
		}
		if(!midwareDbint.getDbintPort().equals(oldDbint.getDbintPort())){
			return true;
		}
		if(!midwareDbint.getDbintServiceName().equals(oldDbint.getDbintServiceName())){
			return true;
		}
		if(!midwareDbint.getDbintNodeNum().equals(oldDbint.getDbintNodeNum())){
			return true;
		}
		return false;
  	}
  	/**
	 * 通过midwareDBId查找出所有应用实例  
	 * @param    Integer  midwareId    中间件ID		 
	 * @return   List <EcmAppIntant>   应用实例列表     
	 */
	public List <EcmAppIntant>  findByMidwareDBId (Integer   midwareId){
		Map<String,Object> conMap = new HashMap<String,Object>();
  		conMap.put("midwareDBId",midwareId);
  		List <EcmAppIntant>   appInfoLint =omsBaseDao.findListByCond(new EcmAppIntant(),"findByMidwareDBId",conMap);//查找出所有用该数据库集群的APP实例集群
  		return appInfoLint;
	}
	/**
	 * 通过midwareDBId查找出所有应用实例  
	 * @param    EcmMidwareDbint  midwareDbint    db实例对象 
	 * @return   
	 */
	public void save(EcmMidwareDbint midwareDbint) {
		try {
		    int dbintId=serviceUtil.getMaxReqID(max_req_no,"ecm_midware_dbint","dbint_id");  //获取当前列表数据最大ID
		    midwareDbint.setDbintStatus(SysConfigConstants.APP_INTANTSTATUS_DEPLOYED);////DB实例状态初始化
		    midwareDbint.setDbintId(dbintId);
		    midwareDbint.setDbintUserPwd(OMSPassWordBase64.encode(midwareDbint.getDbintUserPwd().getBytes("iso8859-1")));//对数据库密码进行加密
		    if(!isStartAppIntant(midwareDbint.getMidwareId())){//如果配有该数据库的实例是启动的，则不能增加
				omsBaseService.insert(midwareDbint);
				updateAppDbConf(midwareDbint);
		    }else{
		    	throw new GalaxyException("不能增加，请先停止配有该数据库的所有应用实例！");
		    }
		} catch (UnsupportedEncodingException e) {
			throw new GalaxyException(e.getMessage());
		}
    }
	/**
	 * 通过midwareDBId查找出所有应用实例  
	 * @param    EcmMidwareDbint  midwareDbint    db实例对象 
	 * @return   
	 */
	public void update(EcmMidwareDbint midwareDbint) {
		try {
		     midwareDbint.setDbintUserPwd(OMSPassWordBase64.encode(midwareDbint.getDbintUserPwd().getBytes("iso8859-1"))); //对数据库密码进行加密
		     if(isStartAppIntant(midwareDbint.getMidwareId())){//如果配有该数据库的实例是启动的，则有些属性是不能修改的
		    	if(!isUpdateDb(midwareDbint)){
				    omsBaseService.updateByPrimaryKey(midwareDbint);
		    	}else{
		    		throw new GalaxyException("该属性不能修改，请先停止配有该数据库的所有应用实例！");
		    	}
		    }else{//如果是没有启动，则可以修改所有属性
				 omsBaseService.updateByPrimaryKey(midwareDbint);
				 updateAppDbConf(midwareDbint);
			}
		 }catch (UnsupportedEncodingException e) {
				throw new GalaxyException(e.getMessage());
		 }
	}
	/**
	 * 通过midwareDBId查找出所有应用实例  
	 * @param    EcmMidwareDbint  midwareDbint    db实例对象 
	 * @return   
	 */
	public void delete(EcmMidwareDbint midwareDbint) {
	    if(!isStartAppIntant(midwareDbint.getMidwareId())){//如果配有该数据库的实例是启动的，则不能删除
	    	omsBaseService.deleteByPrimaryKey(midwareDbint);	    	
	    }else{//如果实例没有启动，则可以删除
	    	throw new GalaxyException("该实例不能删除，请先停止配有该数据库的所有应用实例！");
		}
     }
	
	private void updateAppDbConf(EcmMidwareDbint midwareDbint){
		List <EcmAppIntant> appInfoLint  = findByMidwareDBId(midwareDbint.getMidwareId());//查找出对应的所有应用实例
  		for(EcmAppIntant intant :appInfoLint){
  		    //如果应用实例是尚未部署的则不用修改配置文件
  	    	if(!intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
  	    		dbConfUpdate.dbIntConfUpdate(intant,midwareDbint);
  	    	}
	    }
	}
}