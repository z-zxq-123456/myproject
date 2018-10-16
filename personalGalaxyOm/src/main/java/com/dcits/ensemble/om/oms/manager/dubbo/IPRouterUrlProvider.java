package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP路由封url生成*
 * @author tangxlf
 * @date 2017-03-21
 */
public class IPRouterUrlProvider {
    private  final  static Logger log = LoggerFactory.getLogger(IPRouterUrlProvider.class.getClass());
    /**
     * 创建应用实例路由规则
     * @param	   service  服务名
     * @param	   intant  应用实例列表
     * @param	   providerUrl  提供者URL
     * @return	  URL  路由规则URL
     */
    public static URL createAppIntantRouter(String  service,EcmAppIntant intant,String providerUrl){
        SpelRoute route = new SpelRoute();
        EcmMidwareZkSer dubboService = new EcmMidwareZkSer();
        String handleConOrPro = DubboUtil.handleDubboStr(providerUrl);
        dubboService.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
        dubboService.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
        dubboService.setZkSerName(service);
        route.setName(createDubboServiceRouterRuleName(service));
        route.setEnabled(true);//指定规则是否生效
        route.setForce(true);//当路由结果为空时，是否强制执行。如果不强制执行，路由结果为空的路由规则将自动失效，相当于没有路由。
        route.setPriority(0);//路由规则的优先级，用于排序，优先级越大越靠前执行
        route.setService(DubboUtil.createDubboService(dubboService));//服务
        //route.setService("cc.study.dubbo.service.user.UserService:1.0.0");
        //服务提供者过滤规则
        StringBuilder filterRuleStrBld = new StringBuilder();
        filterRuleStrBld.append("!(#"+ Constants.PROVIDER+".get('host')+':'+#"+Constants.PROVIDER+".get('port')).equals('"+intant.getSerIp()+":"+intant.getAppPort()+"') ");
        route.setFilterRule(filterRuleStrBld.toString());
        log.info("router Url=" + route.toUrl().toString());
        return route.toUrl();
    }

    //创建dubbo服务IP路由规则名
    private static String createDubboServiceRouterRuleName(String serviceName){
        String[] serviceArray = serviceName.split("\\.");
        return serviceArray[serviceArray.length-1]+"_SerIPRule";
    }
}
