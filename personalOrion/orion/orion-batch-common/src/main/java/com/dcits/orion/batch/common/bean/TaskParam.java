package com.dcits.orion.batch.common.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ShardManageUtils;
import com.dcits.orion.batch.common.dao.AutoMapperDao;

/**
 * Created by lixbb on 2015/11/5.
 */
public class TaskParam extends AbstractBean implements ITaskParam {

    private static Logger logger = LoggerFactory.getLogger(TaskParam.class);

    private static final long serialVersionUID = -7194116702070337873L;
    /**
     * This field is TASK_PARAM.TASK_ID
     */
    private String taskId;

    /**
     * This field is TASK_PARAM.JOB_ID
     */
    private String jobId;

    /**
     * This field is TASK_PARAM.JOB_NAME
     */
    private String jobName;

    /**
     * This field is TASK_PARAM.BATCH_CLASS
     */
    private String batchClass;

    /**
     * This field is TASK_PARAM.JOB_TYPE
     */
    private String jobType;

    /**
     * This field is TASK_PARAM.MODULE_ID
     */
    private String moduleId;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * This field is TASK_PARAM.SYSTEM_ID
     */
    private String systemId;

    /**
     * This field is TASK_PARAM.IS_SKIP
     */
    private String isSkip;

    /**
     * This field is TASK_PARAM.GX_CLASS_NAME
     */
    private String gxClassName;

    /**
     * This field is TASK_PARAM.GX_METHOD
     */
    private String gxMethod;

    /**
     * This field is TASK_PARAM.STATIC_PARAM
     */
    private String staticParam;

    /**
     * This field is TASK_PARAM.START_ROW
     */
    private long startRow;

    /**
     * This field is TASK_PARAM.END_ROW
     */
    private long endRow;

    /**
     * This field is TASK_PARAM.SCHEMA_ID
     */
    private String schemaId;

    /**
     * This field is TASK_PARAM.NODE_IP
     */
    private String nodeIp;

    /**
     * This field is TASK_PARAM.SPLIT_JOB_ID
     */
    private String splitJobId;

    private String isSplit;

    private String sendGroup;

    private String startKey;

    private String endKey;

    private String dtpFlag;

    private int rowCount;

    private String recFlag;
    @Override
    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag;
    }

    @Override
    public String getDtpFlag() {
        return dtpFlag;
    }

    @Override
    public void setRecFlag(String recFlag) {
        this.recFlag = recFlag;
    }

    @Override
    public String getRecFlag() {
        return recFlag;
    }


    public int getMaxPerSplit() {
        return maxPerSplit;
    }

    public void setMaxPerSplit(int maxPerSplit) {
        this.maxPerSplit = maxPerSplit;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public void setRowCount(int rowCount) {
       this.rowCount = rowCount;
    }

    private int maxPerSplit;

    @Override
    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    private int batchSize;


    @Override
    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    private int seqNo;


    public long getFileOffset() {
        return fileOffset;
    }

    public void setFileOffset(long fileOffset) {
        this.fileOffset = fileOffset;
    }

    public int getFileLimit() {
        return fileLimit;
    }

    public void setFileLimit(int fileLimit) {
        this.fileLimit = fileLimit;
    }

    @Override
    public void setIsSplit(String isSplit) {
        this.isSplit = isSplit;
    }

    @Override
    public void setSendGroup(String group) {
        this.sendGroup = group;

    }

    @Override
    public String getSendGroup() {
        return sendGroup;
    }

    @Override
    public String getIsSplit() {
        return isSplit;
    }

    @Override
    public ShardManager getShardManager() {

        ShardManager shardManager;
        if(shardManagerId == null || shardManagerId.trim().length() == 0)
            shardManager = (ShardManager)SpringApplicationContext.getContext().getBean("shardManager");
        else
            shardManager = (ShardManager)SpringApplicationContext.getContext().getBean(shardManagerId);
        return shardManager;
    }

    @Override
    public ShardSqlSessionTemplate getShardSqlSessionTemplate() {
       return ShardManageUtils.getShardSqlSessionTemplate(getShardManager());
    }

    @Override
    public void updateRunMsg(String runMsg) {
        if (logger.isDebugEnabled())
        {
            try
            {
                String msg = runMsg;
                if (runMsg.length() > 200)
                    msg = runMsg.substring(0,199);
                ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                        .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN)
                        .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                        .setCheck(true)
                        .setGroup(getSendGroup());
                IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
                iBatchManage.updateRunMsg(this.taskId,msg);
            }
            catch (Exception e)
            {
                logger.debug("更新task run Msg 失败");
                logger.debug(ExceptionUtils.getStackTrace(e));
            }


        }
    }

    /*
        文件的偏移量
         */
    private long fileOffset;

    /*
    行数
     */
    private int fileLimit;


    public String getShardManagerId() {
        return shardManagerId;
    }

    public void setShardManagerId(String shardManagerId) {
        this.shardManagerId = shardManagerId;
    }

    private String shardManagerId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBatchClass() {
        return batchClass;
    }

    public void setBatchClass(String batchClass) {
        this.batchClass = batchClass;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(String isSkip) {
        this.isSkip = isSkip;
    }

    public String getGxClassName() {
        return gxClassName;
    }

    public void setGxClassName(String gxClassName) {
        this.gxClassName = gxClassName;
    }

    public String getGxMethod() {
        return gxMethod;
    }

    public void setGxMethod(String gxMethod) {
        this.gxMethod = gxMethod;
    }

    public String getStaticParam() {
        return staticParam;
    }

    public void setStaticParam(String staticParam) {
        this.staticParam = staticParam;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getSplitJobId() {
        return splitJobId;
    }

    public void setSplitJobId(String splitJobId) {
        this.splitJobId = splitJobId;
    }

    public int getOffset()
    {
        return (int)startRow;
    }
    public int getLimit()
    {
        return (int)(endRow - startRow  + 1);
    }

    public RowArgs getRowArgs()
    {
        RowArgs rowArgs = new RowArgs();
        rowArgs.setOffset(this.getOffset());
        rowArgs.setLimit(this.getLimit());
        return rowArgs;
    }

    @Override
    public List<RowArgs> getRowArgsList() {
        if (batchSize <= 0)
        {
            AutoMapperDao autoMapperDao = (AutoMapperDao)SpringApplicationContext.getContext().getBean("autoMapperDao");
            batchSize = autoMapperDao.getBatchSize();
        }
        List<TwoTuple<Long,Long>> p = BatchUtils.getSplitList(startRow,endRow,batchSize);
        List ret = new ArrayList();
        for (TwoTuple<Long,Long> twoTuple : p)
        {
            RowArgs rowArgs = new RowArgs((int)(twoTuple.first.longValue()),(int)twoTuple.second.longValue());
            ret.add(rowArgs);
        }
        return ret;
    }

    public Shard getShard()
    {
        ShardManager shardManager = getShardManager();
        if (schemaId == null || schemaId.trim().length() == 0)
        {
            schemaId = shardManager.getDefaultShardId();
        }
        Shard shard = shardManager.getShard(schemaId);
        if (shard == null)
            shard = shardManager.getDefaultShard();
        return shard;
    }

    public String getStartKey()
    {
        return startKey;
    }

    public void setStartKey(String startKey)
    {
        this.startKey = startKey;
    }

    public String getEndKey()
    {
        return endKey;
    }

    public void setEndKey(String endKey)
    {
        this.endKey = endKey;
    }

    @Override
    public List<TwoTuple<String, String>> split(List<String> list) {
        if (list == null)
            return null;
        else
        {
            if (list.size() == 0)
                return new ArrayList<>();
            int batchSize = 1000;
            if (this.batchSize > 0)
                batchSize = this.batchSize;
            int size = list.size();
            List retList = new ArrayList();
            for(int i = 0; i < size;i=i+batchSize)
            {
                int end = i+batchSize;
                if (size < i+batchSize)
                    end = size;
                TwoTuple<String,String> ret = new TwoTuple<>(list.get(i),list.get(end-1));
                retList.add(ret);
            }
            return retList;
        }
    }

    public String toString() {
        return "";
    }
}
