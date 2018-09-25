package com.dcits.orion.batch.task.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ZkCommon;
import com.dcits.orion.batch.task.service.RunTask;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.application.Application;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Repository;











import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/12/9.
 */
@Repository
public class ZkUtils extends ZkCommon implements ApplicationListener<ContextRefreshedEvent>, IZkChildListener, IZkDataListener, IZkStateListener,DisposableBean,Ordered {

    @Resource
    RunTask runTask;

    private static Logger logger = LoggerFactory.getLogger(ZkUtils.class);


    //注册服务节点应用，节点用
    public String AppReg() {
        String group = getGroup();
        String groupPath = task_machine_path + "/" + group;
        String path = groupPath + "/" + getSelfName();
        if (!zkClient.exists(groupPath))
            zkClient.createPersistent(groupPath, true);
        if (!zkClient.exists(path))
            taskMachineReg(path);
        return path;
    }

    private void taskMachineReg(String path)
    {
        try {
            zkClient.createEphemeral(path,"" + runTask.getThreadPoolSize());
        }
        catch (Exception e)
        {
            throw new GalaxyException("ZooKeeper 会话还未失效，请稍后再尝试启动！ZooKeeper path=<" + path+">");
        }

    }



    private void init() {
        setListener();
        AppReg();
    }

    //获取当前服务执务节点在所有服务节点的序号和服务器总数，节点用
    public int[] getSelfSeq(String group) {
        String selfName = getSelfName();
        List<String> children = zkClient.getChildren(task_machine_path + "/" + group);
        int[] ret = new int[2];
        ret[1] = children.size();
        int seq = 0;
        for (String appName : children) {
            if (selfName.equals(appName)) {
                break;
            }
            seq++;
        }
        ret[0] = seq;
        return ret;
    }


    public void setListener() {
        zkClient.subscribeStateChanges(this);
        if (!zkClient.exists(tasks_note_path + "/" + getGroup())) {
            zkClient.createPersistent(tasks_note_path + "/" + getGroup(), true);
        }
        zkClient.subscribeDataChanges(tasks_note_path + "/" + getGroup(), this);
        if (!zkClient.exists(task_machine_path + "/" + getGroup())) {
            zkClient.createPersistent(task_machine_path + "/" + getGroup(), true);
        }
        zkClient.subscribeChildChanges(task_machine_path + "/" + getGroup(), this);

    }


    //处理所有task
    public void dealTasks() {

        String path = tasks_path + "/" + getGroup();
        zkClient.createPersistent(path, true);
        List<String> tasks = zkClient.getChildren(path);
        dealTasks(path, tasks);
    }


    public boolean dealTask(String path, String taskId) {

        boolean deal = false;
        boolean isNull = true;
        String taskPath = path + "/" + taskId;
        try {
            if (zkClient.exists(taskPath) && lock(taskPath)) {
                ITaskParam taskParam = null;
                try {
                    taskParam = zkClient.readData(taskPath);
                } catch (Exception e) {
                    isNull = false;
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
                if (taskParam != null) {
                    if ("TIMER".equals(taskParam.getJobType()))
                    {
                        runTask.runTimerTask(taskParam,true);
                        deal = true;

                    }
                    else
                    {
                        while (!runTask.runTask(taskParam,true)) {
                            Thread.sleep(100);
                        }
                        deal = true;
                    }

                } else {
                    unlock(taskPath);
                    if (isNull) {
                        zkClient.delete(taskPath);
                        if (logger.isErrorEnabled())
                            logger.error(taskPath + "节点Task信息不正常，已删除");
                    }
                }
            }
        } catch (Exception e) {
            unlock(taskPath);
            noteTask(getGroup());
            if (logger.isErrorEnabled()) {
                logger.error("与主调通讯失败，发通知让别的节点处理\n" + ExceptionUtils.getStackTrace(e));
            }
        }
        return deal;

    }


    //在指定路径下处理任务
    public void dealTasks(String path, List<String> tasks) {
        int size = tasks.size();
        if (size == 0)
            return;
        int[] tmp = getSelfSeq(getGroup());
        int selfSeq = tmp[0];
        int serversCount = tmp[1];
        if (serversCount == 0) {
            return;
        }
        int perCount = size % serversCount == 0 ? size / serversCount : size / serversCount + 1;
        //根据节点在服务器中的位置开始分段处理，分两次扫描全部task
        int start = selfSeq * perCount;
        //int end = (selfSeq+1) * perCount;
        for (int i = start; i < size; i++) {
            dealTask(path, tasks.get(i));
        }
        for (int i = 0; i < start && i < size; i++) {
            dealTask(path, tasks.get(i));
        }
    }

    //其它服务器启动或退出事件
    @Override
    public void handleChildChange(String path, List<String> tasks) throws Exception {


        AppReg();
        dealTasks();
    }

    //有未处理的TASK事件
    @Override
    public void handleDataChange(String s, Object o) throws Exception {

        dealTasks();
    }

    //有未处理的TASK事件删除（不存在此事件）
    @Override
    public void handleDataDeleted(String s) throws Exception {

    }

    //与ZK的服务器重连事件
    @Override
    public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
        if ("SyncConnected".equals(keeperState.name())) {
            init();
        }
    }

    @Override
    public void handleNewSession() throws Exception {
        init();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        group = ConfigUtils.getProperty("galaxy.application.group");
        if (zkClient == null) {
            String zkurl = ConfigUtils.getProperty("galaxy.registry.address").replaceAll("zookeeper://", "").replaceAll("\\?backup=", ",");
            int timeout = 10000;
            try {
                timeout = Integer.parseInt(ConfigUtils.getProperty("galaxy.registry.timeout"));
            } catch (Exception e) {
                if (logger.isWarnEnabled())
                    logger.warn("galaxy.registry.timeout not config!");
            }
            zkClient = new ZkClient(zkurl, timeout);

        }
        if (logger.isInfoEnabled())
            logger.info("批处理任务执行节点启动完成！");
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


    public IQuartzManager getIQuartzManager(String group) {
        String path = "/galaxy/threads/" + group + "/lock";
        if (zkClient.exists(path)) {
            String nodeData = zkClient.readData(path, true);
            String ip = nodeData.split("-")[0];
            ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                    .setCheck(false)
                    .setGroup(group)
                    .setTimeout(60000)
                    .setUrl("dubbo://" + ip);
            return ServiceProxy.getInstance().getService(IQuartzManager.class, builder.build());
        } else {
            return null;
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}