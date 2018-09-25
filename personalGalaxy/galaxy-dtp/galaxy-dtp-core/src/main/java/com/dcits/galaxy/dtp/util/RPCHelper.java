package com.dcits.galaxy.dtp.util;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.dtp.branch.BranchManager;

/**
 * 远程服务工具类
 *
 * @author Yin.Weicai
 */
public class RPCHelper {

    /**
     * 根据应用组名获取该组下任意一个分支事务管理器
     * @param appName
     * @return
     */
    public static BranchManager getBranchManager(String appGroup) {
        BranchManager branchManager = null;
        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        
        String localAppGroup = getAppGroup();
        
        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
        	.setLoadbalance(AttributesBuilderSupport.LOADBALANCE_RANDOM)
        	.setCheck(true)
        	.setGroup(appGroup);
        
        // 如果服务类型一样,ServiceProxy默认是访问本地。如需要访问远程，需要设置scope
        // 如需要访问远程，需要设置scope为ServiceAttributesBuilder.SCOPE_REMOTE
        if( appGroup != null && !appGroup.equals(localAppGroup) ){
        	builder.setScope(ServiceAttributesBuilder.SCOPE_REMOTE);
        }
        
        Attributes attributes = builder.build();
        branchManager = serviceProxy.getService(BranchManager.class, attributes);
        return branchManager;
    }

    //不再采用独立的服务发布方式，统一采用galaxy中的服务发布方式，故遗弃该方法
//    @Deprecated
//    public static void exportBranchManage(String appName, int port) {
//        BranchManager saveService = new DefaultBranchManager();
//
//        // 当前应用配置
//        ApplicationConfig application = new ApplicationConfig();
//        application.setName(appName);
//
//        // 连接注册中心配置
//        RegistryConfig registry = new RegistryConfig();
//        String address = "zookeeper://127.0.0.1:2181";
//        registry.setAddress(address);
//        registry.setTimeout(5000);
//
//        // 服务提供者协议配置
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//        protocol.setPort(port);
//        protocol.setThreads(200);
//
//        // 服务提供者暴露服务配置
//        ServiceConfig<BranchManager> service = new ServiceConfig<BranchManager>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
//        service.setApplication(application);
//        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
//        service.setProtocol(protocol); // 多个协议可以用setProtocols()
//        service.setInterface(BranchManager.class);
//        service.setRef(saveService);
//        service.setVersion("1.0.0");
//
//        // 暴露及注册服务
//        service.export();
//        System.out.println("BranchManager 向 zookeeper 注册成功");
//    }
    
    /**
     * 获取应用组
     * @return
     */
    public static String getAppGroup(){
    	String appGroup = null;
    	appGroup = ConfigUtils.getProperty("galaxy.application.group");
    	return appGroup;
    }
    
    /**
     * 获取应用名
     * @return
     */
    public static String getAppName(){
    	String appName = null;
    	appName = ConfigUtils.getProperty("galaxy.application.name");
    	return appName;
    }
}
