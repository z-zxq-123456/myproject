package com.dcits.galaxy.sequences;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.core.client.ServiceProxy;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ZK序列锁，负责保证集群内部有且只有一个ShardSequences服务提供序列管理
 * <p/>
 * Created by Tim on 2016/4/3.
 */
public class ZkSequencesLock implements InitializingBean, IZkChildListener, IZkStateListener ,DisposableBean{

    private static Logger logger = LoggerFactory.getLogger(ZkSequencesLock.class);

    /**
     * 序列号生成器服务
     */
    private ShardSequences shardSequences;

    /**
     * 缓存序列管理器
     */
    private SequencesCacheManager shardSequencesCacheManager;

    /**
     * 锁目录
     */
    private final static String lockPath = "lock";

    /**
     * 序列节点锁目录
     */
    private final static String sequencesLockPath = "/galaxy/sequences";

    static ZkClient zkClient = null;

    /**
     * zk 序列锁
     * 暂时不需要这个锁对业务获取序列进行控制，ZK掉了后。
     * 服务仍可以继续通讯，并不会对业务获取序列产生影响。
     */
    public volatile static boolean lock = false;

    /**
     * 唯一标识
     * 用于ZK断链恢复后异常恢复识别
     */
    private static final String uuid;

    /**
     * 应用名
     */
    private static final String appName;

    /**
     * 序列应用组名
     */
    private static final String sequencesGroup;

    static {
        appName = SequencesHandler.getProperties("galaxy.application.name", "zkSequencesLock");
        sequencesGroup = SequencesHandler.getProperties("galaxy.business.sequences.group", "shardSequences");
        String localIP = "UnknownHost";
        try {
            localIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        // 锁持有者唯一标识，应用名+"-"+应用组+"-"+本机IP+"-"+32唯一ID编号
        uuid = appName + "-" + sequencesGroup + "-" + localIP + "-" + SeqUtils.getStringSeq();
    }

    /**
     * Called when the children of the given path changed.
     *
     * @param parentPath    The parent path
     * @param currentChilds The children or null if the root node (parent path) was deleted.
     * @throws Exception
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        serviceExport();
    }

    /**
     * 服务注册
     * 这里的的服务注册与发布是有延时的。
     * 无法保证实时有效，消费者需要控制，如果服务未切换过来这段时间内，使用访问本地序列方式，保证序列的正常提供。
     */
    public void serviceExport() {
        String mode = SequencesHandler.getProperties("galaxy.application.mode", "produce");
        // 非生产模式下。不进行服务注册
        if (!"produce".equals(mode)) {
            return;
        }
        if (lock()) {
            // 暴露及注册服务
            service.export();//@ServiceConfig@Method:export();
            if (logger.isInfoEnabled()) {
                logger.info("ShardSequences 序列生成服务注册成功！");
            }
            lock = true;
            if (logger.isInfoEnabled()) {
                logger.info("locked！");
            }
            // 初始化缓存管理器，只有服务管理器接管后才会初始化缓存管理器
            // 改为软加载模式，服务接口后不初始化序列管理器，在使用时，进行初始化
            //shardSequencesCacheManager.init();
            /**
             if (logger.isInfoEnabled()) {
             logger.info("序列缓存管理器初始化完成！");
             }*/
            // 异步持久化线程
            if ("async".equals(shardSequencesCacheManager.getPersistentMode())) {
                /**
                 * 启动更新守护线程
                 */
                if (logger.isInfoEnabled()) {
                    logger.info("序列缓存管理器持久化守护线程启动。 Period [" + shardSequencesCacheManager.getPersistentPeriod() + "]！");
                }
                Timer t = new Timer("persistent-sequences-thread");//Timer schedule
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                    	try {
                    		shardSequencesCacheManager.persistentSequences();
						} catch (Throwable t) {
							logger.warn("persistent sequences exception. " + t.getMessage(), t);
						}
                    }
                }, shardSequencesCacheManager.getPersistentPeriod(), shardSequencesCacheManager.getPersistentPeriod());
            }
        }
    }

    /**
     * Called when the zookeeper connection state has changed.
     *
     * @param state The new state.
     * @throws Exception On any error.
     */
    @Override
    public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
        if (Watcher.Event.KeeperState.Disconnected == state) {
            if (logger.isInfoEnabled()) {
                logger.info("ZK连接断开！");
            }
        }
        setListener();
        if (Watcher.Event.KeeperState.SyncConnected == state) {
            if (logger.isInfoEnabled()) {
                logger.info("ZK连接恢复！");
            }
        }
    }

    /**
     * Called after the zookeeper session has expired and a new session has been created. You would have to re-create
     * any ephemeral nodes here.
     *
     * @throws Exception On any error.
     */
    @Override
    public void handleNewSession() throws Exception {
        setListener();
    }

    private ApplicationConfig application;
    private RegistryConfig registry;
    private ProtocolConfig protocol;
    ServiceConfig<ShardSequences> service;

    /**
     * 创建序列服务对象
     */
    public void initService() {
        // 当前应用配置
        application = new ApplicationConfig();
        application.setName(SequencesHandler.getProperties("galaxy.application.name", "zkSequencesLock"));  //application -- registryConfig--protocolConfig--serviceConfig 
        // 连接注册中心配置
        registry = new RegistryConfig();
        registry.setAddress(SequencesHandler.getProperties("galaxy.registry.address", "zookeeper://127.0.0.1:2181"));
        registry.setTimeout(Integer.valueOf(SequencesHandler.getProperties("galaxy.registry.timeout", "10000")));
        // 服务提供者协议配置
        protocol = new ProtocolConfig();
        protocol.setName(SequencesHandler.getProperties("galaxy.protocol.name", "dubbo"));
        protocol.setPort(Integer.valueOf(SequencesHandler.getProperties("galaxy.protocol.port", "21887")));
        protocol.setThreads(Integer.valueOf(SequencesHandler.getProperties("galaxy.protocol.threads", "200")));

        // 服务提供者暴露服务配置
        service = new ServiceConfig<>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setGroup(SequencesHandler.getProperties("galaxy.business.sequences.group", "shardSequences"));
        service.setInterface(ShardSequences.class);
        service.setRef(shardSequences);
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (zkClient == null) {
			String zkUrl = ServiceProxy.getInstance().getZkUrl();
            int timeout = Integer.valueOf(SequencesHandler.getProperties("galaxy.registry.timeout", "10000"));
            zkClient = new ZkClient(zkUrl, timeout);
        }
        initService();
        setListener();
        serviceExport();
    }

    private void setListener() {
        zkClient.subscribeStateChanges(this);
        if (!zkClient.exists(getPath())) {
            zkClient.createPersistent(getPath(), true);
        }
        zkClient.subscribeChildChanges(getPath(), this);
    }

    private String getPath() {
        return sequencesLockPath + "/" + sequencesGroup;
    }

    private boolean lock() {
        String path = getPath();
        String seqLockPath = getPath() + "/" + lockPath;
        boolean ret = true;
        if (zkClient.exists(seqLockPath)) {
            ret = false;
        } else {
            try {
                if (!zkClient.exists(path)) {
                    zkClient.createPersistent(path, true);
                }
                zkClient.createEphemeral(seqLockPath, uuid);
                if (logger.isInfoEnabled()) {
                    logger.info(seqLockPath + " locked");
                }
            } catch (Exception e) {
                ret = false;
            }
        }
        return ret;
    }

    public void setShardSequences(ShardSequences shardSequences) {
        this.shardSequences = shardSequences;
    }

    public SequencesCacheManager getShardSequencesCacheManager() {
        return shardSequencesCacheManager;
    }

    public void setShardSequencesCacheManager(SequencesCacheManager shardSequencesCacheManager) {
        this.shardSequencesCacheManager = shardSequencesCacheManager;
    }

    @Override
    public synchronized void destroy() throws Exception {
        if(zkClient != null){
            zkClient.unsubscribeAll();
            zkClient.close();
            ZkSequencesLock.zkClient = null;
        }
    }
}
