/**   
 * <p>Title: ParamError.java</p>
 * <p>Description: Job助手类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月16日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

import com.dcits.orion.schedule.context.JobContext;
import com.dcits.orion.schedule.job.JobStatus;
import com.dcits.orion.schedule.model.ExecutedJob;

/**
 * @description Job助手类
 * @version V1.0
 * @author Tim
 * @update 2014年9月16日 下午2:52:35 
 */

public class JobHandler {

	public static void updateJobException(ExecutedJob ej, Throwable e1) {
		if (null == ej)
			return;
		if (null == ej.getAc()) {
			JobContext ac = new JobContext();
			ac.setException(e1.getClass().getSimpleName());
			ac.setJavaClassName(e1.getClass().getName());
			ac.setMessage(e1.getMessage());
			ej.setAc(ac);
			ej.setJobStatus(JobStatus.FAILURE_ON_SUBMIT);
		} else {
			ej.getAc().setException(e1.getClass().getSimpleName());
			ej.getAc().setJavaClassName(e1.getClass().getName());
			ej.getAc().setMessage(e1.getMessage());
			ej.setJobStatus(JobStatus.FAILURE_ON_SUBMIT);
		}
		if (null != ej.getListenner())
			ej.getListenner().cancel();
	}
	
	public static void updateJobContext(ExecutedJob ej) {
		if (null == ej)
			return;
		if (null == ej.getAc()) {
			JobContext ac = new JobContext();
			ac.setState(JobStatus.SUCCEEDED.toString());
			ac.setFinalStatus(JobStatus.SUCCEEDED.toString());
			ej.setAc(ac);
			ej.setJobStatus(JobStatus.SUCCEEDED);
		} else {
			ej.getAc().setState(JobStatus.SUCCEEDED.toString());
			ej.getAc().setFinalStatus(JobStatus.SUCCEEDED.toString());
			ej.setJobStatus(JobStatus.SUCCEEDED);
		}
		if (null != ej.getListenner())
			ej.getListenner().cancel();
	}
}
