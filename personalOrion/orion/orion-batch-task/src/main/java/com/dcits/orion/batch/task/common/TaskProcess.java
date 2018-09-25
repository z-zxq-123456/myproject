package com.dcits.orion.batch.task.common;

import com.dcits.galaxy.dal.mybatis.transaction.TransactionExtensionConfig;
import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.ITaskProcess;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;

import com.dcits.orion.batch.task.service.CheckJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lixbb on 2015/12/7.
 */
@Repository
public class TaskProcess  implements ITaskProcess{
    private static Logger logger = LoggerFactory.getLogger(TaskThread.class);

    @Resource
    ZkUtils utils;
    @Resource
    IBatchLocal batchLocal;
    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    CheckJob checkJob;

    private final TransactionDefinition definition = new DefaultTransactionDefinition();

    public void processTask(ITaskParam taskParam)
    {        //记录TASK开始时间(框架实现)

        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN)
                .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                .setCheck(false)
                .setGroup(taskParam.getSendGroup());
        IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
        Map task = iBatchManage.getRunTask(taskParam.getTaskId());
        if (task == null)
            throw new GalaxyException("获取任务信息失败！");
        if("S".equals(task.get("TASK_STATUS")) ||"F".equals(task.get("TASK_STATUS")) )
            return;
        if (!"P".equals(task.get("TASK_STATUS")))
            throw new GalaxyException("Task 状态不正确：TASK_ID=" + taskParam.getTaskId() + ",TASK_STATUS=" + task.get("TASK_STATUS"));
        // 重新指定Task的Map对象
        task = new HashMap();
        task.put("TASK_ID", taskParam.getTaskId());
        task.put("START_TIME", BatchUtils.getCurTime());
        task.put("NODE_IP", BatchUtils.getLocalIP());
        iBatchManage.updateRunTask(task);
        boolean succ = true;
        String error_desc = "";
        long begin = System.currentTimeMillis();
        try {
            String uid = SeqUtils.getStringSeq();
            if (logger.isInfoEnabled())
                logger.info("Create a unique transaction identifier[" + uid + "]...");
            ThreadLocalManager.setUID(uid);
            //batchLocal.taskInit();
            Object o = null;
            if (taskParam.getJobType().equals("GX")||taskParam.getJobType().equals("IM")||taskParam.getJobType().equals("EX")||taskParam.getJobType().equals("PM")||taskParam.getJobType().equals("PI")) {
                o = SpringApplicationContext.getContext().getBean(Class.forName(taskParam.getGxClassName()));
            }
            else
            {
                o = Class.forName(taskParam.getGxClassName()).newInstance();
            }
            Method m = ObjectUtils.getMethod(o, taskParam.getGxMethod(), ITaskParam.class);
            ThreadLocalManager.put("taskParam",taskParam);
            ThreadLocalManager.put("JOB_ID",taskParam.getJobId());
            ThreadLocalManager.put("pkValue",taskParam.getSchemaId());
            TaskProcess taskProcess = SpringApplicationContext.getContext().getBean(TaskProcess.class);

            //add by qiqingshan
            if("Y".equals(taskParam.getDtpFlag())){
                String group = com.alibaba.dubbo.common.utils.ConfigUtils.getProperty("galaxy.application.group");
                String flag = checkJob.queryAndcommit(taskParam.getTaskId(),group);
                boolean boo = checkJob.checkIdempoent(taskParam.getTaskId());
                if("N".equals(flag) || "S".equals(flag)){
                    if(!boo){
                        taskProcess.invoke(o,m, taskParam);//modify by lixiaobin 批处理框架引入冥等保证批处理执行的一致性
                    }
                }
            }else{
                boolean boo = checkJob.checkIdempoent(taskParam.getTaskId());
                if(!boo){
                    taskProcess.invoke(o,m, taskParam);//modify by lixiaobin 批处理框架引入冥等保证批处理执行的一致性
                }
            }
        }
        catch (Exception e)
        {
            succ = false;
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
            if (e instanceof InvocationTargetException)
            {
                InvocationTargetException ie = (InvocationTargetException)e;
                Throwable exception = ie.getTargetException();
                if (exception != null)
                {
                    String error_msg = exception.getMessage();
                    error_msg = StringUtils.byteSubString(error_msg,500);
                    task.put("ERROR_MSG",error_msg);
                    error_desc = ExceptionUtils.getStackTrace(exception);
                    error_desc = StringUtils.byteSubString(error_desc,3999);
                    task.put("ERROR_DESC", error_desc);
                }

            }
            else {
                error_desc = ExceptionUtils.getStackTrace(e);
                error_desc = StringUtils.byteSubString(error_desc,3999);
                task.put("ERROR_DESC", error_desc);

            }
        }

        long end = System.currentTimeMillis();
        if (succ) {
            if(taskParam.getJobType().equals("GX")||taskParam.getJobType().equals("IM")||taskParam.getJobType().equals("EX")||taskParam.getJobType().equals("PM")||taskParam.getJobType().equals("PI"))
            {
                task.put("TASK_STATUS", "S");
            }
            else
            {
                task.put("TASK_STATUS", "R");
            }

        } else {
            if (taskParam.getIsSkip().equals("A")) {
                task.put("TASK_STATUS", "A");
            } else {
                task.put("TASK_STATUS", "F");
            }
        }
        task.put("TIME_ELAPSED",end-begin);
        task.put("END_TIME", BatchUtils.getCurTime());
        iBatchManage.updateRunTask(task);
    }

    //add by lixiaobin 批处理框架引入冥等保证批处理执行的一致性
    // @Transactional(rollbackFor={InvocationTargetException.class})
    public void invoke(Object o,Method m, ITaskParam taskParam) throws Exception
    {
        Exception throwable = null;
        if("Y".equals(taskParam.getDtpFlag())){ //add by qiqingshan 判断job是否开启dtp
            TransactionExtensionConfig.setRecordLog(true);
            TransactionExtensionConfig.setOrderSubmit(true);
        }
        TransactionStatus status = transactionManager.getTransaction(definition);
        IdempotentContext.setIdempotentObj(taskParam.getTaskId());
        batchLocal.taskInit();
        boolean succ = false;
        try {
            m.invoke(o, taskParam);
            succ = true;

        }catch (Exception e){

            throwable =  e;
        }
        finally {
            batchLocal.taskRelease();
        }
        if (succ)
        {
            transactionManager.commit(status);
        }
        else {
            transactionManager.rollback(status);
            throw throwable;
        }
    }
}

