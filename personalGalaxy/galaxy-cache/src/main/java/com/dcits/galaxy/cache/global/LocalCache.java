package com.dcits.galaxy.cache.global;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.core.client.ServiceProxy;

/**
 * Created by Tim on 2016/7/9.
 */
public class LocalCache implements Cache, InitializingBean {

    private static final Logger logger = LoggerFactory
            .getLogger(LocalCache.class);


    private String name;

    /**
     * Return the cache name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Return the the underlying native cache provider.
     */
    @Override
    public Object getNativeCache() {
        return null;
    }

    /**
     * Return the value to which this cache maps the specified key.
     * <p>Returns {@code null} if the cache contains no mapping for this key;
     * otherwise, the cached value (which may be {@code null} itself) will
     * be returned in a {@link ValueWrapper}.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which this cache maps the specified key,
     * contained within a {@link ValueWrapper} which may also hold
     * a cached {@code null} value. A straight {@code null} being
     * returned means that the cache contains no mapping for this key.
     */
    @Override
    public ValueWrapper get(Object key) {
        return SharedLocalCacheManager.localCache.get(name).get(key);
    }

    /**
     * Associate the specified value with the specified key in this cache.
     * <p>If the cache previously contained a mapping for this key, the old
     * value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(Object key, Object value) {
        SharedLocalCacheManager.localCache.get(name).put(key, new SimpleValueWrapper(value));
    }

    /**
     * Evict the mapping for this key from this cache if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    @Override
    public void evict(Object key) {
        List<LocalCacheManager> serviceList = ServiceProxy.getInstance().getServiceAll(LocalCacheManager.class);
        if (serviceList != null) {
            for (LocalCacheManager localCacheManager : serviceList) {
                localCacheManager.evict(this.name, key);
                if (logger.isDebugEnabled()) {
                    String serverIP = RpcContext.getContext().getRemoteHost();
                    int serverPort = RpcContext.getContext().getRemotePort();
                    logger.debug("[Evict Local Cache] IP[" + serverIP + "] serverPort[" + serverPort + "] name[" + this.name + "] key[" + key + "] removed!");
                }
            }
        }
    }

    /**
     * Remove all mappings from the cache.
     */
    @Override
    public void clear() {
        List<LocalCacheManager> serviceList = ServiceProxy.getInstance().getServiceAll(LocalCacheManager.class);
        if (serviceList != null) {
            for (LocalCacheManager localCacheManager : serviceList) {
                localCacheManager.clear(this.name);
                if (logger.isDebugEnabled()) {
                    String serverIP = RpcContext.getContext().getRemoteHost();
                    int serverPort = RpcContext.getContext().getRemotePort();
                    logger.debug("[Evict Local Cache] IP[" + serverIP + "] serverPort[" + serverPort + "] name[" + this.name + "] cleared!");
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        SharedLocalCacheManager.localCache.put(name, new ConcurrentHashMap<Object, Cache.ValueWrapper>());
    }
}
