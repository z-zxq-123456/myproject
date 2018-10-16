package com.dcits.ensemble.om.oms.manager.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.serv.EcmServRule;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dubbo路由辅助类*
 * @author tangxlf
 * @date 2017-03-22
 */
@Component
public class DubboRouterHelp {

    @Resource
    private UpgAppIntantCache upgAppIntantCache;
    @Resource
    private OMSIDao omsBaseDao;

    private static final String FIND_SER_RULE_LIST_SQL_ID = "findSerRuleList";//设置应用对应业务路由规则查询SQLID

    private static final String FIND_CLEAR_SER_RULE_LIST_SQL_ID = "findClearSerRuleList";//清除应用对应业务路由规则查询SQLID
       /**
     * 获取dubbo的Registry	 *
     * @param  Integer appId   应用ID
     * @return Registry
     */
    public  Registry getRegistry(Integer appId){
            RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
            return registryFactory.getRegistry(URL.valueOf(upgAppIntantCache.getDubboRegistryUrl(appId)));
    }
    /**
     * 获取业务验证路由规则列表	 *
     * @param  Integer appId   应用ID
     * @param  String servRuleList   业务验证路由规则ID串
     * @return List<EcmServRule> 业务验证路由规则列表
     */
    public  List<EcmServRule> getRuleList(Integer appId, String servRuleList){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("servRuleList", servRuleList);
        return omsBaseDao.findListByCond(new EcmServRule(), FIND_SER_RULE_LIST_SQL_ID, queryMap);
    }

    /**
     * 获取已设定的先升级业务验证路由规则列表	 *
     * @param  Integer appId   应用ID
     * @param  String servRuleList   业务验证路由规则ID串
     * @return List<EcmServRule> 已设定的先升级业务验证路由规则列表
     */
    public  List<EcmServRule> getEarlyRuleList(Integer appId){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        queryMap.put("appUpgStatus", SysConfigConstants.APP_UPGSTATUS_PROGRESS);
        queryMap.put("appValruleType", SysConfigConstants.APP_VALRULE_EARLY);
        return omsBaseDao.findListByCond(new EcmServRule(), FIND_CLEAR_SER_RULE_LIST_SQL_ID, queryMap);
    }

    /**
     * 获取已设定的后升级业务验证路由规则列表	 *
     * @param  Integer appId   应用ID
     * @return List<EcmServRule> 已设定的后升级业务验证路由规则列表
     */
    public  List<EcmServRule> getLateRuleList(Integer appId){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        queryMap.put("appUpgStatus", SysConfigConstants.APP_UPGSTATUS_PROGRESS);
        queryMap.put("appValruleType", SysConfigConstants.APP_VALRULE_LATE);
        return omsBaseDao.findListByCond(new EcmServRule(), FIND_CLEAR_SER_RULE_LIST_SQL_ID, queryMap);
    }

    /**
     * 获取提供者URL	 *
     * @param  String service        服务类全路径名
     * @param  ZkClient zkc          zk客户端链接
     * @param  String hostAndPorts   所有应用实例的IP及端口串
     * @return String 业务验证路由规则列表
     */
    public String getProviderUrl( String service,ZkClient zkc,String hostAndPorts) {
        String provider_path = "/" + DubboUtil.DUBBO_DIR_NAME + "/" + service + "/" + DubboUtil.PROVIDER_DIR_NAME;
        if (zkc.exists(provider_path) && zkc.countChildren(provider_path) > 0) {//如果存在提供者目录，并且有提供者
            List<String> providerList = zkc.getChildren(provider_path);//获取提供者列表
            for (String prdUrl : providerList) {
                if (hostAndPorts.indexOf(DubboUtil.parserProviderUrl(prdUrl)) > -1) {
                    return prdUrl;
                }
            }
        }
        throw new GalaxyException("没有服务"+service+"提供者，请仔细检查!");
    }
}
