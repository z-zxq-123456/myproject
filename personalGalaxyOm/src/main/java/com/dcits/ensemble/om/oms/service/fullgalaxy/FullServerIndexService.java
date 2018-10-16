package com.dcits.ensemble.om.oms.service.fullgalaxy;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dcits.ensemble.om.oms.module.server.EcmServerAlarm;
import com.dcits.galaxy.base.util.DateUtils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.server.AbstractedMonitorServer;
import com.dcits.ensemble.om.oms.manager.server.IMonitorServer;
import com.dcits.ensemble.om.oms.manager.server.MonitorServerFactory;
import com.dcits.ensemble.om.oms.module.fullgalaxy.EcmServerIndex;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.galaxy.base.exception.GalaxyException;


/**
 * 定期采集服务器指标保存到数据库
 * <p>Created on 2017/01/09.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
@Service
public class FullServerIndexService {
	/**
     * 日志对象
     */
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	/**
     * 是否开启了服务器指标查指标轮询线程
     */
	private static boolean isStartThread = false;
	/**
     * 服务器指标查指标轮询线程锁，用于控制采集未完成前，不再采集
     */
	private static final ReadWriteLock readWirteLock =new ReentrantReadWriteLock();	
	/**
     * 查询周期 单位秒
     */
	private  int serverMoniPeriod = 30;
	
	/**
     * 最大服务器指标记录主键值
     */
	private static int max_ser_moni_Id = 0 ;

	private static int max_ser_alarm_Id = 0 ;
	@Resource
	MonitorServerFactory monitorServerFactory;
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	ParamComboxService paramComboxService;

	public static final String PECENT = "%";//百分号
	public static final String SPEED_UTIL = "KB/S";//宽带单位
	public static Map<Integer ,List<EcmServerIndex>> cpuAlarmMap = new HashMap<Integer ,List<EcmServerIndex>>();
	public static Map<Integer ,List<EcmServerIndex>> memoryAlarmMap = new HashMap<Integer ,List<EcmServerIndex>>();
	public static Map<Integer ,List<EcmServerIndex>> ioAlarmMap = new HashMap<Integer ,List<EcmServerIndex>>();
	public static Map<Integer ,List<EcmServerIndex>> netAlarmMap = new HashMap<Integer ,List<EcmServerIndex>>();
	
	/**
     * 启动服务器指标采集     
     */
	public synchronized void startCollSeverIndex(){
		if(!isStartThread){
			startThread();
	   }
	}
	/**
     * 启动线程开始服务器指标采集      
     */	
	private void startThread(){
		isStartThread = true;
		new ServerMoniManagerThread().start();
	}
	/**
     * 服务器指标采集线程     
     */	
	private class ServerMoniManagerThread extends Thread {
		 public void run(){
			 while(true){
				 Lock lock = readWirteLock.writeLock();				
			     if (lock.tryLock()) {
			    	 try {
			    		 List<EcmServerInfo>  servers = findFullServer();
			    		 List<EcmServerIndex> serverIndexList = Collections.synchronizedList(new ArrayList<EcmServerIndex>());
						 List<EcmServerAlarm> serverAlarmList = Collections.synchronizedList(new ArrayList<EcmServerAlarm>());
			    		 CountDownLatch latch = new CountDownLatch(servers.size());
			    		 for(EcmServerInfo serverInfo:servers){
			    			 new ServerMoniExecuteThread(serverInfo,serverIndexList,latch,serverAlarmList).start();
			    		 }
						 latch.await();
						 omsBaseDao.insertBatchList(serverAlarmList);
						 omsBaseDao.insertBatchList(serverIndexList);

			    	 }catch (InterruptedException e) {
						 e.printStackTrace();
					 }finally {
			              lock.unlock();
			         }
			     }
			     try {
			    	String refreshTime = paramComboxService.getParaRemark1(SysConfigConstants.FULL_GALAXY_REFRESH_TIME);
			    	if(!DataUtil.isNull(refreshTime)){
			    		serverMoniPeriod = Integer.parseInt(refreshTime);
			    	}
					Thread.sleep(serverMoniPeriod*1000);
				 } catch (InterruptedException e) {			
					log.error("服务器指标采集 出错:"+DataUtil.printErrorStack(e));
				 }
			 }
		 }
	}
	/**
     * 服务器指标采集线程     
     */
	private class ServerMoniExecuteThread extends Thread{
		private EcmServerInfo serverInfo;
		private List<EcmServerIndex> serverIndexList;
		private CountDownLatch latch;
		private List<EcmServerAlarm> serverAlarmList;
		public ServerMoniExecuteThread(EcmServerInfo serverInfo,List<EcmServerIndex> serverIndexList,CountDownLatch latch ,List<EcmServerAlarm> serverAlarmList){
			this.serverInfo = serverInfo;
			this.serverIndexList = serverIndexList;
			this.latch = latch;
			this.serverAlarmList = serverAlarmList;
		}
		public void run(){
			try{
				 IMonitorServer monitorServer = monitorServerFactory.getIMonitorServer(serverInfo.getSerOs());
	  		     Map<String,String>  serverInfoMap =monitorServer.executeMonitor(serverInfo);
	  		     EcmServerIndex serverIndex = new EcmServerIndex();
	  		     serverIndex.setSerMoniId(createServerIndexPk());
				 serverIndex.setSerId(serverInfo.getSerId());
	  		     serverIndex.setSerMoniDate(DateUtils.getDate());
	  		     serverIndex.setSerMoniTime(DateUtils.getDateTime(DateUtils.PATTERN_DEFAULT_TIME));
	  		     serverIndex.setSerMoniCpu(exchageUtil(serverInfoMap.get(AbstractedMonitorServer.CPU_MARK)));
	  		     serverIndex.setSerMoniMem(exchageUtil(serverInfoMap.get(AbstractedMonitorServer.MEM_MARK)));
	  		     serverIndex.setSerMoniIo(exchageUtil(serverInfoMap.get(AbstractedMonitorServer.IO_MARK)));
	  		     serverIndex.setSerMoniNet(exchageUtil(serverInfoMap.get(AbstractedMonitorServer.NET_MARK)));
				 alarmHandle(serverIndex, SysConfigConstants.CPU_ALARM_LEVER, serverAlarmList);
				 alarmHandle(serverIndex,SysConfigConstants.MEMORY_ALARM_LEVER,serverAlarmList);
				 alarmHandle(serverIndex,SysConfigConstants.IO_ALARM_LEVER,serverAlarmList);
				 alarmHandle(serverIndex,SysConfigConstants.NET_ALARM_LEVER,serverAlarmList);
	  		     serverIndexList.add(serverIndex);
			}catch(Exception e){
 				 log.error(DataUtil.printErrorStack(e));
 			}
			latch.countDown();
		}
	}

	private String exchageUtil(String oldname) {
		return oldname.replaceAll(PECENT,"").replaceAll(SPEED_UTIL,"");
	}
	/**
     * 查询所有服务器     
     */	
	private List<EcmServerInfo> findFullServer(){
		return omsBaseDao.findPageByCond(new EcmServerInfo(), 1, 50);
	}
	
	private  synchronized int createServerIndexPk(){
		max_ser_moni_Id = serviceUtil.getMaxReqID(max_ser_moni_Id,"ECM_SERVER_INDEX","SER_MONI_ID");
        return  max_ser_moni_Id;

	}

	private void alarmHandle(EcmServerIndex serverIndex ,String alarmType ,List<EcmServerAlarm> serverAlarmList) {
		//cpu判断环节
		if(alarmType.equals(SysConfigConstants.CPU_ALARM_LEVER)) {
			float nowCpu = abandonUint(serverIndex.getSerMoniCpu());
			float alarmCpu = abandonUint(paramComboxService.getParaRemark1(SysConfigConstants.CPU_ALARM_LEVER));
			log.info("当前cpu :" + nowCpu);
			if (nowCpu >= alarmCpu) {
				if (cpuAlarmMap.containsKey(serverIndex.getSerId())) {
					cpuAlarmMap.get(serverIndex.getSerId()).add(serverIndex);
					if (cpuAlarmMap.get(serverIndex.getSerId()).size() == Integer.parseInt(paramComboxService.getParaRemark1(SysConfigConstants.ALARM_SIZE))) {
						EcmServerAlarm serverAlarm = new EcmServerAlarm();
						serverAlarm.setSerAlarmId(createServerAlarmPk());
						serverAlarm.setSerId(serverIndex.getSerId());
						serverAlarm.setAlarmTime(serverIndex.getSerMoniDate() +  serverIndex.getSerMoniTime());
						serverAlarm.setAlarmType(SysConfigConstants.CPU_ALARM_LEVER);
						serverAlarm.setAlarmInfo("cpu负荷:" + serverIndex.getSerMoniCpu()+"%");
						serverAlarm.setHandleInfo("邮件警告");
						serverAlarmList.add(serverAlarm);
						cpuAlarmMap.remove(serverIndex.getSerId());
					}
				} else {
					List<EcmServerIndex> serverIndexList = new ArrayList<EcmServerIndex>();
					serverIndexList.add(serverIndex);
					cpuAlarmMap.put(serverIndex.getSerId(), serverIndexList);
				}
			} else {
				if (cpuAlarmMap.containsKey(serverIndex.getSerId())) {
					cpuAlarmMap.remove(serverIndex.getSerId());
				}
			}
		}
		//memory判断环节
		if(alarmType.equals(SysConfigConstants.MEMORY_ALARM_LEVER)) {
			float nowMemory = abandonUint(serverIndex.getSerMoniMem());
			float alarmMemory= abandonUint(paramComboxService.getParaRemark1(SysConfigConstants.MEMORY_ALARM_LEVER));
			log.info("当前memory :" + nowMemory);
			if (nowMemory >= alarmMemory) {
				if (memoryAlarmMap.containsKey(serverIndex.getSerId())) {
					memoryAlarmMap.get(serverIndex.getSerId()).add(serverIndex);
					if (memoryAlarmMap.get(serverIndex.getSerId()).size() == Integer.parseInt(paramComboxService.getParaRemark1(SysConfigConstants.ALARM_SIZE))) {
						EcmServerAlarm serverAlarm = new EcmServerAlarm();
						serverAlarm.setSerAlarmId(createServerAlarmPk());
						serverAlarm.setSerId(serverIndex.getSerId());
						serverAlarm.setAlarmTime(serverIndex.getSerMoniDate() + serverIndex.getSerMoniTime());
						serverAlarm.setAlarmType(SysConfigConstants.MEMORY_ALARM_LEVER);
						serverAlarm.setAlarmInfo("memroy负荷:" + serverIndex.getSerMoniMem() + "%");
						serverAlarm.setHandleInfo("邮件警告");
						serverAlarmList.add(serverAlarm);
						memoryAlarmMap.remove(serverIndex.getSerId());
					}
				} else {
					List<EcmServerIndex> serverIndexList = new ArrayList<EcmServerIndex>();
					serverIndexList.add(serverIndex);
					memoryAlarmMap.put(serverIndex.getSerId(), serverIndexList);
				}
			} else {
				if (memoryAlarmMap.containsKey(serverIndex.getSerId())) {
					memoryAlarmMap.remove(serverIndex.getSerId());
				}
			}
		}
		//io判断环节
		if(alarmType.equals(SysConfigConstants.IO_ALARM_LEVER)) {
			float nowIo = abandonUint(serverIndex.getSerMoniIo());
			float alarmIo = abandonUint(paramComboxService.getParaRemark1(SysConfigConstants.IO_ALARM_LEVER));
			log.info("当前io :" + nowIo);
			if (nowIo >= alarmIo) {
				if (ioAlarmMap.containsKey(serverIndex.getSerId())) {
					ioAlarmMap.get(serverIndex.getSerId()).add(serverIndex);
					if (ioAlarmMap.get(serverIndex.getSerId()).size() == Integer.parseInt(paramComboxService.getParaRemark1(SysConfigConstants.ALARM_SIZE))) {
						EcmServerAlarm serverAlarm = new EcmServerAlarm();
						serverAlarm.setSerAlarmId(createServerAlarmPk());
						serverAlarm.setSerId(serverIndex.getSerId());
						serverAlarm.setAlarmTime(serverIndex.getSerMoniDate() + serverIndex.getSerMoniTime());
						serverAlarm.setAlarmType(SysConfigConstants.IO_ALARM_LEVER);
						serverAlarm.setAlarmInfo("io负荷:" + serverIndex.getSerMoniIo()+"%");
						serverAlarm.setHandleInfo("邮件警告");
						serverAlarmList.add(serverAlarm);
						ioAlarmMap.remove(serverIndex.getSerId());
					}
				} else {
					List<EcmServerIndex> serverIndexList = new ArrayList<EcmServerIndex>();
					serverIndexList.add(serverIndex);
					ioAlarmMap.put(serverIndex.getSerId(), serverIndexList);
				}
			} else {
				if (ioAlarmMap.containsKey(serverIndex.getSerId())) {
					ioAlarmMap.remove(serverIndex.getSerId());
				}
			}
		}
		//net判断环节
		if(alarmType.equals(SysConfigConstants.NET_ALARM_LEVER)) {
			float nowNet = abandonUint(serverIndex.getSerMoniNet());
			float alarmNet = abandonUint(paramComboxService.getParaRemark1(SysConfigConstants.NET_ALARM_LEVER));
			log.info("当前net :" + nowNet);
			if (nowNet >= alarmNet) {
				if (netAlarmMap.containsKey(serverIndex.getSerId())) {
					netAlarmMap.get(serverIndex.getSerId()).add(serverIndex);
					if (netAlarmMap.get(serverIndex.getSerId()).size() == Integer.parseInt(paramComboxService.getParaRemark1(SysConfigConstants.ALARM_SIZE))) {
						EcmServerAlarm serverAlarm = new EcmServerAlarm();
						serverAlarm.setSerAlarmId(createServerAlarmPk());
						serverAlarm.setSerId(serverIndex.getSerId());
						serverAlarm.setAlarmTime(serverIndex.getSerMoniDate()  + serverIndex.getSerMoniTime());
						serverAlarm.setAlarmType(SysConfigConstants.NET_ALARM_LEVER);
						serverAlarm.setAlarmInfo("net负荷:" + serverIndex.getSerMoniNet()+"KB/S");
						serverAlarm.setHandleInfo("邮件警告");
						serverAlarmList.add(serverAlarm);
						netAlarmMap.remove(serverIndex.getSerId());
					}
				} else {
					List<EcmServerIndex> serverIndexList = new ArrayList<EcmServerIndex>();
					serverIndexList.add(serverIndex);
					netAlarmMap.put(serverIndex.getSerId(), serverIndexList);
				}
			} else {
				if (netAlarmMap.containsKey(serverIndex.getSerId())) {
					netAlarmMap.remove(serverIndex.getSerId());
				}
			}
		}
	}
	private float abandonUint(String str) {
		return Float.parseFloat(str.replaceAll("%", "").replaceAll("KB/S", ""));
	}

	private  synchronized int createServerAlarmPk(){
		max_ser_alarm_Id = serviceUtil.getMaxReqID(max_ser_alarm_Id,"ECM_SERVER_ALARM","SER_ALARM_ID");
		return  max_ser_alarm_Id;

	}
	
}
