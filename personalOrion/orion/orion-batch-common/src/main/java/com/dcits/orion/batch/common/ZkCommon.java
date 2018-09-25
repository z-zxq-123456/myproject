package com.dcits.orion.batch.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2015/12/8.
 */
public class ZkCommon {

    private static Logger logger = LoggerFactory.getLogger(ZkCommon.class);

    public final static String schedule_path = "/galaxy/batch/schedule/schedule";

    public final static String schedule_machine_path = "/galaxy/batch/schedule/machine";

    public final static String schedule_note_path = "/galaxy/batch/schedule/note";



    public final static String task_machine_path = "/galaxy/batch/task/machine";

    public final static String tasks_path = "/galaxy/batch/task/tasks";

    public final static String tasks_mutex = "/galaxy/batch/task/mutex";

    public final static String tasks_note_path = "/galaxy/batch/task/note";

    public final static String split_job_path = "/galaxy/batch/job/split";

    public final static String lock = "lock";

    protected ZkClient zkClient=null;

    protected String group;





    public boolean lock(String path)
    {

        String lockPath = path + "/" + lock;
        boolean ret = true;
        if (zkClient == null)
            return false;
        if (zkClient.exists(lockPath))
        {
            ret = false;
        }
        else
        {
            try {
                zkClient.createPersistent(path, true);
                zkClient.createEphemeral(lockPath,getSelfName()+":"+BatchUtils.getCurTime());
                if (logger.isInfoEnabled())
                {
                    logger.info(path + " locked");
                }
            }
            catch (Exception e)
            {
                ret =  false;
            }
        }

        return ret;
    }
    public boolean unlock(String path)
    {
        if (zkClient == null)
            return false;
        boolean ret  = true;
        String lockPath = path + "/" + lock;
        if (zkClient.exists(lockPath))
        {
            zkClient.delete(lockPath);
            while (zkClient.exists(lockPath))
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (logger.isInfoEnabled())
        {
            logger.info(path + " unlocked");
        }
        return ret;
    }




    public void deleteTask(ITaskParam taskParam)
    {
        String path = tasks_path +"/"+ taskParam.getSystemId() +"/"+ taskParam.getTaskId();
        try
        {

            unlockTask(taskParam);
            zkClient.delete(path);

        }
        catch (Exception e)
        {

        }
        if (logger.isInfoEnabled())
        {
            logger.info(path + " deleted!");
        }

    }
    public boolean lockTask(ITaskParam taskParam)
    {

        String path = tasks_path +"/"+ taskParam.getSystemId() +"/"+ taskParam.getTaskId();
        if (!zkClient.exists(path))
            return false;
        return lock(path);
    }

    public void unlockTask(ITaskParam taskParam)
    {
        String path = tasks_path +"/"+ taskParam.getSystemId() +"/"+ taskParam.getTaskId();
        unlock(path);
    }
    public void noteTask(String group)
    {
        String path = tasks_note_path + "/" + group;
        if (!zkClient.exists(path))
            zkClient.createPersistent(path, true);
        String uuid = SeqUtils.getStringSeq();
        zkClient.writeData(path, uuid);
        if (logger.isInfoEnabled())
        {
            logger.info(path + " noted:" + uuid);
        }
    }
    public void noteSchedule()
    {
        String notePath=schedule_note_path + "/" + group;
        if (!zkClient.exists(notePath))
            zkClient.createPersistent(notePath , true);
        String uuid = SeqUtils.getStringSeq();
        zkClient.writeData(notePath, uuid);
        if (logger.isInfoEnabled())
        {
            logger.info(notePath + " noted:" + uuid);
        }
    }

    public void zkReg(String path)
    {
        try {
            zkClient.createEphemeral(path,BatchUtils.getCurTime());
        }
        catch (Exception e)
        {
            throw new GalaxyException("ZooKeeper 会话还未失效，请稍后再尝试启动！ZooKeeper path=<" + path+">");
        }

    }

    public String getGroup()
    {
        return  group;
    }

    public String getAppName()
    {
        return  ConfigUtils.getProperty("galaxy.application.name");
    }
    protected String getSelfName()
    {
        String appName = getAppName();
        String localIP = BatchUtils.getLocalIP();
        return appName + "-" + localIP+"-"+getPort();
    }
    protected String getPort()
    {
        return  ConfigUtils.getProperty("galaxy.protocol.port");
    }





    public boolean existTimerTask(String systemId, String taskId)
    {
        String taskPath = tasks_path + "/" + systemId + "/" + taskId;
        return zkClient.exists(taskPath);
    }



    public boolean checkMutex(String systemId,String jobId,String schemaId)
    {
        if (StringUtils.isBlank(schemaId))
            return  true;
        String mutexPath = tasks_mutex+"/"+systemId;
        if (!zkClient.exists(mutexPath))
            zkClient.createPersistent(mutexPath, true);

        String jobSchemaMutexPath = mutexPath+"/" + jobId +"_"+ schemaId;
        if (zkClient.exists(jobSchemaMutexPath))
            return false;
        else
            return true;
    }
    public void createMutex(String systemId,String jobId,String schemaId)
    {
        if (StringUtils.isBlank(schemaId))
            return;
        String mutexPath = tasks_mutex+"/"+systemId;
        String jobSchemaMutexPath = mutexPath+"/" + jobId +"_"+ schemaId;
        zkClient.createPersistent(jobSchemaMutexPath, true);
    }

    public void deleteMutex(String systemId,String jobId,String schemaId)
    {
        if (StringUtils.isBlank(schemaId))
            return;
        String mutexPath = tasks_mutex+"/"+systemId;
        String jobSchemaMutexPath = mutexPath+"/" + jobId +"_"+ schemaId;
        if (zkClient.exists(jobSchemaMutexPath))
            zkClient.delete(jobSchemaMutexPath);
    }


}
