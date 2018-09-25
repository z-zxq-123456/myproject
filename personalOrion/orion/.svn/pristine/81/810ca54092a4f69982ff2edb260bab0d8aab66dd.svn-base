package com.dcits.orion.batch.common.dao;

import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by lixbb on 2015/11/6.
 */
@Repository
public class BatchDao {

    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;
    @Resource
    private AutoMapperDao autoMapperDao;

    private static final String NAMESPACE = "com.dcits.orion.batch.dao.BatchDao";

    public void insertBatchStatus(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertBatchStatus", map);
    }

    public void deleteBatchStatus(Map map) {
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteBatchStatus", map);
    }

    public void updateBatchStatus(Map map) {
        shardSqlSessionTemplate.update(NAMESPACE, "updateBatchStatus", map);
    }
    @Transactional
    public Map getBatchStatus(String batchClass) {

        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getBatchStatus", map);


    }

    public Map getStdJob(String JOB_ID) {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getStdJob", map);
    }

    public List getStdJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getStdJobs", map);
    }


    public void insertRunJob(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertRunJob", map);
    }

    public void deleteRunJob(Map map) {
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteRunJob", map);
    }

    public void deleteRunJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteRunJobs", map);

    }

    public void updateRunJob(Map map) {

        CommTableObj commTableObj = new CommTableObj("BATCH_RUN_JOB");
        commTableObj.setCondition("JOB_ID", map.get("JOB_ID"));
        map.remove("JOB_ID");
        commTableObj.setFieldValue(map);
        autoMapperDao.update(commTableObj);

        //shardSqlSessionTemplate.update(NAMESPACE, "updateRunJob", map);
    }

    public List getToRunJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getToRunJobs", map);
    }

    public List<Map> getRunningJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunningJobs", map);
    }

    public Map getRunJob(Map map) {
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getRunJob", map);
    }

    public long getNoFinishJobCnt(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        Map result = (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getNoFinishJobCnt", map);
        return BatchUtils.parseLong(result.get("CNT"));
    }


    public void genRunJobs(String batchClass) {
        /*List stdJobs = getStdJobs(batchClass);
        List runJob = new ArrayList();
        for (int i = 0; i < stdJobs.size(); i++) {
            Map stdJob = (Map) stdJobs.get(i);
            Map runJob = new HashMap();
            runJob.put("JOB_ID", stdJob.get("JOB_ID"));
            runJob.put("JOB_STATUS", ("N"));
            insertRunJob(runJob);
        }*/
        Map map = new HashMap();
        map.put("BATCH_CLASS",batchClass);
        shardSqlSessionTemplate.insert(NAMESPACE,"genRunJob",map);

    }


    public void insertRunTask(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertRunTask", map);
    }


    public void backupHist(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        Map batchStatus = getBatchStatus(batchClass);
        if (batchStatus == null)
            return;
        CommTableObj batchStatusObj = new CommTableObj("BATCH_STATUS_HIST");
        batchStatusObj.setFieldValue(batchStatus);
        autoMapperDao.insert(batchStatusObj);
        String runDate = (String) batchStatus.get("RUN_DATE");
        map.put("RUN_DATE", runDate);
        shardSqlSessionTemplate.insert(NAMESPACE, "backupBatchRunJob",map);
        shardSqlSessionTemplate.insert(NAMESPACE,"backupBatchRunTask",map);

       /* List<Map> runJobs = shardSqlSessionTemplate.selectList(NAMESPACE, "runJobs", map);
        List<Map> runTasks = shardSqlSessionTemplate.selectList(NAMESPACE, "runTasks", map);
        if (null != runJobs) {
            for (Map runJob : runJobs) {
                runJob.put("RUN_DATE", runDate);
            }
            CommTableObj runJobObj = new CommTableObj("BATCH_RUN_JOB_HIST");
            runJobObj.setRecords(runJobs);
            autoMapperDao.insertBatch(runJobObj);
        }
        if (null != runTasks) {
            for (Map runTask : runTasks) {
                runTask.put("RUN_DATE", runDate);
            }
            CommTableObj runTaskObj = new CommTableObj("BATCH_RUN_TASK_HIST");
            runTaskObj.setRecords(runTasks);
            autoMapperDao.insertBatch(runTaskObj);
        }*/
    }

    public void deleteAllRunTask(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteAllRunTask", map);
    }

    public void updateRunTask(Map map) {
        CommTableObj commTableObj = new CommTableObj("BATCH_RUN_TASK");
        commTableObj.setCondition("TASK_ID", map.get("TASK_ID"));
        commTableObj.setFieldValue(map);
        autoMapperDao.update(commTableObj);
    }

    public void updateRunTasks(List list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Map) {
                    updateRunTask((Map) list.get(i));
                }
            }
        }
    }

    public List getRunTasks(String JOB_ID)//传JOB_ID
    {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunTasks", map);

    }


    public Map getRunTask(String TASK_ID)//传Task_ID
    {
        Map map = new HashMap();
        map.put("TASK_ID", TASK_ID);
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getRunTask", map);
    }

    public List getToRunTasks(String batchClass, int dealCount) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);


        RowArgs rowArgs = new RowArgs(0, dealCount);//每次只处理dealCount条
        ParameterWrapper paraWrapper = new ParameterWrapper();
        paraWrapper.setRowArgs(rowArgs);
        paraWrapper.setBaseParam(map);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getToRunTasks", paraWrapper);
    }

    public List getRunningTasks(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunningTasks", map);
    }

    public long getFinishTaskCnt(String JOB_ID) {
        Map stdJob = getStdJob(JOB_ID);
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        Map result;
        if ("GP".equals(stdJob.get("JOB_TYPE")))
        {
            result = (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getFinishChildCnt", map);
        }
        else
        {
            result = (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getFinishTaskCnt", map);
        }
        return BatchUtils.parseLong(result.get("CNT"));
    }

    public long getACnt(String JOB_ID) {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        Map result = (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getACnt", map);
        return BatchUtils.parseLong(result.get("CNT"));
    }

    public long getMCnt(String JOB_ID) {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        Map result = (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getMCnt", map);
        return BatchUtils.parseLong(result.get("CNT"));
    }

    public Map getJobSplitParam(String JOB_ID) {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getJobSplitParam", map);
    }

    public long getRowCount(ShardManager shardManager, String namespace, String sqlId, Map map) {
        Shard shard = shardManager.getDefaultShard();
        Map result;
        if (selectByMap(namespace, sqlId))
            result = (Map) ShardManageUtils.getShardSqlSessionTemplate(shardManager).selectOne(namespace + "." + sqlId, map, shard);
        else {
            result = getRowCountByTable(shardManager, shard, map);
        }
        if (result == null)
            return 0;
        else
            return Long.parseLong(result.get("ROW_COUNT").toString());
    }

    private Map getRowCountByTable(ShardManager shardManager, Shard shard, Map map) {
        if ("EX".equals(map.get("JOB_TYPE")) || "IM".equals(map.get("JOB_TYPE"))) {
            String STATIC_PARAM = (String) map.get("STATIC_PARAM");
            CommTableObj commTableObj = new CommTableObj("BATCH_TABLE_DEF");
            commTableObj.setCondition("BATCH_TABLE_ID", STATIC_PARAM);
            Map tableDef = autoMapperDao.selectOne(commTableObj);
            if (tableDef == null)
                throw new GalaxyException("分段参数定义不全！");
            CommTableObj commTableObj1 = new CommTableObj((String) tableDef.get("TABLE_NAME"));
            commTableObj1.setWhereSql((String) tableDef.get("WHERE_SQL"));
            commTableObj1.putAll(map);
            return autoMapperDao.selectCount(commTableObj1, shardManager, shard);
        } else throw new GalaxyException("分段参数定义不全！");

    }

    public List getSchemaRowCounts(ShardManager shardManager, String namespace, String sqlId, Map map, String BY_SCHEMA) {

        List<Shard> shards;
        if (BY_SCHEMA.equals("Y")) {
            shards = shardManager.getShardsWithOutDefault();
        } else {
            shards = shardManager.getAllShard();
        }
        List schemaRowCounts = new ArrayList();
        for (Shard shard : shards) {
            Map rowCount = new HashMap();

            Map result;
            if (selectByMap(namespace, sqlId))
                result = (Map) ShardManageUtils.getShardSqlSessionTemplate(shardManager).selectOne(namespace + "." + sqlId, map, shard);
            else
                result = getRowCountByTable(shardManager, shard, map);
            if (result == null)
                rowCount.put("ROW_COUNT", 0);
            else
                rowCount.put("ROW_COUNT", result.get("ROW_COUNT"));
            rowCount.put("SHARD_ID", shard.getId());
            schemaRowCounts.add(rowCount);
        }
        return schemaRowCounts;
    }

    private boolean selectByMap(String namespace, String sqlId) {
        if (namespace != null && sqlId != null && namespace.trim().length() > 0 && sqlId.trim().length() > 0)
            return true;
        else return false;

    }

    public List getSchemas(ShardManager shardManager, String BY_SCHEMA) {
        List<Shard> shards;
        if (BY_SCHEMA.equals("Y")) {
            shards = shardManager.getShardsWithOutDefault();
        } else {
            shards = shardManager.getAllShard();
        }
        List schemas = new ArrayList();
        Iterator it = shards.iterator();
        while (it.hasNext()) {
            Shard shard = (Shard) it.next();
            schemas.add(shard.getId());
        }
        return schemas;
    }

    public Map getFmSystem() {
        return (Map) shardSqlSessionTemplate.selectOne(NAMESPACE, "getFmSystem");
    }


    /////图形化界面配置的DAO

    public List getBatchList() {
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getBatchList");
    }

    public void deleteJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteLines", map);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteLineTypes", map);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobPos", map);
    }

    public void insertNodes(List<Map> nodes) {
        for (Map node : nodes) {
            insertNode(node);
        }
    }

    public void insertLines(List<Map> lines) {
        for (Map line : lines) {
            insertLine(line);
        }
    }

    public void insertNode(Map map) {

        shardSqlSessionTemplate.insert(NAMESPACE, "insertJobPos", map);

    }

    public void insertLine(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertLine", map);
        shardSqlSessionTemplate.insert(NAMESPACE, "insertLineType", map);
    }


    public List getLines(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getLines", map);
    }

    public List getJobGroupLine(String batchClass,String jobGroupId){
        Map map = new HashMap();
        map.put("BATCH_CLASS",batchClass);
        map.put("JOB_GROUP_ID",jobGroupId);
        return shardSqlSessionTemplate.selectList(NAMESPACE,"getGroupLines",map);
    }
    public List getLineTypes(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getLineTypes", map);
    }

    public List getJobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getJobs", map);
    }

    public List getJobPos(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getJobPos", map);
    }

    public List getJobGroupPos(String batchClass,String jobGroupId) {
        Map map = new HashMap();
        map.put("BATCH_CLASS",batchClass);
        map.put("JOB_GROUP_ID",jobGroupId);
        return shardSqlSessionTemplate.selectList(NAMESPACE,"getGroupPos",map);
    }

    public List getJobSplit(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getJobSplit", map);
    }


    //////////////////////////OLD//////////////////////

    public void saveJob(Map map) {
        String oldJobId = (String) map.get("OLD_JOB_ID");
        if (oldJobId != null && oldJobId.trim().length() > 0) {
            shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobSplit", map);
            shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobPos", map);
            shardSqlSessionTemplate.delete(NAMESPACE, "deleteJob", map);
        }
        shardSqlSessionTemplate.insert(NAMESPACE, "insertJobPos", map);
        shardSqlSessionTemplate.insert(NAMESPACE, "insertJob", map);
        if ("Y".equals(map.get("IS_SPLIT"))) {
            shardSqlSessionTemplate.insert(NAMESPACE, "insertJobSplit", map);
        }
        String jobId = (String) map.get("JOB_ID");
        if (oldJobId != null && jobId != null && oldJobId.trim().length() > 0 && jobId.trim().length() > 0) {
            if (!oldJobId.trim().equals(jobId.trim())) {
                shardSqlSessionTemplate.update(NAMESPACE, "updateJobLineFrom", map);
                shardSqlSessionTemplate.update(NAMESPACE, "updateJobLineTo", map);
                shardSqlSessionTemplate.update(NAMESPACE, "updateJobDependencyFrom", map);
                shardSqlSessionTemplate.update(NAMESPACE, "updateJobDependencyTo", map);
            }
        }
    }

    public void saveNode(Map map) {
        map.put("OLD_JOB_ID", map.get("JOB_ID"));
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobPos", map);
        shardSqlSessionTemplate.insert(NAMESPACE, "insertJobPos", map);

    }

    public void saveLine(Map map) {
        deleteLine(map);
        if (map.containsKey("DEPENDENCY_TYPE"))
            shardSqlSessionTemplate.insert(NAMESPACE, "insertLine", map);
        shardSqlSessionTemplate.insert(NAMESPACE, "insertLineType", map);

    }

    public void deleteJob(Map map) {
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobSplit", map);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteJobPos", map);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteJob", map);
    }

    public void deleteLine(Map map) {
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteLineType", map);
        if (map.containsKey("DEPENDENCY_TYPE"))
            shardSqlSessionTemplate.delete(NAMESPACE, "deleteLine", map);
    }


    public List getRunjobs(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunjobs", map);
    }

    public List getRunJobLines(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunJobLines", map);
    }

    public Map getSystemParam() {
        return (Map) shardSqlSessionTemplate.selectOne("com.dcits.orion.batch.dao.LocalDao", "getSystemParam");
    }

    public List getRunBatchs() {
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getRunBatchs");
    }

    public void insertRunTasks(List tasks) {
        shardSqlSessionTemplate.insertBatch(NAMESPACE, "insertRunTasks", tasks);
    }

    public void deleteSplitTask(String JOB_ID)//删除分段TASK
    {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteSplitTask", map);
    }

    public void updateSplitTask(String JOB_ID)//将分段TASK的状态从S更新为N
    {
        Map map = new HashMap();
        map.put("JOB_ID", JOB_ID);
        shardSqlSessionTemplate.update(NAMESPACE, "updateSplitTask", map);
    }

    public List getFailTasks(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getFailTasks", map);
    }

    public void restartFailTasks(String batchClass) {
        Map map = new HashMap();
        map.put("BATCH_CLASS", batchClass);
        shardSqlSessionTemplate.update(NAMESPACE, "restartFailTasks", map);

    }

    public List getStatusList(String runDate) {
        Map map = new HashMap();
        map.put("RUN_DATE", runDate);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getStatusList", map);
    }

    public List getJobList(String runDate, String batchClass) {
        Map map = new HashMap();
        map.put("RUN_DATE", runDate);
        map.put("BATCH_CLASS", batchClass);
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getJobList", map);
    }

    public List getBatchTimerList() {
        return shardSqlSessionTemplate.selectList(NAMESPACE, "getBatchTimerList");
    }

    public void insertBhTimer(Map map) {
        shardSqlSessionTemplate.insert(NAMESPACE, "insertBhTimer", map);
    }

    public void editBhTimer(Map map) {
        shardSqlSessionTemplate.update(NAMESPACE, "updateByPrimaryKey", map);
    }

    public void deleteTimer(Map map) {
        shardSqlSessionTemplate.delete(NAMESPACE, "deleteTimer", map);
    }

    public Map getBatchDef(String batchClass)
    {
        CommTableObj batchDef = new CommTableObj("BATCH_DEF");
        batchDef.setCondition("BATCH_CLASS",batchClass);
        return autoMapperDao.selectOne(batchDef);
    }
    public int getChildJobCount(String JOB_ID)
    {
        CommTableObj query = new CommTableObj("BATCH_STD_JOB");
        query.setCondition("JOB_GROUP_ID",JOB_ID);
        int count = BatchUtils.parseInt(autoMapperDao.selectCount(query).get("ROW_COUNT"));
        return count;
    }
    public List<Map> getJobGroupRun(String batchClass,String jobGroupId)
    {
        Map map = new HashMap();
        map.put("BATCH_CLASS",batchClass);
        map.put("JOB_GROUP_ID",jobGroupId);
        return shardSqlSessionTemplate.selectList(NAMESPACE,"getJobGroupRun",map);
    }
}
