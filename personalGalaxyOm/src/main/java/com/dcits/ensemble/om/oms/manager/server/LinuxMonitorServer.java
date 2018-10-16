package com.dcits.ensemble.om.oms.manager.server;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * linux服务器监控实现*
 * @author luolang
 * @date 2016-01-28
 */
@Component
public class LinuxMonitorServer extends AbstractedMonitorServer{
	public static final String FIRST_CPU_MARK="FirstCpuMessage";//第一次获取CPUmessage标志
	public static final String SECOND_CPU_MARK="SecondCpuMessage";//第二次获取CPUmessage标志
	private final static int   IntegerDigits = 2; //小数点前保留位数
	private final static int   FractionDigits = 2; //小数点前保留位数
	/**
	 * 解析cpu信息
	 * @param  List<String> resultList  shell中截取的cpu结果
	 * @return String               cpu信息
	 */
	@Override
	String parseCpuInfo(List<String> resultList) {
		float cpuUsage ;
		long idleCpuTime1 = 0, totalCpuTime1 = 0;
		long idleCpuTime2 = 0, totalCpuTime2 = 0;
		String cpuMessage ;
		cpuMessage =resultList.get( resultList.indexOf(FIRST_CPU_MARK)+1);
		cpuMessage = cpuMessage.trim();
		String[] temp1 = cpuMessage.split("\\s+");
		idleCpuTime1 = Long.parseLong(temp1[4]);
		for(String s : temp1){
			if(!s.equals("cpu")){
				totalCpuTime1 += Long.parseLong(s);
			}
		}
		cpuMessage =resultList.get( resultList.indexOf(SECOND_CPU_MARK)+1);
		cpuMessage = cpuMessage.trim();
		String[] temp2 = cpuMessage.split("\\s+");
		idleCpuTime2 = Long.parseLong(temp2[4]);
		for(String s : temp2){
			if(!s.equals("cpu")){
				totalCpuTime2 += Long.parseLong(s);
			}
		}
		cpuUsage = 1 - (float)(idleCpuTime2 - idleCpuTime1)/(float)(totalCpuTime2 - totalCpuTime1);
		String cpuResult = getPercentFormat(cpuUsage,IntegerDigits,FractionDigits);
		return cpuResult;
	}
	/**
	 * 解析mem信息
	 * @param  List<String> resultList  shell中截取的mem结果
	 * @return String               mem信息
	 */
	@Override
	String parseMemInfo(List<String> resultList) {
		long totalMem = 0, freeMem = 0;
		float memUsage ;
		for (String str :resultList) {
			if(str.startsWith("MemTotal")){
				String[] memInfo = str.split("\\s+");
				totalMem = Long.parseLong(memInfo[1]);
			}
			if(str.startsWith("MemFree")){
				String[] memInfo = str.split("\\s+");
				freeMem = Long.parseLong(memInfo[1]);
				break;
			}
		}
		memUsage = 1- (float)freeMem/(float)totalMem;
		String memResult = getPercentFormat(memUsage,IntegerDigits,FractionDigits);
		return memResult;
	}
	/**
	 * 解析net信息
	 * @param  List<String> resultList  shell中截取的net结果
	 * @return String               net信息
	 */
	@Override
	String parsenNetInfo(List<String> resultList) {
		//float netUsage ;  //带宽占用比例
		long inSize1 = 0, outSize1 = 0 ,inSize2 = 0, outSize2 = 0;
		float interval = 0;
		String netMessage;
		netMessage = resultList.get(resultList.indexOf(FIRST_NET_MARK)+1);
		netMessage = netMessage.trim();
		netMessage =netMessage.split(":")[1].trim();
		String[] temp1 = netMessage.split("\\s+");
		inSize1 = Long.parseLong(temp1[0]); //Receive bytes,单位为Byte
		outSize1 = Long.parseLong(temp1[8]);

		netMessage = resultList.get(resultList.indexOf(SECOND_NET_MARK)+1);
		netMessage = netMessage.trim();
		netMessage =netMessage.split(":")[1].trim();
		String[] temp2 = netMessage.split("\\s+");
		inSize2 = Long.parseLong(temp2[0]); //Receive bytes,单位为Byte
		outSize2 = Long.parseLong(temp2[8]);
		for(int i =resultList.indexOf(SECOND_NET_MARK)+1;i<resultList.size();i++) {
			if(resultList.get(i).startsWith("real")) {
				String realTime = resultList.get(i);
				realTime = realTime.trim();
				String[] tempTime = realTime.split("\\s+");
				realTime = tempTime[1];
				realTime = realTime.replace("0m","");
				realTime = realTime.replace("s","");
				interval = Float.parseFloat(realTime);
			}
		}
		float curRate = (float)(inSize2 - inSize1 + outSize2 - outSize1)*8/(1000*interval);
		// netUsage = curRate/TotalBandwidth;  //带宽占用比例
		DecimalFormat df = new DecimalFormat("0.00");
		String netResult = df.format(curRate)+"KB/S";
		return netResult;

	}
	/**
	 * 解析IO信息
	 * @param  List<String> resultList  shell中截取的IO结果
	 * @return String               IO信息
	 */
	@Override
	String parseIoInfo(List<String> resultList) {
		float ioUsage = 0.0f;
		int m = resultList.indexOf(IO_MESSAGE_MARK);
		int  n = resultList.indexOf(FIRST_NET_MARK);
		for (int i = m+4 ;i<n ;i++) {

			String str = resultList.get(i);
			String[] temp = str.split("\\s+");
			if(temp.length > 1){
				float util =  Float.parseFloat(temp[temp.length-1]);
				ioUsage = (ioUsage>util)?ioUsage:util;
			}
		}
		if(ioUsage > 0){
			ioUsage /= 100;
		}
		String ioResult = getPercentFormat(ioUsage,IntegerDigits,FractionDigits);
		return ioResult;
	}
	/**
	 * 产生适应本系统的shell命令
	 * @param  EcmServerInfo serverInfo  服务器信息
	 * @return String             shell命令
	 */
	@Override
	String makeCmd(EcmServerInfo serverInfo){
		ICmd linuxCmd = cmdFactory.getCmd(serverInfo.getSerOs());
		String timeArgs ="("+linuxCmd.netCmd(null)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.sleepCmd(NET_SLEEPTIME)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.echoCmd(SECOND_NET_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.netCmd(null)+")";
		String cmd =linuxCmd.echoCmd(FIRST_CPU_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.cpuCmd(null)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.sleepCmd(CPU_SLEEPTIME)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.echoCmd(SECOND_CPU_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.cpuCmd(null)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.echoCmd(MEMORY_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.memoryCmd(null)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.echoCmd(IO_MESSAGE_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.ioCmd(null)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.echoCmd(FIRST_NET_MARK)+SysConfigConstants.SHELL_LINK_SIGN+
				linuxCmd.timeCmd(timeArgs);
		return  cmd;
	}

	public   String getPercentFormat(float d,int IntegerDigits,int FractionDigits){
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(IntegerDigits);//小数点前保留几位
		nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		String str = nf.format(d);
		return str;
	}


}
