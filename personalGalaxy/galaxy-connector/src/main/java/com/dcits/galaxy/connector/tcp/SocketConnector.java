package com.dcits.galaxy.connector.tcp;

import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.connector.Connector;
import com.dcits.galaxy.core.threadpool.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;

/**
 * TcpConnector. (Singleton, ThreadSafe)
 *
 * @author xuecy
 */
public class SocketConnector implements Connector {

    private static final Logger logger = LoggerFactory
            .getLogger(SocketConnector.class);

    /**
     * 服务的端口
     */
    private int port = 9010;
    /**
     * 超时时间
     */
    private long timeout;
    /**
     * 线程最小数
     */
    private int minThread = 5;
    /**
     * 线程最大数
     */
    private int maxThread = 20;
    /**
     * 线程等待时间，"-1"表示一直等待，单位：s
     */
    private int keepAliveTime = 20;
    /**
     * 测试报文
     */
    private String testMessage;
    /**
     * 测试返回报文
     */
    private String testReturnMessage;

    /**
     * 应用组，通过galaxy.properties配置值注入
     */
    private String appGroup;

    /**
     * 应用名，通过galaxy.properties配置值注入
     */
    private String appName;

    public String getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(String msgFormat) {
        if (!"${socket.msgFormat}".equals(msgFormat)) {
            this.msgFormat = msgFormat;
        }
    }

    /**
     * 报文格式
     */
    private String msgFormat;


    public String getMsgParser() {
        return msgParser;
    }

    public void setMsgParser(String msgParser) {
        if (!"${socket.msgParser}".equals(msgParser)) {
            this.msgParser = msgParser;
        }
    }

    /**
     * 报文处理解析器
     */
    private String msgParser;

    /**
     * 执行的线程池
     */
    ExecutorService executorService;

    private ServerSocket server = null;

    @Override
    public void start() {
        try {
            // 初始化线程池
            executorService = Executors.newCachedThreadPool("TcpConnector",
                    this.minThread, this.maxThread);

            // 初始化SocketServer
            this.server = new ServerSocket(this.port);
            this.server.setReuseAddress(true);

            // 独立线程等待
            Executors.newSingleThreadExecutor("TcpServer").execute(
                    new Server(this));

            if (logger.isInfoEnabled()) {
                logger.info("Socket connector(" + port + ") startup success!");
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("An exception occurred while starting socket connector.\n"
                        + ExceptionUtils.getStackTrace(e));
            }
        }

    }

    @Override
    public void stop() {
        try {
            if (this.server != null) {
                this.server.close();
            }
            if (this.executorService != null) {
                this.executorService.shutdown();
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("An exception occurred while stopping socket connector.\n"
                        + ExceptionUtils.getStackTrace(e));
            }
        }

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getMinThread() {
        return minThread;
    }

    public void setMinThread(int minThread) {
        this.minThread = minThread;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        if (keepAliveTime == -1) {
            this.keepAliveTime = Integer.MAX_VALUE;
        } else {
            this.keepAliveTime = keepAliveTime;
        }
    }

    public String getTestMessage() {
        return testMessage;
    }

    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }

    public String getTestReturnMessage() {
        return testReturnMessage;
    }

    public void setTestReturnMessage(String testReturnMessage) {
        this.testReturnMessage = testReturnMessage;
    }

    /**
     * 获取Connector对应 线程池，用于查看其状态使用
     *
     * @return
     */
    public ExecutorService getExecutor() {
        return executorService;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 独立线程等待消息，否而阻塞
     *
     * @author xuecy
     */
    class Server implements Runnable {

        private SocketConnector connector;

        public Server(SocketConnector connector) {
            this.connector = connector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket client = server.accept();
                    executorService.execute(new DispatcherThread(client,
                            connector));
                } catch (SocketException se) {
                    if (logger.isErrorEnabled()) {
                        logger.error("An exception occurred while receiving socket request.\n"
                                + ExceptionUtils.getStackTrace(se));
                    }
                    break;
                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("An exception occurred while processing socket request.\n"
                                + ExceptionUtils.getStackTrace(e));
                    }
                }
            }
        }
    }
}
