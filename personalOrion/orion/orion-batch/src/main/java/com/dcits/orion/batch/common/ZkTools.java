package com.dcits.orion.batch.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.IRunTask;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/12/9.
 */
@Repository
public class ZkTools extends ZkCommon implements ApplicationListener<ContextRefreshedEvent>, IZkChildListener,IZkDataListener,IZkStateListener,DisposableBean {
    @Resource
    BatchDao batchDao;

    @Resource
    AutoMapperDao autoMapperDao;

    ZkTools zkTools;

    private static Logger logger = LoggerFactory.getLogger(ZkTools.class);
    private String loadBalance;


    private static class SystemInfo
    {
        String systemId;
        int capacity = 0;//任务处理能力
        int occupy = 0;//正在处理的任务数
    }



    private SystemInfo getSystemInfo(String systemId)
    {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.systemId = systemId;
        String taskPath = tasks_path + "/" + systemId;
        if (!zkClient.exists(taskPath)) {
            zkClient.createPersistent(taskPath, true);
        }
        else {
            List<String> tasks = zkClient.getChildren(taskPath);
            if (tasks != null)
            systemInfo.occupy = tasks.size();
        }
        String machinePath = task_machine_path + "/" + systemId;
        if (!zkClient.exists(machinePath)) {
            zkClient.createPersistent(machinePath, true);
        }
        else {
            List<String> machines = zkClient.getChildren(machinePath);
            if (machines != null && machines.size() > 0)
            {
                for (String machine : machines){
                    String machineInfo =  zkClient.readData(machinePath+"/"+machine);;
                    int maxThread = Integer.parseInt(machineInfo);
                    systemInfo.capacity += maxThread;
                }
            }
        }
        return systemInfo;

    }

    //分发任务，主调用
    public boolean dispatchTasks(List<ITaskParam> tasks,Map<String,Map> taskMaps)
    {
        Map<String,SystemInfo> systemInfoMap = new HashMap<>();
        boolean ret  = true;
        try {
            Map<String, String> groups = new HashMap<String, String>();
            for (ITaskParam task : tasks) {
                if (StringUtils.isBlank(task.getSystemId()))
                    task.setSystemId(group);
                SystemInfo systemInfo = systemInfoMap.get(task.getSystemId());
                if (systemInfo == null)
                {
                    systemInfo = getSystemInfo(task.getSystemId());
                    systemInfoMap.put(task.getSystemId(),systemInfo);
                }
                if (systemInfo.occupy < systemInfo.capacity)
                {
                    //if (checkMutex(task.getSystemId(), task.getJobId(), task.getSchemaId())) {
                        task.setSendGroup(group);
                        Map taskMap = taskMaps.get(task.getTaskId());
                        if (updateTaskStatus(task, taskMap)) {
                            if (!rpcRunTask(task))
                                groups.put(task.getSystemId(), task.getSystemId());
                        }

                    //}
                    systemInfo.occupy++;
                }
            }
            for (String group : groups.values())//更改状态
            {
                noteTask(group);
            }
        }
        catch (Exception e)
        {
            ret = false;
        }
        return ret;
    }


    private boolean updateTaskStatus(ITaskParam task,Map taskMap)
    {
        boolean ret = false;
        try {
            if (!zkClient.exists(tasks_path + "/" + task.getSystemId()))
                zkClient.createPersistent(tasks_path + "/" + task.getSystemId(), true);
            String taskPath = tasks_path + "/" + task.getSystemId() + "/" + task.getTaskId();
            if (!zkClient.exists(taskPath)) {
                zkClient.createPersistent(taskPath, task);
                //createMutex(task.getSystemId(), task.getJobId(), task.getSchemaId());
            }
            ITaskParam taskParam = zkClient.readData(taskPath);
            if (taskParam != null)
            {

                if (taskMap.containsKey("R"))
                    taskMap.remove("R");
                Map taskStatus = new HashMap();

                taskStatus.put("TASK_ID",task.getTaskId());
                taskStatus.put("TASK_STATUS", "P");
                batchDao.updateRunTask(taskStatus);
                ret = true;
            }

        }
        catch (Exception e)
        {
           if (logger.isErrorEnabled())
               logger.error(ExceptionUtils.getStackTrace(e));

        }
        return  ret;
    }

    private boolean taskToZk(ITaskParam task)
    {

        boolean ret = false;
        try {
                if (!zkClient.exists(tasks_path + "/" + task.getSystemId()))
                    zkClient.createPersistent(tasks_path + "/" + task.getSystemId(), true);
                String taskPath = tasks_path + "/" + task.getSystemId() + "/" + task.getTaskId();
                if (!zkClient.exists(taskPath))
                    zkClient.createPersistent(taskPath, task);
                ITaskParam taskParam = zkClient.readData(taskPath);
                if (taskParam != null)
                {
                    ret  =true;
                }
        }
        catch (Exception e)
        {
            if (logger.isWarnEnabled())
                logger.warn(ExceptionUtils.getStackTrace(e));
        }
        return  ret;

    }


    private boolean rpcRunTask(ITaskParam taskParam)
    {
        boolean ret  = false;
        try{

            ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                    .setLoadbalance(loadBalance)
                    .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                    .setCheck(true)
                    .setGroup(taskParam.getSystemId());
            IRunTask iRunTask = ServiceProxy.getInstance().getService(IRunTask.class, builder.build());
            ret = iRunTask.runTask(taskParam);

        }
        catch (Exception e)
        {
            logger.info("RPC调用失败");
        }
        return ret;

    }

    public boolean rpcTimerRunTask(ITaskParam taskParam)
    {
        boolean ret  = false;
        try{
            if (taskToZk(taskParam))
            {
                ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                        .setLoadbalance(loadBalance)
                        .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                        .setCheck(true)
                        .setGroup(taskParam.getSystemId());
                IRunTask iRunTask = ServiceProxy.getInstance().getService(IRunTask.class, builder.build());
                ret = iRunTask.runTimerTask(taskParam);
            }
        }
        catch (Exception e)
        {
            logger.info("RPC调用失败");
        }
        return ret;

    }
    //add by qiqingshan
    public boolean rpcKillTimer(ITaskParam taskParam){
        boolean ret = false;
        String path = "/galaxy/threads";
        String threadLockPath = path+"/"+taskParam.getSystemId()+"/"+"lock";
            try{
                if(zkClient.exists(threadLockPath)){
                   String nodeData = zkClient.readData(threadLockPath, true);
                        String ip = nodeData.split("-")[0];
                        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                                .setLoadbalance(loadBalance)
                                .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                                .setCheck(true)
                                .setGroup(taskParam.getSystemId())
                                .setUrl("dubbo://" + ip);
                        IQuartzManager iQuartzManager = ServiceProxy.getInstance().getService(IQuartzManager.class,builder.build());
                        ret = iQuartzManager.killTimer(taskParam);
                }
            }catch(Exception e){
                e.printStackTrace();
                logger.info("RPC删除定时任务调用失败");
            }
        return ret;
    }

    public void rpcStartTimer(ITaskParam taskParam ,String cronExpression){
        String path = "/galaxy/threads";
        String threadLockPath = path+"/"+taskParam.getSystemId()+"/"+"lock";
        try{
            if(zkClient.exists(threadLockPath)){
                String nodeData = zkClient.readData(threadLockPath,true);
                    String ip = nodeData.split("-")[0];
                    ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                            .setLoadbalance(loadBalance)
                            .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                            .setCheck(true)
                            .setGroup(taskParam.getSystemId())
                            .setUrl("dubbo://" + ip);
                    IQuartzManager iQuartzManager = ServiceProxy.getInstance().getService(IQuartzManager.class,builder.build());
                    iQuartzManager.startTimer(taskParam,cronExpression);
            }
        }catch(Exception e){
            logger.info("RPC启动定时任务调用失败");
        }
    }



    public String machineReg()
    {
        String path = schedule_machine_path+"/" +group + "/" +getSelfName();
        if (!zkClient.exists(schedule_machine_path))
            zkClient.createPersistent(schedule_machine_path, true);
        if(!zkClient.exists(path))
            zkReg(path);
        return  path;
    }

    //查询BATCH_MACHINE表判断当前机器是否是主调机器
    private boolean isSchedule()
    {
        boolean ret = true;
        //尝试做主调前，先判断本机能不能做主调
        CommTableObj commTableObj = new CommTableObj("BATCH_MACHINE");
        Map map = null;
        try
        {
            map = autoMapperDao.selectCount(commTableObj);
            int  ROW_COUNT = BatchUtils.parseInt(map.get("ROW_COUNT"));
            if (ROW_COUNT == 0)
              return true;
            commTableObj = new CommTableObj("BATCH_MACHINE");
            commTableObj.setCondition("GROUP_ID",group);
            commTableObj.setCondition("IP", BatchUtils.getLocalIP());
            map = autoMapperDao.selectCount(commTableObj);
            ROW_COUNT = BatchUtils.parseInt(map.get("ROW_COUNT"));
            if (ROW_COUNT == 0)
                ret = false;

        }
        catch (Exception e)
        {
            logger.warn("BATCH_MACHINE表结构不存在！");

        }
        return ret;
    }


    public void toSchedule()
    {


            List<Map> runBatchs = batchDao.getRunBatchs();
            for (Map runBatch:runBatchs)
            {
                if (lockBatch((String) runBatch.get("BATCH_CLASS")))
                {
                    startSchedule((String)runBatch.get("BATCH_CLASS"));
                }
            }

    }


    //通知所有系统
    public void noteAll()
    {
        if (zkClient.exists(tasks_path))
        {
            List<String> groups = zkClient.getChildren(tasks_path);
            for (String group:groups)
            {
                noteTask(group);
            }
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateBatchStatus(String batchClass, String batchInd)
    {
        Map BATCH_STATUS = batchDao.getBatchStatus(batchClass);
        BATCH_STATUS.put("BATCH_IND", batchInd);
        batchDao.updateBatchStatus(BATCH_STATUS);

    }

    public void startSchedule(String batchClass)
    {
        try {
            String batchInd = BatchUtils.getBatchInd();
            zkTools.updateBatchStatus(batchClass, batchInd);

            new Thread(new ScheduleThread(batchClass, batchInd, batchDao, this)).start();
            new Thread(new ListennerThread(batchClass, batchInd, batchDao, this)).start();
            new Thread(new DispatchTaskThread(batchClass, batchInd, batchDao, this)).start();
            noteAll();
        }
        catch (Exception e)
        {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        }

    }








    public void setListener()
    {
        zkClient.subscribeStateChanges(this);
        String notePath=schedule_note_path + "/" + group;
        if (!zkClient.exists(notePath))
        {
            zkClient.createPersistent(notePath,true);
        }
        zkClient.subscribeDataChanges(notePath, this);
        String machinePath=schedule_machine_path + "/" + group;
        if (!zkClient.exists(machinePath))
        {
            zkClient.createPersistent(machinePath,true);
        }
        zkClient.subscribeChildChanges(machinePath, this);
    }

    public boolean lockBatch(String batchClass)
    {
        return lock(schedule_path + "/" + group + "/" + batchClass);

    }

    public boolean unlockBatch(String batchClass)
    {
        return unlock(schedule_path + "/" + group + "/" + batchClass);

    }






    public void init()
    {
        if (isSchedule())
        {
            setListener();
            machineReg();
            //dealUncompletedSplitJob();
            toSchedule();
        }


    }
    //服务器启动或退出事件
    @Override
    public void handleChildChange(String path, List<String> list) throws Exception {
        init();
    }

    //批处理启动事件
    @Override
    public void handleDataChange(String s, Object o) throws Exception {

        toSchedule();


    }
    //批处理启动删除事件（不存在）
    @Override
    public void handleDataDeleted(String s) throws Exception {

    }
    //与ZK服务器连接状态变化
    @Override
    public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
        if("SyncConnected".equals(keeperState.name()))
        {
            init();
        }
    }


    @Override
    public void handleNewSession() throws Exception {

    }


    //保存正在分段的信息，防止系统挂掉，分段信息丢失
    public void saveSplitJob(String JOB_ID)
    {
        String path = split_job_path+"/"+JOB_ID;
        zkClient.createPersistent(path, true);
        lock(path);
    }
    public void deleteSplitJob(String JOB_ID)
    {
        String path = split_job_path+"/"+JOB_ID;
        unlock(path);
        zkClient.delete(path);
    }
    public List getUncompletedSplitJob()
    {

        List retList = new ArrayList();
        if(zkClient.exists(split_job_path))
        {
            List<String> unCompleteSplitJobs = zkClient.getChildren(split_job_path);
            for (String unCompleteSplitJob:unCompleteSplitJobs)
            {
                String childPath = split_job_path+"/"+unCompleteSplitJob;
                if (lock(childPath))
                {
                    retList.add(unCompleteSplitJob);
                }
            }
        }
        return retList;
    }
    protected void dealUncompletedSplitJob()
    {

        List<String> unCompleteSplitJobs = getUncompletedSplitJob();
        for (String unCompleteSplitJob :unCompleteSplitJobs)
        {
            Map runJob = new HashMap();
            runJob.put("JOB_ID",unCompleteSplitJob);
            runJob = batchDao.getRunJob(runJob);
            int SPLIT_CNT = Integer.parseInt(runJob.get("SPLIT_CNT").toString());
            if(SPLIT_CNT != 999999999)//未分段
                batchDao.updateSplitTask(unCompleteSplitJob);
            deleteSplitJob(unCompleteSplitJob);
        }

    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        zkTools = SpringApplicationContext.getContext().getBean(ZkTools.class);
        group = ConfigUtils.getProperty("galaxy.application.group");
        loadBalance = ConfigUtils.getProperty("galaxy.batch.loadBalance");
        if (loadBalance == null || loadBalance.trim().length() == 0)
        {
            loadBalance = AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN;
        }
        if (zkClient == null)
        {
            String zkurl = ConfigUtils.getProperty("galaxy.registry.address").replaceAll("zookeeper://","").replaceAll("\\?backup=",",");
            int timeout = 10000;
            try
            {
                timeout = Integer.parseInt( ConfigUtils.getProperty("galaxy.registry.timeout"));
            }
            catch (Exception e)
            {
                if (logger.isWarnEnabled())
                    logger.warn("galaxy.registry.timeout not config!");
            }
            zkClient = new ZkClient(zkurl,timeout);

        }
        if (logger.isInfoEnabled())
            logger.info("批处理主调度节点启动完成！");
        init();


    }

    @Override
    public synchronized void destroy() throws Exception {
        if(zkClient != null){
            zkClient.unsubscribeAll();
            zkClient.close();
            this.zkClient = null;
        }
    }
}
