package com.dcits.orion.batch.common.dao;
//
//import com.alibaba.dubbo.common.utils.ConfigUtils;
//import com.dcits.orion.batch.bean.CommTableObj;
//import com.dcits.galaxy.cache.redis.sharedsentientl.ShardedSentientlJedis;
//import com.dcits.galaxy.cache.redis.sharedsentientl.ShardedSentientlJedisPool;
//import com.dcits.galaxy.serializer.SerializationUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisSentinelPool;
//
//import javax.annotation.Resource;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;

/**
 * Created by lixbb on 2016/2/25.
 */
public class CommonCacheDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(CommonCacheDao.class);
//    @Resource
//    private ShardedSentientlJedisPool sharedJedisPool;
//
//    @Resource
//    private AutoMapperDao autoMapperDao;
//
//
//    // 默认超时时间
//    private static Integer DEFAULT_TIME_OUT = 60 * 60 * 24;
//
//    public void insert(CommTableObj tableObj,String ... pkName)
//    {
//        autoMapperDao.insert(tableObj);
//        CommTableObj tableObj1 = new CommTableObj();
//        tableObj1.setTableName(tableObj.tableName());
//        for (int i = 0; i < pkName.length; i++)
//        {
//            tableObj1.setCondition(pkName[i],tableObj.getFieldValue().get(pkName[i]));
//        }
//        Map insertResult = autoMapperDao.selectOne(tableObj1);
//        String key = getKey(insertResult, tableObj.tableName(), pkName);
//        setObject(key, insertResult);
//    }
//    public void delete(CommTableObj tableObj,String ... pkName)
//    {
//        List<Map> deleteRows = autoMapperDao.selectList(tableObj);
//        autoMapperDao.delete(tableObj);
//        for (Map deleteRow : deleteRows)
//        {
//            String key = getKey(deleteRow,tableObj.tableName(),pkName);
//            delete(key);
//        }
//    }
//    public void update(CommTableObj tableObj,String ... pkName)
//    {
//        List<Map> updateRows = autoMapperDao.selectList(tableObj);
//        autoMapperDao.update(tableObj);
//        for (Map updateRow : updateRows)
//        {
//            CommTableObj tableObj1 = new CommTableObj();
//            tableObj1.setTableName(tableObj.tableName());
//            for (int i = 0; i < pkName.length; i++)
//            {
//                tableObj1.setCondition(pkName[i], updateRow.get(pkName[i]));
//            }
//            Map map = autoMapperDao.selectOne(tableObj1);
//            String key = getKey(map, tableObj.tableName(), pkName);
//            setObject(key, map);
//
//        }
//
//    }
//    public Map selectOne(CommTableObj tableObj,String ... pkName)
//    {
//        String key = getKey(tableObj.getConditionValue(), tableObj.tableName(), pkName);
//        Map map = (Map)getObject(key);
//        if (map == null)
//        {
//            map = autoMapperDao.selectOne(tableObj);
//            if (map != null)
//            {
//                key = getKey(tableObj.getConditionValue(), tableObj.tableName(), pkName);
//                setObject(key,map);
//            }
//        }
//        return map;
//    }
//    private  String getKey(Map map ,String tableName,String ... pkName)
//    {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(getGroup())
//                .append("-")
//                .append(tableName);
//
//        for (int i = 0; i < pkName.length; i++)
//        {
//            stringBuffer.append("-").append(map.get(pkName[i]));
//
//        }
//        return stringBuffer.toString();
//    }
//    private String getGroup()
//    {
//        return  ConfigUtils.getProperty("galaxy.application.group");
//    }
//
//
//
//    /**
//     * 从缓存中获取字符串
//     * @param key
//     * @return String
//     */
//    public String getString(String key) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            return shardJedis.get(key);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//        return null;
//    }
//
//    /**
//     * 将字符串放入缓存
//     * @param key
//     * @param value
//     * @param timeOut
//     */
//    public void setString(String key, String value, Integer timeOut) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            if (timeOut == null) {
//                shardJedis.setex(key, DEFAULT_TIME_OUT, value);
//            } else {
//                shardJedis.setex(key, timeOut, value);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//    }
//
//    /**
//     * 从缓存中获取对象
//     * @param key
//     * @return Object
//     */
//    public Object getObject(String key) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            byte[] keyByte = SerializationUtils.serialize(key);
//            return SerializationUtils.deserializeObj(shardJedis.get(keyByte));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//        return null;
//    }
//
//    public void setObject(String key, Object value)
//    {
//        setObject(key,value,DEFAULT_TIME_OUT);
//    }
//
//    /**
//     * 将对象放入缓存
//     * @param key
//     * @param value
//     * @param timeOut
//     */
//    public void setObject(String key, Object value, Integer timeOut) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            byte[] keyByte = SerializationUtils.serialize(key);
//            byte[] valueByte = SerializationUtils.serializeObj(value);
//            if (timeOut == null) {
//                shardJedis.setex(keyByte, DEFAULT_TIME_OUT, valueByte);
//            } else {
//                shardJedis.setex(keyByte, timeOut, valueByte);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//    }
//
//    /**
//     * 精准删除
//     * @param key
//     */
//    public void delete(String key) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            shardJedis.del(key);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//    }
//
//    /**
//     * 模糊删除
//     * @param key
//     */
//    public void deleteLike(String key) {
//        boolean flag = true;
//        try {
//            Collection<JedisSentinelPool> jedisPoolArray = sharedJedisPool.getAllSentinelPool();
//            for (JedisSentinelPool jedisPool : jedisPoolArray) {
//                Jedis jedis = jedisPool.getResource();
//                try {
//                    Set<String> keys = jedis.keys(key + "*");
//                    if (keys != null && !keys.isEmpty()) {
//                        jedis.del(keys.toArray(new String[keys.size()]));
//                    }
//                } catch (Exception e) {
//                    logger.error(e.getMessage(), e);
//                    flag = false;
//                } finally {
//                    if (jedis != null) {
//                        if (flag) {
//                            jedisPool.returnResource(jedis);
//                        } else {
//                            jedisPool.returnBrokenResource(jedis);
//                        }
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 判断缓存是否存在
//     * @param key
//     * @return boolean
//     */
//    public boolean exists(String key) {
//        ShardedSentientlJedis shardJedis = null;
//        try {
//            shardJedis = sharedJedisPool.getResource();
//            return shardJedis.exists(key);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            sharedJedisPool.returnResource(shardJedis);
//        }
//        return false;
//    }


}
