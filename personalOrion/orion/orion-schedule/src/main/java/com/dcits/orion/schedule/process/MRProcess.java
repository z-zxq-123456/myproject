/**   
 * <p>Title: MRProcess.java</p>
 * <p>Description: Mapreduce Job处理</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.process;

import org.apache.hadoop.mapreduce.Job;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.JobType;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.galaxy.base.util.ExceptionUtils;

/**
 * @description Mapreduce Job处理
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:30:00
 */

public class MRProcess extends AbstractProcess {

	private static Logger logger = LoggerFactory.getLogger(MRProcess.class);

	public MRProcess(String jobId, AbstractJob job) {
		super(jobId, job);
	}

	@Override
	public void init() {
		logger.debug("jobId[" + jobId + "]\n" + job);
		if (!job.getType().equals(JobType.MR)) {
			throw new JobException(JobError.JOB_005, "JobType define["
					+ job.getType() + "],expect[" + JobType.MR + "]");
		}
	}

	@Override
	public void process() {
		try {
			loadHadoopConfiguration();
			Log.debug(job.toString());
			Job j = getDefaultJob();
			j.submit();
			setApplicationId(j.getJobID().toString());
		} catch (Exception e) {
			throw new JobException(JobError.JOB_004,
					ExceptionUtils.getStackTrace(e));
		}
	}
}
