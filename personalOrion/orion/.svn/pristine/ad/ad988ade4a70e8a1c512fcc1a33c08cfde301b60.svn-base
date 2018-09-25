/**   
 * <p>Title: POJobContext.java</p>
 * <p>Description: 持久化Job执行结果信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月23日 下午5:41:56
 * @version V1.0
 */
package com.dcits.orion.schedule.persist;

import com.dcits.orion.schedule.model.ExecutedJob;

/**
 * @description 持久化Job执行结果信息
 * @version V1.0
 * @author Tim
 * @update 2014年9月23日 下午5:41:56 
 */

public interface POJobContext {
	
	/**
	 * @param ej 执行JOB
	 * @description 持久化JOB执行结果
	 * @version 1.0
	 * @author Tim
	 * @update 2014年9月23日 下午5:53:45
	 */
	void persistJobContext(ExecutedJob ej);
	
	/**
	 * @param ej 执行JOB
	 * @return
	 * @description 判断JOB是否已经执行成功
	 * @version 1.0
	 * @author Tim
	 * @update 2014年9月23日 下午5:51:50
	 */
	boolean checkJobStatus(ExecutedJob ej);
	
}
