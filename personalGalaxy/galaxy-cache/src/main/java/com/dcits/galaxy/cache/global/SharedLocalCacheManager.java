package com.dcits.galaxy.cache.global;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.Cache;

/**
 * Created by Tim on 2016/8/19.
 */
public class SharedLocalCacheManager implements LocalCacheManager {

    protected static final Map<String, Map<Object, Cache.ValueWrapper>> localCache = new HashMap<>();

    @Override
    public void evict(String name, Object key) {
        if (localCache.containsKey(name)) {
            localCache.get(name).remove(key);
        }
    }

    /**
     * Remove all mappings from the cache.
     */
    @Override
    public void clear(String name) {
        if (localCache.containsKey(name)) {
            localCache.remove(name);
        }
    }

    @Override
    public void clearAll() {
        localCache.clear();
    }
}
