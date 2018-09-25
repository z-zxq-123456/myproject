/**   
 * <p>Title: GXProcess.java</p>
 * <p>Description: java job和galaxy 远程RPC job处理实现</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.process;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.common.JobHandler;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.JobType;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.GXJob;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.core.client.ServiceProxy;

/**
 * @description java job和galaxy 远程RPC job处理实现
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:28:48
 */

public class GXProcess extends AbstractProcess {

	private static Logger logger = LoggerFactory.getLogger(GXProcess.class);

	private GXJob gj;

	/**
	 * 类的构造方法
	 * 
	 * @param jobId
	 * @param job
	 */
	public GXProcess(String jobId, AbstractJob job) {
		super(jobId, job);
	}

	@Override
	public void init() {
		logger.debug("jobId[" + jobId + "]\n" + job);
		if (!job.getType().equals(JobType.GALAXY)
				&& !job.getType().equals(JobType.JAVA)) {
			throw new JobException(JobError.JOB_005, "JobType define["
					+ job.getType() + "],expect[" + JobType.GALAXY + "/"
					+ JobType.JAVA + "]");
		}
	}

	@Override
	public void process() {
		if (!job.getType().equals(JobType.GALAXY)
				&& !job.getType().equals(JobType.JAVA)) {
			throw new JobException(JobError.JOB_005, "JobType define["
					+ job.getType() + "],expect[" + JobType.GALAXY + "/"
					+ JobType.JAVA + "]");
		}

		gj = (GXJob) job;
		Log.debug(gj.toString());
		// 普通java，反射调用
		Object o = null;
		Method m = null;

		try {
			if (JobType.JAVA == gj.getType()) {
				o = ClassLoaderUtils.newInstance(gj.getApi());
			} else {// Galaxy RPC远程接口调用
				o = ServiceProxy.getInstance().getService(
						Class.forName(gj.getApi()));
			}

			m = ObjectUtils.getMethod(o, gj.getMethod(),
					gj.getArgs() == null ? null
							: gj.getArgs().getArg() == null ? null : gj
									.getArgs().getArg());
			Object out = m.invoke(o,
					ObjectUtils.getArguments(gj.getArgs() == null ? null : gj
							.getArgs().getArg() == null ? null : gj.getArgs()
							.getArg()));

			if (out instanceof BeanResult) {
				BeanResult br = (BeanResult) out;
				if (!br.getRetStatus().equals(GalaxyConstants.STATUS_SUCCESS)) {
					throw new BusinessException(br.getRs());
				}
			}
			// 更新成功
			JobHandler.updateJobContext(wm.getJob(jobId));
		} catch (Throwable t) {
			Throwable e = t;
			if (e instanceof InvocationTargetException) {
				e = e.getCause();
			}
			if (e instanceof UndeclaredThrowableException) {
				e = e.getCause();
			}
			if (e instanceof InvocationTargetException) {
				e = e.getCause();
			}
			if (e instanceof RuntimeException)
				throw ExceptionUtils.parseException(e);
			else
				throw new GalaxyException(e);
		}
	}
}
