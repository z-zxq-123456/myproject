package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务控制生效失效路由规则生成*
 * @author tangxlf
 * @date 2017-03-21
 */
public class SerControlRouterUrlProvider {
    private  final  static Logger log = LoggerFactory.getLogger(SerControlRouterUrlProvider.class.getClass());

    /**
     * 创建dubbo服务IP路由规则
     * @param	   dubboService  dubbo服务信息
     * @return	  URL  路由规则URL
     */
    public static URL createDubboServiceRouter(EcmMidwareZkSer dubboService){
        System.out.println("dubboService=" + dubboService);
        SpelRoute route = new SpelRoute();
        route.setName(createRouterRuleName(dubboService.getZkSerName()));
        route.setEnabled(true);//指定规则是否生效
        route.setForce(true);//当路由结果为空时，是否强制执行。如果不强制执行，路由结果为空的路由规则将自动失效，相当于没有路由。
        route.setPriority(0);//路由规则的优先级，用于排序，优先级越大越靠前执行
        route.setService(DubboUtil.createDubboService(dubboService));//服务!(#"+Constants.PROVIDER+".get('host')+':'+#"+Constants.PROVIDER+".get('port'))
        //服务提供者过滤规则
        StringBuilder filterRuleStrBld = new StringBuilder();
        if(dubboService.getZkSerType().equals(SysConfigConstants.DUBBO_TYPE_PROVIDERS)){
            filterRuleStrBld.append("!(#" + Constants.PROVIDER + ".get('host')+':'+#" + Constants.PROVIDER + ".get('port')).equals('" + dubboService.getZkIpPort() + "') ");
            route.setFilterRule(filterRuleStrBld.toString());
        }else if(dubboService.getZkSerType().equals(SysConfigConstants.DUBBO_TYPE_CONSUMERS)){
            filterRuleStrBld.append("(#" + Constants.CONSUMER + ".get('host').equals('" + dubboService.getZkIpPort() + "')");
            route.setMatchRule(filterRuleStrBld.toString());
        }else{
            throw new GalaxyException("服务类型为："+dubboService.getZkSerType()+"传递有误,请检查!");
        }
        log.info("router Url=" + route.toUrl().toString());
        return route.toUrl();
    }
    //创建dubbo服务IP路由规则名
    private static String createRouterRuleName(String serviceName){
        String[] serviceArray = serviceName.split("\\.");
        return serviceArray[serviceArray.length-1]+"_SerControlRule";
    }
}
