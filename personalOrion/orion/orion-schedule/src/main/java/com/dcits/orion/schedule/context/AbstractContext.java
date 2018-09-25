/**   
 * <p>Title: AbstractContext.java</p>
 * <p>Description: 抽象上下文父类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.context;

import com.dcits.galaxy.base.util.BeanUtils;

/**
 * @description 抽象上下文父类
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:17:09 
 */

public abstract class AbstractContext {
	@Override
	public String toString(){
		return BeanUtils.getString(this);
	}
}
