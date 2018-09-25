/**
 * <p>Title: JobCallable.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2015年3月12日 下午1:29:46
 * @version V1.0
 */
package com.dcits.orion.schedule.workflow;

import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.common.JobHandler;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.*;
import com.dcits.orion.schedule.job.Process;
import com.dcits.orion.schedule.model.*;
import com.dcits.orion.schedule.persist.POJobContext;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

/**
 * Job执行线程
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年3月12日 下午1:29:46
 */

public class JobCallable implements Callable<Throwable> {

    private static Logger logger = LoggerFactory.getLogger(JobCallable.class);

    private AbstractJob aj;

    private String groupId;

    private String superJobName;

    private Worker worker;

    private JobRunType jt;

    private WorkerManager wm = WorkerManager.getInstance();

    private JobConfiguration jc = JobConfiguration.getInstance();

    public JobCallable(String groupId, String superJobName, JobRunType jt,
                       Worker worker, AbstractJob aj) {
        this.groupId = groupId;
        this.superJobName = superJobName;
        this.jt = jt;
        this.worker = worker;
        this.aj = aj;
    }

    @Override
    public Throwable call() throws Exception {
        String jobId = null;
        ExecutedJob ej = new ExecutedJob();
        POJobContext pojc = null;
        Process pro = null;
        boolean skipJob = false;
        try {
            jobId = wm.getJobId();
            ej.setType(aj.getType().toString());
            ej.setName(aj.getName());
            ej.setDescription(aj.getDescription());
            ej.setWorkName(worker.getName());
            ej.setWorkDescription(worker.getDescription());
            ej.setJobId(jobId);
            ej.setGroupId(groupId);
            ej.setSuperJobName(superJobName);
            ej.setJobRunType(jt);
            ej.setRunDate(worker.getRunDate());
            try {
                if (aj.getType() == JobType.HBASE || aj.getType() == JobType.MR
                        || aj.getType() == JobType.SQOOP)
                    ej.registerListenner((Listenner) ClassLoaderUtils.newInstance(
                            jc.getJobPro(GlobalConfiguration.JOB_HADOOP_LISTENNER),
                            new Class[]{Class
                                    .forName("com.dcits.galaxy.schedule.model.ExecutedJob")},
                            new Object[]{ej}));
                else if (aj.getType() == JobType.JAVA
                        || aj.getType() == JobType.GALAXY)
                    ej.registerListenner((Listenner) ClassLoaderUtils.newInstance(
                            jc.getJobPro(GlobalConfiguration.JOB_GALAXY_LISTENNER),
                            new Class[]{Class
                                    .forName("com.dcits.galaxy.schedule.model.ExecutedJob")},
                            new Object[]{ej}));

            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException
                    | SecurityException e1) {
                throw new JobException(JobError.JOB_004,
                        ExceptionUtils.getStackTrace(e1));
            }
            ej.setJobStatus(JobStatus.BOOTING);
            logger.debug("\nCurrent Worker[" + groupId + "]\nRunJobType[" + jt
                    + "]\nJob[" + jobId + "]\nJob Name[" + aj.getName()
                    + "]\nJob Description[" + aj.getDescription()
                    + "]\nJobType[" + aj.getType().toString() + "]");

            // check Job is succeed
            try {
                pojc = (POJobContext) ClassLoaderUtils.newInstance(jc
                        .getJobPro(GlobalConfiguration.JOB_PERSIST_IMPL));
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e1) {
                throw new JobException(JobError.JOB_004,
                        ExceptionUtils.getStackTrace(e1));
            }

            //检查job是否执行成功
            if (pojc.checkJobStatus(ej)) {
                logger.info("\nCurrent Worker[" + worker.getName()
                        + "]\nRunJobType[" + jt + "]\nJob[" + ej.getName()
                        + "]" + "\nAlready Succeed...Skip the Job.");
                skipJob = true;
                return null;
            }
            // 添加当前Job
            wm.addJob(jobId, ej);
            try {
                pro = getJobProcess(jobId, aj);
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException
                    | SecurityException e1) {
                throw new JobException(JobError.JOB_004,
                        ExceptionUtils.getStackTrace(e1));
            }

            pro.init();
            pro.process();
            // set superJobName
            superJobName = aj.getName();
            // wait for job execute over!
            Long interval = Long.valueOf(5000);
            String ms = jc
                    .getJobPro(GlobalConfiguration.INTERVAL_BITAIN_RESULT);
            if (null != ms && !"".equals(ms))
                interval = Long.valueOf(ms);
            while (ej.getListenner().getJobStatus().isRunning()) {
                try {
                    logger.debug("Job[" + ej.getJobId() + "],status["
                            + ej.getJobStatus() + "]");
                    logger.debug("wait " + interval + " ms!");
                    Thread.sleep(interval);
                } catch (InterruptedException e1) {
                    throw new JobException(JobError.JOB_004,
                            ExceptionUtils.getStackTrace(e1));
                }
            }

            if (ej.getListenner().getJobStatus().isFailure()) {
                throw new JobException(JobError.JOB_004, "Job Status is "
                        + ej.getListenner().getJobStatus());
            }
        } catch (Throwable t) {
            logger.error(ExceptionUtils.getStackTrace(t));
            JobHandler.updateJobException(ej, t);
            return t;
        } finally {
            if (!skipJob) {
                if (null != pojc)
                    pojc.persistJobContext(ej);
                // 移除当前Job，添加历史Job
                if (jobId != null)
                    wm.removeJob(jobId);
            } else {
                // 已经成功的job消除监听
                if (null != ej.getListenner())
                    // 消除Listenner
                    ej.getListenner().cancel();
            }
        }
        return null;
    }

    private Process getJobProcess(String jobId, AbstractJob aj)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        Process pro = null;
        String classImpl = null;
        if (aj instanceof DXJob) {
            classImpl = jc.getJobPro(GlobalConfiguration.ETL_PROCESS);
        } else if (aj instanceof MRJob) {
            if (aj instanceof HBJob) {
                classImpl = jc.getJobPro(GlobalConfiguration.HBASE_PROCESS);
            } else {
                classImpl = jc.getJobPro(GlobalConfiguration.MR_PROCESS);
            }
        } else if (aj instanceof GXJob) {
            classImpl = jc.getJobPro(GlobalConfiguration.GALAXY_PROCESS);
        }
        pro = (Process) ClassLoaderUtils
                .newInstance(
                        classImpl,
                        new Class[]{
                                String.class,
                                Class.forName("com.dcits.galaxy.schedule.model.AbstractJob")},
                        new Object[]{jobId, aj});
        return pro;
    }

}
