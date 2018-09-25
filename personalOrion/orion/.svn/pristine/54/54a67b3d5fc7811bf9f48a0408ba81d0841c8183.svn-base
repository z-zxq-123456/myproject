package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/2/19.
 */
@Repository
public class BatchTableLoad {
    @Resource
    AutoMapperDao autoMapperDao;
    @Resource
    private IBatchLocal batchLocal;
    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;


    public void loadData(ITaskParam taskParam)
    {
        taskParam.updateRunMsg("数据导入导出开始");
        String flag = taskParam.getJobType();
        if (!("EX".equals(flag) || "IM".equals(flag)))
        {
            throw new GalaxyException("导数JOB类型不正确！");
        }
        Map<String,String> tableDef = getBatchDef(taskParam);
        int batchSize = 0;
        try {
            batchSize = BatchUtils.parseInt(tableDef.get("BATCH_SIZE"));        }
        catch (Exception e)
        {

        }

        String SELECT_NAMESPACE = tableDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = tableDef.get("SELECT_SQL_ID");
        List datas;
        if (SELECT_NAMESPACE != null &&
                SELECT_NAMESPACE.trim().length() > 0 &&
                SELECT_SQL_ID != null &&
                SELECT_SQL_ID.trim().length() > 0 )
        {
            if ("Y".equals(taskParam.getIsSplit()))
            {
                ParameterWrapper paraWrapper = new ParameterWrapper();
                paraWrapper.setRowArgs(taskParam.getRowArgs());
                paraWrapper.setBaseParam(batchLocal.getSystemParam());
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID,paraWrapper, taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, paraWrapper,getShard(taskParam));
            }
            else {
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(), taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(),getShard(taskParam));

            }
        }
        else {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TABLE_NAME"));
            String whereSql = tableDef.get("WHERE_SQL");
            if (whereSql != null && whereSql.trim().length() > 0)
            {
                commTableObj.setWhereSql(whereSql);
            }
            commTableObj.putAll(batchLocal.getSystemParam());
            if ("Y".equals(taskParam.getIsSplit()))
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectListByPage(commTableObj,taskParam.getShardManager(), taskParam.getShard(), taskParam.getRowArgs());
                else
                    datas = autoMapperDao.selectListByPage(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam),taskParam.getRowArgs());
            }
            else
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectList(commTableObj,taskParam.getShardManager(),taskParam.getShard());
                else
                    datas = autoMapperDao.selectList(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam));
            }
        }
        taskParam.updateRunMsg("数据查询完成");
        String INSERT_NAMESPACE = tableDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = tableDef.get("INSERT_SQL_ID");
        if (INSERT_NAMESPACE != null &&
                INSERT_NAMESPACE.trim().length() > 0 &&
                INSERT_SQL_ID != null &&
                INSERT_SQL_ID.trim().length() > 0 )
        {
            List<List> splitList = null;
            if (batchSize == 0)
                splitList = autoMapperDao.split(datas);
            else
                splitList = autoMapperDao.split(datas,batchSize);
            for (List list : splitList)
            {
                if ("IM".equals(flag))
                    shardSqlSessionTemplate.insertBatch(INSERT_NAMESPACE, INSERT_SQL_ID, list);
                else
                    taskParam.getShardSqlSessionTemplate().insert(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list);

            }
        }
        else
        {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TARGET_TABLE_NAME"));
            commTableObj.setRecords(datas);
            if (batchSize == 0)
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj);
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager());

            }
            else
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj, batchSize);
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager(), batchSize);

            }

        }
        taskParam.updateRunMsg("数据插入完成");
    }



    public void loadDataAddBatch(ITaskParam taskParam)
    {
        taskParam.updateRunMsg("数据导入导出开始");
        String flag = taskParam.getJobType();
        if (!("EX".equals(flag) || "IM".equals(flag)))
        {
            throw new GalaxyException("导数JOB类型不正确！");
        }
        Map<String,String> tableDef = getBatchDef(taskParam);
        int batchSize = 0;
        try {
            batchSize = BatchUtils.parseInt(tableDef.get("BATCH_SIZE"));        }
        catch (Exception e)
        {

        }

        String SELECT_NAMESPACE = tableDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = tableDef.get("SELECT_SQL_ID");
        List datas;
        if (SELECT_NAMESPACE != null &&
                SELECT_NAMESPACE.trim().length() > 0 &&
                SELECT_SQL_ID != null &&
                SELECT_SQL_ID.trim().length() > 0 )
        {
            if ("Y".equals(taskParam.getIsSplit()))
            {
                ParameterWrapper paraWrapper = new ParameterWrapper();
                paraWrapper.setRowArgs(taskParam.getRowArgs());
                paraWrapper.setBaseParam(batchLocal.getSystemParam());
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID,paraWrapper, taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, paraWrapper,getShard(taskParam));
            }
            else {
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(), taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(),getShard(taskParam));

            }
        }
        else {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TABLE_NAME"));
            String whereSql = tableDef.get("WHERE_SQL");
            if (whereSql != null && whereSql.trim().length() > 0)
            {
                commTableObj.setWhereSql(whereSql);
            }
            commTableObj.putAll(batchLocal.getSystemParam());
            if ("Y".equals(taskParam.getIsSplit()))
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectListByPage(commTableObj,taskParam.getShardManager(), taskParam.getShard(), taskParam.getRowArgs());
                else
                    datas = autoMapperDao.selectListByPage(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam),taskParam.getRowArgs());
            }
            else
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectList(commTableObj,taskParam.getShardManager(),taskParam.getShard());
                else
                    datas = autoMapperDao.selectList(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam));
            }
        }
        taskParam.updateRunMsg("数据查询完成");
        String INSERT_NAMESPACE = tableDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = tableDef.get("INSERT_SQL_ID");
        if (INSERT_NAMESPACE != null &&
                INSERT_NAMESPACE.trim().length() > 0 &&
                INSERT_SQL_ID != null &&
                INSERT_SQL_ID.trim().length() > 0 )
        {
            List<List> splitList = null;
            if (batchSize == 0)
                splitList = autoMapperDao.split(datas);
            else
                splitList = autoMapperDao.split(datas,batchSize);
            for (List list : splitList)
            {
                if ("IM".equals(flag))
                    shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE, INSERT_SQL_ID, list);
                else
                    taskParam.getShardSqlSessionTemplate().insertAddBatch(INSERT_NAMESPACE, INSERT_SQL_ID, list);

            }
        }
        else
        {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TARGET_TABLE_NAME"));
            commTableObj.setRecords(datas);
            if (batchSize == 0)
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj);
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager());

            }
            else
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj, batchSize);
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager(), batchSize);

            }

        }
        taskParam.updateRunMsg("数据插入完成");
    }


    //一一对应的水平间数据导入导出
    public void oneToOneLoadData(ITaskParam taskParam)
    {
        taskParam.updateRunMsg("数据导入导出开始");
        String flag = taskParam.getJobType();
        if (!("EX".equals(flag) || "IM".equals(flag)))
        {
            throw new GalaxyException("导数JOB类型不正确！");
        }
        Map<String,String> tableDef = getBatchDef(taskParam);
        int batchSize = 0;
        try {
            batchSize = BatchUtils.parseInt(tableDef.get("BATCH_SIZE"));        }
        catch (Exception e)
        {

        }

        String SELECT_NAMESPACE = tableDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = tableDef.get("SELECT_SQL_ID");
        List datas;
        if (SELECT_NAMESPACE != null &&
                SELECT_NAMESPACE.trim().length() > 0 &&
                SELECT_SQL_ID != null &&
                SELECT_SQL_ID.trim().length() > 0 )
        {
            if ("Y".equals(taskParam.getIsSplit()))
            {
                ParameterWrapper paraWrapper = new ParameterWrapper();
                paraWrapper.setRowArgs(taskParam.getRowArgs());
                paraWrapper.setBaseParam(batchLocal.getSystemParam());
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID,paraWrapper, taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, paraWrapper,getShard(taskParam));
            }
            else {
                if ("IM".equals(flag))
                    datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(), taskParam.getShard());
                else
                    datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, batchLocal.getSystemParam(),getShard(taskParam));

            }
        }
        else {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TABLE_NAME"));
            String whereSql = tableDef.get("WHERE_SQL");
            if (whereSql != null && whereSql.trim().length() > 0)
            {
                commTableObj.setWhereSql(whereSql);
            }
            commTableObj.putAll(batchLocal.getSystemParam());
            if ("Y".equals(taskParam.getIsSplit()))
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectListByPage(commTableObj,taskParam.getShardManager(), taskParam.getShard(), taskParam.getRowArgs());
                else
                    datas = autoMapperDao.selectListByPage(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam),taskParam.getRowArgs());
            }
            else
            {
                if ("IM".equals(flag))
                    datas = autoMapperDao.selectList(commTableObj,taskParam.getShardManager(),taskParam.getShard());
                else
                    datas = autoMapperDao.selectList(commTableObj,shardSqlSessionTemplate.getShardManager(),getShard(taskParam));
            }
        }
        taskParam.updateRunMsg("数据查询完成");
        String INSERT_NAMESPACE = tableDef.get("INSERT_NAMESPACE");
        String INSERT_SQL_ID = tableDef.get("INSERT_SQL_ID");
        if (INSERT_NAMESPACE != null &&
                INSERT_NAMESPACE.trim().length() > 0 &&
                INSERT_SQL_ID != null &&
                INSERT_SQL_ID.trim().length() > 0 )
        {
            List<List> splitList = null;
            if (batchSize == 0)
                splitList = autoMapperDao.split(datas);
            else
                splitList = autoMapperDao.split(datas,batchSize);
            for (List list : splitList)
            {
                if ("IM".equals(flag))
                    shardSqlSessionTemplate.insert(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list,getMatchShard(shardSqlSessionTemplate.getShardManager(),taskParam.getSchemaId()));
                else
                    taskParam.getShardSqlSessionTemplate().insert(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list,taskParam.getShard());

            }
        }
        else
        {
            CommTableObj commTableObj = new CommTableObj();
            commTableObj.setTableName(tableDef.get("TARGET_TABLE_NAME"));
            commTableObj.setRecords(datas);
            if (batchSize == 0)
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj,getMatchShard(shardSqlSessionTemplate.getShardManager(),taskParam.getSchemaId()));
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager(),taskParam.getShard());

            }
            else
            {
                if ("IM".equals(flag))
                    autoMapperDao.insertBatch(commTableObj,getMatchShard(shardSqlSessionTemplate.getShardManager(),taskParam.getSchemaId()), batchSize);
                else
                    autoMapperDao.insertBatch(commTableObj, taskParam.getShardManager(),getMatchShard(shardSqlSessionTemplate.getShardManager(),taskParam.getSchemaId()), batchSize);

            }

        }
        taskParam.updateRunMsg("数据插入完成");
    }
    //参数数据导出
    public void paramExport(ITaskParam taskParam)
    {
        CommTableObj commTableObj = new CommTableObj();
        commTableObj.setTableName("BATCH_PARAM_TABLE_DEF");
        commTableObj.setCondition("DATA_SOURCE", taskParam.getStaticParam());
        List<Map> tables = autoMapperDao.selectListByPage(commTableObj, taskParam.getShardManager(), taskParam.getShard(), taskParam.getRowArgs());
        for (Map<String,String>  table : tables)
        {

            int batchSize = 0;
            try {
                batchSize = BatchUtils.parseInt(table.get("BATCH_SIZE"));
            }
            catch (Exception e)
            {

            }
            CommTableObj deleteTable = new CommTableObj();
            deleteTable.setTableName(table.get("TARGET_TABLE_NAME"));
            autoMapperDao.delete(deleteTable,taskParam.getShardManager(),taskParam.getShard());
            List<Map> datas;
            String SELECT_NAMESPACE = table.get("SELECT_NAMESPACE");
            String SELECT_SQL_ID = table.get("SELECT_SQL_ID");
            if (SELECT_NAMESPACE != null &&
                    SELECT_NAMESPACE.trim().length() > 0 &&
                    SELECT_SQL_ID != null &&
                    SELECT_SQL_ID.trim().length() > 0 )
            {
                datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE, SELECT_SQL_ID, batchLocal.getSystemParam());
            }
            else
            {
                CommTableObj tableObj = new CommTableObj();
                tableObj.setTableName(table.get("TABLE_NAME"));
                tableObj.setWhereSql(table.get("WHERE_SQL"));
                datas = autoMapperDao.selectList(tableObj);
            }
            String INSERT_NAMESPACE = table.get("INSERT_NAMESPACE");
            String INSERT_SQL_ID = table.get("INSERT_SQL_ID");
            if (INSERT_NAMESPACE != null &&
                    INSERT_NAMESPACE.trim().length() > 0 &&
                    INSERT_SQL_ID != null &&
                    INSERT_SQL_ID.trim().length() > 0 )
            {

                batchInsert(taskParam.getShardSqlSessionTemplate(),INSERT_NAMESPACE + "." + INSERT_SQL_ID, datas,taskParam.getShard(),batchSize);
            }
            else
            {
                CommTableObj tableObj = new CommTableObj();
                tableObj.setTableName(table.get("TARGET_TABLE_NAME"));
                tableObj.setRecords(datas);
                autoMapperDao.insertBatch(tableObj, taskParam.getShardManager(), taskParam.getShard(), batchSize);
            }
        }
    }

    //参数数据导入
    public void paramImport(ITaskParam taskParam)
    {
        CommTableObj commTableObj = new CommTableObj();
        commTableObj.setTableName("BATCH_PARAM_TABLE_DEF");
        commTableObj.setCondition("DATA_SOURCE",taskParam.getStaticParam());
        List<Map> tables = autoMapperDao.selectListByPage(commTableObj,getShard(taskParam),taskParam.getRowArgs());
        for (Map<String,String>  table : tables)
        {
            int batchSize = 0;
            try {
                batchSize = BatchUtils.parseInt(table.get("BATCH_SIZE"));
            }
            catch (Exception e)
            {

            }
            CommTableObj deleteTable = new CommTableObj();
            deleteTable.setTableName(table.get("TARGET_TABLE_NAME"));
            autoMapperDao.delete(deleteTable,getShard(taskParam));
            List<Map> datas;
            String SELECT_NAMESPACE = table.get("SELECT_NAMESPACE");
            String SELECT_SQL_ID = table.get("SELECT_SQL_ID");
            if (SELECT_NAMESPACE != null &&
                    SELECT_NAMESPACE.trim().length() > 0 &&
                    SELECT_SQL_ID != null &&
                    SELECT_SQL_ID.trim().length() > 0 )
            {
                datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE, SELECT_SQL_ID, batchLocal.getSystemParam());
            }
            else
            {
                CommTableObj tableObj = new CommTableObj();
                tableObj.setTableName(table.get("TABLE_NAME"));
                tableObj.setWhereSql(table.get("WHERE_SQL"));
                datas = autoMapperDao.selectList(tableObj,taskParam.getShardManager());
            }
            String INSERT_NAMESPACE = table.get("INSERT_NAMESPACE");
            String INSERT_SQL_ID = table.get("INSERT_SQL_ID");
            if (INSERT_NAMESPACE != null &&
                    INSERT_NAMESPACE.trim().length() > 0 &&
                    INSERT_SQL_ID != null &&
                    INSERT_SQL_ID.trim().length() > 0 )
            {
                batchInsert(shardSqlSessionTemplate,INSERT_NAMESPACE + "." + INSERT_SQL_ID, datas, getShard(taskParam),batchSize);
            }
            else
            {
                CommTableObj tableObj = new CommTableObj();
                tableObj.setTableName(table.get("TARGET_TABLE_NAME"));
                tableObj.setRecords(datas);
                autoMapperDao.insertBatch(tableObj,getShard(taskParam),batchSize);
            }
        }
    }

    private void batchInsert(ShardSqlSessionTemplate shardSqlSessionTemplate,String statement,List datas,Shard shard,int batchSize )
    {
        List<List> splitList = null;
        if (batchSize == 0)
            splitList = autoMapperDao.split(datas);
        else
            splitList = autoMapperDao.split(datas,batchSize);
        for (List list : splitList)
        {
            shardSqlSessionTemplate.insert(statement,list,shard);
        }


    }




    //导出的查询shard
    private Shard getShard(ITaskParam taskParam)
    {
        if (taskParam.getSchemaId() == null || taskParam.getSchemaId().trim().length() == 0)
            return shardSqlSessionTemplate.getShardManager().getDefaultShard();
        else
            return shardSqlSessionTemplate.getShardManager().getShard(taskParam.getSchemaId());
    }

    //对应的shard
    private Shard getMatchShard(ShardManager shardManager,String shardId)
    {
        return shardManager.getShard(shardId);
    }
    private Map getBatchDef(ITaskParam taskParam)
    {
        String BATCH_TABLE_ID = taskParam.getStaticParam();
        CommTableObj commTableObj = new CommTableObj();
        commTableObj.setTableName("BATCH_TABLE_DEF");
        commTableObj.setCondition("BATCH_TABLE_ID", BATCH_TABLE_ID);
        Map tableDef = autoMapperDao.selectOne(commTableObj);
        if (tableDef == null)
        {
            throw new GalaxyException("同步参数未定义<" + BATCH_TABLE_ID + ">");
        }
        return tableDef;
    }

}
