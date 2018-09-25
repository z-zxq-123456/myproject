/**   
 * <p>Title: AppliacationListenner.java</p>
 * <p>Description: Hadoop Job监听实现，负责将Hadoop集群中Job运行状态，同步到工作流处理平台</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.listenner;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.orion.schedule.common.JobContextRequest;
import com.dcits.orion.schedule.common.JobHandler;
import com.dcits.orion.schedule.context.JobContext;
import com.dcits.orion.schedule.job.JobStatus;
import com.dcits.orion.schedule.job.Listenner;
import com.dcits.orion.schedule.model.ExecutedJob;
import com.dcits.galaxy.base.util.ExceptionUtils;

/**
 * @description Hadoop Job监听实现，负责将Hadoop集群中Job运行状态，同步到工作流处理平台
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:16:08
 */

public class AppliacationListenner implements Listenner {

	private static Logger logger = LoggerFactory
			.getLogger(AppliacationListenner.class);

	private String threadName = Thread.currentThread().getName();

	private ExecutedJob ej;

	private Timer timer = new Timer(true);

	public AppliacationListenner(ExecutedJob job) {
		ej = job;
	}

	public JobStatus getJobStatus() {
		return ej.getJobStatus();
	}

	public JobContext getJobInfo() {
		return ej.getAc();
	}

	public void updateJobInfo() {
		logger.info("start timer!");
		timer.schedule(new TimerTask() {
			public void run() {
				JobContextRequest jcr = new JobContextRequest();
				JobContext ac = null;
				logger.debug("[" + threadName
						+ "] get aplication info from remote server!");
				try {
					ac = jcr.getJobContext(ej.getApplicationId());
				} catch (Throwable t) {
					logger.error("[" + threadName + "] "
							+ ExceptionUtils.getStackTrace(t));
					JobHandler.updateJobException(ej, t);
					return;
				}

				// job执行进度
				if (null != ac && null != ac.getProgress()) {
					String jobProgress = ac.getProgress() + "%";
					logger.info("Job progress:" + jobProgress);
				}

				ej.setAc(ac);
				if (null != ac && !ej.getJobStatus().isRunning()) {
					logger.info("cancel timer!");
					cancel();
					logger.info("canceled!");
					return;
				}
			}
		}, 0, 10 * 1000);
	}

	public void cancel() {
		if (null != timer) {
			logger.info("cancel timer!");
			timer.cancel();
		}
		logger.info("canceled!");
	}
}
