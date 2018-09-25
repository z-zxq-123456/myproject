package com.dcits.galaxy.cache.global;


/**
 * Created by Tim on 2016/7/9.
 */
public interface LocalCacheManager {

    void evict(String name, Object key);

    /**
     * Remove all mappings from the cache.
     */
    void clear(String name);

    void clearAll();
}
