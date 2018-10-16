package com.dcits.ensemble.om.oms.service.log;

import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareKafkaintService;
import org.I0Itec.zkclient.ZkClient;
import com.dcits.ensemble.om.oms.module.log.EcmLogconfInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * EcmLogconfInfoService*
 * @ author WangBin
 * @ date 2016-10-13
 */
@Controller
public class EcmLogconfInfoService {

    @Resource
    ZookeeperInfoService zookeeperInfoService;

    @Resource
    EcmMidwareKafkaintService ecmMidwareKafkaintService;

    @Resource
    UpdateZkConf  updateZkConf;

    @Resource
    ParamComboxService  paramComboxService;

    @Resource
    IService  omsBaseService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String  LOG_CONF_INFO_NODE = "logConfInfoNode";//该节点存放日志所有的参数信息


    /**
     * 保存数据到数据库并且同步数据到注册中心上
     * @param  logconfInfo    日志配置信息对象
     */
    @Transactional
    public void save( EcmLogconfInfo logconfInfo) {
        String optionType= "save";
        omsBaseService.insert(logconfInfo);
        saveDateToZookeeper(logconfInfo,optionType );
    }
    /**
     * 修改数据到数据库并且同步数据到注册中心上
     * @param  logconfInfo    日志配置信息对象
     */
    @Transactional
    public void update( EcmLogconfInfo logconfInfo) {
        String optionType= "update";
        omsBaseService.updateByPrimaryKey(logconfInfo);
        saveDateToZookeeper(logconfInfo, optionType);
    }

    /**
     * 删除数据到数据库并且同步到注册中心上
     * @param  logconfInfo    日志配置信息对象
     */
    @Transactional
    public void delete( EcmLogconfInfo logconfInfo) {
        String optionType= "delete";
        omsBaseService.deleteByPrimaryKey(logconfInfo);
        saveDateToZookeeper(logconfInfo, optionType);
    }
    //把最新的数据保存到注册中心，包括新增，删除，修改
    private void saveDateToZookeeper(EcmLogconfInfo logconfInfo,String optionType){
        String router_path ="/"+LOG_CONF_INFO_NODE;//节点路径
        List<EcmMidwareZkint> zkList = zookeeperInfoService.findByMid(logconfInfo.getMidwareId());//通过中间件查找zk实例列表
        String cxnStr  = updateZkConf.zkUrl(zkList);//生成zk地址
        log.info("zk集群地址：" + cxnStr);
        try {
            ZkClient zk = new ZkClient(cxnStr,5000);//链接zookeeper
            if("delete".equals(optionType)) {
                if (zk.exists(router_path)) {
                     zk.delete(router_path);//删除该永久节点
                }
            }else {
                if (!zk.exists(router_path)){   //如果该zk集群没有该节点，则创立一个永久性的节点再把数据放上去，否则直接把数据放上去
                    zk.createPersistent(router_path);
                }
                logconfInfo.setAppenderClass(paramComboxService.getParaRemark1(logconfInfo.getOutPrtnMode()));
                logconfInfo.setLogLevelName(paramComboxService.getParaRemark1(SysConfigConstants.LOGPLAT_LEVEL));//查询日志平台的日志级别
                if(logconfInfo.getIsScan().equals("0009001")){
                    logconfInfo.setIsScanName("true");
                }else {
                    logconfInfo.setIsScanName("false");
                }
                if(logconfInfo.getOutPrtnMode().equals("0072001")) {
                    logconfInfo.setIsCollChain(false);
                    logconfInfo.setIsCollLog(false);
                }
                if(logconfInfo.getOutPrtnMode().equals("0072002")) {
                    logconfInfo.setIsCollChain(true);
                    logconfInfo.setIsCollLog(false);
                }
                if(logconfInfo.getOutPrtnMode().equals("0072003")) {
                    logconfInfo.setIsCollChain(true);
                    logconfInfo.setIsCollLog(true);
                }
                if(!logconfInfo.getOutPrtnMode().equals("0071001")) {
                    Properties kfkPro = new Properties();
                    Properties kfkCons = new Properties();
                    logconfInfo.setKfkProdProp(getKfkPro(kfkPro,ecmMidwareKafkaintService.brokList(logconfInfo.getKkMidwareId())));
                    logconfInfo.setKfkConsumerProp(getKfkConsum(kfkCons, getConsuZkConect(getZkMidwareId(logconfInfo.getKkMidwareId()),logconfInfo.getKkMidwareId().toString())));
                }else{
                    logconfInfo.setExpiredTime(Integer.valueOf(logconfInfo.getExpiredTime()) *1000);//把时间单位由秒转化为毫秒
                }
                // JSONObject jsonObject = (JSONObject) JSON.toJSON(logconfInfo);//把ｊａｖａＢｅａｎ抓换成ｊｓｏｎ对象
               // SerializerFeature[] featureArr = { SerializerFeature.WriteClassName };//把ｊａｖａＢｅａｎ抓换成ｊｓｏｎ对象全局序列化
                String text = JSON.toJSONString(logconfInfo);//fastjson的转换格式   把对象庄华
                zk.writeData(router_path, text);
            }
            zk.close(); //关闭链接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private   Properties getKfkPro( Properties  kfkPro ,String brokerList){
        kfkPro.put(paramComboxService.getParaName("0044001"),paramComboxService.getParaRemark1("0044001"));
        kfkPro.put(paramComboxService.getParaName("0044002"),paramComboxService.getParaRemark1("0044002"));
        kfkPro.put(paramComboxService.getParaName("0044003"),paramComboxService.getParaRemark1("0044003"));
        kfkPro.put(paramComboxService.getParaName("0044004"),paramComboxService.getParaRemark1("0044004"));
        kfkPro.put(paramComboxService.getParaName("0044005"),paramComboxService.getParaRemark1("0044005"));
        kfkPro.put(paramComboxService.getParaName("0044006"),paramComboxService.getParaRemark1("0044006"));
        kfkPro.put(paramComboxService.getParaName("0044007"), paramComboxService.getParaRemark1("0044007"));
        kfkPro.put(paramComboxService.getParaName("0044008"), paramComboxService.getParaRemark1("0044008"));
        kfkPro.put(paramComboxService.getParaName("0044009"), brokerList);//metadata.broker.list参数值
        return kfkPro;
    }
    private   Properties getKfkConsum( Properties  kfkConsum ,String zkConnect){
        kfkConsum.put(paramComboxService.getParaName("0045001"),paramComboxService.getParaRemark1("0045001"));
        kfkConsum.put(paramComboxService.getParaName("0045002"),paramComboxService.getParaRemark1("0045002"));
        kfkConsum.put(paramComboxService.getParaName("0045003"),paramComboxService.getParaRemark1("0045003"));
        kfkConsum.put(paramComboxService.getParaName("0045004"),paramComboxService.getParaRemark1("0045004"));
        kfkConsum.put(paramComboxService.getParaName("0045005"),paramComboxService.getParaRemark1("0045005"));
        kfkConsum.put(paramComboxService.getParaName("0045006"),paramComboxService.getParaRemark1("0045006"));
        kfkConsum.put(paramComboxService.getParaName("0045007"),paramComboxService.getParaRemark1("0045007"));
        kfkConsum.put(paramComboxService.getParaName("0045008"),zkConnect);//zookeeper.connect参数值
        return kfkConsum;
    }
    public  String getConsuZkConect (String   midwareZkId,String  midwareId ){
        List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(Integer.valueOf(midwareZkId));//通过中间件查找zk实例列表
        String cxnStr  = updateZkConf.zkNodeUrl(zkList, "kafka" + midwareId);//生成zk地址串  格式为：192.168.165.161：8080，192.168.165.11：8080/wangbinaf
        return cxnStr;
    }
    //根据kafka的中间键查出该kafka集群绑定的zookeeper集群ID
    public  String getZkMidwareId(int midwareId){
        EcmMidwareInfo midware =new EcmMidwareInfo();
        midware.setMidwareId(midwareId);
        return omsBaseService.selectByPrimaryKey(midware).getKfkZksId();
    }

}
