package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/5/18.
 */
@Repository
public class BatchLoadDataByKey {
    @Resource
    AutoMapperDao autoMapperDao;
    @Resource
    IBatchLocal batchLocal;
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
            batchSize = BatchUtils.parseInt(tableDef.get("BATCH_SIZE"));
        }
        catch (Exception e)
        {

        }
        String SELECT_NAMESPACE = tableDef.get("SELECT_NAMESPACE");
        String SELECT_SQL_ID = tableDef.get("SELECT_SQL_ID");
        List datas = new ArrayList();
        if (SELECT_NAMESPACE != null &&
                SELECT_NAMESPACE.trim().length() > 0 &&
                SELECT_SQL_ID != null &&
                SELECT_SQL_ID.trim().length() > 0 )
        {
            Map param = new HashMap();
            param.putAll(batchLocal.getSystemParam());
            param.put("START_ROW", ""+taskParam.getStartRow());
            param.put("END_ROW", "" + taskParam.getEndRow());
            param.put("START_KEY", taskParam.getStartKey());
            param.put("END_KEY", taskParam.getEndKey());
            if ("IM".equals(flag))
                datas = taskParam.getShardSqlSessionTemplate().selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID,param, taskParam.getShard());
            else
                datas = shardSqlSessionTemplate.selectList(SELECT_NAMESPACE + "." + SELECT_SQL_ID, param,getShard(taskParam));
        }
        taskParam.updateRunMsg("数据查询完成");

        if (datas == null || datas.size() == 0)
            throw new GalaxyException("查询记录数为0");
       if (taskParam.getRowCount() > 0 && taskParam.getRowCount() != datas.size())
       {
           throw new GalaxyException("查询记录数与分段记录数不符，请检查！");
       }

        int insertCount = 0;
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
            for (List list : splitList) {
                if ("IM".equals(flag))
                    insertCount +=  shardSqlSessionTemplate.insertAddBatch(INSERT_NAMESPACE, INSERT_SQL_ID, list);
                else
                    insertCount += taskParam.getShardSqlSessionTemplate().insertAddBatch(INSERT_NAMESPACE + "." + INSERT_SQL_ID, list);

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
        //if (datas.size() != insertCount)
            //throw new GalaxyException("查询记录数与插入记录数不符，请检查！");
        taskParam.updateRunMsg("数据插入完成");
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
    //导出的查询shard
    private Shard getShard(ITaskParam taskParam)
    {
        if (taskParam.getSchemaId() == null || taskParam.getSchemaId().trim().length() == 0)
            return shardSqlSessionTemplate.getShardManager().getDefaultShard();
        else
            return shardSqlSessionTemplate.getShardManager().getShard(taskParam.getSchemaId());
    }
}
