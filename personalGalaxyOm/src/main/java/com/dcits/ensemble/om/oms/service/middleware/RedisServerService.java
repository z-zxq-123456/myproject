package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.ensemble.om.oms.constants.RedisTypeConstants;
import com.dcits.ensemble.om.oms.module.middleware.CacheModel;
import com.dcits.ensemble.om.oms.module.middleware.SeqInfo;
import com.dcits.ensemble.om.oms.module.middleware.ServerModel;
import com.dcits.ensemble.om.oms.module.utils.PropToModel;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/**
 * @author maruie
 * @date   2016-01-12
 * MonitorService
 * redis 操作
 */
class RedisServerService {

    public static final String GALAXY_SEQUENCES = "galaxy.sequences";

    public static String masterServerUrl;

    private static Jedis getRedisConn(String server, String masterName) {
        Set sentinels = new HashSet();
        String sentinelUrls[] = server.split(";");
        for (String hostUrl : sentinelUrls) {
            String[] array = hostUrl.split(":");
            sentinels.add(new HostAndPort(array[0], Integer.parseInt(array[1])).toString());
        }
        try {
            Jedis master;
            if(sentinels.size()>=1) {
                try (JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinels)) {
                    masterServerUrl = sentinelPool.getCurrentHostMaster().toString();
                    master = sentinelPool.getResource();
                    System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
                }
                return master;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void close(Jedis resource){
         if(resource!=null)
         resource.close();
    }

    public static ServerModel getRedisServerInfo(String server, String masterName){
        Jedis  resource = null;
        ServerModel  model = null;
        try {
            resource = getRedisConn(server, masterName);
            if (resource != null) {
                model = PropToModel.propPutModel(masterServerUrl, getPProperties(resource));
            }
            else
                return null;
       } catch (Exception e) {

           e.printStackTrace();
           //throw new RuntimeException(e.getMessage());
           return null;
       }finally{
            close(resource);
       }

        return model;
    }

    public static void deleteRedisServer(String server, String masterName){
        Jedis  resource = null;
        try{
            resource = getRedisConn(server, masterName);
            if (resource != null) {
                Map<String, String> sequencesMap = null;
                if (resource.exists(GALAXY_SEQUENCES)) {
                    sequencesMap = resource.hgetAll(GALAXY_SEQUENCES);
                }
                resource.flushAll();
                if (sequencesMap != null) {
                    resource.hmset(GALAXY_SEQUENCES, sequencesMap);
                }
            }
            else
                return;
        }finally{
            close(resource);
        }

    }


    public static void saveReqIntoServer(SeqInfo seqInfo){
        Jedis  resource = null;
        try{
            resource = getRedisConn(seqInfo.getRedisUrl(),seqInfo.getMasterName());
            if (resource != null) {
                resource.hset(GALAXY_SEQUENCES, seqInfo.getSeqKey(), seqInfo.getSeqValue());
            }
            else
                return;
        }finally{
            close(resource);
        }

    }

    public static void deleteReqIntoServer(SeqInfo seqInfo){
        Jedis  resource = null;
        try{
            resource = getRedisConn(seqInfo.getRedisUrl(), seqInfo.getMasterName());
            if (resource != null) {
                resource.hdel(GALAXY_SEQUENCES, seqInfo.getSeqKey());
            }
            else
                return;
        }finally{
            close(resource);
        }
    }


    public static List<SeqInfo> findReqFromServer(String server, String masterName){
        Jedis  resource = null;
        Map<String,String> sequencesMap = null;
        try{
            resource = getRedisConn(server, masterName);
            if(resource!=null)
                sequencesMap = resource.hgetAll(GALAXY_SEQUENCES);
            else
                return null;
        }finally{
            close(resource);
        }
        return reqMapToList(sequencesMap, server, masterName);
    }

    public static List<CacheModel> getRedisServerList(String server, String masterName) {
        Jedis resource = null;
        List<CacheModel> kvList = new ArrayList<CacheModel>();
        Map<String, CacheModel> kvMap = new TreeMap<String, CacheModel>();
        try {
            resource = getRedisConn(server, masterName);
            if (resource != null) {
                Set<String> keys = resource.keys("*");
                for (String key : keys) {
                    CacheModel model = getCacheModel(resource, key);
                    kvMap.put(model.getType() + ":" + key, model);
                }
            } else
                return null;
        } finally {
            close(resource);
        }
        kvList.addAll(kvMap.values());
        return kvList;
    }

    private static List<SeqInfo> reqMapToList(Map<String,String> sequencesMap,String server,String masterName) {
        List<SeqInfo> reqList = new ArrayList<SeqInfo>();
        for (Map.Entry<String, String> entry : sequencesMap.entrySet()) {
            SeqInfo seqInfo = new SeqInfo();
            seqInfo.setRedisUrl(server);
            seqInfo.setMasterName(masterName);
            seqInfo.setSeqKey(entry.getKey());
            seqInfo.setSeqValue(entry.getValue());
            reqList.add(seqInfo);
        }
        return reqList;
    }

    private static CacheModel  getCacheModel(Jedis  resource,String key){
        CacheModel  model = new CacheModel();
        String keyType = resource.type(key);
        model.setType(keyType);
        model.setKey(key);
        if(keyType.equals(RedisTypeConstants.HASH_TYPE)){
            model.setValue(""+resource.hgetAll(key));
        }else if(keyType.equals(RedisTypeConstants.SET_TYPE)){
            model.setValue(""+resource.smembers(key));
        }else if(keyType.equals(RedisTypeConstants.STRNG_TYPE)){
            model.setValue(""+resource.get(key));
        }else if(keyType.equals(RedisTypeConstants.LIST_TYPE)){
            model.setValue(""+resource.lrange(key,0,resource.llen(key)-1));
        }else if(keyType.equals(RedisTypeConstants.ZSET_TYPE)){
            model.setValue(""+resource.zrange(key,0,resource.zcard(key)-1));
        }
        return model;
    }

    private static Properties getPProperties(Jedis  resource) throws IOException{
        Properties  prop = new Properties();
        //InputStream inStream = new StringBufferInputStream(resource.info());
        InputStream inStream = new BufferedInputStream(new ByteArrayInputStream(resource.info().getBytes()));
        prop.load(inStream);
        return prop;
    }

}
