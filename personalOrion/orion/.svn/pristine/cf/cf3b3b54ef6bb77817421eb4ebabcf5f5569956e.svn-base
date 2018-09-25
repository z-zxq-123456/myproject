package com.dcits.orion.batch.task.service;


import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.file.FilePositions;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by lixbb on 2015/11/5.
 */
@Repository
public class SplitJob {

    private static Logger logger = LoggerFactory.getLogger(SplitJob.class);

    public void split(ITaskParam bean)throws Exception
    {
        SplitJob splitJob = null;
        try
        {
             splitJob = (SplitJob) SpringApplicationContext.getContext().getBean("localSplitJob");
        }
        catch (Exception e)
        {
            if (logger.isDebugEnabled())
                logger.debug("未找到本地分段方法");
        }
        if (splitJob != null)
        {
            splitJob.split(bean);
            return;
        }
        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                .setCheck(true)
                .setTimeout(60000)
                .setGroup(bean.getSendGroup());
        IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
        logger.debug(bean.getSplitJobId() + "分段Start!");

        List<Map> tasks = new ArrayList<Map> ();
        String splitJobId = bean.getSplitJobId();
        Map splitParam = iBatchManage.getJobSplitParam(splitJobId);
        Map job = iBatchManage.getStdJob(splitJobId);
        if(splitParam == null)
            splitParam = new HashMap();
        splitParam.putAll(job);

        ShardManager shardManager;
        if ("EX".equals(bean.getJobType())||"PI".equals(bean.getJobType()))
        {
            shardManager = (ShardManager)SpringApplicationContext.getContext().getBean("shardManager");
        }
        else
        {
            shardManager = bean.getShardManager();
        }
        String BY_SCHEMA = (String)job.get("BY_SCHEMA");
        if(BY_SCHEMA.equals("Y") || BY_SCHEMA.equals("A"))
        {
            tableSplit(job,splitParam,bean,shardManager,tasks);
        }
        else
        {
            String SPLIT_CLASS = (String)splitParam.get("SPLIT_CLASS");
            if (SPLIT_CLASS.equals("T"))
            {
                tableSplit(job,splitParam,bean,shardManager,tasks);
            }
            else if (SPLIT_CLASS.equals("F"))
            {
                FileSplit(splitParam,bean,tasks);
            }
        }
        boolean ret = false;
        ret  = iBatchManage.saveSplitResult(splitJobId,tasks);
        if (ret == false)
        {
            throw new GalaxyException("更新分段信息失败！");
        }
        logger.debug(bean.getSplitJobId() + "分段 End!");
    }
    public void tableSplit(Map job,Map splitParam,ITaskParam bean,ShardManager shardManager,List<Map> tasks)
    {

        String BY_SCHEMA = (String)job.get("BY_SCHEMA");
        List<Shard> shards;
        if (BY_SCHEMA.equals("Y"))
        {
            shards = shardManager.getShardsWithOutDefault();
        }
        else if (BY_SCHEMA.equals("A"))
        {
            shards = shardManager.getAllShard();
        }
        else
        {
            shards = new ArrayList<>();
            shards.add(shardManager.getDefaultShard());
        }

        Map param = BatchUtils.getSystemParam();
        if(splitParam.get("MAX_PER_SPLIT") != null && splitParam.get("MAX_PER_SPLIT").toString().trim().length()>0)
        {
            int maxCount = BatchUtils.parseInt(splitParam.get("MAX_PER_SPLIT"));
            param.put("maxPerCount", maxCount);
        }
        param.put("STATIC_PARAM",splitParam.get("STATIC_PARAM"));
        param.putAll(splitParam);
        if(job.get("IS_SPLIT").equals("Y"))
        {
            List<ShardResultSet> shardResultSets = ShardManageUtils.getShardSqlSessionTemplate(shardManager).selectList(splitParam.get("NAMESPACE")+"."+splitParam.get("SQL_ID"),param,shards);
            for (ShardResultSet shardResultSet:shardResultSets)
            {
                List<Map> results = shardResultSet.getResult();
                if ("K".equals(splitParam.get("SPLIT_TYPE")))//按关键字分段
                {
                    int i = 1;
                    if (results != null) {
                        for (Map result : results) {
                            if (result != null) {
                                Map task = new HashMap();
                                task.put("TASK_ID", BatchUtils.getTaskID());
                                task.put("JOB_ID", bean.getSplitJobId());
                                task.put("TASK_STATUS", "N");
                                task.put("SEQ_NO", i++);
                                task.put("START_ROW", result.get("START_ROW"));
                                task.put("END_ROW", result.get("END_ROW"));
                                task.put("START_KEY", result.get("START_KEY"));
                                task.put("END_KEY", result.get("END_KEY"));
                                task.put("ROW_COUNT",result.get("ROW_COUNT"));
                                task.put("SYSTEM_ID", bean.getSystemId());
                                task.put("SCHEMA_ID", shardResultSet.getShardId());
                                tasks.add(task);
                            }

                        }
                    }
                }
                else//分页方式分段
                {
                    Map result = results.get(0);
                    long rowCount = Long.parseLong(result.get("ROW_COUNT").toString());
                    doSplit(rowCount,splitParam,bean,shardResultSet.getShardId(),tasks);
                }
            }
        }
        else
        {
            for(Shard shard:shards)
            {
                Map task = new HashMap();
                task.put("TASK_ID",BatchUtils.getTaskID());
                task.put("JOB_ID",bean.getSplitJobId());
                task.put("TASK_STATUS","N");
                task.put("SYSTEM_ID", bean.getSystemId());
                task.put("SEQ_NO",1);
                task.put("SCHEMA_ID",shard.getId());
                tasks.add(task);
            }
        }
    }
    public void FileSplit(Map splitParam,ITaskParam bean,List<Map> tasks)
    {
        int maxPerCount = 5000;
        try
        {
            maxPerCount = BatchUtils.parseInt(splitParam.get("MAX_PER_SPLIT"));

        }
        catch (Exception e)
        {

        }
        String filePath = getFileName((String)splitParam.get("FILE_PATH"))+"/"+getFileName((String) splitParam.get("FILE_NAME"));
        TwoTuple<Long, List<FilePositions>> ret = FileUtils.getRowCount(filePath, maxPerCount);
        int i = 0;
        for (FilePositions filePositions:ret.second)
        {
            if (filePositions.getNum() != 0) {
                Map task = new HashMap();
                task.put("TASK_ID", BatchUtils.getTaskID());
                task.put("JOB_ID", bean.getSplitJobId());
                task.put("TASK_STATUS", "N");
                task.put("SEQ_NO", i + 1);
                task.put("FILE_OFFSET", filePositions.getPos());
                task.put("FILE_LIMIT", filePositions.getNum());
                task.put("SYSTEM_ID", bean.getSystemId());
                tasks.add(task);
                i++;
            }
        }
    }
    public long doSplit(long rowCount, Map splitParam,ITaskParam bean,String schemaId,List<Map> tasks)
    {

        long splitCnt = 1;
        String SPLIT_TYPE  = (String)splitParam.get("SPLIT_TYPE");
        if(SPLIT_TYPE.equals("N"))
        {
            splitCnt = BatchUtils.getNodeCount(bean.getSystemId());
        }
        else if(SPLIT_TYPE.equals("S"))
        {
            splitCnt = BatchUtils.parseInt(splitParam.get("SPLIT_CNT"));
        }
        else if (SPLIT_TYPE.equals("M"))
        {
            int maxCount = BatchUtils.parseInt(splitParam.get("MAX_PER_SPLIT"));
            splitCnt = rowCount%maxCount==0? rowCount/maxCount:rowCount/maxCount + 1;
        }
        else  if (SPLIT_TYPE.equals("B"))
        {
            splitCnt = BatchUtils.parseInt(splitParam.get("SPLIT_CNT"));
            int maxCount = BatchUtils.parseInt(splitParam.get("MAX_PER_SPLIT"));
            long tmpsplitCnt =  rowCount%maxCount==0? rowCount/maxCount:rowCount/maxCount + 1;
            if(tmpsplitCnt > splitCnt)
            {
                splitCnt = tmpsplitCnt;
            }
        }
        if(rowCount < splitCnt)
            splitCnt = rowCount;
        if (splitCnt == 0)
        {
            return splitCnt;
        }
        long minPerCount = rowCount/splitCnt;
        long maxPerCount = rowCount%splitCnt==0?rowCount/splitCnt:rowCount/splitCnt+1;
        long remainRow = rowCount;
        long remainSplitCnt = splitCnt;
        long perCount = 0;
        long startRow = 0;
        long endRow = rowCount-1;
        for(int i = 0; i< splitCnt; i++)
        {
            if(remainRow%remainSplitCnt==0 && remainRow/remainSplitCnt==maxPerCount)
            {
                perCount = maxPerCount;
            }
            else
            {
                perCount = minPerCount;
            }
            remainSplitCnt--;
            remainRow = remainRow - perCount;
            endRow = startRow + perCount - 1;
            Map task = new HashMap();
            task.put("TASK_ID",BatchUtils.getTaskID());
            task.put("JOB_ID",bean.getSplitJobId());
            task.put("TASK_STATUS","N");
            task.put("SEQ_NO",i+1);
            task.put("START_ROW",startRow);
            task.put("END_ROW",endRow);
            startRow = endRow + 1;
            task.put("SYSTEM_ID", bean.getSystemId());
            if(schemaId!=null)
            {
                task.put("SCHEMA_ID",schemaId);
            }
            tasks.add(task);
        }
        return splitCnt;
    }

    public String getFileName(String fileName)
    {
        if (fileName.indexOf("<") == -1 && fileName.indexOf(">")==-1)
            return fileName;
        else {

            IBatchLocal batchLocal = (IBatchLocal) SpringApplicationContext.getContext().getBean("batchLocal");
            String[] fileNames = StringUtils.split(fileName, '<');
            String head = fileNames[0];
            String[] dateAndTail = StringUtils.split(fileNames[1], '>');
            String dateAndFormat = dateAndTail[0];
            String tail = "";
            if (dateAndTail.length == 2) {
                tail = dateAndTail[1];
            }
            String strDate = "";
            if (dateAndFormat.indexOf("?") == -1)
                strDate = (String)batchLocal.getSystemParam().get(dateAndFormat);
            else
            {
                String[] dateAndFormats =  StringUtils.split(dateAndFormat, '?');
                Object dateToFormat = batchLocal.getSystemParam().get(dateAndFormats[0]);
                Date date = null;
                if (dateToFormat instanceof Date)
                {
                    date = (Date)dateToFormat;
                }
                else
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                    try {
                        date = format.parse((String)dateToFormat);
                    } catch (ParseException e) {
                        throw new GalaxyException("日期格式不正确");
                    }
                }
                SimpleDateFormat format = new SimpleDateFormat(dateAndFormats[1]);
                strDate = format.format(date);
            }
            return head + strDate + tail;
        }
    }

}
