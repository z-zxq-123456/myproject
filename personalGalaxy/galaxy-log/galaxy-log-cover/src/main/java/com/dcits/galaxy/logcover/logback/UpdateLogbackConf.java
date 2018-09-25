package com.dcits.galaxy.logcover.logback;


import com.dcits.galaxy.logcover.config.EcmLogconfInfo;

/**
 *修改logback.xml的配置文件* 
 * @author wangbinaf
 * @date 2016-10-17 
 */
public class UpdateLogbackConf {
	
	 
	  
	/**
	 *修改logback.xml的配置文件，修改属性的值* 
	 * @param EcmLogconfInfo   updateInfo  日志配置信息
	 */
	 public   static   void updateLogbackConf( EcmLogconfInfo info){		
			 UpdateLogbackConfUtils.editLogbackConf( info);//修改logback.xml 		
	 }
	 
    /**
	 *修改logback.xml的配置文件，增加loggger节点* 
	 * @param EcmLogconfInfo   updateInfo  日志配置信息
	 */
	 public static  void addLogbackNode( EcmLogconfInfo info){		
			 UpdateLogbackConfUtils.addNode( info);//修改logback.xml 		 
	 }
 
}
