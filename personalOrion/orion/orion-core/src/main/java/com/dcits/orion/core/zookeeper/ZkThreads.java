package com.dcits.orion.core.zookeeper;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.orion.api.IZkThread;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.galaxy.base.util.ClassLoaderUtils;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by lixbb on 2016/2/17.
 */
public class ZkThreads implements ApplicationListener<ContextRefreshedEvent>, IZkChildListener, IZkStateListener,Ordered {

    private static Logger logger = LoggerFactory.getLogger(ZkThreads.class);

    private final static String threadLockPath = "/galaxy/threads";


    public final static String lock = "lock";

    private List<String> threadNames;

    static ZkClient zkClient = null;

    /**
     * 应用名
     */
    private static String appName;

    static {
        appName = ServiceHandler.getAppName();
        String localIP = "UnknownHost";
        String port ="0";
        try {
            localIP = InetAddress.getLocalHost().getHostAddress();
            port = ConfigUtils.getProperty("galaxy.protocol.port");
        } catch (UnknownHostException e) {
        }
        // 锁持有者唯一标识，本机IP + 应用名
        appName = localIP +":"+port+ "-" + appName;
    }


    private boolean lock() {
        String path = getPath();
        String lockPath = getPath() + "/" + lock;
        boolean ret = true;
        if (zkClient.exists(lockPath)) {
            ret = false;
        } else {
            try {
                if (!zkClient.exists(path)) {
                    zkClient.createPersistent(path, true);
                }
                zkClient.createEphemeral(lockPath, appName);
                if (logger.isInfoEnabled()) {
                    logger.info(path + " locked");
                }
            } catch (Exception e) {
                ret = false;
            }
        }
        return ret;
    }

    private String getPath() {
        return threadLockPath + "/" + getGroup();
    }

    private String getGroup() {
        return ConfigUtils.getProperty("galaxy.application.group");
    }

    @Override
    public void handleChildChange(String s, List<String> list) throws Exception {
        toStartThreads();

    }

    private void toStartThreads() {
        if (lock()) {
            if (threadNames != null) {


                for (String threadName : threadNames) {
                    String[] threadClass = threadName.split("\\|", 2);
                    try {
                        if (threadClass.length > 1) {
                            IZkThread iZkThread = (IZkThread) ClassLoaderUtils.loadClass(threadClass[0]).newInstance();
                            iZkThread.setArgs(threadClass[1].split("\\|"));
                            new Thread(iZkThread).start();
                        } else {
                            Runnable thread = (Runnable) ClassLoaderUtils.loadClass(threadName).newInstance();
                            new Thread(thread).start();
                        }

                    } catch (Exception e) {
                        if (logger.isDebugEnabled()) {
                            logger.warn("线程启动失败：<" + threadName + ">");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {

        setListener();
    }

    @Override
    public void handleNewSession() throws Exception {
        setListener();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
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
        setListener();
        toStartThreads();
    }

    public void setListener() {
        zkClient.subscribeStateChanges(this);
        if (!zkClient.exists(getPath())) {
            zkClient.createPersistent(getPath(), true);
        }
        zkClient.subscribeChildChanges(getPath(), this);
    }

    public List<String> getThreadNames() {
        return threadNames;
    }

    public void setThreadNames(List<String> threadNames) {
        this.threadNames = threadNames;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
