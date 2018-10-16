package com.dcits.ensemble.om.oms.common;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 路径辅助类* 
 * @author tangxlf
 * @date 2016-04-25 
 */
public class CommonPathHelp {	
	
	private static final Logger log = LoggerFactory.getLogger(CommonPathHelp.class);
	
    private static final String PROD_DEL_DIR="/classes/";//生产环境删除目录
	
	private static final String DEVELOP_DEL_DIR="/target/test-classes/";//开发环境删除目录
	
	private static final String DEVELOP_ADD_DIR="/src/main/webapp/WEB-INF";//开发环境添加目录
	/**
	* 处理传入的路径，如果最后已"/"结束,去掉
	* @param String path              应用实例对象		
	* @return String  规整后的path
	*/ 
	public static String handlePath(String path){
		char lastChar = path.charAt(path.length()-1);
		if("/".equals(""+lastChar)){
			return path.substring(0,path.length()-1);
		}
	    return path;
	}
	/**
	* 获取中间件版本的绝对路径
	* @param String path         版本表中记录的相对路径	
	* @return String  中间件版本的绝对路径
	*/ 
	public static String createTotalPath(String midwareVerPath,ParamComboxService paramComboxService){
			return createPrefixUrl(paramComboxService)+"/"+midwareVerPath;
	}
	//中间件保存路径前缀  开发模式下前缀为D:\newWork\2014\Galaxy\trunk\project\Galaxy-oms\src\main\webapp\WEB-INF
    //             生产模式下前缀为/home/oms/syssoft/apache-tomcat-7.0.40/webapps/galaxyoms/WEB-INF
	/**
	* 生成中间件版本路径前缀	
	* @return String  中间件版本路径前缀
	*/ 
	public static String createPrefixUrl(ParamComboxService paramComboxService){
		 String classPath=CommonPathHelp.class.getResource("/").getPath();
		 log.info("classPath="+classPath);
		 if(classPath.indexOf(DEVELOP_DEL_DIR)>0){//开发环境
			 if(paramComboxService.getParaRemark1(SysConfigConstants.DEV_MODE).equals("1")){
				 classPath=handlePath(paramComboxService.getParaRemark2(SysConfigConstants.DEV_MODE));
			 }else{
				 classPath=classPath.substring(0,classPath.indexOf(DEVELOP_DEL_DIR))+DEVELOP_ADD_DIR;
			 }
		 }else{//生产环境
			  classPath=classPath.substring(0,classPath.indexOf(PROD_DEL_DIR));
		 }
		 return classPath;
	}
}
