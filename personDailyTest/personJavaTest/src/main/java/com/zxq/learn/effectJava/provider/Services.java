package com.zxq.learn.effectJava.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created{ by zhouxqh} on 2017/10/19.
 * 适配器模式封装  provider
 * 返回比提供者需要的更多的服务接口
 */
public class Services {

    //防止实例化对象
    private Services(){}
    private static final String DEFAULT_PROVIDER_NAME="pro1";
    private static final Map<String, Provider> providerMap = new ConcurrentHashMap<String, Provider>();
    //服务注册
    public static void registerDefaultProvider(Provider provider){
        registerProvider(DEFAULT_PROVIDER_NAME,provider);
    }
    public static void registerProvider(String name, Provider provider){
        providerMap.put(name,provider);
    }
    //服务访问
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name){
        Provider provider = providerMap.get(name);
        if(provider == null)
            throw new IllegalArgumentException("there is no provider name = "+name);
        return provider.newInstance();
    }
}
