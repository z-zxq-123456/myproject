package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.redis.RedisIContainerImpl;
import com.dcits.ensemble.om.oms.manager.redis.RedisStatusCheck;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareinstDeploy;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareoprInfo;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmMidwareRedisintService* 
 * @author luolang
 * @date 2016-04-27
 */
@Service
public class EcmMidwareRedisintService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService omsBaseService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	RedisIContainerImpl redisIContainerImpl;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ShellExcuteService shellService;
	@Resource
	RedisStatusCheck redisStatusCheck;
	private int max_midware_deploy_Id = 0;
	
	private int max_req_no = 0;
	
	private static final String REDIS_FLAG="redis";//解压缩后文件名称
	private static final String IS_EXIST_DIRORFILE ="是否存在文件或者目录";
	private static final String DELETE_FILE_OPERATION ="删除文件操作";
	/**
	 * 记录部署信息	 * 
	 * @param EcmMidwareRedisint  midwareRedisint 实例对象
	 * @param String  userId 用户ID		 
	 */
	 @Transactional
	 private void save(EcmMidwareRedisint midwareRedisint, String userId) {
		 EcmMidwareinstDeploy midwareinstDeploy = new EcmMidwareinstDeploy();
		 int midwareDeployId=serviceUtil.getMaxReqID(max_midware_deploy_Id,"ecm_midwareinst_deploy","MIDWARE_DEPLOY_ID");
		 midwareinstDeploy.setMidwareDeployId(midwareDeployId);
		 midwareinstDeploy.setMidwareDeployDate(DateUtils.getDateTime());
		 midwareinstDeploy.setMidwareDeployUserid(userId);
		 midwareinstDeploy.setMidwareIntantId(midwareRedisint.getRedisintId());
		 midwareinstDeploy.setMidwareVerId(midwareRedisint.getMidwareVerId());
		 midwareinstDeploy.setMidwareType(SysConfigConstants.REDIS_MIDWARE);
		 log.info("midwareinstDeploy = "+midwareinstDeploy);
		 omsBaseDao.insert(midwareinstDeploy);
		 EcmMidwareRedisint updateMidwareRedisint =new EcmMidwareRedisint();
		 updateMidwareRedisint.setRedisintId(midwareRedisint.getRedisintId());
		 updateMidwareRedisint.setRedisintStatus(SysConfigConstants.APP_INTANTSTATUS_DEPLOYED);
		 omsBaseDao.updateByPrimaryKey(updateMidwareRedisint);
	 }
	/**
	 * 准备安装redis	 * 
	 * @param int  midwareId 中间件id
	 * @param String  userId 用户ID		 
	 */
	 @Transactional
	 public  void dePloyRedis(int  midwareId,String userId) {
		 List<EcmMidwareRedisint> redisintList= findAll(midwareId);
		 Map<String,List<EcmMidwareRedisint>> redisMap =findRedisMap(redisintList);
		 startDeploy(redisMap,userId);
	 }
	 //获取redis map集合
	 private Map<String,List<EcmMidwareRedisint>> findRedisMap(List<EcmMidwareRedisint> redisintList) {
		 Map<String,List<EcmMidwareRedisint>> redisMap = new HashMap<String,List<EcmMidwareRedisint>>();
		 for(EcmMidwareRedisint redisint: redisintList) {
			 List<EcmMidwareRedisint> redisListTmp = new ArrayList<EcmMidwareRedisint>();
			 if (redisMap.containsKey(redisint.getSerIp())) {
				 redisMap.get(redisint.getSerIp()).add(redisint);
			 } else {
				 redisMap.put(redisint.getSerIp(), redisListTmp);
				 redisMap.get(redisint.getSerIp()).add(redisint);
			 }
		 }
		 return redisMap;
	 }
    /**
	 * 开始安装redis	 * 
	 * @param  Map<String,List<EcmMidwareRedisint>> redisMap 实例Map对象,key为ip,value为ip所对应的实例
	 * @param String        userId 用户ID		 
	 */
	 private void startDeploy( Map<String,List<EcmMidwareRedisint>> redisMap, String userId ) {
		  for(String ip:redisMap.keySet()) {
			  ProgressMessageUtil.startProgress("" + userId, ip, redisMap.get(ip).get(0).getMidwareName());
			  redisIContainerImpl.assemContainer(redisMap,ip,userId+"");
			  for(EcmMidwareRedisint midwareRedisint:redisMap.get(ip) ) {
				   save(midwareRedisint, userId);
				   }
              }
		  ProgressMessageUtil.stopProgress("" + userId);
	 }
	 
	/**
	 * 获取集群哨兵列表
	 * @param  int  midwareId 集群Id
	 */ 
	public List<EcmMidwareRedisint> findSentinels(int  midwareId) {
		Map<String,Object> midwareMap=new HashMap<String,Object>();
		midwareMap.put("midwareId", midwareId);
		midwareMap.put("redisintType", SysConfigConstants.REDIS_SENTINEL);
		List<EcmMidwareRedisint> redisintList=omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList", midwareMap);
		return redisintList;
	} 
	

	/**
     * 集群所有redis实例启动
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */  
	public void startAll(int midwareId , String userId) {
		 List<EcmMidwareRedisint> redisintList= findAll(midwareId);
		 startOp(redisintList,userId);
	}
	/**
     * 集群所有redis实例停止
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */ 
	public void stopAll(int midwareId , String userId) {
		List<EcmMidwareRedisint> redisintList= findAll(midwareId);
		stopOp(redisintList,userId);
	}
	/**
     * 集群所有redis实例重启
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */  
	public void reStartAll(int midwareId , String userId) {
		 List<EcmMidwareRedisint> redisintList= findAll(midwareId);
		 reStartOp(redisintList,userId);
	}
	/**
     * 单redis实例启动
     * @param  String redisIdString  redisId字符串  
     * @param  String userId  用户ID
     */  
	public void start(String redisIdString , String userId) {
		String redisIdList = redisIdString.substring(1, redisIdString.length()-1);
		List<EcmMidwareRedisint> redisintList = findRedisint(redisIdList);
		startOp(redisintList,userId);
	}
	/**
     * 单redis实例停止
     * @param  String redisIdString  redisId字符串  
     * @param  String userId  用户ID
     */  
	public void stop(String redisIdString , String userId) {
		String redisIdList = redisIdString.substring(1, redisIdString.length()-1);
		List<EcmMidwareRedisint> redisintList = findRedisint(redisIdList);
		stopOp(redisintList,userId);
	}
	/**
     * redis实例重启
     * @param  List<EcmMidwareRedisint> redisintList 实例对象列表
     * @param  String userId  用户ID
     */ 
	private void reStartOp(List<EcmMidwareRedisint> redisintList, String userId) {
		for(EcmMidwareRedisint midwareRedisint:redisintList){
			ProgressMessageUtil.startProgress("" + userId, midwareRedisint.getSerIp(), midwareRedisint.getRedisintName());
			ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint,null);
			if(result.getCheckStatus()!=null&&result.getCheckStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)){
				 redisIContainerImpl.reStartContainer(midwareRedisint, ""+userId);
				 saveStartOrStopRedisintStatus(midwareRedisint, SysConfigConstants.APP_INTANTSTATUS_RESTART,userId);
 	         }
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	//找出被选对象实例
	private List<EcmMidwareRedisint> findRedisint(String redisIdList ) {
		Map<String,Object> redisintCond =new HashMap<String,Object>();
		redisintCond.put("redisIdList", redisIdList);
		List<EcmMidwareRedisint> redisintList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisit",redisintCond );
		return redisintList;
	}
	//实例启动操作
	private void startOp(List<EcmMidwareRedisint> redisintList, String userId) {
		for(EcmMidwareRedisint midwareRedisint:redisintList){
			ProgressMessageUtil.startProgress("" + userId, midwareRedisint.getSerIp(), midwareRedisint.getRedisintName());
			ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint,null);
			 if(result.getCheckStatus()!=null&&result.getCheckStatus()!= SysConfigConstants.APP_INTANTSTATUS_START){
				 redisIContainerImpl.startContainer(midwareRedisint,""+userId);
				 saveStartOrStopRedisintStatus(midwareRedisint, SysConfigConstants.APP_INTANTSTATUS_START,userId);
  	         }
			 
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	//实例停止操作
	private void stopOp(List<EcmMidwareRedisint> redisintList, String userId) {
		for(EcmMidwareRedisint midwareRedisint:redisintList) {
			ProgressMessageUtil.startProgress("" + userId, midwareRedisint.getSerIp(), midwareRedisint.getRedisintName());
			ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint,null);
			if(result.getCheckStatus()!=null&&result.getCheckStatus()!= SysConfigConstants.APP_INTANTSTATUS_STOP){
				 redisIContainerImpl.stopContainer(midwareRedisint,""+userId);  
				 saveStartOrStopRedisintStatus(midwareRedisint, SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
  	         }
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	
	/**
     * 根据中间件ID找出所有实例
     * @param  int midwareId  中间件ID     
     */ 
	
	private List<EcmMidwareRedisint> findAll(int midwareId) {
		 Map<String,Object> midwareMap=new HashMap<String,Object>();
		 midwareMap.put("midwareId", midwareId);
		 List<EcmMidwareRedisint> redisintList=omsBaseService.findListByCond(new EcmMidwareRedisint(),"findDepList", midwareMap);
		 return redisintList;
	}
	 /**
     * 停止或启动redis实例后，修改例状态，并添加实例操作记录
     * @param EcmMidwareRedisint  midwareRedisint    redis实例对象	
     * @param String intantStatus     redis实例状态
     * @param int    userId           用户ID 		     
     */
	@Transactional
	public void saveStartOrStopRedisintStatus(EcmMidwareRedisint midwareRedisint,String redisintStatus, String userId) {
		 EcmMidwareoprInfo operInfo = new EcmMidwareoprInfo();
		 int midwareOperId = serviceUtil.getMaxReqID(max_req_no,"ecm_midwareopr_info","MIDWARE_OPER_ID");
		 operInfo.setMidwareOperId(midwareOperId);
		 operInfo.setMidwareIntantId(midwareRedisint.getRedisintId());
		 operInfo.setMidwareOperTime(DateUtils.getDateTime());
		 operInfo.setMidwareOperType(redisintStatus);
		 operInfo.setMidwareOperUserid(userId);
		 operInfo.setMidwareType(SysConfigConstants.REDIS_MIDWARE);
		 log.info("operInfo = "+operInfo);
		 omsBaseDao.insert(operInfo);
		 if(redisintStatus!=null) {
		 EcmMidwareRedisint newMidwareRedisint = new EcmMidwareRedisint();
		 newMidwareRedisint.setRedisintId(midwareRedisint.getRedisintId());
		 newMidwareRedisint.setRedisintStatus(redisintStatus);
		 omsBaseDao.updateByPrimaryKey(newMidwareRedisint);
		 }
	}
	public void clear(int midwareId, String userId) {
		List<EcmMidwareRedisint> redisintList= findAll(midwareId);
		Map<String,List<EcmMidwareRedisint>> redisMap =findRedisMap(redisintList);
		for(String ip:redisMap.keySet()) {
			  ProgressMessageUtil.startProgress("" + userId, ip, redisMap.get(ip).get(0).getMidwareName());
			  ProgressMessageUtil.addProgressAction("" + userId, "Reids集群实例清理");
			  if(isExistFileOrDir(redisMap.get(ip).get(0))) {
				  deleteRedis(redisMap.get(ip).get(0));
			  }
            }
		for(EcmMidwareRedisint midwareRedisint :redisintList) {
		EcmMidwareRedisint updateMidwareRedisint =new EcmMidwareRedisint();
		updateMidwareRedisint.setRedisintId(midwareRedisint.getRedisintId());
		updateMidwareRedisint.setRedisintStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
		omsBaseDao.updateByPrimaryKey(updateMidwareRedisint);
		}
		  ProgressMessageUtil.stopProgress("" + userId);
	}
	/**
	 * 是否存在该文件或者目录 * 
	 * @param  EcmMidwareRedisint  midwareRedisint Redis实例对象
	 * @return boolean true 存在 false 不存在
	 */	
	private boolean isExistFileOrDir(EcmMidwareRedisint midwareRedisint) {
		try {
			log.info("start");
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			String cdCmd = cmd.cdCmd(midwareRedisint.getMidwarePath());//到应用目录
			String lsCmd = cmd.lsCmd("");//ls带应用实例ID的应用目录名
			ShellResult result=shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),
					cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
				   +lsCmd);			
			for(String rowStr:result.getOutList()){
				if(rowStr!=null&&rowStr.equals(REDIS_FLAG)){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log.error(IS_EXIST_DIRORFILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(IS_EXIST_DIRORFILE+"出错!");
		}
	}
	//删除安装后的redis jar包
	private void deleteRedis(EcmMidwareRedisint midwareRedisint ) {
		try {	
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),
					cmd.cdCmd(midwareRedisint.getMidwarePath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
							+cmd.rmrCmd(REDIS_FLAG)); 
		} catch (IllegalStateException e) {
			log.error(DELETE_FILE_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_FILE_OPERATION+"出错!");
		}
		
	}
}
