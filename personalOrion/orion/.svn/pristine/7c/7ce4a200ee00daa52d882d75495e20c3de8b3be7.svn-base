package com.dcits.orion.base.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.MessageDistributor;
import com.dcits.galaxy.base.extension.ExtensionLoader;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.orion.api.Handler;
import com.dcits.orion.api.IError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

/**
 * 为通讯适配器消息转发提供的实现。
 * Created by lixbb on 2016/1/20.
 */
public class MessageHandler implements MessageDistributor {

    private static final Logger logger = LoggerFactory
            .getLogger(MessageHandler.class);

    public static final String GALAXY_CONNECTOR_LOADBALANCE = "galaxy.connector.loadBalance";

    public static final String GALAXY_ORION_APPLICATION_HANDLER_SCOPE = "galaxy.orion.message.handler.scope";

    public static final String GALAXY_CLUSTER_LIMITOVER_SIZE = "galaxy.cluster.limitover.size";

    public String execute(String inMsg, String appGroup, String msgFormat, String msgParser) {
        if (StringUtils.isBlank(inMsg))
            return "Galaxy server is starting!";
        Object msg = null;
        if (msgParser == null) {
            if (msgFormat != null && msgFormat.trim().length() > 0 && !msgFormat.equals("json")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(GalaxyConstants.MSG_FORMAT)
                        .append("=")
                        .append(msgFormat)
                        .append("|")
                        .append(inMsg);
                msg = stringBuffer.toString();
            } else msg = inMsg;
        } else {
            MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension(msgParser);
            msg = parser.toMap(inMsg);
        }
        /**
         * 获取轮询策略
         */
        String loadBalance = ConfigUtils.getProperty(GALAXY_CONNECTOR_LOADBALANCE);
        if (null == loadBalance)
            loadBalance = AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN;
        /**
         * 应用模式
         */
        String scope = ConfigUtils.getProperty(GALAXY_ORION_APPLICATION_HANDLER_SCOPE);
        String limitOver = ConfigUtils.getProperty(GALAXY_CLUSTER_LIMITOVER_SIZE);
        Attributes attributes;
        ServiceAttributesBuilder serviceAttributesBuilder = new ServiceAttributesBuilder()
                .setLoadbalance(loadBalance)
                .setGroup(appGroup)
                .setCheck(false);
        // Handler Scope
        if (StringUtils.isNotEmpty(scope) && scope.equals(ServiceAttributesBuilder.SCOPE_REMOTE)) {
            serviceAttributesBuilder = serviceAttributesBuilder.setScope(ServiceAttributesBuilder.SCOPE_REMOTE);
        }
        if (null != limitOver) {
            serviceAttributesBuilder = serviceAttributesBuilder.setCluster("limitover");
        }
        attributes = serviceAttributesBuilder.build();
        Handler service = ServiceProxy.getInstance().getService(
                Handler.class, attributes);

        String out = "";
        try {
            if (msg instanceof Map) {
                Map ret = service.handle((Map) msg);
                MapParser<String> parser = ExtensionLoader.getExtensionLoader(MapParser.class).getExtension(msgParser);
                out = parser.toObj(ret);
            } else if (msg instanceof String)
                out = service.handle((String) msg);
        } catch (Throwable t) {
            if (msgParser == null) msgParser = "json2map";
            out = ExtensionLoader.getExtensionLoader(IError.class).getExtension(msgParser).pack(t);
        }
        if (logger.isDebugEnabled()) {
            String serverIP = RpcContext.getContext().getRemoteHost();
            int serverPort = RpcContext.getContext().getRemotePort();
            String methodName = RpcContext.getContext().getMethodName();
            logger.debug("***********************oms Handler***********************\nserverIP =" + serverIP + "serverPort =" + serverPort + " methodName=" + methodName);
        }
        return out;
    }

    /**
     * 设置平台ID
     */
    public void setPlatFormId() {
        ThreadLocalManager.setUID(SeqUtils.getStringSeq());
        //设置slf4j的平台ID
        MDC.put(GalaxyConstants.PLATFORM_ID, ThreadLocalManager.getUID());
    }

    /**
     * 清除平台ID
     */
    public void removePlatFormId() {
        ThreadLocalManager.remove();
        //清除平台ID slf4j的平台ID
        MDC.remove(GalaxyConstants.PLATFORM_ID);
    }
}
