/**
 * <p>Title: WorkerManager.java</p>
 * <p>Description: 管理整个工作流的调度工作，生成Job执行计划，并按计划执行Job，<br>
 * 对于执行完毕的Job存放在HistroyJob容器中。<br>
 * 工作流调起后，不允许再次执行，除非当前的整个工作流处理结束。 支持并发多worker执行</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.workflow;

import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.common.UniqId;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.JobRunType;
import com.dcits.orion.schedule.model.*;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * @author Tim
 * @version V1.0
 * @description 管理整个工作流的调度工作，生成Job执行计划，并按计划执行Job，<br>
 * 对于执行完毕的Job存放在HistroyJob容器中。<br>
 * 工作流调起后，不允许再次执行，除非当前的整个工作流处理结束。<br>
 * 支持并发多worker执行
 * ----------------------------------------------------------
 * modify by Tim 20140922<br>
 * 屏蔽统一执行Worker的接口<br>
 * 对外提供单独执行独立Woker接口，由调度平台调起
 * @update 2014年9月15日 下午3:32:00
 */

public class WorkerManager {

    private static Logger logger = LoggerFactory.getLogger(WorkerManager.class);

    private static WorkerManager wm;

    private JobConfiguration jc;

    private WorkFlow wf;

    private HistoryJob hjob = new HistoryJob();

    private CurrentJob cjob = new CurrentJob();

    // private List<Worker> plan = new ArrayList<Worker>();

    private Map<String, Worker> current_worker = new HashMap<String, Worker>();

    // 对外不提供总调度入口
    // private int PARALLEL_WORKER = 5;

    private int workerIndex = 0;

    private int jobIndex = 0;

    // 对外不提供总调度入口
    // private int executeWorker = 0;

    public static WorkerManager getInstance() {
        if (null == wm) {
            wm = new WorkerManager();
            wm.initialization();
        }
        return wm;
    }

    private void initialization() {
        jc = JobConfiguration.getInstance();
        wf = (WorkFlow) jc.getWorkFolw();
        // 对外不提供总调度入口
        // PARALLEL_WORKER = Integer.parseInt(wf.getParallel_worker());
    }

	/*
     * 对外不提供总调度入口 modify by Tim 20140922 private void generateExecutionPlan() {
	 * for (Worker worker : wf.getWorker()) { plan.add(worker); } workerIndex =
	 * 0; executeWorker = 0; }
	 */

    protected synchronized void addJob(String jobId, ExecutedJob job) {
        logger.debug("Job[" + jobId + "] add to CurrentJob.");
        cjob.putJob(jobId, job);
    }

    public ExecutedJob getJob(String jobId) {
        return cjob.getJob(jobId);
    }

    protected synchronized void removeJob(String jobId) {
        if (null != cjob.getJob(jobId)) {
            logger.debug("Job[" + jobId + "] add to HistoryJob.");
            hjob.putJob(jobId, cjob.getJob(jobId));
            logger.debug("Job[" + jobId + "] removed from CurrentJob.");
            cjob.removeJob(jobId);
        }
    }

    protected synchronized void addWorker(String groupId, Worker worker) {
        logger.debug("Worker[" + groupId + "] add to CurrentWorker.");
        current_worker.put(groupId, worker);
        workerIndex++;
    }

    protected synchronized void removeWorker(String groupId) {
        logger.debug("Worker[" + groupId + "] removed from CurrentWorker.");
        current_worker.remove(groupId);
        workerIndex--;
    }

    protected String getJobId() {
        return "Job_"
                + StringUtils.lfillStr(String.valueOf(jobIndex++), 4, "0")
                + "_" + UniqId.getInstance().getUniqTime() + "";
    }

    public List<JobPlan> getJobPlan(String workerName) {
        Worker w = null;
        List jobPlans = new ArrayList();
        try {
            w = this.wf.getWorker(workerName);
        } catch (JobException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        if (null != w) {
            int k = 1;
            for (List<AbstractJob> la : w.getBeginJob().getJob()) {
                int l = 1;
                for (AbstractJob ajob : la) {
                    JobPlan jp = new JobPlan();
                    jp.setIndex(la.size() > 1 ? String.valueOf(k) + "." + String.valueOf(l) : String.valueOf(k));
                    jp.setJobName(ajob.getName());
                    jp.setJobDescription(ajob.getDescription());
                    jp.setJobType(ajob.getType().toString());
                    jp.setStage(JobRunType.BEGIN.toString());
                    jobPlans.add(jp);
                    l++;
                }
                ++k;
            }
            for (List<AbstractJob> la : w.getStartJob().getJob()) {
                int l = 1;
                for (AbstractJob ajob : la) {
                    JobPlan jp = new JobPlan();
                    jp.setIndex(la.size() > 1 ? String.valueOf(k) + "." + String.valueOf(l) : String.valueOf(k));
                    jp.setJobName(ajob.getName());
                    jp.setJobDescription(ajob.getDescription());
                    jp.setJobType(ajob.getType().toString());
                    jp.setStage(JobRunType.START.toString());
                    jobPlans.add(jp);
                    l++;
                }
                ++k;
            }
            for (List<AbstractJob> la : w.getEndJob().getJob()) {
                int l = 1;
                for (AbstractJob ajob : la) {
                    JobPlan jp = new JobPlan();
                    jp.setIndex(la.size() > 1 ? String.valueOf(k) + "." + String.valueOf(l) : String.valueOf(k));
                    jp.setJobName(ajob.getName());
                    jp.setJobDescription(ajob.getDescription());
                    jp.setJobType(ajob.getType().toString());
                    jp.setStage(JobRunType.END.toString());
                    jobPlans.add(jp);
                    l++;
                }
                ++k;
            }
            for (List<AbstractJob> la : w.getErrorJob().getJob()) {
                int l = 1;
                for (AbstractJob ajob : la) {
                    JobPlan jp = new JobPlan();
                    jp.setIndex(la.size() > 1 ? String.valueOf(k) + "." + String.valueOf(l) : String.valueOf(k));
                    jp.setJobName(ajob.getName());
                    jp.setJobDescription(ajob.getDescription());
                    jp.setJobType(ajob.getType().toString());
                    jp.setStage(JobRunType.ERROR.toString());
                    jobPlans.add(jp);
                    l++;
                }
                ++k;
            }
        }
        return jobPlans;
    }

    public boolean checkWorkIsRunning(String workername) {
        boolean isRunning = false;
        for (Worker worker : this.current_worker.values()) {
            logger.debug("current_worker [" + worker.getName() + "] is running......");
            if (worker.getName().equals(workername)) {
                isRunning = true;
                break;
            }
        }
        logger.debug("[" + workername + "] isRunning [" + isRunning + "]......");
        return isRunning;
    }

    public void runWorker(String workername, String runDate, boolean noWait) throws InterruptedException {
        Worker worker = null;
        worker = wf.getWorker(workername);

        // 传入批处理日期，否则取系统日期
        if (StringUtils.isNotEmpty(runDate)) {
            try {
                DateUtils.parse(runDate, "yyyyMMdd");
            } catch (Throwable t) {
                throw new JobException(JobError.JOB_017);
            }
            worker.setRunDate(runDate);
        } else
            worker.setRunDate(DateUtils.getDate());

        // 检查job是否已经执行
        if (checkWorkIsRunning(workername)) {
            throw new JobException(JobError.JOB_002, "Worker Name ["
                    + workername + "]");
        }

        //plan.add(worker);
        String groupId = "Worker_" + UniqId.getInstance().getUniqTime();
        Thread t = new Thread(new WorkerRun(groupId, worker));
        t.start();
        logger.debug(wm.toString());
        if (!noWait) {
            // wait job execute over，testing add
            do {
                Thread.sleep(1000);
            } while (cjob.getJobs().size() > 0);
            logger.debug("worker run over!");
        }
    }

	/*
     * 对外不提供总调度入口 modify by Tim 20140922
	 * 
	 * private void checkCurrentJob() { if (cjob.getJobs().size() > 0) { throw
	 * new JobException(JobError.JOB_002); } }
	 * 
	 * protected void excuteJob() throws InterruptedException,
	 * InstantiationException, IllegalAccessException, ClassNotFoundException {
	 * checkCurrentJob(); generateExecutionPlan(); while (executeWorker <
	 * plan.size()) { if (workerIndex < PARALLEL_WORKER) { Worker worker =
	 * plan.get(executeWorker); String groupId = "Worker_" +
	 * UniqId.getInstance().getUniqTime(); logger.debug("groupId[" + groupId +
	 * "],Worker is Running!"); current_worker.put(groupId, worker); Thread t =
	 * new Thread(new WorkerRun(groupId, worker)); t.start(); workerIndex++;
	 * executeWorker++; logger.debug(wm); } Thread.sleep(5000); } // wait job
	 * execute over，testing add for (; cjob.getJobs().size() > 0;) {
	 * Thread.sleep(5000); }
	 * 
	 * logger.debug("worker run over!"); }
	 */


    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Worker work : wf.getWorker()) {
            sb.append("\nworker[" + work.getName() + "] description[" + work.getDescription() + "]");
            sb.append("\n" + wm.getJobPlan(work.getName()).toString());
        }
        return sb.toString();
    }

    public List<Map<String, String>> getCurrentJob() {
        List<Map<String, String>> currentJob = new ArrayList<>();
        for (Map.Entry job : cjob.getJobs().entrySet()) {
            ExecutedJob eJob = (ExecutedJob) job.getValue();
            Map<String, String> jobParam = new TreeMap<>();
            jobParam.put("jobId", eJob.getJobId());
            jobParam.put("jobName", eJob.getName());
            jobParam.put("jobDesc", eJob.getDescription());
            jobParam.put("runDate", eJob.getRunDate());
            jobParam.put("applicationId", eJob.getApplicationId());
            jobParam.put("startedTime", DateUtils.getDateTime(new Date(eJob.getStartedTime()), DateUtils.DEFAULT_DATETIME_FORMAT));
            jobParam.put("progress", eJob.getAc().getProgress());
            jobParam.put("jobStatus", eJob.getJobStatus().toString());
            currentJob.add(jobParam);
        }
        Collections.sort(currentJob, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o2.get("jobId").compareTo(o1.get("jobId"));
            }
        });
        logger.debug(currentJob.toString());
        return currentJob;
    }

    public List<Map<String, String>> getHistoryJob() {
        List<Map<String, String>> historyJob = new ArrayList<>();
        for (Map.Entry job : hjob.getJobs().entrySet()) {
            ExecutedJob eJob = (ExecutedJob) job.getValue();
            Map<String, String> jobParam = new TreeMap<>();
            jobParam.put("jobId", eJob.getJobId());
            jobParam.put("jobName", eJob.getName());
            jobParam.put("jobDesc", eJob.getDescription());
            jobParam.put("runDate", eJob.getRunDate());
            jobParam.put("applicationId", eJob.getApplicationId());
            jobParam.put("startedTime", DateUtils.getDateTime(new Date(eJob.getStartedTime()), DateUtils.DEFAULT_DATETIME_FORMAT));
            jobParam.put("finishedTime", DateUtils.getDateTime(new Date(eJob.getFinishedTime()), DateUtils.DEFAULT_DATETIME_FORMAT));
            jobParam.put("elapsedTime", String.valueOf(eJob.getElapsedTime()));
            jobParam.put("jobStatus", eJob.getJobStatus().toString());
            jobParam.put("progress", eJob.getAc().getProgress());
            jobParam.put("errorMessages", eJob.getAc().getJavaClassName() == null ? "" : eJob.getAc().getJavaClassName() + ":" + eJob.getAc().getMessage());
            historyJob.add(jobParam);
        }
        Collections.sort(historyJob, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o2.get("jobId").compareTo(o1.get("jobId"));
            }
        });
        logger.debug(historyJob.toString());
        return historyJob;
    }

    public static void main(String arg[]) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException,
            InterruptedException, IOException {

        if (0 == arg.length)
            throw new JobException(JobError.JOB_009);
        String workerName = arg[0];
        if (null == workerName || "".equals(arg[0]))
            throw new JobException(JobError.JOB_009);
        String runDate = null;

        // 第二参数批处理日期
        if (2 == arg.length) {
            runDate = arg[1];
        }

        logger.debug(System.getenv().toString());
        WorkerManager wm = WorkerManager.getInstance();
        wm.runWorker(workerName, runDate, false);
    }
}
