package com.dcits.orion.batch.task.service;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLog;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLogGroup;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 批处理重试提交失败task
 * @author qiqingshan
 * @date 20170227
 */
@Repository
public class CheckJob {

    private static Logger logger = LoggerFactory.getLogger(CheckJob.class);

    @Resource
    AutoMapperDao autoMapperDao;
    @Resource
    ShardSqlSessionTemplate shardSqlSessionTemplate;

    /**
     * @desc  s数据恢复入口
     * @param taskId
     * @return flag : N:没有记录  B:全部是大事物  S:全是小事物
     */
    public String queryAndcommit(String taskId,String groupId){
        String flag = "N";
        Map<String, ShardSqlSessionTemplate> shardSqlSessionTemplates = getShardSqlSessionTemplates();
        try {
            for (ShardSqlSessionTemplate shardSqlSessionTemplate : shardSqlSessionTemplates.values()) {
                ShardManager shardManager = shardSqlSessionTemplate.getShardManager();
                List<Shard> shards = shardManager.getAllShard();
                Map param = new HashMap();
                param.put("TASK_ID",taskId);
                param.put("GROUP_ID",groupId);
                List<ShardResultSet> shardResultSetList = shardSqlSessionTemplate.selectList("com.dcits.orion.batch.service.BatchTableLoad.selectCommitData",param, shards);
                for (ShardResultSet shardResultSet : shardResultSetList) {
                    List<Map> results = shardResultSet.getResult();
                    int i = 0 ;
                    if (results != null && results.size() !=0) {
                        for (Map result : results) {
                            String TASK_ID = (String)result.get("TASK_ID");
                            String IDEM_ID = (String)result.get("IDEM_ID");
                            String SHARD_MANAGER_ID =(String)result.get("SHARD_MANAGER_ID");
                            String SHARD_ID = (String)result.get("SHARD_ID");
                            if( !TASK_ID.equals(IDEM_ID)){
                                i++;
                            }
                            if(!checkIdempoent(IDEM_ID,SHARD_MANAGER_ID,SHARD_ID)){//检查幂等
                                CheckJob checkJob = SpringApplicationContext.getContext().getBean(CheckJob.class);
                                try{
                                    checkJob.doSubmit(result);
                                }catch (Exception e){
                                    throw e;
                                }
                            }
                        }
                        if(results.size() == i){ //全是小事物
                            flag = "S";
                        }else{ //全是大事物
                            flag = "B";
                        }
                        //一个库上对应的一个task_id执行完成 删除batch_to_commit
                        String shardId = shardResultSet.getShardId();
                        Shard shard = shardManager.getShard(shardId);
                        deleteCommitData(param,shard);
                    }
                }
            }
        } catch (Exception e) {
            throw  new GalaxyException(e);
        }
        return flag;
    }
    /**
     * @desc Check idempoent
     * @param taskId
     * @return boolean
     */
    public boolean checkIdempoent(String taskId){
        ShardManager shardManager;
        List<Shard> shards;
        Map param = new HashMap<>();
        param.put("TASK_ID", taskId);
        Map<String, ShardSqlSessionTemplate> shardSqlSessionTemplates = getShardSqlSessionTemplates();
        for (ShardSqlSessionTemplate shardSqlSessionTemplate : shardSqlSessionTemplates.values()) {
            shardManager = shardSqlSessionTemplate.getShardManager();
            shards = shardManager.getAllShard();
            List<ShardResultSet> shardResultSetList = shardSqlSessionTemplate.selectList("com.dcits.orion.batch.service.BatchTableLoad.getIdempoent", param, shards);
            for(ShardResultSet shardResultSet : shardResultSetList){
                List<Map> results = shardResultSet.getResult();
                if(results != null && results.size() !=0){
                    logger.info("idempotent already exist IDEM_ID[" + taskId + "]！");
                    return true;
                }
            }
        }
        logger.info("idempotent not exist IDEM_ID[" + taskId + "]！");
        return false;
    }

    /**
     * @desc commit data
     * @param result
     */
    @Transactional
    public void doSubmit(Map result){
        String SHARD_MANAGER_ID =(String)result.get("SHARD_MANAGER_ID");
        String SHARD_ID = (String)result.get("SHARD_ID");
        Blob COMMIT_DATA = (Blob)result.get("COMMIT_DATA");
        Shard shard = getShard(SHARD_MANAGER_ID, SHARD_ID);
       // IdempotentContext.setIdempotentObj(IDEM_ID); //拦截sql中已经存在幂等，不需要重新设置幂幂等
        try {
            SqlLogGroup sqlLogGroup = BatchUtils.deserialize(COMMIT_DATA);
            List<SqlLog> allSqlLog = sqlLogGroup.getAllSqlLog();
            for (SqlLog sqlLog : allSqlLog) {
                excuteSqlLog(sqlLog, shard);
            }
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }

    public void excuteSqlLog(SqlLog sqlLog,Shard shard) {
        String sql = sqlLog.getSql();
        List<SqlLog.ParameterValue> list = sqlLog.getAllParameterValue();
        List<Object[]> value = new ArrayList();
        try{
            for(SqlLog.ParameterValue parameterValue : list){
                Object[]array= parameterValue.getAllValue().toArray();
                value.add(array);
            }
            if(value != null && value.size()>0){
                autoMapperDao.executeBatchSql(sql, value, shard);
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * @desc check idempoent base on idem_id
     * @param idem_id
     * @param shardManageId
     * @param ShardId
     * @return boolean
     */
    private boolean checkIdempoent(String idem_id,String shardManageId,String ShardId){
        ShardManager shardManager;
        Shard shard;
        CommTableObj tableObj = new CommTableObj("STATEINFO");
        tableObj.setCondition("ID",idem_id);
        shardManager = getShardManager(shardManageId);
        shard = shardManager.getShard(ShardId);
        List result = autoMapperDao.selectList(tableObj, shardManager, shard);
        if(result != null && result.size() > 0){
            logger.info("idempotent already exist IDEM_ID[" + idem_id + "]！");
            return true;
        }
        logger.info("idempotent not exist IDEM_ID[" + idem_id + "]！");
        return false;
    }
    /**
     * @desc 反序列化待提交数据
     * @param blob
     * @return Object
     */
    public Object deserializeBlobToObject(Blob blob) throws Exception{
        byte[] temp = new byte[(int)blob.length()];
        InputStream in = blob.getBinaryStream();
        in.read(temp);
        return SerializationUtils.deserializeObj(temp);
    }

    private Map<String,ShardSqlSessionTemplate> getShardSqlSessionTemplates(){
        return SpringApplicationContext.getContext().getBeansOfType(ShardSqlSessionTemplate.class);
    }

    /**
     * @desc 待提交数据的ShardManager
     * @param shard_manager_id
     * @return
     */
    private ShardManager getShardManager(String shard_manager_id){
        return (ShardManager)SpringApplicationContext.getContext().getBean(shard_manager_id);
    }
    /**
     * @desc 待提交数据的Shard
     * @param shard_id
     * @return
     */
    private Shard getShard(String shard_manager_id,String shard_id){
        return getShardManager(shard_manager_id).getShard(shard_id);
    }

    private ShardSqlSessionTemplate getShardSqlSessionTemplate(String shard_manager_id){
        return ShardManageUtils.getShardSqlSessionTemplate(getShardManager(shard_manager_id));
    }

    /**
     * @desc delete batch_to_commit
     * @param param
     */
    public void deleteCommitData(Map param , Shard shard){
        shardSqlSessionTemplate.delete("com.dcits.orion.batch.service.BatchTableLoad.deleteCommitData",param,shard);
    }

    /**
     *
     * @param map
     */
    public void updateCommit(Map map){
        CommTableObj tableObj = new CommTableObj("BATCH_TO_COMMIT");
        tableObj.setCondition("INTERNAL_KEY",map.get("INTERNAL_KEY"));
        tableObj.setField("RESUME_TIMES",((BigDecimal)map.get("RESUME_TIMES")).intValue()+1);
        autoMapperDao.update(tableObj, (ShardManager)map.get("shardManager"), (Shard)map.get("shard"));
    }
}
