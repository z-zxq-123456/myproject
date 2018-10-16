package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.URL;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Handler业务路由url生成*
 * @author tangxlf
 * @date 2017-03-21
 */
public class HandlerRouterUrlProvider {
    private  final  static Logger log = LoggerFactory.getLogger(HandlerRouterUrlProvider.class.getClass());

    private static final String SYS_HEAD_NAME = "SYS_HEAD";

    private static final String MESSAGE_CODE_NAME = "MESSAGE_CODE";

    private static final String MESSAGE_TYPE_NAME = "MESSAGE_TYPE";

    private static final String SERVICE_CODE_NAME = "SERVICE_CODE";

    private static final int   HANDLER_ARGS_POS = 0;
    /**
     * 创建handler正匹配服务路由规则
     * @return	  String   host:ort
     */
	public static URL createPosHandlerServiceRouter(EcmServRule rule,List<EcmAppIntant> upgList,String providerHandlerUrl){
		SpelRoute route = new SpelRoute();
		EcmMidwareZkSer dubboService = new EcmMidwareZkSer();
		String handleConOrPro = DubboUtil.handleDubboStr(providerHandlerUrl);
		dubboService.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
		dubboService.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
		dubboService.setZkSerName(rule.getHandlerClsName());
		route.setName(createServiceRuleName(rule.getServRuleId()));
		route.setEnabled(true);//指定规则是否生效
		route.setForce(true);//当路由结果为空时，是否强制执行。如果不强制执行，路由结果为空的路由规则将自动失效，相当于没有路由。
		route.setPriority(0);//路由规则的优先级，用于排序，优先级越大越靠前执行
		route.setService(DubboUtil.createDubboService(dubboService));//服务
		//route.setService("cc.study.dubbo.service.user.UserService:1.0.0");
		//服务提供者过滤规则
		//String matchRule = " !#method.equals('sayHello') and #arguments[0].get('id') == 1";//这种写法经过验证是可行的--map
		route.setMatchRule(createPosMatchArgs(rule));

		//服务提供者过滤规则
		StringBuilder filterRuleStrBld = new StringBuilder();
		for(int i=0; i<upgList.size();i++ ){
			if(i==(upgList.size()-1)){
				filterRuleStrBld.append("(#provider.get('host').equals('"+upgList.get(i).getSerIp()+"') and #provider.get('port').equals('"+upgList.get(i).getAppPort()+"') ) ");
			}else{
				filterRuleStrBld.append("(#provider.get('host').equals('"+upgList.get(i).getSerIp()+"') and #provider.get('port').equals('"+upgList.get(i).getAppPort()+"') ) or ");
			}
		}
		log.info(filterRuleStrBld.toString());
		route.setFilterRule(filterRuleStrBld.toString());
		return route.toUrl();
	}

    /**
     * 创建负匹配服务路由规则
     * @param	   service  服务名
     * @param	   host  实例IP
     * @param	  String port  实例端口
     * @return	  String   host:ort
     */
    public static URL createNegHandlerServiceRouter(EcmServRule rule,List<EcmAppIntant> upgList,String providerHandlerUrl){
        SpelRoute route = new SpelRoute();
        EcmMidwareZkSer dubboService = new EcmMidwareZkSer();
        String handleConOrPro = DubboUtil.handleDubboStr(providerHandlerUrl);
        dubboService.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
        dubboService.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
        dubboService.setZkSerName(rule.getHandlerClsName());
        route.setName(createServiceRuleName(rule.getServRuleId()));
        route.setEnabled(true);//指定规则是否生效
        route.setForce(true);//当路由结果为空时，是否强制执行。如果不强制执行，路由结果为空的路由规则将自动失效，相当于没有路由。
        route.setPriority(0);//路由规则的优先级，用于排序，优先级越大越靠前执行
        route.setService(DubboUtil.createDubboService(dubboService));//服务
        //route.setService("cc.study.dubbo.service.user.UserService:1.0.0");
        //服务提供者过滤规则
        //String matchRule = " !#method.equals('sayHello') and #arguments[0].get('id') == 1";//这种写法经过验证是可行的--map
        route.setMatchRule(createNegMatchArgs(rule));

        //服务提供者过滤规则
        StringBuilder filterRuleStrBld = new StringBuilder();
        for(int i=0; i<upgList.size();i++ ){
            if(i==(upgList.size()-1)){
                filterRuleStrBld.append("(#provider.get('host').equals('"+upgList.get(i).getSerIp()+"') and #provider.get('port').equals('"+upgList.get(i).getAppPort()+"') ) ");
            }else{
                filterRuleStrBld.append("(#provider.get('host').equals('"+upgList.get(i).getSerIp()+"') and #provider.get('port').equals('"+upgList.get(i).getAppPort()+"') ) or ");
            }
        }
        log.info(filterRuleStrBld.toString());
        route.setFilterRule(filterRuleStrBld.toString());
        return route.toUrl();
    }


    //生成正匹配条件
    private static String createPosMatchArgs(EcmServRule rule){
        StringBuilder matchArgsStrBuild = new StringBuilder(" #method.equals('"+rule.getHandlerClsMthName()+"') ");
        matchArgsStrBuild.append(" and ( #arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+MESSAGE_CODE_NAME+"').equals('"+rule.getMessageCode()+"') )");
        matchArgsStrBuild.append(" and ( #arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+MESSAGE_TYPE_NAME+"').equals('"+rule.getMessageType()+"') )");
        String serviceCode = rule.getServiceCode();
        if(!DataUtil.isNull(serviceCode)){
            matchArgsStrBuild.append(" and ( '"+serviceCode+"'.indexOf(#arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+SERVICE_CODE_NAME+"'))> -1 )");
        }

        matchArgsStrBuild.append(" and ( #arguments["+HANDLER_ARGS_POS+"]");
        String routerColCdn = rule.getRouterColCdn();
        String[] routerColArray = routerColCdn.split("[.]");
        if(routerColArray.length==2){
            matchArgsStrBuild.append( ".get('"+ JsonUtils.convertUpperCase(routerColArray[0])+"').get('"+JsonUtils.convertUpperCase(routerColArray[1])+"')");
        }else{
            throw new GalaxyException("路由规则字段"+routerColCdn+"设置有误，请仔细检查!");
        }
        matchArgsStrBuild.append(createOperatorAndVal(rule) +") ");
        log.info(matchArgsStrBuild.toString());
        return matchArgsStrBuild.toString();
    }
    //生成负匹配条件
    private static String createNegMatchArgs(EcmServRule rule){
        StringBuilder matchArgsStrBuild = new StringBuilder(" !#method.equals('"+rule.getHandlerClsMthName()+"') ");
        matchArgsStrBuild.append(" or !( #arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+MESSAGE_CODE_NAME+"').equals('"+rule.getMessageCode()+"') )");
        matchArgsStrBuild.append(" or !( #arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+MESSAGE_TYPE_NAME+"').equals('"+rule.getMessageType()+"') )");
        String serviceCode = rule.getServiceCode();
        if(!DataUtil.isNull(serviceCode)){
            matchArgsStrBuild.append(" or !( '"+serviceCode+"'.indexOf(#arguments["+HANDLER_ARGS_POS+"].get('"+SYS_HEAD_NAME+"').get('"+SERVICE_CODE_NAME+"'))> -1 )");
        }
        matchArgsStrBuild.append(" or !( #arguments["+HANDLER_ARGS_POS+"]");
        String routerColCdn = rule.getRouterColCdn();
        String[] routerColArray = routerColCdn.split("[.]");
        if(routerColArray.length==2){
            matchArgsStrBuild.append( ".get('"+ JsonUtils.convertUpperCase(routerColArray[0])+"').get('"+JsonUtils.convertUpperCase(routerColArray[1])+"')");
        }else{
            throw new GalaxyException("路由规则字段"+routerColCdn+"设置有误，请仔细检查!");
        }
        matchArgsStrBuild.append(createOperatorAndVal(rule) +") ");
        log.info(matchArgsStrBuild.toString());
        return matchArgsStrBuild.toString();
    }

    //生成匹配条件的操作符和值
    private static String createOperatorAndVal(EcmServRule rule){
        return ".equals('"+rule.getRouterCondVal() +"')";
    }

    //创建服务路由规则名
    private static String createServiceRuleName(Integer servRuleId){
        return servRuleId+"_SerHandlerRule";
    }
}
