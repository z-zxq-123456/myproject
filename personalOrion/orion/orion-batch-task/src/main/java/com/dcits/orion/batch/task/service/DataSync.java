package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.base.file.FileParseUtils;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2016/11/9.
 */
@Repository
public class DataSync {

    @Resource
    AutoMapperDao autoMapperDao;
    @Resource
    IBatchLocal batchLocal;
    @Resource
    SplitJob splitJob;

    private static Logger logger = LoggerFactory.getLogger(DataSync.class);
    //数据同步入口
    public void execute(ITaskParam taskParam) {
        if (logger.isInfoEnabled()) {
            logger.info("DataSync begin");
        }
        CommTableObj commTableObj = new CommTableObj("BATCH_DATA_SYNC_DEF");
        commTableObj.setCondition("SYNC_ID", taskParam.getStaticParam());
        Map batchDataSyncDef = autoMapperDao.selectOne(commTableObj);
        fileDeal(batchDataSyncDef);
        String syncFlag = (String)batchDataSyncDef.get("SYNC_FLAG");
        String sourceType = syncFlag.substring(0,1);
        switch (sourceType)
        {
            case "T"://数据来源是数据库表
                dataFromDb(taskParam,batchDataSyncDef);
                break;
            case "F"://数据来源是文件
                dataFromFile(taskParam,batchDataSyncDef);
                break;
            case "I"://数据来源是JAVA接口
                dataFromInterface(taskParam,batchDataSyncDef);
                break;
        }
        if (logger.isInfoEnabled()) {
            logger.info("DataSync end");
        }
    }

    private void dataFromDb(ITaskParam taskParam,Map batchDataSyncDef)
    {
        List<Shard> shards = getShards(batchDataSyncDef);
        for (Shard shard : shards) {
            executeByShard(shard, batchDataSyncDef);
        }

    }
    private void dataFromFile(ITaskParam taskParam,Map batchDataSyncDef)
    {


    }
    private void dataFromInterface(ITaskParam taskParam,Map batchDataSyncDef)
    {

    }

    private void dataToDb(Map batchDataSyncDef,Map param,List list)
    {
        Shard shard = (Shard)param.get("shard");
        String INSERT_NAMESPACE = (String) batchDataSyncDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = (String) batchDataSyncDef.get("INSERT_SQL_ID");
        if (!StringUtils.isBlank(INSERT_NAMESPACE) && !StringUtils.isBlank(INSERT_SQL_ID)) {
            insertByDal(list, shard, batchDataSyncDef);
        } else {
            insertByAutoMapperDao(list, shard, batchDataSyncDef);
        }
    }
    private void dataToFile(Map batchDataSyncDef,Map param,List list)
    {
        writeFile(list,batchDataSyncDef);
    }
    private void dataToInterface(Map batchDataSyncDef,Map param,List list)
    {
        callJavaMethod(list,param,batchDataSyncDef);
    }
    private void executeByShard(Shard shard, Map batchDataSyncDef) {
        int i = 0;

        int batchSize = getBatchSize(batchDataSyncDef);
        List<Map> list;
        do {
            list = query(shard, batchDataSyncDef, i);
            afterQuery(shard, batchDataSyncDef, list);
            if (list != null && list.size() > 0) {
                dealData(list,batchDataSyncDef,shard);
            }
            i++;
        } while (list != null && list.size() >= batchSize);
    }

    private void dealData(List list, Map batchDataSyncDef,Shard shard)
    {
        String syncFlag = (String)batchDataSyncDef.get("SYNC_FLAG");
        String targetType = syncFlag.substring(3);
        Map param =  new HashMap();
        String matchFlag = (String)batchDataSyncDef.get("MATCH_FLAG");
        if ("Y".equals(matchFlag))
        {
            shard = getInsertShardManager(batchDataSyncDef).getShard(shard.getId());
        }
        param.put("shard",shard);
        switch (targetType)
        {
            case "T"://数据来源是数据库表
                dataToDb(batchDataSyncDef,param,list);
                break;
            case "F"://数据来源是文件
                dataToFile(batchDataSyncDef,param,list);
                break;
            case "I"://数据来源是JAVA接口
                dataToInterface(batchDataSyncDef,param,list);
                break;
        }
    }
    private void afterQuery(Shard shard, Map batchDataSyncDef, List list) {
        if (list != null && list.size() > 0) {
            String SYNC_CLASS = (String) batchDataSyncDef.get("SYNC_CLASS");
            if ("I".equals(SYNC_CLASS))//增量方式更新同步标识
            {
                String KEY_FIELD = (String) batchDataSyncDef.get("KEY_FIELD");
                String SYNC_FLAG_FIELD = (String) batchDataSyncDef.get("SYNC_FLAG_FIELD");
                List<Map> maps = new ArrayList<>();
                for (Object o : list) {
                    Map map = new HashMap();
                    map.put(SYNC_FLAG_FIELD, "Y");
                    if (o instanceof Map) {
                        map.put(KEY_FIELD, ((Map) o).get(KEY_FIELD));
                    } else {
                        map.put(KEY_FIELD, BeanUtils.getFieldValue(o, JsonUtils.convertHumpCase(KEY_FIELD)));
                    }
                    maps.add(map);
                }
                String TABLE_NAME = (String) batchDataSyncDef.get("TABLE_NAME");
                CommTableObj update = new CommTableObj(TABLE_NAME);
                update.setRecords(maps);
                update.setField(SYNC_FLAG_FIELD, "");
                update.setCondition(KEY_FIELD, "");
                autoMapperDao.batchUpdate(update, getQueryShardManager(batchDataSyncDef), shard);
            } else if ("D".equals(SYNC_CLASS)) {
                String TABLE_NAME = (String) batchDataSyncDef.get("TABLE_NAME");
                String KEY_FIELD = (String) batchDataSyncDef.get("KEY_FIELD");
                CommTableObj delete = new CommTableObj(TABLE_NAME);

                List<Map> maps = new ArrayList<>();
                for (Object o : list) {
                    Map map = new HashMap();
                    if (o instanceof Map) {
                        map.put(KEY_FIELD, ((Map) o).get(KEY_FIELD));
                    } else {
                        map.put(KEY_FIELD, BeanUtils.getFieldValue(o, JsonUtils.convertHumpCase(KEY_FIELD)));
                    }
                    maps.add(map);
                }
                delete.setRecords(maps);
                delete.setCondition(KEY_FIELD, "");
                autoMapperDao.batchDelete(delete, getQueryShardManager(batchDataSyncDef), shard);
            }
        }
    }
    public List query(Shard shard, Map batchDataSyncDef, int index) {
        String SELECT_NAMESPACE = (String) batchDataSyncDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = (String) batchDataSyncDef.get("SELECT_SQL_ID");
        if (!StringUtils.isBlank(SELECT_NAMESPACE) && !StringUtils.isBlank(SELECT_SQL_ID)) {
            return queryByDal(shard, batchDataSyncDef);
        } else {
            return queryByAutoMapper(shard, batchDataSyncDef, index);
        }
    }
    public List queryByDal(Shard shard, Map batchDataSyncDef) {
        String SELECT_NAMESPACE = (String) batchDataSyncDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = (String) batchDataSyncDef.get("SELECT_SQL_ID");
        Map param = new HashMap();
        param.putAll(batchLocal.getSystemParam());
        param.put("maxPerCount", getBatchSize(batchDataSyncDef));
        return getQueryShardSqlSessionTemplate(batchDataSyncDef).selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, param, shard);
    }
    public List queryByAutoMapper(Shard shard, Map batchDataSyncDef, int index) {
        String TABLE_NAME = (String) batchDataSyncDef.get("TABLE_NAME");
        CommTableObj query = new CommTableObj(TABLE_NAME);
        query.putAll(batchLocal.getSystemParam());
        String SYNC_CLASS = (String) batchDataSyncDef.get("SYNC_CLASS");
        int batchSize = getBatchSize(batchDataSyncDef);
        if ("I".equals(SYNC_CLASS) || "D".equals(SYNC_CLASS))//增量方式更新同步标识或同步数据后删除
        {

            String whereSql = (String) batchDataSyncDef.get("WHERE_SQL");
            if (whereSql == null || "".equals(whereSql)) {
                whereSql = " where 1 =1 ";
            }

            if ("I".equals(SYNC_CLASS)) {
                String SYNC_FLAG_FIELD = (String) batchDataSyncDef.get("SYNC_FLAG_FIELD");
                whereSql = StringUtils.append(whereSql, " and ", SYNC_FLAG_FIELD, " = 'N'");
            }
            query.setWhereSql(whereSql);
            RowArgs rowArgs = new RowArgs(0, batchSize);
            return autoMapperDao.selectListByPage(query, getQueryShardManager(batchDataSyncDef), shard, rowArgs);
        } else {
            String KEY_FIELD = (String) batchDataSyncDef.get("KEY_FIELD");
            if (!StringUtils.isBlank(KEY_FIELD)) {
                query.setKeyField(KEY_FIELD);
                List<Map> splitList = null;
                if (index == 0) {
                    query.put("maxPerCount", batchSize);
                    splitList = autoMapperDao.selectSplitByKeyList(query, shard);
                    ThreadLocalManager.put("SPLIT_LIST", splitList);
                } else {
                    splitList = (List) ThreadLocalManager.get("SPLIT_LIST");
                }
                if (splitList != null && index < splitList.size()) {
                    Map range = splitList.get(index);
                    query.putAll(range);
                    return autoMapperDao.selectRangeList(query, shard);
                } else {
                    return null;
                }
            } else {
                String whereSql = (String) batchDataSyncDef.get("WHERE_SQL");
                if (!StringUtils.isBlank(whereSql))
                    query.setWhereSql(whereSql);
                RowArgs rowArgs = new RowArgs(batchSize * index, batchSize);
                return autoMapperDao.selectListByPage(query, getQueryShardManager(batchDataSyncDef), shard, rowArgs);
            }
        }
    }



    public static void main(String[] args)
    {
        System.out.println("F->T".substring(0,1));
        System.out.println("F->T".substring(3));
    }

    private List getShards(Map batchDataSyncDef) {
        String shardManageId = (String)batchDataSyncDef.get("SHARD_MANAGE_ID");
        if (StringUtils.isBlank(shardManageId))
            shardManageId = "shardManager";
        ShardManager shardManager = (ShardManager)SpringApplicationContext.getContext().getBean(shardManageId);
        String bySchema = (String) batchDataSyncDef.get("BY_SCHEMA");
        List<Shard> shards = null;
        if ("A".equals(bySchema)) {
            shards = shardManager.getAllShard();
        } else if ("Y".equals(bySchema)) {
            shards = shardManager.getShardsWithOutDefault();
        } else {
            Shard shard = shardManager.getDefaultShard();
            shards = new ArrayList<>();
            shards.add(shard);
        }
        return shards;
    }
    private ShardManager getQueryShardManager(Map batchDataSyncDef)
    {
        String shardManageId = (String)batchDataSyncDef.get("SHARD_MANAGE_ID");
        if (StringUtils.isBlank(shardManageId))
            shardManageId = "shardManager";
        ShardManager shardManager = (ShardManager)SpringApplicationContext.getContext().getBean(shardManageId);
        return shardManager;
    }
    private ShardSqlSessionTemplate getQueryShardSqlSessionTemplate(Map batchDataSyncDef)
    {
         return ShardManageUtils.getShardSqlSessionTemplate(getQueryShardManager(batchDataSyncDef));
    }

    private ShardManager getInsertShardManager(Map batchDataSyncDef)
    {
        String shardManageId = (String)batchDataSyncDef.get("INSERT_SHARD_MANAGE_ID");
        if (StringUtils.isBlank(shardManageId))
            shardManageId = "shardManager";
        ShardManager shardManager = (ShardManager)SpringApplicationContext.getContext().getBean(shardManageId);
        return shardManager;
    }
    private ShardSqlSessionTemplate getInsertShardSqlSessionTemplate(Map batchDataSyncDef)
    {
        return ShardManageUtils.getShardSqlSessionTemplate(getInsertShardManager(batchDataSyncDef));
    }
    private int getBatchSize(Map batchDataSyncDef) {
        int batchSize = 10000;
        try {
            batchSize = BatchUtils.parseInt(batchDataSyncDef.get("BATCH_SIZE"));
            if (batchSize == 0)
                batchSize = 10000;
        } catch (Exception e) {

        }
        return batchSize;
    }
    private void insertByDal(List list, Shard shard, Map batchDataSyncDef) {
        String INSERT_NAMESPACE = (String) batchDataSyncDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = (String) batchDataSyncDef.get("INSERT_SQL_ID");
        String MATCH_FLAG = (String) batchDataSyncDef.get("MATCH_FLAG");
        ShardSqlSessionTemplate shardSqlSessionTemplate = getInsertShardSqlSessionTemplate(batchDataSyncDef);
        if ("Y".equals(MATCH_FLAG)) {
            Shard insertShard = getInsertShardManager(batchDataSyncDef).getShard(shard.getId());
            shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list, insertShard);

        } else {
            shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list);
        }
    }
    private void insertByAutoMapperDao(List list, Shard shard, Map batchDataSyncDef) {
        String TARGET_TABLE_NAME = (String) batchDataSyncDef.get("TARGET_TABLE_NAME");
        CommTableObj insert = new CommTableObj(TARGET_TABLE_NAME);
        insert.setRecords(list);
        String MATCH_FLAG = (String) batchDataSyncDef.get("MATCH_FLAG");
        if ("Y".equals(MATCH_FLAG)) {
            Shard insertShard = getInsertShardManager(batchDataSyncDef).getShard(shard.getId());
            autoMapperDao.batchInsert(insert, getInsertShardManager(batchDataSyncDef), insertShard);
        } else {
            autoMapperDao.batchInsert(insert, getInsertShardManager(batchDataSyncDef));
        }
    }

    //如果是写入文件，提前将文件路径检查好
    private void fileDeal(Map batchDataSyncDef) {
        String syncFlag = (String)batchDataSyncDef.get("SYNC_FLAG");
        String targetType = syncFlag.substring(3);
        if ("F".equals(targetType)) {
            String FILE_WRITE_TYPE = (String) batchDataSyncDef.get("FILE_WRITE_TYPE");
            TwoTuple<String, String> pathAndFileName = getPathAndFileName(batchDataSyncDef);
            File path = new File(pathAndFileName.first);
            if (!path.exists())
                path.mkdir();
            if ("W".equals(FILE_WRITE_TYPE)) {
                File file = new File(pathAndFileName.first + "/" + pathAndFileName.second);
                if (file.exists())
                    file.delete();
            }
        }
    }
    private TwoTuple<String, String> getPathAndFileName(Map batchDataSyncDef) {
        String path = splitJob.getFileName((String) batchDataSyncDef.get("FILE_PATH"));
        String fileName = splitJob.getFileName((String) batchDataSyncDef.get("FILE_NAME"));
        return new TwoTuple<>(path, fileName);

    }
    private void writeFile(List list, Map batchDataSyncDef) {
        String fileFormat = (String) batchDataSyncDef.get("FILE_FORMAT");
        TwoTuple<String, String> pathAndFileName = getPathAndFileName(batchDataSyncDef);
        String path = pathAndFileName.first;
        String fileName = pathAndFileName.second;
        FileParseUtils.writeBody(path, fileName, fileFormat, list, true);
    }

    private void callJavaMethod(List list,  Map param, Map batchDataSyncDef) {
        try {
            Object o = SpringApplicationContext.getContext().getBean(Class.forName((String) batchDataSyncDef.get("CLASS_NAME")));
            Method m = ObjectUtils.getMethod(o, (String) batchDataSyncDef.get("METHOD_NAME"), List.class, Map.class);
            m.invoke(o, list, param);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }
}
