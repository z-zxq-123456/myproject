package com.dcits.ensemble.om.oms.manager.db;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库实例状态检查类* 
 * @author tangxlf
 * @date 2016-04-27
 */
@Component
public class DBStatusCheck {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	private static final String CHECK_DB_CONNECTION ="数据库连通性检查";
	
	private static final int CHECK_FAIL = 1; //失败
	
	@Resource
	ParamComboxService paramComboxService;
	

	/**
	 * 数据库连通性检查
	 * @param EcmMidwareDbint         intant          db实例对象	 
	 * @param ContainerCheckResult  checkResult       检查结果    
	 * 
	 */ 	
	public  void checkDbConnection(EcmMidwareDbint intant,ContainerCheckResult checkResult) {
		String classDriverName = paramComboxService.getParaRemark1(intant.getDbType());
		Connection conn = null;
		try {		
			Class.forName(classDriverName);
			String pwd  = OMSPassWordBase64.decodeToString(intant.getDbintUserPwd());
			conn = DriverManager.getConnection(DbIntHelpUtil.createDbUrl(intant),intant.getDbintUserName(),pwd);
			checkResult.addMessage(CHECK_DB_CONNECTION+":连通");						
		}catch (ClassNotFoundException e) {
			log.error(CHECK_DB_CONNECTION+"出错,错误信息为："+e.getMessage());
			errorCheck(CHECK_DB_CONNECTION,checkResult);
		}catch (SQLException e) {
			log.error(CHECK_DB_CONNECTION+"出错,错误信息为："+e.getMessage());
			errorCheck(CHECK_DB_CONNECTION,checkResult);
		}finally{
			if(conn!=null){
				try {
					conn.close();
				}catch (SQLException e) {
					log.error(e.getMessage());
					throw new GalaxyException(e.getMessage());
				}
			}
		}
    }
	
	/**
	 * 异常报错
	 * @param String   str  检查对象
	 * @param ContainerCheckResult  checkResult       检查结果
	 * 
	 */
	private void errorCheck(String str ,ContainerCheckResult checkResult) {
	     checkResult.addResultNum(CHECK_FAIL);
	     checkResult.addMessage(str+":不连通");  
	}	
	
}
