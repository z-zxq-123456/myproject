/**   
 * <p>Title: FilePOJobContext.java</p>
 * <p>Description: 持久化context信息到数据库</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月23日 下午5:55:01
 * @version V1.0
 */
package com.dcits.orion.schedule.persist;

import java.sql.ResultSet;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.DBException;
import com.dcits.orion.schedule.jdbc.JDBCExecutor;
import com.dcits.orion.schedule.job.JobStatus;
import com.dcits.orion.schedule.model.ExecutedJob;
import com.dcits.galaxy.base.util.ExceptionUtils;

/**
 * @description 持久化context信息到数据库
 * @version V1.0
 * @author Tim
 * @update 2014年9月23日 下午5:55:01
 */

public class DBPOJobContext implements POJobContext {
	final String connectionName = "JobLog";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dcits.galaxy.schedule.job.POJobContext#persistJobContext(com.dcits
	 * .galaxy.schedule.model.ExecutedJob)
	 */
	@Override
	public void persistJobContext(ExecutedJob ej) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into jobcontext (groupId,workName,workDescription");
		sb.append(",runDate,jobId,jobName,jobDescription,jobType");
		sb.append(",jobStatus,jobRunType,applicationId,user,startedTime");
		sb.append(",finishedTime,elapsedTime,javaClassName,exception,message) values ('");
		sb.append(ej.getGroupId());
		sb.append("','");
		sb.append(ej.getWorkName());
		sb.append("','");
		sb.append(ej.getWorkDescription());
		sb.append("','");
		sb.append(ej.getRunDate());
		sb.append("','");
		sb.append(ej.getJobId());
		sb.append("','");
		sb.append(ej.getDescription());
		sb.append("','");
		sb.append(ej.getName());
		sb.append("','");
		sb.append(ej.getType().toString());
		sb.append("','");
		sb.append(ej.getJobStatus().toString());
		sb.append("','");
		sb.append(ej.getJobRunType().toString());
		sb.append("','");
		sb.append(ej.getApplicationId());
		sb.append("','");
		sb.append(ej.getAc().getUser());
		sb.append("','");
		sb.append(ej.getStartedTime());
		sb.append("','");
		sb.append(ej.getFinishedTime());
		sb.append("','");
		sb.append(ej.getElapsedTime());
		sb.append("','");
		sb.append(ej.getAc().getJavaClassName());
		sb.append("','");
		sb.append(ej.getAc().getException());
		sb.append("','");
		sb.append(ej.getAc().getMessage());
		sb.append("')");
		String sql = sb.toString().replace("'null'", "NULL")
				.replace("''", "NULL");
		JDBCExecutor db = new JDBCExecutor(connectionName);
		try {
			db.executeUpdate(sql);
		} finally {
			db.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dcits.galaxy.schedule.persist.POJobContext#checkJobStatus(com.dcits
	 * .galaxy.schedule.model.ExecutedJob)
	 */
	@Override
	public boolean checkJobStatus(ExecutedJob ej) {
		StringBuffer sb = new StringBuffer();
		sb.append("select jobStatus from jobcontext where runDate = '");
		sb.append(ej.getRunDate());
		sb.append("' and workName = '");
		sb.append(ej.getWorkName());
		sb.append("' and jobType = '");
		sb.append(ej.getType().toString());
		sb.append("' and jobName = '");
		sb.append(ej.getName());
		sb.append("'");

		JDBCExecutor db = new JDBCExecutor(connectionName);
		ResultSet rs = db.executeQuery(sb.toString());
		try {
			while (rs.next()) {
				if (JobStatus.SUCCEEDED.toString().equals(
						rs.getString("jobStatus"))) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new DBException(JobError.JOB_016,
					ExceptionUtils.getStackTrace(e));
		} finally {
			db.close();
		}
		return false;
	}

}
