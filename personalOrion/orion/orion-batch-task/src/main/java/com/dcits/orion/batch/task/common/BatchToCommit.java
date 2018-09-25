package com.dcits.orion.batch.task.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.transaction.DataSourceUtils;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLogGroup;
import com.dcits.galaxy.dal.mybatis.transaction.log.SqlLogRegistrar;
import com.dcits.galaxy.dal.mybatis.transaction.ordered.OrderedSubmitUtils;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.bean.TaskParam;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by nick on 2017/2/27.
 */
@Repository
public class BatchToCommit implements SqlLogRegistrar {
    @Resource
    AutoMapperDao autoMapperDao;

    @Override
    public void doBeforeCommit(Map<DataSource,SqlLogGroup> sqlLogGroupMap){

        String shardId=null;

        ShardManager shardManager = null;
        Shard shard = null;
        if(sqlLogGroupMap == null || sqlLogGroupMap.isEmpty()){
            return;
        }else{
            LinkedHashSet<DataSource> order =  OrderedSubmitUtils.getOrderInfo();
            if(order == null || order.isEmpty()){
                return ;
            }
            int index = 1;
            for(DataSource dataSource:order)
            {
                SqlLogGroup sqlLogGroup = sqlLogGroupMap.get(dataSource);
                if (index == 1)
                {
                    shardManager = (ShardManager) SpringApplicationContext.getContext().getBean(sqlLogGroup.getShardInfo().getShardManagerBeanId());
                    shardId = sqlLogGroup.getShardInfo().getShardId();
                    shard =shardManager.getShard(shardId);
                }
                else {
                    insert(shardManager,shard,sqlLogGroup);
                }
                index++;
            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doAfterCommit(Map<DataSource, SqlLogGroup> sqlLogGroupMap) {
        if(sqlLogGroupMap == null || sqlLogGroupMap.isEmpty()){
            return ;
        }
        if (sqlLogGroupMap.size() > 1)
        {
            Map map = (Map)ThreadLocalManager.get("thread_local_map");
            if(map != null && map.size() > 0){
                ShardManager shardManager = (ShardManager)map.get("ShardManager");
                Shard shard = (Shard)map.get("Shard");
                String idem_id  = (String)map.get("idem_id");
                deleteCommit(shardManager,shard,idem_id);
            }
        }
    }
    /**
     * @desc delete batch_to_commit
     * @param shardManager
     * @param shard
     */
    public void deleteCommit(ShardManager shardManager,Shard shard,String idem_id){
        TaskParam taskParam=(TaskParam) ThreadLocalManager.get("taskParam");
        String taskId= taskParam.getTaskId();
        CommTableObj commTableObj = new CommTableObj("BATCH_TO_COMMIT");
        commTableObj.setCondition("TASK_ID",taskId);
        commTableObj.setCondition("IDEM_ID",idem_id);
        autoMapperDao.delete(commTableObj,shardManager,shard);
    }

    /**
     * @desc insert batch_to_commit
     * @param shardManager
     * @param shard
     * @param sqlLogGroup
     */
    public void insert(ShardManager shardManager,Shard shard,SqlLogGroup sqlLogGroup)
    {
        byte[] commitData=null;
        commitData= BatchUtils.serialize(sqlLogGroup);
        String   shardManagerId = sqlLogGroup.getShardInfo().getShardManagerBeanId();
        String shardId = sqlLogGroup.getShardInfo().getShardId();
        String GroupId= ConfigUtils.getProperty("galaxy.application.group");
        String  idemId=(String) IdempotentContext.getIdempotentObj();
        TaskParam taskParam=(TaskParam) ThreadLocalManager.get("taskParam");
        String taskId= taskParam.getTaskId();
        String batchGroupId=taskParam.getSendGroup();
        CommTableObj commTableObj = new CommTableObj("BATCH_TO_COMMIT");
        commTableObj.setField("TASK_ID",taskId);
        commTableObj.setField("SHARD_MANAGER_ID",shardManagerId);
        commTableObj.setField("SHARD_ID",shardId);
        commTableObj.setField("COMMIT_DATA",commitData);
        commTableObj.setField("GROUP_ID", GroupId);
        commTableObj.setField("BATCH_GROUP_ID", batchGroupId);
        commTableObj.setField("IDEM_ID", idemId);
        commTableObj.setField("RECORD_DATE", BatchUtils.getCurTimes());
        autoMapperDao.insert(commTableObj, shardManager, shard);
        Map threadMap = new HashMap();
        threadMap.put("ShardManager", shardManager);
        threadMap.put("Shard",shard);
        threadMap.put("idem_id",IdempotentContext.getIdempotentObj());
        ThreadLocalManager.put("thread_local_map",threadMap);
    }
}
