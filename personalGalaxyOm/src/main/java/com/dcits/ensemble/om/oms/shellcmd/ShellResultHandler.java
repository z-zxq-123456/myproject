package com.dcits.ensemble.om.oms.shellcmd;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


/**
 * shellResult处理*
 * @author tangxlf
 * @date   2015-8-24
 */
 public class ShellResultHandler {
	
	/**
	* 解析SHELL执行结果，判断固定位置是否存在某值
	* @param ShellResult result shell执行结果 
	* @param int dataInd 输入结果的每条记录的需要检查的值 
	* @param String data 检查是否存在的数据 
	* @return  boolean true:存在   false:不存在
	*/	 
	public static boolean isExistValue(ShellResult result,int dataInd,String data){
		if(result.getExitStatus()== SysConfigConstants.SHELL_STATUS_FAIL){
			return false;
		}		
		StringTokenizer tokenStat = null;
		for(String resultStr:result.getOutList()){
			tokenStat = new StringTokenizer(resultStr);
            try{
			  for(int i=0;i<(dataInd+1);i++){
				  tokenStat.nextToken();
			  }
			  return tokenStat.nextToken().equals(data);
            }catch(NoSuchElementException e){
            	return false;
            }
		}
		return false;
	}
	
	
	/**
	* 解析固定位置SHELL执行结果
	* @param ShellResult result shell执行结果 
	* @param int dataInd 输入结果的每条记录的需要检查的值 	
	* @return  String[] 每条返回记录固定位置数组
	*/	 
//	public static String[] parseIndResult(ShellResult result,int dataInd){
//		List<String> outList = 	result.getOutList();
//		String[] resultArray = new String[outList.size()];		
//		StringTokenizer tokenStat = null;
//		for(int i=0;i<outList.size();i++){
//			tokenStat = new StringTokenizer(outList.get(i));
//            try{
//			  for(int a=0;a<dataInd;a++){
//				  tokenStat.nextToken();
//			  }
//			  resultArray[i]=tokenStat.nextToken();
//            }catch(NoSuchElementException e){
//              resultArray[i]=null;
//            }
//		}
//		return resultArray;
//	}
	/**
	* 解析固定位置SHELL执行结果
	* @param ShellResult result shell执行结果 
	* @param int dataInd 输入结果的每条记录的需要检查的值 	
	* @return  String[] 每条返回记录固定位置数组
	*/	 
	public static String[] parseIndResult(ShellResult result,int dataInd){
		List<String> outList = 	result.getOutList();
		String[] resultArray = new String[outList.size()];		
		String[] rowArray = null;
		for(int i =0;i<outList.size();i++){
			rowArray = outList.get(i).split("\\s{1,}");		
			if(dataInd+1>rowArray.length){
				resultArray[i]=null;
			}else{
				resultArray[i]=rowArray[dataInd];
			}
		}		
		return resultArray;		
	}
	
	public static void main(String[] args){
		ShellResult result = new ShellResult();
		result.addOutLine("tcp        0      0 0.0.0.0:9001                0.0.0.0:*                   LISTEN      2986/java");
		result.addOutLine("Not all processes could be identified, non-owned process info");
		result.addOutLine("will not be shown, you would have to be root to see it all");
		result.addOutLine("will not");
		//appStatus(result);
		String[] resultArray =parseIndResult(result,3);
		for(String str:resultArray){
			System.out.println(str);
		}
	}
}
