package com.dcits.orion.core.dao;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.annotation.TablePkScanner;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.core.Context;
import com.dcits.orion.core.util.BusinessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 基础dao，对cobar的封装
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年2月9日 下午5:24:38
 */
@Component
public class DataAccessor {

    static final String INSERT = "insert";

    static final String UPDATE = "updateByPrimaryKey";

    static final String DELETE = "deleteByPrimaryKey";

    static final String SELECT = "selectByPrimaryKey";

    private static final Logger logger = LoggerFactory
            .getLogger(DataAccessor.class);

    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    /**
     * 创建Cache key
     *
     * @param record
     * @param <T>
     * @return
     */
    static <T extends AbstractBean> String createCacheKey(T record) {
        return createCacheKey(record, (Shard) null);
    }

    static <T extends AbstractBean> String createCacheKey(T record, Shard shard) {
        String[] pks = TablePkScanner.pkColsScanner(record);
        List<Object> pkValue = new ArrayList<>();
        if (shard != null) {
            pkValue.add(shard.getId());
        }
        for (int i = 0; i < pks.length; i++) {
            try {
                pkValue.add(ObjectUtils.getField(record, pks[i]));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new GalaxyException(e);
            }
        }
        return createKey(record.getClass().getSimpleName(), null == shard ? null : shard.getId(), pkValue.toArray());
    }

    /**
     * 创建Cache key
     *
     * @param record
     * @param pkValue
     * @param <T>
     * @return
     */
    static <T extends AbstractBean> String createCacheKey(T record,
                                                          Object... pkValue) {
        return createKey(record.getClass().getSimpleName(), null, pkValue);
    }

    static <T extends AbstractBean> String createCacheKey(T record, Shard shard,
                                                          Object... pkValue) {
        return createKey(record.getClass().getSimpleName(), null == shard ? null : shard.getId(), pkValue);
    }

    /**
     * 判断是否加入缓存，所有的key必须不为null，否则不添加缓存。
     *
     * @param record
     * @param <T>
     * @return
     */
    static <T extends AbstractBean> boolean isAddCache(T record) {
        String[] pks = TablePkScanner.pkColsScanner(record);
        for (int i = 0; i < pks.length; i++) {
            try {
                // 有一个主键为null，不记录缓存
                if (null == record.readValue(pks[i])) {
                    return false;
                }
            } catch (Exception e) {
                throw new GalaxyException(e);
            }
        }
        return true;
    }

    /**
     * 判断是否加入缓存，所有的key必须不为null，否则不添加缓存。
     *
     * @param record
     * @param pkValue
     * @param <T>
     * @return
     */
    static <T extends AbstractBean> boolean isAddCache(T record,
                                                       Object... pkValue) {
        String[] pks = TablePkScanner.pkColsScanner(record);
        if (pks.length != pkValue.length)
            return false;
        for (int i = 0; i < pks.length; i++) {
            if (null == pkValue[i])
                return false;
        }
        return true;
    }

    private static String createKey(String tableName, String shardId, Object... pkValue) {
        StringBuilder sb = new StringBuilder();
        if (null == shardId)
            sb.append(ServiceHandler.getAppGroup()).append("_").append(tableName.replaceAll(".class", ""));
        else
            sb.append(ServiceHandler.getAppGroup()).append("_").append(tableName.replaceAll(".class", "")).append("_").append(shardId);
        if (null != pkValue) {
            for (int i = 0; i < pkValue.length; i++) {
                sb.append("_").append(pkValue[i]);
            }
        }
        String key = sb.toString();
        if (logger.isDebugEnabled())
            logger.debug("缓存Key[{}]", key);
        return key;
    }

    <T extends AbstractBean> int insert(String namespace, T record, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (shard == null) {
                // 增加批量提交存储
                if (keepThreadCache(Context.ADD_BATCH_INSERT_KEY, namespace + "." + INSERT, record))
                    return 1;
                else
                    return getSessionTemplate().insert(namespace, INSERT, record);
            } else {
                // 增加批量提交存储
                if (keepThreadCache(Context.ADD_BATCH_INSERT_KEY, namespace + "." + INSERT, record, shard))
                    return 1;
                else
                    return getSessionTemplate().insert(namespace + "." + INSERT, record, shard);

            }

        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("插入记录 namespace [" + namespace + "] id [" + INSERT + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    <T extends AbstractBean> int insert(String namespace, T record) {
        return insert(namespace, record, null);
    }

    /**
     * 修改的record时，使用CacheEvict清空缓存。
     * 方法不使用CachePut主要考虑，record的结果集数据不一定是全的。
     *
     * @param namespace
     * @param record
     * @param cacheKey
     * @param <T>
     * @return
     */
    @CacheEvict(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> updateParamCache(String namespace, T record, String cacheKey, Shard shard) {
        int i = this.updateNoCache(namespace, record, shard);
        return new TwoTuple<Integer, T>(new Integer(i), record);
    }

    /**
     * 修改的record时，使用CacheEvict清空缓存。
     * 方法不使用CachePut主要考虑，record的结果集数据不一定是全的。
     *
     * @param namespace
     * @param record
     * @param cacheKey
     * @param <T>
     * @return
     */
    @CacheEvict(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> updateParamCache(String namespace, T record, String cacheKey) {
        int i = this.updateNoCache(namespace, record);
        return new TwoTuple<Integer, T>(new Integer(i), record);
    }


    /**
     * 修改的record时，使用CacheEvict清空缓存。
     * 方法不使用CachePut主要考虑，record的结果集数据不一定是全的。
     *
     * @param namespace
     * @param record
     * @param cacheKey
     * @param <T>
     * @return
     */
    @CacheEvict(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> updateBusinessCache(String namespace, T record, String cacheKey) {
        return this.updateBusinessCache(namespace, record, cacheKey, null);
    }

    @CacheEvict(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> updateBusinessCache(String namespace, T record, String cacheKey, Shard shard) {
        int i = this.updateNoCache(namespace, record, shard);
        return new TwoTuple<Integer, T>(i, record);
    }

    <T extends AbstractBean> int updateNoCache(String namespace, T record, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (shard == null) {
                if (keepThreadCache(Context.ADD_BATCH_UPDATE_KEY, namespace + "." + UPDATE, record))
                    return 1;
                else
                    return getSessionTemplate().update(namespace, UPDATE, record);
            } else {
                if (keepThreadCache(Context.ADD_BATCH_UPDATE_KEY, namespace + "." + UPDATE, record, shard))
                    return 1;
                else
                    return getSessionTemplate().update(namespace + "." + UPDATE, record, shard);

            }

        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("根据主键更新记录 namespace [" + namespace + "] id [" + UPDATE + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    <T extends AbstractBean> int updateNoCache(String namespace, T record) {
        return updateNoCache(namespace, record, null);
    }


    /**
     * 修改的record时，使用CacheEvict清空缓存。
     * 方法不使用CachePut主要考虑，record的结果集数据不一定是全的。
     *
     * @param namespace
     * @param record
     * @param cacheKey
     * @param <T>
     * @return
     */
    @CacheEvict(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> deleteParamCache(String namespace, T record, String cacheKey) {
        return deleteParamCache(namespace, record, cacheKey, null);
    }

    @CacheEvict(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> deleteParamCache(String namespace, T record, String cacheKey, Shard shard) {
        int i = this.deleteNoCache(namespace, record, shard);
        return new TwoTuple<Integer, T>(new Integer(i), record);
    }

    /**
     * 修改的record时，使用CacheEvict清空缓存。
     * 方法不使用CachePut主要考虑，record的结果集数据不一定是全的。
     *
     * @param namespace
     * @param record
     * @param cacheKey
     * @param <T>
     * @return
     */
    @CacheEvict(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> deleteBusinessCache(String namespace, T record, String cacheKey) {
        return deleteBusinessCache(namespace, record, cacheKey, null);
    }

    @CacheEvict(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> TwoTuple<Integer, T> deleteBusinessCache(String namespace, T record, String cacheKey, Shard shard) {
        int i = this.deleteNoCache(namespace, record, shard);
        return new TwoTuple<Integer, T>(i, record);
    }

    <T extends AbstractBean> int deleteNoCache(String namespace, T record, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (shard == null)
                return getSessionTemplate().delete(namespace, DELETE, record);
            else
                return getSessionTemplate().delete(namespace + "." + DELETE, record, shard);
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("根据主键删除记录 namespace [" + namespace + "] id [" + UPDATE + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    <T extends AbstractBean> int deleteNoCache(String namespace, T record) {
        return deleteNoCache(namespace, record, null);
    }

    /**
     * 根据主键查询record时，使用Cacheable缓存record。
     *
     * @param param
     * @param pkValue
     * @param <T>
     * @return
     */
    @Cacheable(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> T selectParamCache(String namespace, T param, String cacheKey,
                                                       Object... pkValue) {
        return selectParamCache(namespace, param, null, cacheKey, pkValue);
    }

    @Cacheable(value = "param", key = "#cacheKey")
    public <T extends AbstractBean> T selectParamCache(String namespace, T param, Shard shard, String cacheKey,
                                                       Object... pkValue) {
        return selectNoCache(namespace, shard, param, pkValue);
    }


    /**
     * 根据主键查询record时，使用Cacheable缓存record。
     *
     * @param namespace
     * @param param
     * @param cacheKey
     * @param pkValue
     * @param <T>
     * @return
     */
    @Cacheable(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> T selectBusinessCache(String namespace, T param, String cacheKey,
                                                          Object... pkValue) {
        return selectBusinessCache(namespace, param, null, cacheKey, pkValue);
    }

    @Cacheable(value = "business", key = "#cacheKey")
    public <T extends AbstractBean> T selectBusinessCache(String namespace, T param, Shard shard, String cacheKey,
                                                          Object... pkValue) {
        return selectNoCache(namespace, shard, param, pkValue);
    }

    <T extends AbstractBean> T selectNoCache(String namespace, Shard shard, T record,
                                             Object... pkValue) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            Object p = parsePks(record, false, pkValue);

            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard, namespace + "." + SELECT, p)) {
                return (T) BaseDao.queryCacheData(shard, namespace + "." + SELECT, p);
            } else {
                if (null == p) {
                    if (logger.isWarnEnabled())
                        logger.debug("根据主键获取查询参数为null，执行无参数查询");
                    if (shard == null)
                        return (T) getSessionTemplate().selectOne(namespace, SELECT);
                    else
                        return (T) getSessionTemplate().selectOne(namespace + "." + SELECT, shard);
                } else {
                    if (shard == null)
                        return (T) getSessionTemplate().selectOne(namespace, SELECT, p);
                    else
                        return (T) getSessionTemplate().selectOne(namespace + "." + SELECT, p, shard);
                }
            }
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("根据主键查询记录 namespace [" + namespace + "] id [" + SELECT + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }


    <T extends AbstractBean> T selectNoCache(String namespace, T record,
                                             Object... pkValue) {
        return selectNoCache(namespace, null, record, pkValue);
    }

    <T extends AbstractBean> List<T> selectListByPrimaryKey(String namespace, Shard shard, T param,
                                                            Object... pkValue) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            Object p = parsePks(param, true, pkValue);
            if (null == p) {
                if (logger.isWarnEnabled())
                    logger.debug("根据主键获取查询参数为null，不进行查询操作返回空数组");
                return new ArrayList<>();
            }

            return selectList(namespace, SELECT, p, shard);
            /*
            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard,namespace + "." + SELECT,p))
            {
                return (List<T>)BaseDao.queryCacheData(shard,namespace + "." + SELECT,p);
            }
            else {
                if (shard == null)
                    return getSessionTemplate().selectList(namespace, SELECT, p);
                else
                    return getSessionTemplate().selectList(namespace + "." + SELECT, p, shard);
            }*/
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("根据主键查询记录集 namespace [" + namespace + "] id [" + SELECT + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    <T extends AbstractBean> List<T> selectListByPrimaryKey(String namespace, T param,
                                                            Object... pkValue) {
        return selectListByPrimaryKey(namespace, null, param, pkValue);
    }

    final protected <T extends AbstractBean> Object parsePks(T param, boolean ignoreNullValue, Object... pkValue) {
        String[] pks = TablePkScanner.pkColsScanner(param);
        // 表无主键
        if (pks.length == 0) {
            return null;
        }
        // 主键值为Null
        if (null == pkValue || pkValue.length == 0) {
            throw BusinessUtils.createBusinessException("100502");
        }
        // 有效value
        boolean effectiveValue = false;
        for (Object v : pkValue) {
            if (null != v) {
                effectiveValue = true;
                break;
            }
        }
        // 无有效value
        if (!effectiveValue) {
            throw BusinessUtils.createBusinessException("100503");
        }

        // 将主键值存于param对象中
        if (null == param) {
            Map<String, Object> p = new HashMap<String, Object>();
            int i = 0;
            for (String pk : pks) {
                if (null != pkValue[i])
                    p.put(pk, pkValue[i]);
                else if (!ignoreNullValue) {
                    throw BusinessUtils.createBusinessException("100501", new String[]{pk});
                }
                i++;
            }
            return p;
        } else {
            int i = 0;
            for (String pk : pks) {
                if (null != pkValue[i])
                    try {
                        param.writeValue(pk, pkValue[i]);
                    } catch (Exception e) {
                        throw BusinessUtils.createBusinessException("100504", new String[]{pk});
                    }
                else if (!ignoreNullValue) {
                    throw BusinessUtils.createBusinessException("100501", new String[]{pk});
                }
                i++;
            }
            return param;
        }
    }

    int insert(String nameSpace, String sqlId, Object param, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();
        try {
            if (keepThreadCache(Context.ADD_BATCH_INSERT_KEY, nameSpace + "." + sqlId, param, shard))
                return 1;
            else {
                if (shard == null)
                    return getSessionTemplate().insert(nameSpace, sqlId, param);
                else
                    return getSessionTemplate().insert(nameSpace + "." + sqlId, param, shard);
            }
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("插入记录 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    int insert(String nameSpace, String sqlId, Object param) {
        return insert(nameSpace, sqlId, param, null);
    }

    int update(String nameSpace, String sqlId, Object param, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();
        try {
            if (keepThreadCache(Context.ADD_BATCH_UPDATE_KEY, nameSpace + "." + sqlId, param, shard))
                return 1;
            else {
                if (shard == null)
                    return getSessionTemplate().update(nameSpace, sqlId, param);
                else
                    return getSessionTemplate().update(nameSpace + "." + sqlId, param, shard);

            }

        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("更新记录 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    int update(String nameSpace, String sqlId, Object param) {
        return update(nameSpace, sqlId, param, null);
    }

    int delete(String nameSpace, String sqlId, Object param, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (keepThreadCache(Context.ADD_BATCH_DELETE_KEY, nameSpace + "." + sqlId, param, shard))
                return 1;
            if (shard == null)
                return getSessionTemplate().delete(nameSpace, sqlId, param);
            else
                return getSessionTemplate().delete(nameSpace + "." + sqlId, param, shard);
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("删除记录 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }


    int delete(String nameSpace, String sqlId, Object param) {
        return delete(nameSpace, sqlId, param, null);
    }

    @SuppressWarnings("unchecked")
    <T> T selectOne(String nameSpace, String sqlId) {
        return selectOne(nameSpace, sqlId, null);
    }

    <T> T selectOne(String nameSpace, String sqlId, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard, nameSpace + "." + sqlId, null)) {
                return (T) BaseDao.queryCacheData(shard, nameSpace + "." + sqlId, null);
            } else {
                if (shard == null)
                    return (T) getSessionTemplate().selectOne(nameSpace, sqlId);
                else
                    return (T) getSessionTemplate().selectOne(nameSpace + sqlId, shard);
            }
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("查询记录 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    @SuppressWarnings("unchecked")
    <T> T selectOne(String nameSpace, String sqlId, Object param) {
        return selectOne(nameSpace, sqlId, param, null);
    }

    <T> T selectOne(String nameSpace, String sqlId, Object param, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard, nameSpace + "." + sqlId, param)) {
                return (T) BaseDao.queryCacheData(shard, nameSpace + "." + sqlId, param);
            } else {
                if (shard == null)
                    return (T) getSessionTemplate().selectOne(nameSpace, sqlId, param);
                else
                    return (T) getSessionTemplate().selectOne(nameSpace + "." + sqlId, param, shard);
            }
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("查询记录 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    @SuppressWarnings("unchecked")
    <T> List<T> selectList(String nameSpace, String sqlId) {
        return selectList(nameSpace, sqlId, null);
    }

    @SuppressWarnings("unchecked")
    <T> List<T> selectList(String nameSpace, String sqlId, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard, nameSpace + "." + sqlId, null)) {
                return (List<T>) BaseDao.queryCacheData(shard, nameSpace + "." + sqlId, null);
            } else {
                if (shard == null)
                    return getSessionTemplate().selectList(nameSpace, sqlId);
                else
                    return getSessionTemplate().selectList(nameSpace + "." + sqlId, shard);
            }

        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("查询记录集 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    @SuppressWarnings("unchecked")
    <T> List<T> selectList(String nameSpace, String sqlId, Object param, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();

        try {
            if (BaseDao.isBatchTransactional() && BaseDao.isCachedData(shard, nameSpace + "." + sqlId, param)) {
                return (List<T>) BaseDao.queryCacheData(shard, nameSpace + "." + sqlId, param);
            } else {
                if (shard == null)
                    return getSessionTemplate().selectList(nameSpace, sqlId, param);
                else
                    return getSessionTemplate().selectList(nameSpace + "." + sqlId, param, shard);
            }
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("查询记录集 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    <T> List<T> selectList(String nameSpace, String sqlId, Object param) {
        return selectList(nameSpace, sqlId, param, null);
    }

    /**
     * 批量插入数据  通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param paramters
     * @param shard
     * @return
     */
    int insertAddBatch(String nameSpace, String sqlId, List paramters, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();
        try {
            if (shard == null)
                return getSessionTemplate().insertAddBatch(nameSpace, sqlId, paramters);
            else
                return getSessionTemplate().insertAddBatch(nameSpace + "." + sqlId, paramters, shard);
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("批量插入数据 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    /**
     * 批量插入数据  通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param paramters
     * @return
     */
    int insertAddBatch(String nameSpace, String sqlId, List paramters) {
        return insertAddBatch(nameSpace, sqlId, paramters, null);
    }

    /**
     * 批量更新 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param parameters
     * @return
     */
    int updateAddBatch(String nameSpace, String sqlId, List parameters, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();
        try {
            if (shard == null)
                return getSessionTemplate().updateAddBatch(nameSpace, sqlId, parameters);
            else
                return getSessionTemplate().updateAddBatch(nameSpace + "." + sqlId, parameters, shard);
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("批量更新数据 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    /**
     * 批量更新 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param parameters
     * @return
     */
    int updateAddBatch(String nameSpace, String sqlId, List<?> parameters) {
        return updateAddBatch(nameSpace, sqlId, parameters, null);
    }

    /**
     * 批量删除 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param parameters
     * @return
     */
    int deleteAddBatch(String nameSpace, String sqlId, List parameters, Shard shard) {
        long start = 0;
        if (logger.isDebugEnabled())
            start = System.currentTimeMillis();
        try {
            if (shard == null)
                return getSessionTemplate().deleteAddBatch(nameSpace, sqlId, parameters);
            else
                return getSessionTemplate().deleteAddBatch(nameSpace + "." + sqlId, parameters, shard);
        } finally {
            if (logger.isDebugEnabled()) {
                long elapsedTime = System.currentTimeMillis() - start;
                logger.debug("批量删除数据 namespace [" + nameSpace + "] id [" + sqlId + "] elapsedTime[" + elapsedTime + "]");
            }
        }
    }

    /**
     * 批量删除 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param parameters
     * @return
     */
    int deleteAddBatch(String nameSpace, String sqlId, List<?> parameters) {
        return deleteAddBatch(nameSpace, sqlId, parameters, null);
    }

    /**
     * @return shardSqlSessionTemplate : return the property getSessionTemplate().
     */
    public ShardSqlSessionTemplate getSessionTemplate() {
        // Galaxy2.0.0 版本升级后，通过BaseDao继承的Dao类，无法获取shardSqlSessionTemplate。
        if (null == shardSqlSessionTemplate) {
            this.shardSqlSessionTemplate = (ShardSqlSessionTemplate) SpringApplicationContext.getContext().getBean("shardSqlSessionTemplate");
        }
        return shardSqlSessionTemplate;
    }

    public void setShardSqlSessionTemplate(ShardSqlSessionTemplate shardSqlSessionTemplate) {
        this.shardSqlSessionTemplate = shardSqlSessionTemplate;
    }

    /**
     * 将执行的sql暂存到ThreadLocal，业务执行完后统一进行批量提交。
     *
     * @param cacheKey
     * @param statement
     * @param record
     * @param <T>
     * @return
     */
    private <T> boolean keepThreadCache(String cacheKey, String statement, T record) {
        return keepThreadCache(cacheKey, statement, record, null);
    }

    private <T> boolean keepThreadCache(String cacheKey, String statement, T record, Shard shard) {
        Stack<Map> stack = (Stack) ThreadLocalManager.get("cacheStack");
        if (stack != null && !stack.empty()) {
            Map cacheMap = stack.peek();
            List records = null;
            Map<String, Map> batchMap = (Map) cacheMap.get(cacheKey);
            if (batchMap == null) {
                batchMap = new HashMap<>();
                cacheMap.put(cacheKey, batchMap);
            }
            Map<Object, List> shardStatement = batchMap.get(statement);
            if (shardStatement == null) {
                shardStatement = new HashMap();
                batchMap.put(statement, shardStatement);
            } else {
                records = shardStatement.get(shard);
            }
            if (records == null) {
                records = new ArrayList();
                shardStatement.put(shard, records);
            }
            records.add(record);
            return true;
        }
        return false;
    }


}
