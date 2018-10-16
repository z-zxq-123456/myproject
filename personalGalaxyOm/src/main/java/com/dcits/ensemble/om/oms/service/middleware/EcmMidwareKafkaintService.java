package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.kafka.KafkaIContainerImpl;
import com.dcits.ensemble.om.oms.manager.kafka.KafkaStatusCheck;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareinstDeploy;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareoprInfo;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.DateUtils;
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
 * EcmMidwareKafkaintService* 
 * @author luolang
 * @date 2016-04-27
 */
@Service
public class EcmMidwareKafkaintService {
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
	KafkaIContainerImpl kafkaIContainerImpl;
	@Resource
	ShellExcuteService shellService;
	private int max_midware_deploy_Id = 0;
	
	private int max_req_no = 0;
	
	private static final String IS_EXIST_DIRORFILE ="是否存在文件或者目录";
	/**
	 * 记录部署信息	 * 
	 * @param EcmMidwareKafkaint  midwareKafkaint 实例对象
	 * @param String  userId 用户ID		 
	 */
	 @Transactional
	 private void save(EcmMidwareKafkaint midwareKafkaint, String userId) {
		 EcmMidwareinstDeploy midwareinstDeploy = new EcmMidwareinstDeploy();
		 int midwareDeployId=serviceUtil.getMaxReqID(max_midware_deploy_Id,"ecm_midwareinst_deploy","MIDWARE_DEPLOY_ID");
		 midwareinstDeploy.setMidwareDeployId(midwareDeployId);
		 midwareinstDeploy.setMidwareDeployDate(DateUtils.getDateTime());
		 midwareinstDeploy.setMidwareDeployUserid(userId);
		 midwareinstDeploy.setMidwareIntantId(midwareKafkaint.getKafkaintId());
		 midwareinstDeploy.setMidwareVerId(midwareKafkaint.getMidwareVerId());
		 midwareinstDeploy.setMidwareType(SysConfigConstants.KAFKA_MIDWARE);
		 log.info("midwareinstDeploy = "+midwareinstDeploy);
		 omsBaseDao.insert(midwareinstDeploy);
		 EcmMidwareKafkaint updateMidwareKafkaint =new EcmMidwareKafkaint();
		 updateMidwareKafkaint.setKafkaintId(midwareKafkaint.getKafkaintId());
		 updateMidwareKafkaint.setKafkaintStatus(SysConfigConstants.APP_INTANTSTATUS_DEPLOYED);
		 omsBaseDao.updateByPrimaryKey(updateMidwareKafkaint);
	 }
	/**
	 * 准备安装kafka	 * 
	 * @param int  midwareId 中间件id
	 * @param String  userId 用户ID		 
	 */
	 @Transactional
	 public  void dePloyKafka(int  midwareId,String userId) {
		 List<EcmMidwareKafkaint> kafkaintList= findAllById(midwareId);
		 Map<String,List<EcmMidwareKafkaint>> kafkaMap =findKafkaMap(kafkaintList);
		 startDeploy(kafkaMap,userId);
	 }
	 //获取kafka map集合
	 private Map<String,List<EcmMidwareKafkaint>> findKafkaMap(List<EcmMidwareKafkaint> kafkaintList) {
		 String ipTmp="xx";
		 Map<String,List<EcmMidwareKafkaint>> kafkaMap = new HashMap<String,List<EcmMidwareKafkaint>>();
		 for(EcmMidwareKafkaint kafkaint: kafkaintList) {
				List<EcmMidwareKafkaint> kafkaListTmp=new ArrayList<EcmMidwareKafkaint>();
	            if(!kafkaint.getSerIp().equals(ipTmp)) {
	               if(kafkaMap.containsKey(kafkaint.getSerIp())) {
	            	  kafkaMap.get(kafkaint.getSerIp()).add(kafkaint);  
	               }else {
	        	      kafkaMap.put(kafkaint.getSerIp(), kafkaListTmp);
	        	      kafkaMap.get(kafkaint.getSerIp()).add(kafkaint);
	               }
	           }else {
	        	   kafkaMap.get(kafkaint.getSerIp()).add(kafkaint);
	           }
	           ipTmp=kafkaint.getSerIp();
			}
		 return kafkaMap;
		
	 }
    /**
	 * 开始安装kafka	 * 
	 * @param  Map<String,List<EcmMidwareKafkaint>> kafkaMap 实例Map对象,key为ip,value为ip所对应的实例
	 * @param String        userId 用户ID		 
	 */
	 private void startDeploy( Map<String,List<EcmMidwareKafkaint>> kafkaMap, String userId ) {
		  for(String ip:kafkaMap.keySet()) {
			  ProgressMessageUtil.startProgress("" + userId, ip, kafkaMap.get(ip).get(0).getMidwareName());
			  kafkaIContainerImpl.assemContainer(kafkaMap,ip,userId+"");
			  for(EcmMidwareKafkaint midwareKafkaint:kafkaMap.get(ip) ) {
				   save(midwareKafkaint, userId);
			  }
          }
		  ProgressMessageUtil.stopProgress("" + userId);
	 }
	 
	/**
     * 集群所有kafka实例启动
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */  
	public void startAll(int midwareId , String userId) {
		 List<EcmMidwareKafkaint> kafkaintList= findAllById(midwareId);
		 startOp(kafkaintList, userId);
	}
	/**
     * 集群所有kafka实例停止
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */ 
	public void stopAll(int midwareId , String userId) {
		List<EcmMidwareKafkaint> kafkaintList= findAllById(midwareId);
		Map<String ,List<EcmMidwareKafkaint>> kafkaMap = getSerMap(kafkaintList);
		stopOp(kafkaMap, userId);
	}

	private Map<String ,List<EcmMidwareKafkaint>> getSerMap(List<EcmMidwareKafkaint> kafkaintList) {
		Map<String ,List<EcmMidwareKafkaint>> kafkaMap = new HashMap<String ,List<EcmMidwareKafkaint>>();
		for(EcmMidwareKafkaint kafkaint : kafkaintList) {
            if(kafkaMap.containsKey(kafkaint.getSerIp())) {
				kafkaMap.get(kafkaint.getSerIp()).add(kafkaint);
			}else {
				List<EcmMidwareKafkaint> kafkaIntantList = new ArrayList<EcmMidwareKafkaint>();
				kafkaIntantList.add(kafkaint);
				kafkaMap.put(kafkaint.getSerIp(),kafkaIntantList);
			}
		}
		return kafkaMap;
	}
	/**
     * 集群所有kafka实例重启
     * @param  Integer midwareId  中间件ID
     * @param  String userId  用户ID
     */  
	public void reStartAll(int midwareId , String userId) {
		 List<EcmMidwareKafkaint> kafkaintList= findAllById(midwareId);
		 Map<String ,List<EcmMidwareKafkaint>> kafkaMap = getSerMap(kafkaintList);
		 reStartOp(kafkaMap, userId);
	}

	/**
     * kafka实例重启
     * @param  List<EcmMidwareKafkaint> kafkaintList 实例对象列表
     * @param  String userId  用户ID
     */ 
	private void reStartOp(Map<String ,List<EcmMidwareKafkaint>> kafkaMap, String userId) {
		for (Map.Entry<String ,List<EcmMidwareKafkaint>> all :kafkaMap.entrySet() ) {
			boolean flag = false;
			StringBuffer sbu = new StringBuffer("");
			String name ="";
			for(EcmMidwareKafkaint midwareKafkaint : all.getValue()) {
				sbu = sbu.append(midwareKafkaint.getKafkaintName()+",");
			}
			if(sbu.toString().length()>1) {
				name = sbu.toString().substring(0,sbu.toString().length()-2);
			}
			ProgressMessageUtil.startProgress("" + userId, all.getKey(), name);
			List<EcmMidwareKafkaint> kafkaintList = new ArrayList<EcmMidwareKafkaint>();
			for(EcmMidwareKafkaint midwareKafkaint : all.getValue()) {
				ContainerCheckResult result = kafkaIContainerImpl.checkContainerStatus(midwareKafkaint,null);
				if(result.getCheckStatus()!=null&&result.getCheckStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)) {
					flag=true;
					kafkaintList.add(midwareKafkaint);
				}else {
					continue;
				}
			}
			if(flag) {
				kafkaIContainerImpl.stopContainer(all.getValue().get(0), "" + userId);
				if(kafkaintList.size()>0) {
					for(EcmMidwareKafkaint midwareKafkaint :kafkaintList) {
						kafkaIContainerImpl.startContainer(midwareKafkaint,""+userId);
						saveStartOrStopKafkaintStatus(midwareKafkaint, SysConfigConstants.APP_INTANTSTATUS_RESTART, userId);
					}
				}

			}
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	//实例启动操作
	private void startOp(List<EcmMidwareKafkaint> kafkaintList, String userId) {
		for(EcmMidwareKafkaint midwareKafkaint:kafkaintList){
			ProgressMessageUtil.startProgress("" + userId, midwareKafkaint.getSerIp(), midwareKafkaint.getKafkaintName());
			ContainerCheckResult result = kafkaIContainerImpl.checkContainerStatus(midwareKafkaint,null);
			 if(result.getCheckStatus()!=null&&result.getCheckStatus()!= SysConfigConstants.APP_INTANTSTATUS_START){
				 kafkaIContainerImpl.startContainer(midwareKafkaint, "" + userId);
				 saveStartOrStopKafkaintStatus(midwareKafkaint, SysConfigConstants.APP_INTANTSTATUS_START, userId);
  	         }
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	//实例停止操作
	private void stopOp(Map<String ,List<EcmMidwareKafkaint>> kafkaMap, String userId) {
		for (Map.Entry<String ,List<EcmMidwareKafkaint>> all :kafkaMap.entrySet() ) {
			StringBuffer sbu = new StringBuffer("");
			String name ="";
			for(EcmMidwareKafkaint midwareKafkaint : all.getValue()) {
				sbu = sbu.append(midwareKafkaint.getKafkaintName()+",");
			}
			if(sbu.toString().length()>1) {
				name = sbu.toString().substring(0,sbu.toString().length()-1);
			}
			ProgressMessageUtil.startProgress("" + userId, all.getKey(), name);
			for(EcmMidwareKafkaint midwareKafkaint : all.getValue()) {
				saveStartOrStopKafkaintStatus(midwareKafkaint, SysConfigConstants.APP_INTANTSTATUS_STOP, userId);
			}
		    kafkaIContainerImpl.stopContainer(all.getValue().get(0), "" + userId);
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}

	public List<EcmMidwareKafkaint> findAllKafkaIntants() {
		return omsBaseService.findListByCond(new EcmMidwareKafkaint());
	}
	/**
     * 根据中间件ID找出所有实例
     * @param  int midwareId  中间件ID     
     */
	public List<EcmMidwareKafkaint> findAllById(int midwareId) {
		 Map<String,Object> midwareMap=new HashMap<String,Object>();
		 midwareMap.put("midwareId", midwareId);
		 List<EcmMidwareKafkaint> kafkaintList = omsBaseService.findListByCond(new EcmMidwareKafkaint(), midwareMap);
		 return kafkaintList;
	}
	 /**
     * 停止或启动kafka实例后，修改例状态，并添加实例操作记录
     * @param EcmMidwareKafkaint  midwareKafkaint    kafka实例对象	
     * @param String intantStatus     kafka实例状态
     * @param int    userId           用户ID 		     
     */
	@Transactional
	public void saveStartOrStopKafkaintStatus(EcmMidwareKafkaint midwareKafkaint,String kafkaintStatus, String userId) {
		 EcmMidwareoprInfo operInfo = new EcmMidwareoprInfo();
		 int midwareOperId = serviceUtil.getMaxReqID(max_req_no, "ecm_midwareopr_info", "MIDWARE_OPER_ID");
		 operInfo.setMidwareOperId(midwareOperId);
		 operInfo.setMidwareIntantId(midwareKafkaint.getKafkaintId());
		 operInfo.setMidwareOperTime(DateUtils.getDateTime());
		 operInfo.setMidwareOperType(kafkaintStatus);
		 operInfo.setMidwareOperUserid(userId);
		 operInfo.setMidwareType(SysConfigConstants.REDIS_MIDWARE);
		 log.info("operInfo = "+operInfo);
		 omsBaseDao.insert(operInfo);
		 if(kafkaintStatus!=null) {
		 EcmMidwareKafkaint newMidwareKafkaint = new EcmMidwareKafkaint();
		 newMidwareKafkaint.setKafkaintId(midwareKafkaint.getKafkaintId());
		 newMidwareKafkaint.setKafkaintStatus(kafkaintStatus);
		 omsBaseDao.updateByPrimaryKey(newMidwareKafkaint);
		 }
	}
	public void clear(int midwareId, String userId) {
		List<EcmMidwareKafkaint> kafkaintList= findAllById(midwareId);
		Map<String,List<EcmMidwareKafkaint>> kafkaMap =findKafkaMap(kafkaintList);
		for(String ip:kafkaMap.keySet()) {
			  ProgressMessageUtil.startProgress("" + userId, ip, kafkaMap.get(ip).get(0).getMidwareName());
			  ProgressMessageUtil.addProgressAction("" + userId, "Kafka集群实例清理");
			  if(isExistFileOrDir(kafkaMap.get(ip).get(0))) {
				  kafkaIContainerImpl.clearContainer(kafkaMap.get(ip).get(0),userId);
			  }
            }
		for(EcmMidwareKafkaint midwareKafkaint :kafkaintList) {
		EcmMidwareKafkaint updateMidwareKafkaint =new EcmMidwareKafkaint();
		updateMidwareKafkaint.setKafkaintId(midwareKafkaint.getKafkaintId());
		updateMidwareKafkaint.setKafkaintStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
		omsBaseDao.updateByPrimaryKey(updateMidwareKafkaint);
		}
		ProgressMessageUtil.stopProgress("" + userId);
	}
	/**
	 * zkNodeUrl---格式为192.168.165.161：8080，192.168.165.11：8080（举例）.
	 * @param int  midwareId 中间件Id
	 * @return  String  字符串
	 */
	 public String brokList(int  midwareId ){
		List<EcmMidwareKafkaint> kafkaintList =findAllById(midwareId);
		if(kafkaintList.isEmpty()){
			throw new GalaxyException("应用登记kafka集群没有对应的kafka实例!");
		}
		StringBuilder kafkaStrBuilder = new StringBuilder();
		for(int i =0 ; i <kafkaintList.size() ; i++) {
			kafkaStrBuilder.append(kafkaintList.get(i).getSerIp()+":"+kafkaintList.get(i).getKafkaintPort()+",");
		}
		return kafkaStrBuilder.deleteCharAt(kafkaStrBuilder.length()-1).toString();
	}
	/**
	 * 是否存在该文件或者目录 * 
	 * @param  EcmMidwareKafkaint  midwareKafkaint Kafka实例对象
	 * @return boolean true 存在 false 不存在
	 */	
	private boolean isExistFileOrDir(EcmMidwareKafkaint midwareKafkaint) {
		try {
			log.info("start");
			ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
			String cdCmd = cmd.cdCmd(midwareKafkaint.getMidwarePath());//到应用目录
			String lsCmd = cmd.lsCmd("");//ls带应用实例ID的应用目录名
			ShellResult result = shellService.doCmd(midwareKafkaint.getSerIp(), midwareKafkaint.getSerUser(), midwareKafkaint.getSerPwd(),
					cdCmd + SysConfigConstants.SHELL_LINK_SIGN
				   +lsCmd);			
			for(String rowStr:result.getOutList()){
				if(rowStr!=null&&rowStr.equals(kafkaIContainerImpl.getKafkaWorkName(midwareKafkaint))){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log.error(IS_EXIST_DIRORFILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(IS_EXIST_DIRORFILE+"出错!");
		}
	}

}
