package com.dcits.galaxy.connector.http;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.dcits.galaxy.connector.Connector;

/**
 * JettyConnector. (Singleton, ThreadSafe)
 *
 * @author xuecy
 */
public class JettyConnector implements Connector {

    private static final Logger logger = LoggerFactory
            .getLogger(JettyConnector.class);

    public static final int DEFAULT_THREADS = 200;

    private int port = 8080;
    private int minThreads = 10;
    private int maxThreads = 200;

    private int acceptors = 4;
    private int solinger = -1;
    private int acceptQueueSize = 0;
    private int selectors = 4;

    private String urlPattern;
    private String servletClass;

    public String getMsgParser() {
        return msgParser;
    }

    public void setMsgParser(String msgParser) {
        this.msgParser = msgParser;
    }

    public String getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(String msgFormat) {
        this.msgFormat = msgFormat;
    }

    private String msgFormat;

    private String msgParser;

    public String getIpFilter() {
        return ipFilter;
    }

    public void setIpFilter(String ipFilter) {
        this.ipFilter = ipFilter;
    }

    private String ipFilter;

    /**
     * 应用组，通过galaxy.properties配置值注入
     */
    private String appGroup;

    /**
     * 应用名，通过galaxy.properties配置值注入
     */
    private String appName;

    /**
     * 处理的线程池
     */
    QueuedThreadPool threadPool;

    ServerConnector connector;

    public void start() {
        // threadPool
        threadPool = new QueuedThreadPool();
        threadPool.setName("Galaxy-connector");
        threadPool.setDaemon(true);
        threadPool.setMaxThreads(maxThreads);
        threadPool.setMinThreads(minThreads);

        Server server = new Server(threadPool);

        // connector
        connector = new ServerConnector(server, acceptors, selectors);
        connector.setPort(port);
        connector.setSoLingerTime(solinger);
        connector.setReuseAddress(true);
        connector.setAcceptQueueSize(acceptQueueSize);

        server.addConnector(connector);

        // handler
        ServletHandler handler = new ServletHandler();
        ServletHolder servletHolder = null;
        String[] urls = urlPattern.split("\\|");
        String[] servletClasses = servletClass.split("\\|");
        String[] appGroups = appGroup.split("\\|");
        String[] msgFormats = msgFormat.split("\\|");

        String[] msgParsers = null;

        if (!StringUtils.isBlank(msgParser)) {
            msgParsers = msgParser.split("\\|");
        }

        String[] ipFilters = null;
        if (!StringUtils.isBlank(ipFilter))
        {
            ipFilters = ipFilter.split("\\|");
        }


        if (urls.length != servletClasses.length) {
            throw new IllegalStateException(
                    "Jetty's parameter (urlPattern or servletClasses or handlerIds) configuration errors, the number must match.");
        }
        int i = 0;
        for (String url : urls) {
            if ("".equals(servletClasses[i]) || "".equals(url))
                throw new IllegalStateException(
                        "Jetty's urlPattern or servletClasses parameter can't be empty.");
            servletHolder = handler.addServletWithMapping(servletClasses[i], url);
            servletHolder.setInitParameter("appGroup", appGroups[i]);
            servletHolder.setInitParameter("appName", appName);
            if ("${jetty.msgFormat}".equals(msgFormat)) {
                servletHolder.setInitParameter("msgFormat", "json");
            } else {
                servletHolder.setInitParameter("msgFormat", msgFormats[i]);
            }
            if (msgParsers != null && !"${jetty.msgParser}".equals(msgParser)) {
                servletHolder.setInitParameter("msgParser", msgParsers[i]);
            }
            if (ipFilters != null && !"${jetty.ipFilter}".equals(ipFilter))
            {
                servletHolder.setInitParameter("ipFilter", ipFilters[i]);
            }
            servletHolder.setInitOrder(2);
            i++;
        }
        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to start jetty server on "
                    + NetUtils.getLocalHost() + ":" + port + ", cause: "
                    + e.getMessage(), e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("Jetty connector(" + this.port + ") startup success!");
        }

    }

    public void stop() {
        try {
            if (connector != null) {
                connector.close();
                connector = null;
            }
            if (threadPool != null) {
                threadPool.stop();
            }

        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    /**
     * 获取执行的线程池，用于查看执行状态等
     *
     * @return
     */
    public QueuedThreadPool getThreadPool() {
        return threadPool;
    }

    /**
     * @return urlPattern : return the property urlPattern.
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * @param urlPattern
     *         : set the property urlPattern.
     */
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    /**
     * @return servletClass : return the property servletClass.
     */
    public String getServletClass() {
        return servletClass;
    }

    /**
     * @param servletClass
     *         : set the property servletClass.
     */
    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public int getAcceptors() {
        return acceptors;
    }

    public void setAcceptors(int acceptors) {
        this.acceptors = acceptors;
    }

    public int getSolinger() {
        return solinger;
    }

    public void setSolinger(int solinger) {
        this.solinger = solinger;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public int getSelectors() {
        return selectors;
    }

    public void setSelectors(int selectors) {
        this.selectors = selectors;
    }

    public int getAcceptQueueSize() {
        return acceptQueueSize;
    }

    public void setAcceptQueueSize(int acceptQueueSize) {
        this.acceptQueueSize = acceptQueueSize;
    }

}