/**   
 * <p>Title: ConnectionContainer.java</p>
 * <p>Description: JDBC连接容器<br>
 *              存放定义的连接配置<br>
 *              为Sqoop Job提供Connection连接配置</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @description JDBC连接容器<br>
 *              存放定义的连接配置<br>
 *              为Sqoop Job提供Connection连接配置
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:19:46
 */

@XStreamAlias("connections")
public class ConnectionContainer {

	@XStreamImplicit(itemFieldName = "connection")
	private List<JBDCConnection> sjc = new ArrayList<JBDCConnection>();

	@Override
	public String toString() {
		return BeanUtils.getString(this);
	}

	public JBDCConnection getConnection(String name) {
		JBDCConnection sjcc = null;
		if (null == sjc)
			throw new JobException(JobError.JOB_007);
		for (JBDCConnection sj : sjc) {
			if (name.equals(sj.getConnection_name())) {
				sjcc = sj;
				break;
			}
		}
		if (null == sjcc)
			throw new JobException(JobError.JOB_007, "Connection Name [" + name
					+ "]");
		return sjcc;
	}
}
