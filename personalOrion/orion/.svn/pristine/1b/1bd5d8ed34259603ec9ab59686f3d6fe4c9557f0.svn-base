/**   
 * <p>Title: Listenner.java</p>
 * <p>Description: 监听接口，负责更新Job执行状态</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.job;

import com.dcits.orion.schedule.context.AbstractContext;

/**
 * @description Job 监听接口，负责更新Job执行状态
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:14:13
 */

public interface Listenner {

	AbstractContext getJobInfo();

	void updateJobInfo();

	JobStatus getJobStatus();

	void cancel();
}
