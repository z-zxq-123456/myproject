package com.dcits.ensemble.om.oms.manager.dubbo;


import com.alibaba.dubbo.registry.Registry;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.I0Itec.zkclient.ZkClient;
import javax.annotation.Resource;
import java.util.List;


/**
 * 服务路由处理* 
 * @author tangxlf
 * @date 2016-03-24 
 */
@Component
public class ServiceRouterHandler {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UpgAppIntantCache upgAppIntantCache;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	DubboRouterHelp dubboRouterHelp;
	/**
	 * 添加先部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 * @param String  servRuleList 服务规则ID串
	 */
	public void addEarlyServiceRouterRule(Integer appId, String userId, String servRuleList) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getRuleList(appId,servRuleList);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注具体服务，有关应用实例的路由封 ，同时注册 测试用业务路由规则
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(), zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			parseBusService(serRule.getAppSerClsnm(), serRule);
			for (EcmAppIntant intant : earlyUpgList) {//解注先升级应用实例路由规则
				registry.unregister(IPRouterUrlProvider.createAppIntantRouter(serRule.getAppSerClsnm(), intant,providerUrl));//解注路由规则
				registry.unregister(IPRouterUrlProvider.createAppIntantRouter(serRule.getHandlerClsName(), intant,handlerProviderUrl));//解注hanlder路由规则
			}
			registry.register(BusRouterUrlProvider.createPosServiceRouter(serRule, earlyUpgList, providerUrl));//注册业务路由正规则
			registry.register(BusRouterUrlProvider.createNegServiceRouter(serRule, lateUpgList, providerUrl));//注册业务路由反规则
			registry.register(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//注册handler服务业务路由正规则
			registry.register(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//注册handler服务业务路由反规则
		}
		zkc.close();
		//registry.destroy();
	}

	/**
	 * 取消先部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 * @param String  servRuleList 服务规则ID串
	 */
	public void cancelEarlyServiceRouterRule(Integer appId, String userId, String servRuleList) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getRuleList(appId, servRuleList);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注测试用业务路由规则 ，同时注册 具体服务有关应用实例的路由封
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(),zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			parseBusService(serRule.getAppSerClsnm(), serRule);
			for (EcmAppIntant intant : earlyUpgList) {//注册先升级应用实例路由规则
				registry.register(IPRouterUrlProvider.createAppIntantRouter(serRule.getAppSerClsnm(), intant, providerUrl));//注册路由规则
				registry.register(IPRouterUrlProvider.createAppIntantRouter(serRule.getHandlerClsName(), intant, handlerProviderUrl));//注册hanlder路由规则
			}
			registry.unregister(BusRouterUrlProvider.createPosServiceRouter(serRule, earlyUpgList, providerUrl));//解注路由规则
			registry.unregister(BusRouterUrlProvider.createNegServiceRouter(serRule, lateUpgList, providerUrl));

			registry.unregister(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//解注handler服务业务路由正规则
			registry.unregister(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//解注handler服务业务路由反规则
		}
		zkc.close();
		//registry.destroy();
	}

	/**
	 * 添加后部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 * @param String  servRuleList 服务规则ID串
	 */
	public void addLateServiceRouterRule(Integer appId, String userId, String servRuleList) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getRuleList(appId, servRuleList);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注具体服务有关应用实例的路由封 ，同时注册 测试用业务路由规则
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(), zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			registry.register(BusRouterUrlProvider.createPosServiceRouter(serRule, lateUpgList, providerUrl));
			registry.register(BusRouterUrlProvider.createNegServiceRouter(serRule, earlyUpgList, providerUrl));
			parseBusService(serRule.getAppSerClsnm(), serRule);
			registry.register(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//注册handler服务业务路由正规则
			registry.register(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//注册handler服务业务路由反规则
			for (EcmAppIntant intant : lateUpgList) {//解注后升级应用实例路由规则
				registry.unregister(IPRouterUrlProvider.createAppIntantRouter(serRule.getAppSerClsnm(), intant, providerUrl));//解注路由规则
				registry.unregister(IPRouterUrlProvider.createAppIntantRouter(serRule.getHandlerClsName(), intant,handlerProviderUrl));//解注hanlder路由规则
			}
		}
		zkc.close();
		//registry.destroy();
	}

	/**
	 * 取消后部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 * @param String  servRuleList 服务规则ID串
	 */
	public void cancelLateServiceRouterRule(Integer appId, String userId) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getLateRuleList(appId);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注测试用业务路由规则 ，同时注册 具体服务有关应用实例的路由封
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(), zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			parseBusService(serRule.getAppSerClsnm(), serRule);
			for (EcmAppIntant intant : lateUpgList) {//注册后升级应用实例路由规则
				registry.register(IPRouterUrlProvider.createAppIntantRouter(serRule.getAppSerClsnm(), intant,providerUrl));//注册路由规则
				registry.register(IPRouterUrlProvider.createAppIntantRouter(serRule.getHandlerClsName(), intant, handlerProviderUrl));//注册hanlder路由规则
			}
			registry.unregister(BusRouterUrlProvider.createPosServiceRouter(serRule, lateUpgList, providerUrl));
			registry.unregister(BusRouterUrlProvider.createNegServiceRouter(serRule, earlyUpgList, providerUrl));

			registry.unregister(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//解注handler服务业务路由正规则
			registry.unregister(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//解注handler服务业务路由反规则
		}
		zkc.close();
		//registry.destroy();
	}

	/**
	 * 删除先部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 */
	public void removeEarlyServiceRouterRule(Integer appId, String userId) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getEarlyRuleList(appId);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注测试用业务路由规则
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(), zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			registry.unregister(BusRouterUrlProvider.createPosServiceRouter(serRule, earlyUpgList,providerUrl));//解注路由规则
			registry.unregister(BusRouterUrlProvider.createNegServiceRouter(serRule, lateUpgList,providerUrl));
			parseBusService(serRule.getAppSerClsnm(), serRule);
			registry.unregister(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//解注handler服务业务路由正规则
			registry.unregister(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//解注handler服务业务路由反规则
		}
		zkc.close();
		//registry.destroy();
	}

	/**
	 * 删除后部署实例测试用业务路由规则
	 *
	 * @param Integer appId   应用ID
	 * @param String  userId  用户ID
	 */
	public void removeLateServiceRouterRule(Integer appId, String userId) {
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<EcmServRule> ruleList = dubboRouterHelp.getLateRuleList(appId);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		ZkClient zkc = new ZkClient(zkUrl, 5000);
		//解注测试用业务路由规则
		for (EcmServRule serRule : ruleList) {
			String providerUrl = dubboRouterHelp.getProviderUrl(serRule.getAppSerClsnm(), zkc, hostAndPorts);
			String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
			String handlerProviderUrl = dubboRouterHelp.getProviderUrl(handlerClsName, zkc, hostAndPorts);
			registry.unregister(BusRouterUrlProvider.createPosServiceRouter(serRule, lateUpgList,providerUrl));//解注路由规则
			registry.unregister(BusRouterUrlProvider.createNegServiceRouter(serRule, earlyUpgList,providerUrl));
			parseBusService(serRule.getAppSerClsnm(), serRule);
			registry.unregister(HandlerRouterUrlProvider.createPosHandlerServiceRouter(serRule, lateUpgList, handlerProviderUrl));//解注handler服务业务路由正规则
			registry.unregister(HandlerRouterUrlProvider.createNegHandlerServiceRouter(serRule, earlyUpgList, handlerProviderUrl));//解注handler服务业务路由反规则
		}
		zkc.close();
		//registry.destroy();
	}


	//解析messagecode | messagetype | servicecode
	private void parseBusService(String busServiceName,EcmServRule rule){
		String[] array = busServiceName.split("[.]");
		String  simpleServiceName = array[array.length-1];
        int len = simpleServiceName.length();
		String serviceCode = simpleServiceName.substring(0, len - 8);
		String messageType = simpleServiceName.substring(len-8,len-4);
		String messageCode = simpleServiceName.substring(len-4);
		List<EcmParam>  paramList = paramComboxService.getParaDetailList(serviceCode);
		StringBuilder serviceClsName = new StringBuilder("");
		for(EcmParam param : paramList){
			serviceClsName.append(param.getRemark1()+",");
		}
		String handlerClsName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_CLASSNAME);
		String handlerMtdName = paramComboxService.getParaRemark1(SysConfigConstants.HANDLER_SERVICE_MTDNAME);
		log.info("serviceClsName:" + serviceClsName + "serviceCode:" + serviceCode + "  messageCode:" + messageCode +  " messageType:" + messageType);
		rule.setHandlerClsName(handlerClsName);
		rule.setHandlerClsMthName(handlerMtdName);
		rule.setMessageCode(messageCode);
		rule.setMessageType(messageType);
		rule.setServiceCode(serviceClsName.toString());
	}


//	public static void main(String[] args){
//		parseBusService("com.txl.IFin90001004",null);
//	}
}
