package com.dcits.ensemble.om.oms.manager.server;

import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器监控抽象实现* 
 * @author tangxlf
 * @date 2016-01-18 
 */
@Component
public abstract class AbstractedMonitorServer implements IMonitorServer{
	
	@Resource
	ShellExcuteService shellService;
	@Resource
	CmdFactory  cmdFactory;
	
	public static final String CPU_MARK="oms_cpu";//shell访问服务器信息返回结果cpu信息起始标志
	public static final String MEM_MARK="oms_mem";//shell访问服务器信息返回结果内存信息起始标志
	public static final String IO_MARK="oms_io";//shell访问服务器信息返回结果磁盘IO信息起始标志
	public static final String NET_MARK="oms_net";//shell访问服务器信息返回结果网络信息起始标志
	public static final String MEMORY_MARK="MemoryMessage";//获取MemoryMessage标志
	public static final String IO_MESSAGE_MARK="IOMessage";//获取IOMessage标志
	public static final String FIRST_NET_MARK="FirstNetMessage";//第一次获取NetMessage标志
	public static final String SECOND_NET_MARK="SecondNetMessage";//第二次获取NetMessage标志
	public static final String CPU_SLEEPTIME="0.005";//执行完第一次cpu访问指令后的休眠0.005s
	public static final String NET_SLEEPTIME="1";//在执行完第一次NET访问指令后休眠1s
	
	
   /**
	* 执行监控服务器相关信息	 
	* @param EcmServerInfo serverInfo    服务器信息 	 
	* @return Map<String,String>         服务器相关信息
	*/
	public Map<String,String> executeMonitor(EcmServerInfo serverInfo ){
		return 	parseResult(exceuteShell(serverInfo));
	}
   /**
	* 执行监控服务器相关shell	 
	* @param EcmServerInfo serverInfo    服务器信息 	 
	* @return List<String>               shell执行返回结果
	*/
	public List<String> exceuteShell(EcmServerInfo serverInfo){
		String cmd = makeCmd(serverInfo);
		ShellResult result=shellService.doCmd(serverInfo,cmd);	
		return result.getOutList();
	}
   /**
	* 解析shell执行结果	 
	* @param List<String> resultList   shell执行返回结果
	* @return Map<String,String>       解析结果     
	*/	
	public Map<String,String> parseResult(List<String> resultList){
		Map<String,String>  monitorMap = new HashMap<String,String>();
		
		monitorMap.put(CPU_MARK,parseCpuInfo(resultList));
		monitorMap.put(MEM_MARK,parseMemInfo(resultList));
		monitorMap.put(NET_MARK,parsenNetInfo(resultList));
		monitorMap.put(IO_MARK,parseIoInfo(resultList));
		return monitorMap;
	}
   /**
	* 解析cpu信息	 
	* @param  List<String> resultList  shell中截取的cpu结果   	 
	* @return String               cpu信息
	*/	
	abstract String parseCpuInfo(List<String> resultList);
   /**
	* 解析mem信息	 
	* @param  List<String> resultList  shell中截取的mem结果   	 
	* @return String               mem信息
	*/	
	abstract String parseMemInfo(List<String> resultList);
   /**
	* 解析net信息	 
	* @param  List<String> resultList  shell中截取的net结果   	 
	* @return String               net信息
	*/	
	abstract String parsenNetInfo(List<String> resultList);
   /**
	* 解析IO信息	 
	* @param  List<String> resultList  shell中截取的IO结果   	 
	* @return String               IO信息
	*/	
	abstract String parseIoInfo(List<String> resultList);
	/**
	* 产生适应本系统的shell命令	 
	* @param  EcmServerInfo serverInfo  服务器信息	 
	* @return String             shell命令
	*/	
	abstract String makeCmd(EcmServerInfo serverInfo);
}
