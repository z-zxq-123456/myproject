package com.dcits.orion.base.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.NoProviderException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.MappingManager;
import com.dcits.orion.api.*;


/**
 * Created by Tim on 2016/6/16.
 */
public class ServiceHandler {

    /**
     * 获取服务名称
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月22日 上午10:41:42
     */
    public static final String getServiceName(BaseRequest request) {
        String name = request.getSysHead().getServiceCode() + '-'
                + request.getSysHead().getMessageType() + '-'
                + request.getSysHead().getMessageCode();
        return name;
    }

    /**
     * 获取服务
     *
     * @param serviceCode
     * @param messageType
     * @param messageCode
     * @return
     */
    public static IProcess getProcess(String serviceCode, String messageType, String messageCode) {
        return getProcess(serviceCode, messageType, messageCode, null);
    }

    /**
     * 获取服务
     *
     * @param serviceCode
     * @param messageType
     * @param messageCode
     * @param scope
     * @return
     */
    public static IProcess getProcess(String serviceCode, String messageType, String messageCode, String scope) {
        String name = serviceCode + '-' + messageType + '-' + messageCode;
        return getProcess(name, scope);
    }

    /**
     * 获取服务
     *
     * @param serviceName
     * @return
     */
    public static IProcess getProcess(String serviceName) {
        return getProcess(serviceName, null);
    }

    /**
     * 获取服务
     *
     * @param serviceName
     * @param scope
     * @return
     */
    public static IProcess getProcess(String serviceName, String scope) {
        Class<?> interFace = MappingManager.getInstance().getInterface(serviceName);
        if (null == interFace)
            throw new BusinessException(GalaxyConstants.CODE_FAILED, "[" + serviceName
                    + "]RPC服务引用未找到，请检查系统配置！");
        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        Attributes attributes;
        if (StringUtils.isEmpty(scope)) {
            attributes = new ServiceAttributesBuilder()
                    .setLoadbalance(getServiceLoadBalance())
                    .setCheck(false).build();
        } else {
            attributes = new ServiceAttributesBuilder()
                    .setLoadbalance(getServiceLoadBalance())
                    .setScope(scope)
                    .setCheck(false).build();
        }
        return (IProcess) serviceProxy
                .getService(interFace, attributes);
    }

    /**
     * 获取公共检查服务
     *
     * @return
     */
    public static SystemCheck getSystemCheck() {
        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        Attributes attributes = new ServiceAttributesBuilder()
                .setLoadbalance(getServiceLoadBalance())
                .setGroup(getAppGroup())
                .setCheck(true).build();
        try {
            return serviceProxy.getService(
                    SystemCheck.class, attributes);
        } catch (NoProviderException e) {
            return null;
        }
    }

    /**
     * 获取后处理服务
     *
     * @return
     */
    public static AfterService getAfterService() {
        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        Attributes attributes = new ServiceAttributesBuilder()
                .setLoadbalance(getServiceLoadBalance())
                .setGroup(getAppGroup())
                .setCheck(true).build();
        try {
            return serviceProxy.getService(
                    AfterService.class, attributes);
        } catch (NoProviderException e) {
            return null;
        }
    }

    /**
     * 获取业务流水服务
     *
     * @return
     */
    public static BusinessTraceNo getBusinessTraceNo() {
        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        Attributes attributes = new ServiceAttributesBuilder()
                .setLoadbalance(getServiceLoadBalance())
                .setGroup(getAppGroup())
                .setCheck(true).build();
        try {
            return serviceProxy.getService(
                    BusinessTraceNo.class, attributes);
        } catch (NoProviderException e) {
            return null;
        }
    }

    /**
     * 获取轮询策略
     *
     * @return
     */
    public static String getServiceLoadBalance() {
        /**
         * 获取轮询策略
         */
        String loadBalance = ConfigUtils.getProperty(Service.GALAXY_BUSINESS_SERVICE_LOADBALANCE);
        if (null == loadBalance)
            loadBalance = AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN;
        return loadBalance;
    }

    /**
     * 获取应用组
     *
     * @return
     */
    public static String getAppGroup() {
        return ConfigUtils.getProperty("galaxy.application.group");
    }

    /**
     * 获取应用名
     *
     * @return
     */
    public static String getAppName() {
        return ConfigUtils.getProperty("galaxy.application.name");
    }

    /**
     * 获取应用模式
     *
     * @return
     */
    public static String getAppMode() {
        /**
         * 应用模式
         */
        String mode = ConfigUtils.getProperty(Service.GALAXY_APPLICATION_MODE);
        if (null == mode)
            mode = "produce";
        return mode;
    }
}
