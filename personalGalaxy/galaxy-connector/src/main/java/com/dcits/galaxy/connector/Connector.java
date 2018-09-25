package com.dcits.galaxy.connector;

import com.alibaba.dubbo.container.Container;

/**
 * Connector.
 * 
 * @author xuecy
 *
 */
public interface Connector extends Container{

    public static final String GALAXY_CONNECTOR_LOADBALANCE = "galaxy.connector.loadBalance";
    public static final String GALAXY_APPLICATION_MODE = "galaxy.application.mode";

}