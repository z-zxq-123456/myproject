package com.dcits.orion.batch.api.bean;

import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

import java.util.List;

/**
 * Created by lixbb on 2015/11/5.
 */
public interface ITaskParam {

    public String getTaskId()  ;

    public void setTaskId(String taskId)  ;

    public String getJobId()  ;

    public void setJobId(String jobId)  ;

    public String getJobName()  ;

    public void setJobName(String jobName)  ;

    public String getBatchClass() ;

    public void setBatchClass(String batchClass) ;

    public String getJobType()   ;

    public void setJobType(String jobType) ;

    public String getModuleId()  ;

    public void setModuleId(String moduleId) ;

    public String getIsSkip()  ;

    public void setIsSkip(String isSkip) ;

    public String getGxClassName()  ;

    public void setGxClassName(String gxClassName)  ;

    public String getGxMethod() ;

    public void setGxMethod(String gxMethod)  ;

    public String getStaticParam()  ;

    public void setStaticParam(String staticParam) ;

    public long getStartRow()  ;

    public void setStartRow(long startRow)  ;

    public long getEndRow()  ;

    public void setEndRow(long endRow)  ;

    public String getSchemaId()  ;

    public void setSchemaId(String schemaId)  ;

    public String getNodeIp()  ;

    public void setNodeIp(String nodeIp) ;

    public String getSplitJobId() ;

    public void setSplitJobId(String splitJobId);

    public String getSystemId();

    public void setSystemId(String systemId);

    public int getOffset();
    public int getLimit();
    public RowArgs getRowArgs();

    public List<RowArgs> getRowArgsList();

    public Shard getShard();

    public String getShardManagerId();

    public void setShardManagerId(String shardManagerId);

    public long getFileOffset();

    public void setFileOffset(long fileOffset) ;

    public int getFileLimit();

    public void setFileLimit(int fileLimit);

    public void setIsSplit(String isSplit);

    public void setSendGroup(String group);

    public String getSendGroup();

    public String getIsSplit();

    public ShardManager getShardManager();

    public ShardSqlSessionTemplate getShardSqlSessionTemplate();

    public void updateRunMsg(String runMsg);

    public int getSeqNo();

    public void setSeqNo(int seqNo);

    public String getStartKey();

    public void setStartKey(String startKey);

    public String getEndKey();

    public void setEndKey(String endKey);

    public void setBatchSize(int batchSize);

    public int getBatchSize();

    public List<TwoTuple<String,String>> split(List<String> list);

    public int getMaxPerSplit();

    public void setMaxPerSplit(int maxPerSplit);

    public int getRowCount();

    public void setRowCount(int rowCount);

    public void setDtpFlag(String dtpFlag);

    public String getDtpFlag();

    public void setRecFlag(String recFlag);

    public String getRecFlag();

}
