package com.dcits.orion.core.dao;

import com.dcits.orion.core.Context;
import com.dcits.orion.core.util.BusinessUtils;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.data.IAppHead;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import java.util.*;

/**
 * 基础dao，对cobar的封装
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年2月9日 下午5:24:38
 */
public abstract class BaseDao implements IDao {

    private static final Logger logger = LoggerFactory
            .getLogger(BaseDao.class);

    /**
     * 具体的数据处理者。直接面向DB，包括缓存机制的处理
     */
    @Resource
    private DataAccessor dataAccessor;

    public void setDataAccessor(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    protected abstract String getNameSpace();

    protected CacheType cacheType;

    /**
     * 二级缓存类型
     */
    protected enum CacheType {
        /**
         * 参数级缓存，有效期永久
         */
        PARAM_CACHE,
        /**
         * 业务级缓存，有效期1天
         */
        BUSINESS_CACHE,;
    }

    protected CacheType getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheType cacheTYpe) {
        this.cacheType = cacheTYpe;
    }

    /**
     * 插入记录
     *
     * @param record
     *         dbmodel bean，必须定义了@TablePk主键
     * @param <T>
     *         记录mapper泛型
     * @return
     */
    @Override
    public <T extends AbstractBean> int insert(T record) {
        return dataAccessor.insert(getNameSpace(), record);
    }


    public <T extends AbstractBean> int insert(T record, Shard shard) {
        return dataAccessor.insert(getNameSpace(), record, shard);
    }

    /**
     * 根据主键更新记录
     *
     * @param record
     *         dbmodel bean，必须定义了@TablePk主键
     * @param <T>
     *         记录mapper泛型
     * @return
     */
    @Override
    public <T extends AbstractBean> int updateByPrimaryKey(T record) {

        return updateByPrimaryKey(record, null);
    }

    public <T extends AbstractBean> int updateByPrimaryKey(T record, Shard shard) {
        int count;
        if (doRowCache(record)) {
            if (getCacheType() == CacheType.PARAM_CACHE) {
                count = dataAccessor.updateParamCache(getNameSpace(), record, DataAccessor.createCacheKey(record, shard), shard).first;
            } else if (getCacheType() == CacheType.BUSINESS_CACHE) {
                count = dataAccessor.updateBusinessCache(getNameSpace(), record, DataAccessor.createCacheKey(record, shard), shard).first;
            } else {
                count = dataAccessor.updateNoCache(getNameSpace(), record, shard);
            }
        } else {
            count = dataAccessor.updateNoCache(getNameSpace(), record, shard);
        }
        return count;
    }

    /**
     * 根据主键删除记录
     *
     * @param record
     *         dbmodel bean，必须定义了@TablePk主键
     * @param <T>
     *         记录mapper泛型
     * @return
     */
    @Override
    public <T extends AbstractBean> int deleteByPrimaryKey(T record) {

        return deleteByPrimaryKey(record, null);
    }

    public <T extends AbstractBean> int deleteByPrimaryKey(T record, Shard shard) {
        int count;
        if (doRowCache(record)) {
            if (getCacheType() == CacheType.PARAM_CACHE) {
                count = dataAccessor.deleteParamCache(getNameSpace(), record, DataAccessor.createCacheKey(record, shard), shard).first;
            } else if (getCacheType() == CacheType.BUSINESS_CACHE) {
                count = dataAccessor.deleteBusinessCache(getNameSpace(), record, DataAccessor.createCacheKey(record, shard), shard).first;
            } else {
                count = dataAccessor.deleteNoCache(getNameSpace(), record, shard);
            }
        } else {
            count = dataAccessor.deleteNoCache(getNameSpace(), record, shard);
        }
        return count;
    }

    /**
     * 根据主键查询结果
     *
     * @param record
     *         dbmodel bean，必须定义了@TablePk主键
     * @param pkValue
     *         主键值，顺序必须与定义主键的顺序保持一致
     * @param <T>
     *         记录mapper泛型
     * @return
     */
    @Override
    public <T extends AbstractBean> T selectByPrimaryKey(T record, Object... pkValue) {
        return selectByPrimaryKey(record, null, pkValue);
    }

    public <T extends AbstractBean> T selectByPrimaryKey(T record, Shard shard, Object... pkValue) {
        T out;
        /**
         * 性能优化，原来的spel表达式使用T()方式进行运算时，需要创建calss对象。大并发下存在Loader锁的资源。
         */
        if (doRowCache(record, pkValue)) {
            if (getCacheType() == CacheType.PARAM_CACHE) {
                out = dataAccessor.selectParamCache(getNameSpace(), record, shard, DataAccessor.createCacheKey(record, shard, pkValue), pkValue);
            } else if (getCacheType() == CacheType.BUSINESS_CACHE) {
                out = dataAccessor.selectBusinessCache(getNameSpace(), record, shard, DataAccessor.createCacheKey(record, shard, pkValue), pkValue);
            } else {
                out = dataAccessor.selectNoCache(getNameSpace(), shard, record, pkValue);
            }
        } else {
            out = dataAccessor.selectNoCache(getNameSpace(), shard, record, pkValue);
        }
        return out;
    }

    /**
     * 判断是否存在主键
     *
     * @param record
     * @param pkValue
     * @param <T>
     * @return
     */
    private <T extends AbstractBean> boolean doRowCache(T record, Object... pkValue) {
        if (getCacheType() == CacheType.PARAM_CACHE || getCacheType() == CacheType.BUSINESS_CACHE) {
            if (null == pkValue || pkValue.length == 0) {
                return DataAccessor.isAddCache(record);
            } else {
                return DataAccessor.isAddCache(record, pkValue);
            }
        }
        return false;
    }


    /**
     * 根据主键查询结果集合
     *
     * @param record
     *         dbmodel bean，必须定义了@TablePk主键
     * @param pkValue
     *         主键值，顺序必须与定义主键的顺序保持一致
     * @param <T>
     *         记录mapper泛型
     * @return
     */
    @Override
    public <T extends AbstractBean> List<T> selectListByPrimaryKey(T record, Object... pkValue) {
        return selectListByPrimaryKey(record, null, pkValue);
    }

    public <T extends AbstractBean> List<T> selectListByPrimaryKey(T record, Shard shard, Object... pkValue) {
        return dataAccessor.selectListByPrimaryKey(getNameSpace(), shard, record, pkValue);
    }

    /**
     * 插入记录
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    public int insert(String sqlId, Object param) {
        return insert(sqlId, param, null);
    }

    public int insert(String sqlId, Object param, Shard shard) {
        return dataAccessor.insert(getNameSpace(), sqlId, param, shard);
    }

    /**
     * 插入记录
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    @Deprecated
    public int insert(String nameSpace, String sqlId, Object param) {
        return insert(nameSpace, sqlId, param, null);
    }

    @Deprecated
    public int insert(String nameSpace, String sqlId, Object param, Shard shard) {
        return dataAccessor.insert(nameSpace, sqlId, param, shard);
    }


    /**
     * 更新记录
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    public int update(String sqlId, Object param) {
        return update(sqlId, param, null);
    }

    public int update(String sqlId, Object param, Shard shard) {
        return dataAccessor.update(getNameSpace(), sqlId, param, shard);
    }

    /**
     * 更新记录
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    @Deprecated
    public int update(String nameSpace, String sqlId, Object param) {
        return update(nameSpace, sqlId, param, null);
    }

    @Deprecated
    public int update(String nameSpace, String sqlId, Object param, Shard shard) {
        return dataAccessor.update(nameSpace, sqlId, param, shard);
    }

    /**
     * 删除记录
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    public int delete(String sqlId, Object param) {
        return delete(sqlId, param, null);
    }

    public int delete(String sqlId, Object param, Shard shard) {
        return dataAccessor.delete(getNameSpace(), sqlId, param, shard);
    }


    /**
     * 删除记录
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @return
     */
    @Deprecated
    public int delete(String nameSpace, String sqlId, Object param) {
        return delete(nameSpace, sqlId, param, null);
    }

    @Deprecated
    public int delete(String nameSpace, String sqlId, Object param, Shard shard) {
        return dataAccessor.delete(nameSpace, sqlId, param, shard);
    }

    /**
     * 查询单条记录，无查询参数
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param <T>
     *         结果泛型
     * @return
     */
    public <T> T selectOne(String sqlId) {
        return selectOne(sqlId, (Shard) null);
    }

    public <T> T selectOne(String sqlId, Shard shard) {
        return dataAccessor.selectOne(getNameSpace(), sqlId, shard);
    }

    /**
     * 查询单条记录，无查询参数
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param <T>
     *         结果泛型
     * @return
     */
    @Deprecated
    public <T> T selectOne(String nameSpace, String sqlId) {
        return selectOne(nameSpace, sqlId, null);
    }

    @Deprecated
    public <T> T selectOne(String nameSpace, String sqlId, Shard shard) {
        return dataAccessor.selectOne(nameSpace, sqlId, shard);
    }

    /**
     * 查询单条记录，有查询参数
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @param <T>
     *         结果泛型
     * @return
     */
    public <T> T selectOne(String sqlId, Object param) {
        return selectOne(sqlId, param, null);
    }

    public <T> T selectOne(String sqlId, Object param, Shard shard) {
        return dataAccessor.selectOne(getNameSpace(), sqlId, param, shard);
    }

    /**
     * 查询单条记录，有查询参数
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @param <T>
     *         结果泛型
     * @return
     */
    @Deprecated
    public <T> T selectOne(String nameSpace, String sqlId, Object param) {
        return selectOne(nameSpace, sqlId, param, null);
    }

    @Deprecated
    public <T> T selectOne(String nameSpace, String sqlId, Object param, Shard shard) {
        return dataAccessor.selectOne(nameSpace, sqlId, param, shard);
    }

    /**
     * 查询结果集合，无查询参数
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param <T>
     *         结果泛型
     * @return
     */
    public <T> List<T> selectList(String sqlId) {
        return selectList(sqlId, (Shard) null);
    }

    public <T> List<T> selectList(String sqlId, Shard shard) {
        return dataAccessor.selectList(getNameSpace(), sqlId, shard);
    }

    /**
     * 查询结果集合，无查询参数
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param <T>
     *         结果泛型
     * @return
     */
    @Deprecated
    public <T> List<T> selectList(String nameSpace, String sqlId) {
        return selectList(nameSpace, sqlId, null);
    }

    @Deprecated
    public <T> List<T> selectList(String nameSpace, String sqlId, Shard shard) {
        return dataAccessor.selectList(nameSpace, sqlId, shard);
    }

    /**
     * 查询结果集合，有查询参数
     *
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @param <T>
     *         结果泛型
     * @return
     */
    public <T> List<T> selectList(String sqlId, Object param) {
        return selectList(sqlId, param, null);
    }

    public <T> List<T> selectList(String sqlId, Object param, Shard shard) {
        return dataAccessor.selectList(getNameSpace(), sqlId,
                param, shard);
    }

    /**
     * 查询结果集合，有查询参数
     *
     * @param nameSpace
     *         命名空间
     * @param sqlId
     *         mapper文件中的sqlid
     * @param param
     *         参数
     * @param <T>
     *         结果泛型
     * @return
     */
    @Deprecated
    public <T> List<T> selectList(String nameSpace, String sqlId, Object param) {
        return selectList(nameSpace, sqlId, param, null);
    }

    @Deprecated
    public <T> List<T> selectList(String nameSpace, String sqlId, Object param, Shard shard) {
        return dataAccessor.selectList(nameSpace, sqlId,
                param, shard);
    }

    /**
     * 批量插入数据  通过addBatch方式提交
     *
     * @param sqlId
     * @param paramters
     * @return
     */
    public int insertAddBatch(String sqlId, List<?> paramters) {
        return insertAddBatch(sqlId, paramters, null);
    }


    public int insertAddBatch(String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.insertAddBatch(getNameSpace(), sqlId,
                paramters, shard);
    }


    /**
     * 批量插入数据  通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param paramters
     * @return
     */
    @Deprecated
    public int insertAddBatch(String nameSpace, String sqlId, List<?> paramters) {
        return insertAddBatch(nameSpace, sqlId, paramters, null);
    }

    @Deprecated
    public int insertAddBatch(String nameSpace, String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.insertAddBatch(nameSpace, sqlId,
                paramters, shard);
    }

    /**
     * 批量更新 通过addBatch方式提交
     *
     * @param sqlId
     * @param paramters
     * @return
     */
    public int updateAddBatch(String sqlId, List<?> paramters) {
        return updateAddBatch(sqlId, paramters, null);
    }

    public int updateAddBatch(String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.updateAddBatch(getNameSpace(), sqlId,
                paramters, shard);
    }

    /**
     * 批量更新 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param paramters
     * @return
     */
    @Deprecated
    public int updateAddBatch(String nameSpace, String sqlId, List<?> paramters) {
        return updateAddBatch(nameSpace, sqlId, paramters, null);
    }

    @Deprecated
    public int updateAddBatch(String nameSpace, String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.updateAddBatch(nameSpace, sqlId,
                paramters, shard);
    }

    /**
     * 批量删除 通过addBatch方式提交
     *
     * @param sqlId
     * @param paramters
     * @return
     */
    public int deleteAddBatch(String sqlId, List<?> paramters) {
        return deleteAddBatch(sqlId, paramters, null);
    }

    public int deleteAddBatch(String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.deleteAddBatch(getNameSpace(), sqlId,
                paramters, shard);
    }

    /**
     * 批量删除 通过addBatch方式提交
     *
     * @param nameSpace
     * @param sqlId
     * @param paramters
     * @return
     */
    @Deprecated
    public int deleteAddBatch(String nameSpace, String sqlId, List<?> paramters) {
        return deleteAddBatch(nameSpace, sqlId, paramters, null);
    }

    @Deprecated
    public int deleteAddBatch(String nameSpace, String sqlId, List<?> paramters, Shard shard) {
        return dataAccessor.deleteAddBatch(nameSpace, sqlId,
                paramters, shard);
    }

    /**
     * 获取原生dal框架的ShardSqlSessionTemplate
     *
     * @return
     */
    @Deprecated
    public ShardSqlSessionTemplate getSessionTemplate() {
        return dataAccessor.getSessionTemplate();
    }

    public <T> List<T> selectByPage(String sqlId, Object param) {

        return selectByPage(getNameSpace(), sqlId, param);
    }

    @Deprecated
    public <T> List<T> selectByPage(String nameSpace, String sqlId, Object param) {
        IAppHead appHead = Context.getInstance().getAppHead();
        return selectByPage(nameSpace, sqlId, appHead, param);
    }

    public <T> List<T> selectByPage(String sqlId, IAppHead appHead, Object param) {
        return selectByPage(getNameSpace(), sqlId, appHead, param);
    }

    @Deprecated
    public <T> List<T> selectByPage(String nameSpace, String sqlId, IAppHead appHead, Object param) {
        RowArgs rowArgs = BusinessUtils.convertAppHead(appHead);
        List<T> list = null;
        if (rowArgs == null) {
            Map mapParam = new HashMap<>();
            mapParam.put("baseParam", param);
            list = selectList(nameSpace, sqlId, mapParam);

        } else {
            ParameterWrapper parameterWrapper = new ParameterWrapper();
            parameterWrapper.setRowArgs(rowArgs);
            parameterWrapper.setBaseParam(param);
            list = selectList(nameSpace, sqlId, parameterWrapper);
        }

        if ("E".equals(appHead.getTotalFlag())) {
            Map mapParam = new HashMap<>();
            mapParam.put("baseParam", param);
            Integer total = selectOne(nameSpace, sqlId + "$count", mapParam);
            if (total == null)
                total = 0;
            appHead.setTotalRows(total.toString());
        }
        return list;
    }

    private static TwoTuple<String, List> getSqlParams(String statement, Object object) {
        List paramList = new ArrayList();
        ShardSqlSessionTemplate shardSqlSessionTemplate = (ShardSqlSessionTemplate) SpringApplicationContext.getContext().getBean("shardSqlSessionTemplate");
        Configuration configuration = shardSqlSessionTemplate.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        TypeHandlerRegistry typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        BoundSql boundSql = mappedStatement.getBoundSql(object);
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null)
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    String propertyName = parameterMapping.getProperty();
                    Object value;
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (object == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(object.getClass())) {
                        value = object;
                    } else {
                        MetaObject typeHandler = configuration.newMetaObject(object);
                        value = typeHandler.getValue(propertyName);
                    }
                    paramList.add(value);
                }
            }
        return new TwoTuple<>(sql, paramList);
    }

    public static boolean isCachedData(Shard shard, String statement, Object param) {
        Map<Shard, Map> queryCacheMap = (Map) ThreadLocalManager.get("queryCacheMap");
        if (queryCacheMap == null)
            return false;
        Map<String, Map> shardMap = queryCacheMap.get(shard);
        if (shardMap == null)
            return false;
        TwoTuple<String, List> sqlAndParams = getSqlParams(statement, param);
        Map sqlMap = shardMap.get(sqlAndParams.first);
        if (sqlMap == null)
            return false;
        if (sqlMap.containsKey(sqlAndParams.second))
            return true;
        else
            return false;
    }

    public static Object queryCacheData(Shard shard, String statement, Object param) {
        Map<Shard, Map> queryCacheMap = (Map) ThreadLocalManager.get("queryCacheMap");
        if (queryCacheMap == null)
            return null;
        Map<String, Map> shardMap = queryCacheMap.get(shard);
        if (shardMap == null)
            return null;
        TwoTuple<String, List> sqlAndParams = getSqlParams(statement, param);
        Map sqlMap = shardMap.get(sqlAndParams.first);
        if (sqlMap == null)
            return null;
        return sqlMap.get(sqlAndParams.second);
    }

    public static void cacheQueryData(String statement, Object param, Object value) {
        cacheQueryData(null, statement, param, value);
    }

    public static void cacheQueryData(Shard shard, String statement, Object param, Object value) {
        TwoTuple<String, List> sqlAndParams = getSqlParams(statement, param);
        Map<Shard, Map> queryCacheMap = (Map) ThreadLocalManager.get("queryCacheMap");
        if (queryCacheMap == null) {
            queryCacheMap = new HashMap();
            ThreadLocalManager.put("queryCacheMap", queryCacheMap);
        }
        Map<String, Map> shardMap = queryCacheMap.get(shard);
        if (shardMap == null) {
            shardMap = new HashMap<>();
            queryCacheMap.put(shard, shardMap);
        }
        Map sqlMap = shardMap.get(sqlAndParams.first);
        if (sqlMap == null) {
            sqlMap = new HashMap();
            shardMap.put(sqlAndParams.first, sqlMap);
        }
        sqlMap.put(sqlAndParams.second, value);
    }

    public static void clearQueryData() {
        Map queryCacheMap = (Map) ThreadLocalManager.get("queryCacheMap");
        if (queryCacheMap != null && !queryCacheMap.isEmpty()) {
            queryCacheMap.clear();
        }
    }

    public static void batchDbSubmit(Map cache) {
        batchDbSubmit(Context.ADD_BATCH_DELETE_KEY, cache);
        batchDbSubmit(Context.ADD_BATCH_INSERT_KEY, cache);
        batchDbSubmit(Context.ADD_BATCH_UPDATE_KEY, cache);

    }

    private static void batchDbSubmit(String cacheKey, Map cache) {
        ShardSqlSessionTemplate shardSqlSessionTemplate = (ShardSqlSessionTemplate) SpringApplicationContext.getContext().getBean("shardSqlSessionTemplate");
        Map<String, Map> batchMap = (Map) cache.get(cacheKey);
        if (batchMap == null || batchMap.isEmpty())
            return;
        for (Map.Entry<String, Map> entry : batchMap.entrySet()) {
            String statement = entry.getKey();
            Map<Shard, List> shardStatement = entry.getValue();
            if (shardStatement != null && !shardStatement.isEmpty()) {
                for (Map.Entry<Shard, List> shardStatementEntry : shardStatement.entrySet()) {
                    Shard shard = shardStatementEntry.getKey();
                    List records = shardStatementEntry.getValue();
                    if (records != null && !records.isEmpty()) {
                        if (shard == null) {
                            switch (cacheKey) {
                                case Context.ADD_BATCH_INSERT_KEY:
                                    shardSqlSessionTemplate.insertAddBatch(statement, records);
                                    break;
                                case Context.ADD_BATCH_UPDATE_KEY:
                                    shardSqlSessionTemplate.updateAddBatch(statement, records);
                                    break;
                                case Context.ADD_BATCH_DELETE_KEY:
                                    shardSqlSessionTemplate.deleteAddBatch(statement, records);
                                    break;
                            }
                        } else {
                            switch (cacheKey) {
                                case Context.ADD_BATCH_INSERT_KEY:
                                    shardSqlSessionTemplate.insertAddBatch(statement, records, shard);
                                    break;
                                case Context.ADD_BATCH_UPDATE_KEY:
                                    shardSqlSessionTemplate.updateAddBatch(statement, records, shard);
                                    break;
                                case Context.ADD_BATCH_DELETE_KEY:
                                    shardSqlSessionTemplate.deleteAddBatch(statement, records, shard);
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isBatchTransactional() {
        Stack stack = (Stack) ThreadLocalManager.get("cacheStack");
        if (stack != null && !stack.empty())
            return true;
        else
            return false;
    }
}
