/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.container.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.page.PageServlet;
import com.alibaba.dubbo.container.page.ResourceFilter;

/**
 * JettyContainer. (SPI, Singleton, ThreadSafe)
 * 
 * @author william.liangf
 */
public class JettyContainer implements Container {

    private static final Logger logger = LoggerFactory.getLogger(JettyContainer.class);

    public static final String JETTY_PORT = "dubbo.jetty.port";

    public static final String JETTY_DIRECTORY = "dubbo.jetty.directory";

    public static final String JETTY_PAGES = "dubbo.jetty.page";

    public static final int DEFAULT_JETTY_PORT = 8080;

    ServerConnector connector;

    public void start() {
        String serverPort = ConfigUtils.getProperty(JETTY_PORT);
        int port;
        if (serverPort == null || serverPort.length() == 0) {
            port = DEFAULT_JETTY_PORT;
        } else {
            port = Integer.parseInt(serverPort);
        }
        Server server = new Server(JettyContainer.DEFAULT_JETTY_PORT);
        connector = new ServerConnector(server);
//        connector.setPort(port);
        ServletHandler handler = new ServletHandler();
        
        String resources = ConfigUtils.getProperty(JETTY_DIRECTORY);
        if (resources != null && resources.length() > 0) {
            FilterHolder resourceHolder = handler.addFilterWithMapping(ResourceFilter.class, "/*", FilterMapping.DEFAULT);
//        	handler.addFilterWithMapping(ResourceFilter.class, "/*", DispatcherType.REQUEST);
            resourceHolder.setInitParameter("resources", resources);
        }
        
        ServletHolder pageHolder = handler.addServletWithMapping(PageServlet.class, "/*");
        pageHolder.setInitParameter("pages", ConfigUtils.getProperty(JETTY_PAGES));
        pageHolder.setInitOrder(2);
        
        server.addConnector(connector);
        server.setHandler(handler);
        try {
            server.start();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to start jetty server on " + NetUtils.getLocalHost() + ":" + port + ", cause: " + e.getMessage(), e);
        }
    }

    public void stop() {
        try {
            if (connector != null) {
                connector.close();
                connector = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}