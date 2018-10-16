package com.dcits.ensemble.om.oms.common;


import com.dcits.ensemble.om.oms.module.utils.ShellResult;


/**
 * 容器公共类* 
 * @author tangxlf
 * @date 2015-09-22 
 */
public class MachineUtil {
	  //  private static  final Logger log = LoggerFactory.getLogger(MachineUtil.class);    
	   /**
	    * 根据应用状态SHELL执行结果，解析应用状态
		* @param ShellResult result    shell执行结果 
		* @param String appIntantPort  应用实例端口 
		* @return  boolean true:运行   false:停止
		*/	 
//		public static  boolean appStatus(ShellResult result,String appIntantPort){					
//			String[] resultArray = ShellResultHandler.parseIndResult(result,3);
//			for(String resultStr:resultArray){
//				if(resultStr!=null&&resultStr.indexOf(":")>-1){
////					if(resultStr.split(":")[1].equals(appIntantPort)){
////						log.info("true  resultStr="+resultStr);
////						return true;
////					}
//					if(resultStr.indexOf(appIntantPort)>0){
//						log.info("true  resultStr="+resultStr);
//						return true;
//					}
//				}
//			}
//			return false;
//	   }	  
		
		
		/**
		    * 根据应用状态SHELL执行结果，解析应用状态
			* @param ShellResult result    shell执行结果 
			* @param String appIntantPort  应用实例端口 
			* @return  boolean true:运行   false:停止
			*/	 
			public static  boolean appStatus(ShellResult result,String appIntantPort){	
				if(result.getOutList().size()>0){
					return true;
				}else{
					return false;
				}
		    }	  
}
