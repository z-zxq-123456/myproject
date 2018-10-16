package com.dcits.ensemble.om.oms.manager.app;


import java.util.regex.Pattern;


/**
 * 处理同一jar包的不同版本*  
 * 应用版本增量发布时,同一个JAR包可能版本号不同，需要识别出来此类JAR包 
 * @author tangxlf
 * @date 2015-10-09
 */
public class HandlerSameJar {
	/**
	 * 生成jar标准名称
	 * jar标准名称:jar版本号之前的名称为jar标准名称
	 * @param   String jarName  jar名称	 
	 * @return  standJarName   jar标准名称
	 */
	public static String createStdJarName(String jarName){		   
		   int pointJarIndex = jarName.indexOf(".jar");
		   if(pointJarIndex>0){
			   String handleJarName = jarName.substring(0,pointJarIndex);
			   String[] handlerJarNames = handleJarName.split("-");
			   //String standJarName = "";
			   StringBuilder standJarName = new StringBuilder();
			   for(String fragJarName:handlerJarNames){
				   if(isJarName(fragJarName)){
					  break; 
				   }else{
					   //standJarName+=fragJarName+"-";
					   standJarName.append(fragJarName+"-");
				   }
			   }
			   return standJarName.toString();
		   }else{
			   return jarName;
		   }
	}
	/**
	 * 判断是否版本号
	 * @param   String fragJarName  jar名称片段	 
	 * @return  boolean    true :是  false:否
	 */
	public static boolean isJarName(String fragJarName){
		   Pattern pattern = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.?[0-9]{0,3}");
		   return pattern.matcher(fragJarName).matches();
	}
	   
	public static void main(String[] args){		   		  
		   System.out.println(createStdJarName("aopalliance-1.0.jar"));
		   System.out.println(createStdJarName("galaxy-client-1.0.1-20150518.020846-4.jar"));
		   System.out.println(createStdJarName("galaxy-business-stria-1.0.1-SNAPSHOT.jar"));
		   //System.out.println(map);
	}
}
