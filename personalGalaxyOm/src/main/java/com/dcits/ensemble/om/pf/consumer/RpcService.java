package com.dcits.ensemble.om.pf.consumer;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dcits.orion.stria.api.rpc.IFlowService;
import com.dcits.galaxy.base.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tim on 2015/12/9.
 */
public class RpcService {
    private static final Logger log = LoggerFactory
            .getLogger(RpcService.class);
    private static Map<String, ReferenceConfig> references = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> tClass, String zkUrl, String group) {
        String referenceKey = zkUrl + "-" + group + "-" + tClass.getName();
        if (log.isDebugEnabled())
            log.debug("cache key is {}", referenceKey);

        if (references.containsKey(referenceKey)) {
            if (log.isDebugEnabled())
                log.debug("get service from cache");

            ReferenceConfig<T> reference = (ReferenceConfig<T>) references.get(referenceKey);
            T t = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
            return t;
        } else {
            if (log.isDebugEnabled())
                log.debug("get service from rpc, zk url {}", zkUrl);

            // 当前应用配置
          /*  ApplicationConfig application = new ApplicationConfig("DM");*/
            // 连接注册中心配置
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(zkUrl);
            // 引用远程服务
            ReferenceConfig<T> reference = new ReferenceConfig<>(); // 该类较重，封装了与注册中心的连接以及与提供者的连接，请自行缓存
            reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
            reference.setInterface(tClass);
            /*reference.setCheck(false);
            reference.setLoadbalance("random");*/
            if (StringUtils.isNotEmpty(group))
                reference.setGroup(group);
            // 和本地bean一样使用xxxService
            T t = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

            if (t != null)
                references.put(referenceKey, reference);
            return t;
        }
    }

    public static void main(String arg[]) {
        IFlowService flow = RpcService.getService(IFlowService.class, "zookeeper://192.168.165.141:2181", "demo-all");
        System.out.println(flow.getFLow());
    }
}
