package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.zookeeper.ZkContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareinstDeploy;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareoprInfo;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ZookeeperInfoService* 
 * @author wangbinaf
 * @createdate 2016-3-7
 */
@Service
public class ZookeeperInfoService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String  FIND_BY_MID = "findByMid";
	
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	ZkContainer zkContainer;
	private int max_midwareDeploy_Id = 0;
	private int max_midwareOper_Id = 0;
	/**
     * 部署zk实例后，修改zk实例状态，并添加应用实例部署记录
     * @param EcmMidwareZkint  intant    应用实例对象	
     * @param int    userId           用户ID 		     
     */

	public void save(EcmMidwareZkint intant, String userId) {
    	EcmMidwareinstDeploy deploy = new EcmMidwareinstDeploy();
    	int midwareDeployId=serviceUtil.getMaxReqID(max_midwareDeploy_Id,"ECM_MIDWAREINST_DEPLOY","MIDWARE_DEPLOY_ID");
    	deploy.setMidwareDeployId(midwareDeployId);
    	deploy.setMidwareDeployDate(DateUtils.getDateTime());
    	deploy.setMidwareDeployUserid(userId);
    	deploy.setMidwareIntantId(intant.getZkintId());
    	deploy.setMidwareType(SysConfigConstants.ZOOKEEPER_MIDWARE);//类型是zk
    	deploy.setMidwareVerId(intant.getMidwareVerId());
    	log.info("deploy = " + deploy);
	    omsBaseDao.insert(deploy);
	    EcmMidwareZkint verNumUpdatZk = new EcmMidwareZkint();
	    verNumUpdatZk.setZkintId(intant.getZkintId());
	    verNumUpdatZk.setZkintStatus(SysConfigConstants.APP_INTANTSTATUS_DEPLOYED);//修改状态为已部署
	    log.info("verNumUpdatZk = "+verNumUpdatZk);
	    omsBaseDao.updateByPrimaryKey(verNumUpdatZk);	    
    }
    /**
     * 清理或停止或启动ZK实例后，修改ZK实例状态，并添加ZK实例操作记录
     * @param appIntantstatusStart 
     * @param EcmAppIntant  intant    应用实例对象	
     * @param String intantStatus     应用实例状态
     * @param int    userId           用户ID 		     
     */
    @Transactional
	public void saveStartOrStopOrCleanZkIntantStatus(EcmMidwareZkint intant,String midwareOperType,String zkintStatus, String userId) {
    	EcmMidwareoprInfo deploy = new EcmMidwareoprInfo();
    	int midwareOperId=serviceUtil.getMaxReqID(max_midwareOper_Id,"ECM_MIDWAREOPR_INFO","MIDWARE_OPER_ID");
    	deploy.setMidwareOperId(midwareOperId);
    	deploy.setMidwareIntantId(intant.getZkintId());
    	deploy.setMidwareType(SysConfigConstants.ZOOKEEPER_MIDWARE);//类型是zk
    	deploy.setMidwareOperTime(DateUtils.getDateTime());
    	deploy.setMidwareOperUserid(userId);
    	deploy.setMidwareOperType(midwareOperType);
    	log.info("deploy = "+deploy);
	    omsBaseDao.insert(deploy);
	    EcmMidwareZkint verNumUpdatZk = new EcmMidwareZkint();
	    verNumUpdatZk.setZkintId(intant.getZkintId());
	    verNumUpdatZk.setZkintStatus(zkintStatus);
	    log.info("verNumUpdatZk = "+verNumUpdatZk);
	    omsBaseDao.updateByPrimaryKey(verNumUpdatZk);	    
    }
    /**
     * 部署集群下所有ZK实例
     * @param EcmMidwareZkint  intant    zk实例对象
     * @param int    userId           用户ID 		     
     */
    @Transactional
    public void saveAllZkIntant(Integer  midwareId, String userId) {
		List<EcmMidwareZkint>  zkList=findByMid(midwareId);
		saveZkIntant(zkList,userId);
   }
    /**
     * 部署单zk实例
     * @param List<EcmMidwareZkint>  zkList  zk实例对象		
     * @param int    userId           用户ID 		     
     */
    public void saveZkIntant(List<EcmMidwareZkint>  zkList, String userId ) {
      for(EcmMidwareZkint temp:zkList){
	     zkContainer.assemContainer(temp,""+userId,zkList);	
	     save(temp,userId);
      }
   }
    /**
     * 启动集群下所有ZK实例
     * @param EcmMidwareZkint  intant    zk实例对象
     * @param int    userId           用户ID 		     
     */
   @Transactional
   public void startAllZkIntant(Integer  midwareId, String userId) {
       List<EcmMidwareZkint>  zkList=findByMid(midwareId);
       for(EcmMidwareZkint temp:zkList){
 		 ProgressMessageUtil.startProgress("" + userId, temp.getSerIp(), temp.getMidwareName());
         startZkIntant(temp,userId);
       }
   }
   /**
    * 启动单ZK实例
    * @param EcmMidwareZkint  intant    zk实例对象
    * @param int    userId           用户ID 		     
    */
   public void startZkIntant(EcmMidwareZkint temp, String userId) {
	   if(!temp.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)){
		  log.info("startZkIntantemp"+temp);
		  zkContainer.startContainer(temp,""+userId);            
		  saveStartOrStopOrCleanZkIntantStatus(temp, SysConfigConstants.APP_INTANTSTATUS_START, SysConfigConstants.APP_INTANTSTATUS_START,userId);
	   }
   }
   /**
    * 停止集群下所有ZK实例
    * @param EcmMidwareZkint  intant    zk实例对象
    * @param int    userId           用户ID 		     
    */
  @Transactional
  public void stopAllZkIntant(Integer  midwareId, String userId) {
      List<EcmMidwareZkint>  zkList=findByMid(midwareId);
      for(EcmMidwareZkint temp:zkList){
    	ProgressMessageUtil.startProgress("" + userId, temp.getSerIp(), temp.getMidwareName());
        stopZkIntant(temp,userId);
      }
  }
  /**
   * 停止单ZK实例
   * @param EcmMidwareZkint  intant    zk实例对象
   * @param int    userId           用户ID 		     
   */
  public void stopZkIntant(EcmMidwareZkint temp, String userId) {
	  if(!temp.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_STOP)){
		  log.info("stopZkIntanttemp"+temp);
	      zkContainer.stopContainer(temp,""+userId);	   
	      saveStartOrStopOrCleanZkIntantStatus(temp, SysConfigConstants.APP_INTANTSTATUS_STOP, SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
      }
  }
  /**
   * 重启集群下所有ZK实例
   * @param EcmMidwareZkint  intant    zk实例对象
   * @param int    userId           用户ID 		     
   */
 @Transactional
 public void reStartAllZkIntant(Integer  midwareId, String userId) {
     List<EcmMidwareZkint>  zkList=findByMid(midwareId);
     for(EcmMidwareZkint temp:zkList){
      ProgressMessageUtil.startProgress("" + userId, temp.getSerIp(), temp.getMidwareName());
      reStartZkIntant(temp,userId);
     }
 }
 /**
  * 重启单ZK实例
  * @param EcmMidwareZkint  intant    zk实例对象
  * @param int    userId           用户ID 		     
  */
 public void reStartZkIntant(EcmMidwareZkint temp, String userId) {
  if(!temp.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
	 log.info("reStartZkIntanttemp"+temp);
	 zkContainer.stopContainer(temp,""+userId);//先停止全部实例
	 zkContainer.startContainer(temp,""+userId); //启动所有部署了的实例 
	 saveStartOrStopOrCleanZkIntantStatus(temp, SysConfigConstants.APP_INTANTSTATUS_RESTART, SysConfigConstants.APP_INTANTSTATUS_RESTART,userId);
   }
 }
  /**
   * 清理集群下所有ZK实例
   * @param EcmMidwareZkint  intant    zk实例对象
   * @param int    userId           用户ID 		     
   */
 @Transactional
 public void cleanAllZkIntant(Integer  midwareId, String userId) {
     List<EcmMidwareZkint>  zkList=findByMid(midwareId);
     for(EcmMidwareZkint temp:zkList){
      ProgressMessageUtil.startProgress("" + userId, temp.getSerIp(), temp.getMidwareName());
      cleanZkIntant(temp,userId);
     }
 }
 /**
  * 清理单ZK实例
  * @param EcmMidwareZkint  intant    zk实例对象
  * @param int    userId           用户ID 		     
  */
 public void cleanZkIntant(EcmMidwareZkint temp, String userId) {
	  if(!temp.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
          zkContainer.cleanZkIntant(temp,""+userId);
		 saveStartOrStopOrCleanZkIntantStatus(temp, SysConfigConstants.APP_INTANTSTATUS_CLEAN, SysConfigConstants.APP_INTANTSTATUS_NODEPLOY,userId);
	   }
 }

  /**
	* 通过中间件Id查出对应的所有实例
	* @param  String userId   用户ID     	
	*
   */	
 public List<EcmMidwareZkint>  findByMid(Integer  midwareId){
	Map<String,Object> conMap = new HashMap<String,Object>();
	if(null!=midwareId){
	  conMap.put("midwareId",midwareId);
	}
	List<EcmMidwareZkint>  zkList=omsBaseDao.findListByCond(new EcmMidwareZkint(),FIND_BY_MID,conMap);
	return zkList;
 }	 
}