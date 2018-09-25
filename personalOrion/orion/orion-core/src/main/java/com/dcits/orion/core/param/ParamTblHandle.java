/**   
 * <p>Title: ParamTblHandle.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月10日 下午3:28:26
 * @version V1.0
 */
package com.dcits.orion.core.param;

import com.dcits.orion.core.Context;
import com.dcits.orion.core.dao.IDao;
import com.dcits.galaxy.base.bean.AbstractBean;

import java.util.List;

/**
 * 通用参数表获取助手类
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年2月10日 下午3:28:26
 */

public class ParamTblHandle {

	/**
	 * 根据主键查询单条
	 * 
	 * @param mapper
	 * @param pkValue
	 * @return
	 */
	public static <T extends AbstractBean> T getTblParam(T mapper,
			Object... pkValue) {
		String daoName = mapper.getClass().getSimpleName().substring(0, 1)
				.toLowerCase()
				+ mapper.getClass().getSimpleName().substring(1) + "Dao";
		IDao dao = Context.getInstance().getBean(daoName);
		if (null == dao)
			return null;
		return dao.selectByPrimaryKey(mapper, pkValue);
	}

	/**
	 * 多主键查询需要，主键占位符
	 * <br>对于多主键目前，只提供2个主键查询列表
	 * 
	 * @param mapper
	 * @param pkValue
	 * @return
	 */
	public static <T extends AbstractBean> List<T> getTblParamList(T mapper,
			Object... pkValue) {
		String daoName = mapper.getClass().getSimpleName().substring(0, 1)
				.toLowerCase()
				+ mapper.getClass().getSimpleName().substring(1) + "Dao";
		IDao dao = Context.getInstance().getBean(daoName);
		if (null == dao)
			return null;
		return dao.selectListByPrimaryKey(mapper, pkValue);
	}
}
