package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * dubbo路径工具类* 
 * @author tangxlf
 * @date 2016-03-21
 */
public class DubboUtil {
	private  final  static Logger log = LoggerFactory.getLogger(DubboUtil.class.getClass());
	public final static String PROVIDER_DIR_NAME= Constants.PROVIDERS_CATEGORY;//zookeeper注册中心提供者目录
	public final static String CONSUMER_DIR_NAME=Constants.CONSUMERS_CATEGORY;//zookeeper注册中心消费者目录
	public final static String ROUTER_DIR_NAME=Constants.ROUTERS_CATEGORY;//zookeeper注册中心路由规则目录
	public final static String DUBBO_DIR_NAME=Constants.DEFAULT_DIRECTORY;//zookeeper注册中心dubbo目录

	/**
	 * 处理获取到的dubbo串
	 * @param	   dubboStr  从ZK上获取到的URL
	 * @return	  String 翻译后的URL
	 */
	public static  String handleDubboStr(String dubboStr){
		return dubboStr.replace("%2F","/")
				.replace("%2B"," ")
				.replace("%3A",":")
				.replace("%3F","?")
				.replace("%3D","=")
				.replace("%26","&&")
				.replace("%255B","[")
				.replace("%255D","]")
				.replace("%253D","=")
				.replace("%253E",">")
				.replace("%2523","#")
				.replace("%2527","'")
				.replace("%2528","(")
				.replace("%2529",")")
				.replace("%2521","!")
				.replace("%252B","+")
				.replace("%252C",",")
				.replace("%253A",":");
	}



	/**
	 * 生成路由规则
	 * @param	   str  字符串处理对象
	 */
	public static  String stringHandler(String str) {
		return  str.split(Constants.RULE_KEY+"=")[1].trim().split("&&")[0];
	}


	/**
	 * 解析提供者URL获取 host 和  port
	 * @param	   url  提供者url
	 * @return	  String   host:ort
	 */
	public static  String parserProviderUrl(String url){
		String formatProvider=handleDubboStr(url);
		return formatProvider.split("[?]")[0].split("//")[1].split("/")[0];//?是正则表达式中的特殊字符，需要[?]来处理
	}


	//创建dubbo服务IP路由规则名
	public static String createDubboService(EcmMidwareZkSer dubboService){
		StringBuilder dubboServiceBld = new StringBuilder();
		if(!DataUtil.isNull(dubboService.getZkSerGrop())){
			dubboServiceBld.append(dubboService.getZkSerGrop()+"/");
		}
		dubboServiceBld.append(dubboService.getZkSerName());
		if(!DataUtil.isNull(dubboService.getZkVersion())){
			dubboServiceBld.append(":"+dubboService.getZkVersion());
		}
		return dubboServiceBld.toString();
	}

	/**
	 * 返回消费者或者提供者信息的IP端口号
	 * @param	  String conOrPro  提供者或者消费者路径下的具体信息 "/dubbo/服务名/提供者或者消费者/" 已经是 转义过后的字符串
	 * @return	  String  ZkIpPort IP端口号
	 */
	public static String findZkIpPort(String tempPath){
		return tempPath.split("//")[1].split("/")[0];
	}
	/**
	 * 返回消费者或者提供者信息的 版本号
	 * @param	  String conOrPro  提供者或者消费者路径下的具体信息 "/dubbo/服务名/提供者或者消费者/"已经是 转义过后的字符串
	 * @return	  String  ZkVsesion 如果有则返回版本号 ，没有返回空字符串
	 */
	public static String findZkRevision(String tempPath){
		if(tempPath.indexOf(Constants.REVISION_KEY)!=-1){
			return tempPath.split(Constants.REVISION_KEY+"=")[1].split("&&")[0];
		}else{
			return  "";
		}

	}

	/**
	 * 返回路由规则的 版本号
	 * @param	  String conOrPro  路由规则路径下的具体信息 "/dubbo/服务名/路由规则/"已经是 转义过后的字符串
	 * @return	  String  ZkVsesion 如果有则返回版本号 ，没有返回空字符串
	 */
	public static String findZkVersion(String tempPath){
		if(tempPath.indexOf(Constants.VERSION_KEY)!=-1){
			return tempPath.split(Constants.VERSION_KEY+"=")[1].split("&&")[0];
		}else{
			return  "";
		}

	}
	/**
	 * 返回消费者或者提供者信息的分组信息
	 * @param	  String conOrPro  提供者或者消费者路径下的具体信息 "/dubbo/服务名/提供者或者消费者/"已经是 转义过后的字符串
	 * @return	  String  ZkSerGrop   如果有则返回分组信息，没有返回空字符串
	 */
	public static String findZkSerGroup(String tempPath){
		if(tempPath.indexOf(Constants.GROUP_KEY)!=-1){
			return tempPath.split(Constants.GROUP_KEY+"=")[1].split("&&")[0];
		}else{
			return  "";
		}

	}

	public static void main(String[] args){
		List<EcmAppIntant> upgList  = new ArrayList<EcmAppIntant>();
		EcmAppIntant intant1 = new EcmAppIntant();
		intant1.setAppId(1);
		intant1.setSerIp("192.168.165.194");
		intant1.setAppPort("20777");
		EcmAppIntant intant2 = new EcmAppIntant();
		intant2.setAppId(1);
		intant2.setSerIp("192.168.165.194");
		intant2.setAppPort("20888");
		upgList.add(intant1);
		//upgList.add(intant2);
		//createAppIntantRouter("cc.study.dubbo.service.HelloWorldService",upgList);
		String serName="com.dcits.galaxy.dtp.branch.BranchManager";
		System.out.println(serName.split("\\.").length);
	}

}
