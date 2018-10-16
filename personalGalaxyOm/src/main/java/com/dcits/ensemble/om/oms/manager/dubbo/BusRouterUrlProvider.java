package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.URL;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 业务路由url生成*
 * @author tangxlf
 * @date 2017-03-21
 */
public class BusRouterUrlProvider {
    private  final  static Logger log = LoggerFactory.getLogger(BusRouterUrlProvider.class.getClass());
    /**
     * 创建正匹配服务路由规则
     * @return	  String   host:ort
     */
    public static URL createPosServiceRouter(EcmServRule rule,List<EcmAppIntant> upgList,String providerUrl){
        SpelRoute route = new SpelRoute();
        EcmMidwareZkSer dubboService = new EcmMidwareZkSer();
        String handleConOrPro = DubboUtil.handleDubboStr(providerUrl);
        dubboService.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
        dubboService.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
        dubboService.setZkSerName(rule.getAppSerClsnm());
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
    public static URL createNegServiceRouter(EcmServRule rule,List<EcmAppIntant> upgList,String providerUrl){
        SpelRoute route = new SpelRoute();
        EcmMidwareZkSer dubboService = new EcmMidwareZkSer();
        String handleConOrPro = DubboUtil.handleDubboStr(providerUrl);
        dubboService.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
        dubboService.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
        dubboService.setZkSerName(rule.getAppSerClsnm());
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

    //创建服务路由规则名
    private static String createServiceRuleName(Integer servRuleId){
        return servRuleId+"_SerBusRule";
    }
    //生成正匹配条件
    private static String createPosMatchArgs(EcmServRule rule){
        StringBuilder matchArgsStrBuild = new StringBuilder(" #method.equals('"+rule.getSerMtdEnm()+"') ");
        if(rule.getServRuleType().equals(SysConfigConstants.USER_DEFINED)){
            matchArgsStrBuild.append(" and ( "+ rule.getServRuleSelf() +  " )");
        }else{
            matchArgsStrBuild.append(" and ( #arguments["+ rule.getRouterArgsPos() +  "]");
            if(rule.getRouterArgsType().equals(SysConfigConstants.ROUTER_ARGS_MAP)){
                matchArgsStrBuild.append( ".get('"+rule.getRouterColCdn()+"')");
            }else if(rule.getRouterArgsType().equals(SysConfigConstants.ROUTER_ARGS_JAVABEAN)){
                matchArgsStrBuild.append( "."+rule.getRouterColCdn());
            }
            matchArgsStrBuild.append(createOperatorAndVal(rule) +") ");
        }
        log.info(matchArgsStrBuild.toString());        ;
        return matchArgsStrBuild.toString();
    }
    //生成负匹配条件
    private static String createNegMatchArgs(EcmServRule rule){
        StringBuilder matchArgsStrBuild = new StringBuilder(" !#method.equals('"+rule.getSerMtdEnm()+"') ");
        if(rule.getServRuleType().equals(SysConfigConstants.USER_DEFINED)){
            matchArgsStrBuild.append(" or !( "+ rule.getServRuleSelf() +  " )");
        }else{
            matchArgsStrBuild.append(" or !( #arguments["+ rule.getRouterArgsPos() +  "]");
            if(rule.getRouterArgsType().equals(SysConfigConstants.ROUTER_ARGS_MAP)){
                matchArgsStrBuild.append( ".get('"+rule.getRouterColCdn()+"')");
            }else if(rule.getRouterArgsType().equals(SysConfigConstants.ROUTER_ARGS_JAVABEAN)){
                matchArgsStrBuild.append( "."+rule.getRouterColCdn());
            }
            matchArgsStrBuild.append(createOperatorAndVal(rule) +") ");
        }
        log.info(matchArgsStrBuild.toString());
        return matchArgsStrBuild.toString();
    }

    //生成匹配条件的操作符和值
    private static String createOperatorAndVal(EcmServRule rule){
        //操作符是等于  路由字段类型是字符时 返回值为 例如：.equals('2')
        if(rule.getRouterCondOper().equals(SysConfigConstants.OPERATOR_EQUAL)&&rule.getRouterColType().equals(SysConfigConstants.ROUTER_COL_TYPE_STR)){
            return ".equals('"+rule.getRouterCondVal() +"')";
        }
        //操作符是等于  路由字段类型是数字时 返回值为 例如：== 2
        if(rule.getRouterCondOper().equals(SysConfigConstants.OPERATOR_EQUAL)&&rule.getRouterColType().equals(SysConfigConstants.ROUTER_COL_TYPE_NUM)){
            return " == "+rule.getRouterCondVal();
        }
        throw new GalaxyException("未定义的路由条件操作符:"+rule.getRouterCondOper());
    }
}
