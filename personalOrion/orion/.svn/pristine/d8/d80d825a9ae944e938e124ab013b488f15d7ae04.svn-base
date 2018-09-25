package com.dcits.orion.batch.task.service;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.orion.base.file.FileParseUtils;
import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.galaxy.base.exception.GalaxyException;

import com.dcits.galaxy.base.tuple.TwoTuple;

import com.dcits.galaxy.base.util.StringUtils;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;


import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.batch.task.service.SplitJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;



/**
 * @desc Synchronous data
 * @author  qiqingshan
 * @date 2017-2-16 14:37:19
 */
@Repository
public class BatchDataSync {

    @Resource
    private AutoMapperDao autoMapperDao;
    @Resource
    private IBatchLocal batchLocal;
    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;
    @Resource
    private SplitJob splitJob;

    private static Logger logger = LoggerFactory.getLogger(BatchDataSync.class);


    /**
     * @desc combine file
     * @param taskParam
     * @return void
     */
    public void fileCombine(ITaskParam taskParam)
    {
        Map dataSyncDef = getDataSyncDef(taskParam);
        TwoTuple<String,String> twoTuple = getFilePathAndName(dataSyncDef);
        String fileFormat = (String)dataSyncDef.get("FILE_FORMAT");
        String fileName = twoTuple.first;
        String filePath = twoTuple.second;

        FileParseUtils.fileCombine(filePath,fileName,fileFormat);

        FileParseUtils.fileDeleteExceptSelf(filePath,fileName);

    }

    /**
     * @desc Synchronous data entry
     * @param taskParam
     */
    public void execute(ITaskParam taskParam){
        if(logger.isInfoEnabled()){
            logger.info("数据同步开始----》》》");
        }
        Map dataSyncDef = getDataSyncDef(taskParam);
        checkFile(dataSyncDef);
        String syncFlag = (String)dataSyncDef.get("SYNC_FLAG");
        String dataFromFlag = syncFlag.substring(0, 1);
        switch (dataFromFlag){
            case "T":
                //数据来源于表
                dataFromTable(taskParam,dataSyncDef);
                break;
            case "F":
                //数据来源于文件
                dataFromFile(taskParam,dataSyncDef);
                break;
             }
    }

    /**
     * @desc  data from db
     * @param taskParam
     * @param dataSyncDef
     */
    private void dataFromTable(ITaskParam taskParam,Map dataSyncDef){
        String SELECT_NAMESPACE = (String) dataSyncDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = (String)dataSyncDef.get("SELECT_SQL_ID");
        List datas = null;
        int batchSize = getBatchSize(taskParam);
        datas = queryData(dataSyncDef, taskParam);
        List<List> splitList = autoMapperDao.split(datas, batchSize);
        for(List list :splitList){
            dealData(list, dataSyncDef, taskParam);
        }
    }
    /**
     * @desc data from file
     * @param taskParam,dataSyncDef
     * @param dataSyncDef
     */
    private void dataFromFile(ITaskParam taskParam,Map dataSyncDef){
        List datas = queryDataFromFile(dataSyncDef,taskParam);
        List<List> splitList = autoMapperDao.split(datas, getBatchSize(taskParam));
        for(List list :splitList){
            dealData(list,dataSyncDef,taskParam);
        }
    }

    /**
     * @desc query data from file
     * @param dataSyncDef
     * @return list
     */
    private List queryDataFromFile(Map dataSyncDef,ITaskParam taskParam){
        List dataList = new ArrayList();
        TwoTuple<String,String> twoTuple = getFilePathAndName(dataSyncDef);
        String fileName= twoTuple.first;
        String filePath = twoTuple.second;
        String fileFormat = (String)dataSyncDef.get("FILE_FORMAT");
        if("Y".equals(taskParam.getIsSplit())){
            dataList  = FileParseUtils.getBody(filePath,fileName,fileFormat,taskParam.getFileOffset(),taskParam.getFileLimit());

        }else{
            dataList  = FileParseUtils.getBody(filePath,fileName,fileFormat);
        }

        return dataList;
    }
    /**
     * @desc deal data to db or file
     * @param datas
     * @param dataSyncDef
     * @param taskParam
     */
    private void dealData(List datas,Map dataSyncDef,ITaskParam taskParam){
        String syncFlag = (String)dataSyncDef.get("SYNC_FLAG");
        String dataToFlag = syncFlag.substring(3);
        switch (dataToFlag){
            case "T":
                //target db
                dataToDb(datas,dataSyncDef,taskParam);
                break;
            case "F":
                //target file
                dataToFile(datas,dataSyncDef,taskParam);
                break;
             }
    }

    /**
     * @desc query data
     * @param dataSyncDef
     * @param taskParam
     * @return list
     */
    private List queryData(Map dataSyncDef,ITaskParam taskParam){
        String SELECT_NAMESPACE = (String) dataSyncDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = (String) dataSyncDef.get("SELECT_SQL_ID");
        if(!StringUtils.isBlank(SELECT_NAMESPACE) && !StringUtils.isBlank(SELECT_SQL_ID)){
            Shard shard = getShard(getQueryShardManager(dataSyncDef),taskParam);
            return queryBySpace(dataSyncDef,shard,taskParam);
        }else{
            //直接通过表名查询
            return queryAutoMapperDao(dataSyncDef,taskParam);
        }
    }
    /**
     * @desc query data throw table name and where sql
     * @param dataSyncDef
     * @param taskParam
     * @return
     */
    private List queryAutoMapperDao(Map dataSyncDef , ITaskParam taskParam){
        String TABLE_NAME = (String)dataSyncDef.get("TABLE_NAME");
        CommTableObj queryTableObj = new CommTableObj(TABLE_NAME);
        String KEY_FIELD = (String)dataSyncDef.get("KEY_FIELD");
        String whereSql = (String)dataSyncDef.get("WHERE_SQL");
        queryTableObj.setWhereSql(whereSql);
        queryTableObj.setKeyField(KEY_FIELD);
        String START_KEY = taskParam.getStartKey();
        String END_KEY = taskParam.getEndKey();
        queryTableObj.setRange(START_KEY, END_KEY);
        queryTableObj.putAll(batchLocal.getSystemParam());
        Shard queryShard = getShard(getQueryShardManager(dataSyncDef),taskParam);
        String is_split = taskParam.getIsSplit();
        if("Y".equals(is_split)){
            return autoMapperDao.selectRangeList(queryTableObj,getQueryShardManager(dataSyncDef), queryShard);
        }else{
            return  autoMapperDao.selectList(queryTableObj,getQueryShardManager(dataSyncDef),queryShard);
        }

    }
    /**
     * @desc Through SELECT_NAMESPACE and SELECT_SQL_ID query data
     * @param dataSyncDef
     * @param shard
     * @return
     */
    private List queryBySpace(Map dataSyncDef, Shard shard , ITaskParam taskParam){
        String SELECT_NAMESPACE = (String) dataSyncDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = (String) dataSyncDef.get("SELECT_SQL_ID");
        Map param = new HashMap();
        param.putAll(batchLocal.getSystemParam());
        param.put("START_ROW", "" + taskParam.getStartRow());
        param.put("END_ROW", "" + taskParam.getEndRow());
        param.put("START_KEY", taskParam.getStartKey());
        param.put("END_KEY", taskParam.getEndKey());
        param.put("maxPerCount",taskParam.getMaxPerSplit());
        return getQueryShardSqlSessionTemplate(dataSyncDef).selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, param, shard);
    }
    private int getBatchSize(ITaskParam taskParam){
        int batchSize = 5000;
        try {
            batchSize = BatchUtils.parseInt(taskParam.getBatchSize());
            if(batchSize == 0){
                batchSize = 5000;
            }
        }catch (Exception e){
            throw new GalaxyException("请检查批量提交数！"+"batchSize["+taskParam.getBatchSize()+"]");
        }
        return batchSize;
    }
    /**
     * @desc import data to DataBase
     * @param datas
     * @param dataSyncDef
     * @param taskParam
     */
    private void dataToDb(List datas,Map dataSyncDef,ITaskParam taskParam){
        String INSERT_NAMESPACE = (String)dataSyncDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = (String)dataSyncDef.get("INSERT_SQL_ID");
        if(!StringUtils.isBlank(INSERT_NAMESPACE) && !StringUtils.isBlank(INSERT_SQL_ID)){
            insertBySpace(datas, dataSyncDef, taskParam);
        }else{
            insertAutoMapperDao(datas, dataSyncDef, taskParam);
        }
    }
    private void insertBySpace(List list,Map dataSyncDef,ITaskParam taskParam){
        String INSERT_NAMESPACE = (String)dataSyncDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = (String)dataSyncDef.get("INSERT_SQL_ID");
        String MATCH_FLAG = (String) dataSyncDef.get("MATCH_FLAG");
        ShardSqlSessionTemplate shardSqlSessionTemplate = getInsertShardSqlSessionTemplate(dataSyncDef);
        if("Y".equals(MATCH_FLAG)){
            Shard insertShard = getInsertShardManager(dataSyncDef).getShard(taskParam.getSchemaId());
            shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list, insertShard);
        }else{
            shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list);
        }
    }
    private void insertAutoMapperDao(List list,Map dataSyncDef,ITaskParam taskParam){
        String TARGET_TABLE_NAME = (String)dataSyncDef.get("TARGET_TABLE_NAME");
        CommTableObj tableObj = new CommTableObj(TARGET_TABLE_NAME);
        tableObj.setRecords(list);
        String MATCH_FLAG = (String) dataSyncDef.get("MATCH_FLAG");
        if("Y".equals(MATCH_FLAG)){
            Shard insertShard = getShard(getInsertShardManager(dataSyncDef), taskParam);
            autoMapperDao.batchInsert(tableObj, getInsertShardManager(dataSyncDef), insertShard);
        }else{
            autoMapperDao.batchInsert(tableObj, getInsertShardManager(dataSyncDef));
        }
    }

    private void dataToFile(List list, Map dataSyncDef ,ITaskParam taskParam){
        writeFile(list, dataSyncDef,taskParam);
    }

    /**
     * @desc if dataTarget = file and  FILE_WRITE_TYPE = W(增量) check file
     * @param dataSyncDef
     * @return void
     */
    private void checkFile(Map dataSyncDef){
        String SYNC_FLAG =(String)dataSyncDef.get("SYNC_FLAG");
        String dataToFlag = SYNC_FLAG.substring(3);
        if("F".equals(dataToFlag)){
            String FILE_WRITE_TYPE = (String)dataSyncDef.get("FILE_WRITE_TYPE");
            TwoTuple<String,String> twoTuple = getFilePathAndName(dataSyncDef);
            File filePath = new File(twoTuple.second);
            if(!filePath.exists()){
                filePath.mkdir();
            }
            if("W".equals(FILE_WRITE_TYPE)){
                File oldFile = new File(twoTuple.second + File.separator + twoTuple.first);
                if(oldFile.exists()){
                    oldFile.delete();
                }
            }

        }
    }

    private void writeFile(List list ,Map dataSyncDef ,ITaskParam taskParam){
        String fileFormat = (String)dataSyncDef.get("FILE_FORMAT");
        TwoTuple<String,String> twoTuple = getFilePathAndName(dataSyncDef);
        String fileName = twoTuple.first;
        String filePath = twoTuple.second;
        String is_split = taskParam.getIsSplit();
        String newFileName = fileName;
        if("Y".equals(is_split)){
            newFileName =fileName+StringUtils.pd(taskParam.getSeqNo()+"","L","0",6);
        }
        FileParseUtils.writeBody(filePath, newFileName, fileFormat, list, false);
    }
    private TwoTuple<String,String> getFilePathAndName(Map dataSyncDef){
        String filePath = splitJob.getFileName((String)dataSyncDef.get("FILE_PATH"));
        String fileName = splitJob.getFileName((String) dataSyncDef.get("FILE_NAME"));
        return new TwoTuple<>(fileName,filePath);
    }
   /* private Shard getShard(ITaskParam taskParam)
    {
        if (taskParam.getSchemaId() == null || taskParam.getSchemaId().trim().length() == 0)
            return shardSqlSessionTemplate.getShardManager().getDefaultShard();
        else
            return shardSqlSessionTemplate.getShardManager().getShard(taskParam.getSchemaId());
    }*/

    /**
     * @desc 查询shardManager
     * @param dataSyncDef
     * @return
     */
    private ShardManager getQueryShardManager(Map dataSyncDef){
        String shard_manage_id = (String)dataSyncDef.get("SHARD_MANAGE_ID");
        if(StringUtils.isBlank(shard_manage_id)){
            shard_manage_id = "shardManager";
        }
        return (ShardManager) SpringApplicationContext.getContext().getBean(shard_manage_id);
    }

    /**
     * @desc 查询insert shardManager
     * @param dataSyncDef
     * @return
     */
    private ShardManager getInsertShardManager(Map dataSyncDef){
        String insert_shard_manage_id = (String) dataSyncDef.get("INSERT_SHARD_MANAGE_ID");
        if(StringUtils.isBlank(insert_shard_manage_id)){
            insert_shard_manage_id = "shardManager";
        }
        return (ShardManager) SpringApplicationContext.getContext().getBean(insert_shard_manage_id);
    }

    /**
     * @desc query ShardSqlSessionTemplate
     * @param dataSyncDef
     * @return
     */
    private ShardSqlSessionTemplate getQueryShardSqlSessionTemplate(Map dataSyncDef){
        return ShardManageUtils.getShardSqlSessionTemplate(getQueryShardManager(dataSyncDef));
    }

    /**
     * @desc  insert ShardSqlSessionTemplate
     * @param dataSyncDef
     * @return
     */
    private ShardSqlSessionTemplate getInsertShardSqlSessionTemplate(Map dataSyncDef){
        return ShardManageUtils.getShardSqlSessionTemplate(getInsertShardManager(dataSyncDef));
    }
    private Map getDataSyncDef(ITaskParam taskParam){
        CommTableObj tableObj = new CommTableObj("BATCH_DATA_SYNC_DEF");
        tableObj.setCondition("SYNC_ID",taskParam.getStaticParam());
        Map dataSyncDef = autoMapperDao.selectOne(tableObj);
        if(dataSyncDef==null){
            throw new GalaxyException("同步参数未定义<" + taskParam.getStaticParam() + ">");
        }
        return dataSyncDef;
    }
    private Shard getShard(ShardManager shardManager, ITaskParam taskParam){
        String schemaId = taskParam.getSchemaId();
        if(StringUtils.isBlank(schemaId)){
            schemaId = shardManager.getDefaultShardId();
        }
        return shardManager.getShard(schemaId);
    }
}
