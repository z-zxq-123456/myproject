package com.dcits.orion.batch.service;

import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.batch.api.IBatchService;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ZkTools;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.bean.TaskParam;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.batch.common.dao.BatchDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/11/20.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CoreBatchService implements IBatchService
{
    @Resource
    BatchDao batchDao;

    @Resource
    ZkTools zkTools;

    @Resource
    AutoMapperDao autoMapperDao;
    public  void saveBatch(String batchClass,List nodes, List lines)
    {
        batchDao.deleteJobs(batchClass);
        batchDao.insertNodes(nodes);
        batchDao.insertLines(lines);

    }
    public JSONObject getNodesAndLines(String batchClass)
    {
        List<Map> lines   = batchDao.getLines(batchClass);
        List<Map> jobPos = batchDao.getJobPos(batchClass);

        return getFlowByNodesAndlines(batchClass,jobPos,lines);
    }

    public JSONObject getFlowByNodesAndlines(String batchClass,List<Map> nodes,List<Map> lines)
    {
        JSONObject retJson = new JSONObject();

        Map <String,Map>nodeMap = BatchUtils.getMapByList(nodes,"JOB_ID");
        for(Map node:nodeMap.values())
        {
            if(node.containsKey("TOP_POS")&&node.get("TOP_POS")!=null)
                node.put("top",node.get("TOP_POS"));
            else
                node.put("top",10);
            if(node.containsKey("LEFT_POS")&&node.get("LEFT_POS")!=null)
                node.put("left",node.get("LEFT_POS"));
            else
                node.put("left",10);
            if(node.containsKey("WIDTH")&&node.get("WIDTH")!=null)
                node.put("width",node.get("WIDTH"));
            else  node.put("width",100);
            if (node.containsKey("HEIGHT")&&node.get("HEIGHT")!=null)
                node.put("height",node.get("HEIGHT"));
            node.put("height",24);

            node.put("name",node.get("JOB_ID"));
            node.put("title",node.get("JOB_DESC"));
            if(node.containsKey("JOB_STATUS"))
            {
                if(node.containsKey("FAIL_CNT")&& node.get("FAIL_CNT")!=null)
                {
                    long FAIL_CNT = BatchUtils.parseLong(node.get("FAIL_CNT"));// + BatchUtils.parseLong(node.get("CHILD_FAIL_CNT"));
                    long SPLIT_FAIL_CNT = BatchUtils.parseLong(node.get("SPLIT_FAIL_CNT"));
                    if(FAIL_CNT > 0  )
                    {
                        String percent = (String)node.get("PERCENT");
                        if(percent == null || percent.trim().length() ==0)
                        {
                            if(node.containsKey("FINISH_CNT")&&node.get("FINISH_CNT")!=null&&node.containsKey("SPLIT_CNT")&&node.get("SPLIT_CNT")!=null)
                            {
                                percent = node.get("FINISH_CNT")+"/"+FAIL_CNT+"/"+node.get("SPLIT_CNT");
                            }
                        }
                        node.put("name",node.get("JOB_ID")+ " " + percent);
                        node.put("type", "fail");
                    }
                    else if(SPLIT_FAIL_CNT ==1)
                    {
                        node.put("type", "stdjob");
                    }
                    else
                    {
                        String JOB_STATUS = (String)node.get("JOB_STATUS");
                        if ("N".equals(JOB_STATUS))
                        {
                            node.put("type", "standby");
                        }
                        else if ("P".equals(JOB_STATUS)||"K".equals(JOB_STATUS))
                        {
                            String percent = (String)node.get("PERCENT");
                            if(percent == null || percent.trim().length() ==0)
                            {
                                if(node.containsKey("FINISH_CNT")&&node.get("FINISH_CNT")!=null&&node.containsKey("SPLIT_CNT")&&node.get("SPLIT_CNT")!=null)
                                {
                                    if(node.get("SPLIT_CNT").toString().equals("999999999"))
                                        percent = node.get("FINISH_CNT")+"/?";
                                    else
                                        percent = node.get("FINISH_CNT")+"/"+node.get("SPLIT_CNT");
                                }
                            }
                            node.put("name",node.get("JOB_ID")+ " " + percent);
                            node.put("type", "run");
                        }
                        else if("S".equals(JOB_STATUS)||"A".equals(JOB_STATUS)||"M".equals(JOB_STATUS))
                        {
                            node.put("type", "finish");
                        }
                    }
                }

            }
            else
            {
                if("GP".equals(node.get("JOB_TYPE"))){
                    node.put("type", "complex");
                }else{
                    node.put("type", "stdjob");
                }
            }
            node.put("alt","true");
            node.put("JOB_ID",node.get("JOB_ID"));
        }

        JSONObject nodesJson = new JSONObject();
        nodesJson.putAll(nodeMap);
        retJson.put("nodes", nodesJson);


        JSONObject ls = new JSONObject();
        int lineIndex = 0;
        for(Map line : lines)
        {
            JSONObject jDetail = new JSONObject();
            jDetail.put("from", line.get("PREDECESSOR"));
            jDetail.put("name","");
            jDetail.put("to", line.get("DESCENDENT"));
            jDetail.put("DEPENDENCY_TYPE", line.get("DEPENDENCY_TYPE"));
            jDetail.put("type",line.get("LINE_TYPE")==null?"sl":line.get("LINE_TYPE"));
            jDetail.put("M",line.get("M_VALUE")==null?null:line.get("M_VALUE"));
            jDetail.put("al","true");
            ls.put("LineId"+lineIndex, jDetail);
            lineIndex++;
        }
        retJson.put("lines", ls);
        retJson.put("title", batchClass);
        retJson.put("flowid", batchClass);
        retJson.put("dtpFlag", "N");
        retJson.put("timeOut", "-1");
        retJson.put("state", "1");
        retJson.put("initNum", ""+(nodes.size()+lines.size()));
        return retJson;

    }


    public JSONObject getJob(String JOB_ID)
    {
        JSONObject retJson = new JSONObject();
        Map jobMap = batchDao.getStdJob(JOB_ID);
        Map splitMap = batchDao.getJobSplitParam(JOB_ID);
        if(jobMap!=null)
            retJson.putAll(jobMap);
        if(splitMap!=null)
            retJson.putAll(splitMap);
        return retJson;
    }
    public void saveJob(JSONObject job)
    {
        Map jobMap  = new HashMap();
        jobMap.putAll(job);
        batchDao.saveJob(jobMap);
    }
    public void saveNode(JSONObject job)
    {
        Map jobMap  = new HashMap();
        jobMap.putAll(job);
        batchDao.saveNode(jobMap);
    }
    public void saveLine(JSONObject line)
    {
        Map lineMap = new HashMap();
        lineMap.putAll(line);
        batchDao.saveLine(line);
    }

    public void deleteJob(JSONObject job)
    {
        Map jobMap  = new HashMap();
        jobMap.putAll(job);
        batchDao.deleteJob(jobMap);
    }
    public void deleteLine(JSONObject line)
    {
        Map lineMap = new HashMap();
        lineMap.putAll(line);
        batchDao.deleteLine(line);
    }
    public List getBatchList()
    {
        return batchDao.getBatchList();
    }


    public JSONObject getBatchStatus(String batchClass)
    {

        Map batchStatus = batchDao.getBatchStatus(batchClass);
        if(batchStatus == null)
        {
            batchStatus = new HashMap();
            batchStatus.put("BATCH_STATUS","C");
        }
        List<Map> runjobs = batchDao.getRunjobs(batchClass);
        List<Map> runJobLines   = batchDao.getRunJobLines(batchClass);
        JSONObject jsonObject = getFlowByNodesAndlines(batchClass,runjobs,runJobLines);
        jsonObject.put("batchStatus",batchStatus);
        return jsonObject;
    }
    public List getStatusList(String RUN_DATE){
        return  batchDao.getStatusList(RUN_DATE);
    }
    public List getJobList(String RUN_DATE,String BATCH_CLASS){
        return batchDao.getJobList(RUN_DATE, BATCH_CLASS);
    }
    public List getBatchTimberList()
    {
        return batchDao.getBatchTimerList();
    }

    @Transactional
    public void saveTimer(JSONObject timer){
        CommTableObj insert = new CommTableObj("BATCH_TIMER_DEF");
        insert.setFieldValue(timer);

        TaskParam taskParam = new TaskParam();
        taskParam.setJobId((String) timer.get("TIMER_ID"));
        taskParam.setGxClassName((String) timer.get("GX_CLASS_NAME"));
        taskParam.setGxMethod((String) timer.get("GX_METHOD"));
        taskParam.setSystemId((String) timer.get("SYSTEM_ID"));
        taskParam.setStaticParam((String) timer.get("STATIC_PARAM"));
        taskParam.setTaskId((String) timer.get("TIMER_ID"));
        taskParam.setJobType("TIMER");
        String  cronExpression = (String)timer.get("CRON_EXPRESSION");
        if("Y".equals((String)timer.get("STATUS"))){
            zkTools.rpcStartTimer(taskParam, cronExpression);
        }
        autoMapperDao.insert(insert);

    }
    @Transactional
    public void editTimer(JSONObject timer){
        CommTableObj update = new CommTableObj("BATCH_TIMER_DEF");
        update.setCondition("TIMER_ID",timer.getString("TIMER_ID"));
        update.setFieldValue(timer);

        TaskParam taskParam = new TaskParam();
        taskParam.setJobId((String) timer.get("TIMER_ID"));
        taskParam.setGxClassName((String) timer.get("GX_CLASS_NAME"));
        taskParam.setGxMethod((String) timer.get("GX_METHOD"));
        taskParam.setSystemId((String) timer.get("SYSTEM_ID"));
        taskParam.setStaticParam((String) timer.get("STATIC_PARAM"));
        taskParam.setTaskId((String) timer.get("TIMER_ID"));
        taskParam.setSendGroup(zkTools.getGroup());
        taskParam.setJobType("TIMER");
        String  cronExpression = (String)timer.get("CRON_EXPRESSION");

        zkTools.rpcKillTimer(taskParam);
        if("Y".equals((String)timer.get("STATUS"))){
            zkTools.rpcStartTimer(taskParam, cronExpression);
        }
        autoMapperDao.update(update);
    }
    @Transactional
    public void deleteTimer(JSONObject timer)
    {
        CommTableObj delete = new CommTableObj("BATCH_TIMER_DEF");
        delete.setCondition("TIMER_ID", timer.getString("TIMER_ID"));
        TaskParam taskParam = new TaskParam();
        taskParam.setJobId((String) timer.get("TIMER_ID"));
        taskParam.setSystemId((String) timer.get("SYSTEM_ID"));
        zkTools.rpcKillTimer(taskParam);
        autoMapperDao.delete(delete);
    }

    @Override
    public List getBhDataSynList() {
        CommTableObj select  = new CommTableObj("BATCH_DATA_SYNC_DEF");

        return autoMapperDao.selectList(select);
    }

    @Override
    public void saveDataSyn(JSONObject json) {

        CommTableObj insert  = new CommTableObj("BATCH_DATA_SYNC_DEF");
        insert.setFieldValue(json);
        autoMapperDao.insert(insert);
    }

    @Override
    public void updateDataSyn(JSONObject json) {
        CommTableObj update = new CommTableObj("BATCH_DATA_SYNC_DEF");
        update.setCondition("SYNC_ID",json.getString("SYNC_ID"));
        update.setFieldValue(json);
        autoMapperDao.update(update);

    }

    @Override
    public void deleteDataSyn(JSONObject json) {
        CommTableObj delete = new CommTableObj("BATCH_DATA_SYNC_DEF");
        delete.setCondition("SYNC_ID", json.getString("SYNC_ID"));
        autoMapperDao.delete(delete);
    }

    @Override
    public JSONObject getJobGroup(String batchClass, String jobGroupId) {
        List<Map> lines   = batchDao.getJobGroupLine(batchClass,jobGroupId);
        List<Map> jobPos = batchDao.getJobGroupPos(batchClass, jobGroupId);
        return getFlowByNodesAndlines(batchClass,jobPos,lines);
    }

    public JSONObject getJobGroupRun(String batchClass, String jobGroupId) {
        List<Map> lines   = batchDao.getJobGroupLine(batchClass,jobGroupId);
        List<Map> jobPos = batchDao.getJobGroupRun(batchClass, jobGroupId);
        return getFlowByNodesAndlines(batchClass,jobPos,lines);
    }

    @Override
    public void executeTimer(JSONObject timer)
    {
        String TIMER_ID = timer.getString("TIMER_ID");
        CommTableObj timerQuery = new CommTableObj("BATCH_TIMER_DEF");
        timerQuery.setCondition("TIMER_ID",TIMER_ID);
        Map timerDef = autoMapperDao.selectOne(timerQuery);
        ITaskParam taskParam = new TaskParam();
        taskParam.setJobId((String) timerDef.get("TIMER_ID"));
        taskParam.setGxClassName((String) timerDef.get("GX_CLASS_NAME"));
        taskParam.setGxMethod((String) timerDef.get("GX_METHOD"));
        taskParam.setSystemId((String) timerDef.get("SYSTEM_ID"));
        taskParam.setStaticParam((String) timerDef.get("STATIC_PARAM"));
        taskParam.setTaskId((String) timerDef.get("TIMER_ID"));
        taskParam.setSendGroup(zkTools.getGroup());
        taskParam.setJobType("TIMER");
        zkTools.rpcTimerRunTask(taskParam);
    }
}
