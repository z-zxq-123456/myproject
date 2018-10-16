package com.dcits.ensemble.om.oms.manager.kafka;


import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.kafka.KafkaPathCreateHelp;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * kafka路径处理类* 
 * @author luolang
 * @date 2016-12-12
 */
@Component
public class KafkaPathHandler {
	@Resource
	KafkaPathCreateHelp kafkaPathCreateHelp;
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ShellExcuteService shellService;
   /**
    * 处理kafka安装路径
    * @param EcmMidwareKafkaint  midwareKafkaint             kafka实例对象       
    */ 
	public void handlerOsPath(EcmMidwareKafkaint midwareKafkaint){
		   kafkaPathCreateHelp.createOsPath(midwareKafkaint);
	}
	
  
	
}
