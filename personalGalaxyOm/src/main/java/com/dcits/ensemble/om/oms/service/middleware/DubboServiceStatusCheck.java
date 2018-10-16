package com.dcits.ensemble.om.oms.service.middleware;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.module.middleware.DubboRouterInfo;
import com.dcits.ensemble.om.oms.module.middleware.DubboServiceRouterRuleList;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.Constants;



/**
 * dubbo服务检查* 
 * @author tangxlf
 * @date 2016-06-12
 */
@Component
public class DubboServiceStatusCheck {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String ROUTER_RULE_SPLIT = "=>";//路由规则消费者匹配条件和提供者的过滤条件的分隔符
	@Resource
	ParamComboxService paramComboxService;
	
	private static final Map<String,DubboServiceRouterRuleList> serviceRouterListMap
	                                      = new HashMap<String,DubboServiceRouterRuleList>();//dubbo服务路由规则信息集合 k为服务路径 V为该服务路由规则信息集合
	
   /**
    * @param   String servicePath      服务路径		
	* @return  List<DubboRouterInfo>   服务路由规则信息集合
	*/
    public  DubboServiceRouterRuleList getServiceRuleList(ZkClient zkc,String servicePath) {		
		if(!serviceRouterListMap.containsKey(servicePath)){
			initServiceRouterListMap(zkc,servicePath);
		}
		return serviceRouterListMap.get(servicePath);
	}
	//初始化服务路由规则信息集合
	private void initServiceRouterListMap(ZkClient zkc,String servicePath){
		String routerPath = servicePath +"/"+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS);
		DubboServiceRouterRuleList routerInfoList = new DubboServiceRouterRuleList();
		if(zkc.exists(routerPath)){
			List<String> routerList =zkc.getChildren(routerPath);
		    for(String routerUrl:routerList){
		    	DubboRouterInfo routerInfo = new DubboRouterInfo();
		    	routerUrl = DubboUtil.handleDubboStr(routerUrl);
		    	String[] routerArray  = DubboUtil.stringHandler(routerUrl).split(ROUTER_RULE_SPLIT);
		    	if(!DataUtil.isNull(routerArray[0])&&!DataUtil.isNull(routerArray[1])){
		    		continue;
		    	}
		    	log.info("routerUrl="+ routerUrl);
		    	routerInfo.setGroup(DubboUtil.findZkSerGroup(routerUrl));
		    	routerInfo.setVersion(DubboUtil.findZkVersion(routerUrl));
		    	if(!DataUtil.isNull(routerArray[0])){//消费者规则
		    		routerInfo.setConsumerBlackName(parseCousumerIpStr(routerArray[0]));
		    		routerInfoList.addConsumerRuleInfo(routerInfo);
		    		continue;
		    	}
		    	if(!DataUtil.isNull(routerArray[1])){//提供者规则
		    		routerInfo.setProviderWhiteName(parseProviderIpStr(routerArray[1]));
		    		routerInfoList.addProviderRuleInfo(routerInfo);
		    	}
		    }
		}
		serviceRouterListMap.put(servicePath,routerInfoList);
	}
	//解析消费者路由规则中的黑名单IP
	private String parseCousumerIpStr(String consumerRuleStr){		
		return parseIp(consumerRuleStr,Constants.CONSUMER);
	}
	//解析消费者路由规则中的白名单IP加端口
	private String parseProviderIpStr(String providerRuleStr){
		return parseIpPort(providerRuleStr,Constants.PROVIDER);
	}
	//取IP
	private String parseIp(String iPStr,String argsMark){
		return iPStr.split("#"+argsMark+".get\\('host'\\).equals\\('")[1].split("'\\)")[0];
	}
	//取IPandPort--提供者路由规则 !(#provider.get('host')+':'+#provider.get('port')).equals('192.168.165.197:20777')
	private String parseIpPort(String portStr,String argsMark){
		log.info("portStr="+ portStr);
		//return portStr.split("#"+argsMark+".get\\('port'\\).equals\\('")[1].split("'\\)")[0];	
		//System.out.println("#"+argsMark+".get\\('host'\\)+':'+#"+argsMark+".get\\('port'\\)\\).equals\\('");
		return portStr.split("#"+argsMark+".get\\('host'\\)\\+':'\\+#"+argsMark+".get\\('port'\\)\\).equals\\('")[1].split("'\\)")[0];
	}
	
	/**
	 *当节点序号发生修改时，清空缓存。
	 */
	public void removeServiceRouterLis(){
		serviceRouterListMap.clear();
	}
	

	/**
	 * 检查服务状态
	 * @param ZkClient  zkc                   zk客户端
	 * @param String    servicePath           服务路径	 
	 * @param String    serviceUrl            具体的服务URL    
	 * @param String    serviceType           服务类型--0062001：providers 0062002：consumers 
	 * @return String   0001001：有效   0001002：无效
	 */ 	
	public  String checkServeicStatus(ZkClient zkc,String servicePath,String serviceUrl,String serviceType) {
		DubboServiceRouterRuleList ruleList = getServiceRuleList(zkc,servicePath);
		log.info("ruleList ="+ruleList +" serviceUrl="+serviceUrl);
		if(serviceType.equals(SysConfigConstants.DUBBO_TYPE_PROVIDERS)){
			   List<DubboRouterInfo> providerRuleList = ruleList.getProviderRuleList();
			   if(providerRuleList.size()==0){//没有提供者路由规则，返回有效
				   return SysConfigConstants.STATUS_OK;
			   }
			   for(DubboRouterInfo routerInfo:providerRuleList){
				  if(isSameGroup(serviceUrl,routerInfo)&&isSameVersion(serviceUrl,routerInfo)){
						if(isProviderMatchRouterRule(serviceUrl,routerInfo)){//提供者设置的是白名单，若匹配符合则为有效
							return SysConfigConstants.STATUS_FAIL;
						}
				  }
			   }
			   //若所有提供者规则都没有匹配的，则为无效
			   return SysConfigConstants.STATUS_OK;
		}
        if(serviceType.equals(SysConfigConstants.DUBBO_TYPE_CONSUMERS)){
        	   List<DubboRouterInfo> consumerRuleList = ruleList.getConsumerRuleList();
			   if(consumerRuleList.size()==0){//没有消费者路由规则，返回有效
				   return SysConfigConstants.STATUS_OK;
			   }
			   for(DubboRouterInfo routerInfo:consumerRuleList){
				  if(isSameGroup(serviceUrl,routerInfo)&&isSameVersion(serviceUrl,routerInfo)){//消费者设置的是黑名单，若匹配符合则为无效
						if(isConsumerMatchRouterRule(serviceUrl,routerInfo)){
							return SysConfigConstants.STATUS_FAIL;
						}
				  }
			   }
			    //若所有消费者规则都没有匹配的，则为有效
			   return SysConfigConstants.STATUS_OK; 
		}
		return SysConfigConstants.STATUS_OK;
    }
	//检查提供者状态
	private  boolean  isProviderMatchRouterRule(String serviceUrl,DubboRouterInfo routerInfo){
		return DubboUtil.findZkIpPort(serviceUrl).indexOf(routerInfo.getProviderWhiteName())>=0;
	}
	//检查消费者状态
	private  boolean  isConsumerMatchRouterRule(String serviceUrl,DubboRouterInfo routerInfo){
		return DubboUtil.findZkIpPort(serviceUrl).indexOf(routerInfo.getConsumerBlackName())>=0;
	}
	//组名是否相同
	private boolean isSameGroup(String serviceUrl,DubboRouterInfo routerInfo){
		return DubboUtil.findZkSerGroup(serviceUrl).equals(routerInfo.getGroup());		
	}
	//版本是否相同
	private boolean isSameVersion(String serviceUrl,DubboRouterInfo routerInfo){
		return DubboUtil.findZkVersion(serviceUrl).equals(routerInfo.getVersion());
	}
	
	
	public static void main(String[] args){
		String rule ="route://0.0.0.0/cc.study.dubbo.service.HelloWorldService?category=routers&&dynamic=false&&enabled=true&&force=true&&name=routeSpelHello20888&&priority=0&&router=spel&&rule=   =>  !(#provider.get('host')+':'+#provider.get('port')).equals('192.168.165.197:20777')&&runtime=true";
	    String routerRule  = DubboUtil.stringHandler(rule);
	    //System.out.println("ip="+new DubboServiceStatusCheck().parseIp(rule,Constants.PROVIDER));
	    String[] array = routerRule.split("=>");
	    if(array[0]==null){
	    	System.out.println("null");
	    }
        if(array[0]!=null&&array[0].equals("")){
        	System.out.println(" kong c");
	    }
	    System.out.println(array[0]);
	    System.out.println(array[1]);
	}
}
