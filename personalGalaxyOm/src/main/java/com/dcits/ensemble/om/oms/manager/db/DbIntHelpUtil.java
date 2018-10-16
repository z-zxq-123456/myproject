package com.dcits.ensemble.om.oms.manager.db;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;


/**
 * 数据库实例辅助类* 
 * @author tangxlf
 * @date 2016-04-27
 */
public class DbIntHelpUtil {

	/**
	 * 生成数据库URL
	 * @param   EcmMidwareDbint         intant          db实例对象	 
	 * @return  String                  数据库URL
	 * 
	 */ 	
	public static String createDbUrl(EcmMidwareDbint intant) {
		System.out.print("intant.SerIp="+intant.getSerIp());
		if(intant.getDbType().equals(SysConfigConstants.DB_MYSQL)){
			//return "jdbc:mysql://"+intant.getSerIp()+":"+intant.getDbintPort()+"/"+intant.getDbintServiceName()+"?useUnicode=true&characterEncoding=utf8";
			return "jdbc:mysql://"+intant.getSerIp()+":"+intant.getDbintPort()+"/"+intant.getDbintServiceName()+"?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useServerPrepStmts=false";//设置批处理参数
		}
		if(intant.getDbType().equals(SysConfigConstants.DB_ORACLE)){
			return "jdbc:oracle:thin:@"+intant.getSerIp()+":"+intant.getDbintPort()+":"+intant.getDbintServiceName();
		}
		return null;
    }
	
}
