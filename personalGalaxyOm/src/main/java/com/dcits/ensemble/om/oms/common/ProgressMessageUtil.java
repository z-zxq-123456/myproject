package com.dcits.ensemble.om.oms.common;

import com.dcits.ensemble.om.oms.module.utils.ProgressInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作进度通知工具类* 
 * @author tangxlf
 * @date 2015-11-05
 */
public class ProgressMessageUtil {

	public static Map<String,ProgressInfo> proMessMap = new HashMap<String,ProgressInfo>();
	
	/**
	 * 进度通知启动
	 * @param  String   userId      用户ID	 
	 * @param  String   ip          实际执行动作的机器IP  
	 * @param  String   appIntantName     应用实例名称	 
	 */		
	public static void startProgress(String userId,String  ip,String appIntantName){
		ProgressInfo info = new ProgressInfo();
		proMessMap.put(userId,info);
		info.setAppName(appIntantName);
		info.setIp(ip);
		info.setStartTime(getCurrentTime());				
	}
	
	
	/**
	 * 进度通知启动
	 * @param  String   userId      用户ID	 	  
	 */		
	public static void startProgress(String userId){
		startProgress(userId,null,null);		
	}
	
	/**
	 * 添加进度动作
	 * @param  String   userId      用户ID
	 * @param  String   actionName  动作名称	  
	 */		
	public static void addProgressAction(String userId,String  actionName){
		ProgressInfo info = 	proMessMap.get(userId);
		info.setActionName(actionName);
		info.addProgressStage();
	}
	/**
	 * 获取当前进度
	 * @param   String   userId      用户ID
	 * @return 	String[]  String[0]进度百分比  String[1]消息  
	 */		
	public static String[] getCurrentProgress(String userId){
		 try {
 			Thread.sleep(500);
 		} catch (InterruptedException e) {			
 			e.printStackTrace();
 		}
		ProgressInfo info = 	proMessMap.get(userId);
		String[] messArray = new String[2];
		if(info ==null){
			messArray[0] ="0";
			messArray[1] = "正在进行初始化，请稍等...！";
		}else{
			messArray[0] =""+computeProgress(info.getStartTime(),info.getProgressStage());
			messArray[1] = getProressMessage(info);
		}		
		return messArray;		
	}
	
	/**
	 * 进度通知停止
	 * @param  String   userId      用户ID	 
	 */		
	public static void stopProgress(String userId){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proMessMap.remove(userId);
		//proMessMap.remove(userId);
		//new ClearProgressThread(userId).start();
	}
	//得到当前时间
	private static long getCurrentTime(){
		return new Date().getTime();
	}
	//计算进度百分比
	private static int computeProgress(long   startTime,int progressStage){
		int cousmerNum = Math.round((getCurrentTime()-startTime)/100);				
		return Math.round((cousmerNum+progressStage*10)*100/(cousmerNum+200));
	}
	
	private static String getProressMessage(ProgressInfo info){
		StringBuilder sBuilder = new StringBuilder();
		if(info.getIp()!=null){
			sBuilder.append("机器IP:"+info.getIp()+" ");
		}
		if(info.getAppName()!=null){
			sBuilder.append("实例名:"+info.getAppName()+" ");
		}
		if(info.getActionName()!=null){
			sBuilder.append("<br/>正在执行"+info.getActionName()+"操作,请等待...<br/><br/>");
		}
		return sBuilder.toString();
	}
	
	
	public static void main(String[] args) throws InterruptedException{		
		ProgressMessageUtil.startProgress("1");
		String[] array1 = ProgressMessageUtil.getCurrentProgress("1");
		System.out.println("message="+array1[1] +"进度百分比为"+array1[0]);
		for(int i=0;i<15;i++){
			ProgressMessageUtil.addProgressAction("1", "action" + i);
			Thread.sleep(2000);
			String[] array = ProgressMessageUtil.getCurrentProgress("1");
			System.out.println("message="+array[1] +"进度百分比为"+array[0]);
		}		
		ProgressMessageUtil.stopProgress("1");
		String[] array2 = ProgressMessageUtil.getCurrentProgress("1");
		System.out.println("message="+array2[1] +"进度百分比为"+array2[0]);
	}
}
