/**   
 * <p>Title: WorkerRun.java</p>
 * <p>Description: Worker执行具体Job的控制实现</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.JobRunType;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.Worker;
import com.dcits.galaxy.base.util.ExceptionUtils;

/**
 * @description Worker执行具体Job的控制实现
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:32:52
 */

public class WorkerRun implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(WorkerRun.class);

	private String groupId;

	private WorkerManager wm = WorkerManager.getInstance();

	private Worker worker;

	public WorkerRun(String groupId, Worker worker) {
		this.groupId = groupId;
		this.worker = worker;
	}

	@Override
	public void run() {
		try {
			logger.debug("groupId[" + groupId + "],Worker is Running!");
			wm.addWorker(groupId, worker);
			// execute begin jobs
			runJob(JobRunType.BEGIN);
			// execute start jobs
			runJob(JobRunType.START);
			// execute start jobs
			runJob(JobRunType.END);
		} catch (Throwable t) {
			runJob(JobRunType.ERROR);
			throw t;
		} finally {
			wm.removeWorker(groupId);
		}
	}

	private void runJob(JobRunType jt) {
		List<List<AbstractJob>> llj = null;
		if (JobRunType.BEGIN == jt) {
			llj = worker.getBeginJob().getJob();
		} else if (JobRunType.START == jt) {
			llj = worker.getStartJob().getJob();
		} else if (JobRunType.END == jt) {
			llj = worker.getEndJob().getJob();
		} else if (JobRunType.ERROR == jt) {
			llj = worker.getErrorJob().getJob();
		}
		String superJobName = null;
		for (List<AbstractJob> la : llj) {
			// 执行Job
			List<FutureTask<Throwable>> lfoo = new ArrayList<FutureTask<Throwable>>();
			// 检查
			for (AbstractJob ajob : la) {
				JobCallable jc = new JobCallable(groupId, superJobName, jt,
						worker, ajob);
				FutureTask<Throwable> jobTask = new FutureTask<Throwable>(jc);
				Thread jobThread = new Thread(jobTask);
				jobThread.start();
				lfoo.add(jobTask);
			}

			// 检查Job执行结果
			for (FutureTask<Throwable> foo : lfoo) {
				Throwable t = null;
				try {
					t = foo.get();
				} catch (InterruptedException | ExecutionException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
					throw new JobException(JobError.JOB_004, e);
				}
				if (null != t)
					throw new RuntimeException(t);
			}
		}
	}
}
