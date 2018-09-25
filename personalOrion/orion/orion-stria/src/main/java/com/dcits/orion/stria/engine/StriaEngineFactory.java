/**
 * <p>Title: StriaEngineProcessFactory.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年12月17日 下午3:00:52
 * @version V1.0
 */
package com.dcits.orion.stria.engine;

import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.rpc.impl.FlowService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author Tim
 * @version V1.0
 * @description 流程引擎工厂
 * @update 2014年12月17日 下午3:00:52
 */

public class StriaEngineFactory implements InitializingBean {

    private static final Logger log = LoggerFactory
            .getLogger(StriaEngineFactory.class);

    private Resource[] striaXmlLocations;
    private Resource[] striaJsonLocations;

    private FlowService flowService;

    @Override
    public void afterPropertiesSet() throws Exception {
        buildFlow();
    }

    private void buildFlow() throws IOException {
        if (null != getStriaXmlLocations() || null != getStriaJsonLocations()) {
            if (null != getStriaXmlLocations()) {
                if (log.isDebugEnabled())
                    log.debug("加载Xml流程定义文件 [" + getStriaXmlLocations().length + "]");
                for (Resource resource : getStriaXmlLocations()) {
                    if (log.isDebugEnabled())
                        log.debug("加载Xml流程定义文件 [" + resource.getFilename() + "]");
                    flowService.deployFlow(resource.getInputStream());
                }
            }
            if (null != getStriaJsonLocations()) {
                if (log.isDebugEnabled())
                    log.debug("加载Json流程定义文件 [" + getStriaJsonLocations().length + "]");
                for (Resource resource : getStriaJsonLocations()) {
                    if (log.isDebugEnabled())
                        log.debug("加载Json流程定义文件 [" + resource.getFilename() + "]");
                    flowService.deployFlow(resource.getInputStream());
                }
            }
        } else {
            /**  屏蔽启动加载内存，采用软加载
             List<Flow> flows = flowService.getFlow();
             for (Flow flow : flows) {
             if (log.isDebugEnabled())
             log.debug("加载流程定义模型 [" + flow.getFlowid() + "]");
             flowService.deployFlow(flowService.getFLow(flow.getFlowid()));
             }
             */
        }
    }

    /**
     * @return striaLocations : return the property striaLocations.
     */
    public Resource[] getStriaXmlLocations() {
        return striaXmlLocations;
    }

    /**
     * @param striaXmlLocations : set the property striaLocations.
     */
    public void setStriaXmlLocations(Resource[] striaXmlLocations) {
        this.striaXmlLocations = striaXmlLocations;
    }


    public Resource[] getStriaJsonLocations() {
        return striaJsonLocations;
    }

    public void setStriaJsonLocations(Resource[] striaJsonLocations) {
        this.striaJsonLocations = striaJsonLocations;
    }

    /**
     * 软加载模式，加载流程定义
     *
     * @param flowId
     * @return
     */
    public Flow getFlows(String flowId) {
        Flow flow = null;
        if (flowService.containsKeyForCache(flowId)) {
            // 方便测试
            flow = flowService.getFlowForCache(flowId);
        } else {
            flow = flowService.deployFlow(flowService.getFLowById(flowId));
        }
        return flow;
    }

    public FlowService getFlowService() {
        return flowService;
    }

    public void setFlowService(FlowService flowService) {
        this.flowService = flowService;
    }

}
