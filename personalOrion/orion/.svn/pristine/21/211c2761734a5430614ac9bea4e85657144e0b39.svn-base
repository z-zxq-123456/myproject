package com.dcits.orion.batch.api;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/11/26.
 */
public interface IBatchService {

    public List getBatchList();
    public  void saveBatch(String batchClass,List nodes, List lines);
    public JSONObject getNodesAndLines(String batchClass);
    public JSONObject getJob(String JOB_ID);
    public void saveJob(JSONObject job);
    public void saveNode(JSONObject job);
    public void saveLine(JSONObject line);
    public void deleteJob(JSONObject job);
    public void deleteLine(JSONObject line);
    public JSONObject getBatchStatus(String batchClass);
    public List  getStatusList(String RUN_DATE);
    public List  getJobList(String RUN_DATE,String BATCH_CLASS);
    public List getBatchTimberList();
    public void saveTimer(JSONObject timer);
    public void editTimer(JSONObject timer);
    public void deleteTimer(JSONObject timer);

    public void executeTimer(JSONObject timer);

    public List getBhDataSynList();
    public void saveDataSyn(JSONObject timer);
    public void updateDataSyn(JSONObject timer);
    public void deleteDataSyn(JSONObject timer);
    public JSONObject getJobGroup(String batch_class,String jobId);
    JSONObject getJobGroupRun(String batchClass, String jobGroupId);
}
