package com.dcits.ensemble.om.oms.manager.kafka;

import com.dcits.ensemble.om.oms.common.MachineUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.middleware.DubboMinitorService;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareKafkaintService;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Kafka状态检查类* 
 * @author luolang
 * @date 2016-12-12
 */
@Component
public class KafkaStatusCheck {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	private static final String CHECK_KAFKA_CONNECTION ="Kafka的连通性检查";
	private static final String CHECK_KAFKA_IDS="Kafka的IDS检查";
	private static final String CHECK_ZKTREE_PATH="/brokers/ids";
	private static final int CHECK_FAIL = 1; //失败

	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	ShellExcuteService shellService;
	@Resource
	DubboMinitorService dubboMinitorService;
	@Resource
	IService omsBaseService;
	@Resource
	UpdateZkConf updateZkConf;
	/**
	 *kafka连通性检查
	 *@param EcmMidwareKafkaint  midwareKafkaint 容器实例对象	 
	 *@param ContainerCheckResult  checkResult       检查结果     
	 */ 
	public void checkKafkaConnection(EcmMidwareKafkaint midwareKafkaint,ContainerCheckResult checkResult) {
		try {
			ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
			String checkPort = midwareKafkaint.getKafkaintPort();
			ShellResult result = shellService.doCmd(midwareKafkaint.getSerIp(), midwareKafkaint.getSerUser(), midwareKafkaint.getSerPwd(),
					cmd.netstatCmd(checkPort));
			if (MachineUtil.appStatus(result, checkPort)) {
				checkResult.addMessage(CHECK_KAFKA_CONNECTION + ":连通");
			} else {
				errorCheck(CHECK_KAFKA_CONNECTION, checkResult);
			}
			String statusCode = "";
			if(checkResult.getResultNum()>0){
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			}else{
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			checkResult.setCheckStatus(statusCode);
			checkResult.setCheckStatusName(paramComboxService.getParaName(statusCode));
		} catch (IllegalStateException e) {
			log.error(CHECK_KAFKA_CONNECTION + "出错,错误信息为：" + e.getMessage());
			throw new GalaxyException(CHECK_KAFKA_CONNECTION + "出错!");
		}
	}

	/**
	 * kafkaIDS检查
	 * @param midwareKafkaint 容器实例对象
	 * @return checkResult 检查结果
	 * 在zk中，有一个broker节点的列表，列表中的每一项表示一个逻辑broker。在启动时，broker节点会在zk中的/kafka/broker/ids/目录下创建一个znode，名称为配置文件中定义的broker id，如上面所示的/kafka/brokers/ids/2。建立逻辑broker id的目的是允许一个broker节点迁移到另一台机器上，而不会影响到consumer的消费。如果想注册一个已经存在的broker id会引起错误（比如说有2个broker的配置文件都写了同一个broker id）。
	 * 由于broker在zk中注册的是一个ephemeral znodes，因此当这个broker关机或者挂掉的时候，这个注册信息会自动删除，从而会通知consumer这个节点已经不可用。
	 */
	public void checkKafkaIDS(EcmMidwareKafkaint midwareKafkaint , ContainerCheckResult checkResult) {
        Integer brokerId = midwareKafkaint.getKafkaintId();
		String bId = brokerId.toString();
		Integer midwareId = midwareKafkaint.getMidwareId();
		EcmMidwareInfo temp = new EcmMidwareInfo();
		temp.setMidwareId(midwareId);
		try {
			ZkClient zkc = new ZkClient(getZkUrl(Integer.valueOf(omsBaseService.selectByPrimaryKey(temp).getKfkZksId())),5000);
			if (zkc.exists("/kafka"+midwareKafkaint.getMidwareId()+CHECK_ZKTREE_PATH)) {
				if (zkc.countChildren("/kafka"+midwareKafkaint.getMidwareId()+CHECK_ZKTREE_PATH) == 0) {
					checkResult.addResultNum(CHECK_FAIL);
					checkResult.addMessage(CHECK_KAFKA_IDS + ":不存在");
				} else {
					List<String> info = zkc.getChildren("/kafka"+midwareKafkaint.getMidwareId()+CHECK_ZKTREE_PATH);
					if (info.contains(bId)) {
						checkResult.addMessage(CHECK_KAFKA_IDS + ":存在");
					} else {
						checkResult.addResultNum(CHECK_FAIL);
						checkResult.addMessage(CHECK_KAFKA_IDS + ":不存在");
					}
				}
			} else {
				checkResult.addResultNum(CHECK_FAIL);
				checkResult.addMessage(CHECK_KAFKA_IDS + ":不存在");
			}
			zkc.close();
		}catch (ZkTimeoutException e){
			checkResult.addResultNum(CHECK_FAIL);
			checkResult.addMessage(CHECK_KAFKA_IDS + ":不存在");
		   log.info("错误日志：" + e.getMessage());
	   }
	}

	/**
	 * 异常报错
	 *
	 * @param String               str  检查对象
	 * @param ContainerCheckResult checkResult       检查结果
	 */
	private void errorCheck(String str, ContainerCheckResult checkResult) {
		checkResult.addResultNum(CHECK_FAIL);
		checkResult.addMessage(str + ":不连通");
	}

	//根据中间件id得到集群zk的url
	private  String getZkUrl(int midwareId) {
		List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareId);//通过中间件查找zk实例列表
		String zkUrl  = updateZkConf.zkUrl(zkList);//生成zk地址串
		return zkUrl;
	}
	


}
