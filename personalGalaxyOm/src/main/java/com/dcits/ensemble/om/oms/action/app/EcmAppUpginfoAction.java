package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import com.dcits.ensemble.om.oms.module.app.GuideNodeInfo;
import com.dcits.ensemble.om.oms.service.app.EcmAppUpgforService;
import com.dcits.ensemble.om.oms.service.utils.OmsBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppUpginfoAction* 
 * @author wangbinaf
 * @date 2016-03-09
 */
@Controller
public class EcmAppUpginfoAction {	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	@Resource
	UpgNodeCache upgNodeCache;
	@Resource
	OmsBaseService omsBaseService;
	
	//根据APPid和状态 查找出当前节点
	@RequestMapping("unInterruptedUpdate")
	public void findUninterruptedUpdateInfo(HttpServletRequest request, PrintWriter printWriter,Integer appId) {
		try {
			Map<String,Object> nodeMap = new HashMap<String,Object>();
			EcmUpgflowNode currentNode  = ecmAppUpgforService.findCurrentUpgNodeSeq(appId);//查找当前节点
			List<EcmUpgflowNode> nodeList = upgNodeCache.getUpgFlowNodeList();//查找节点信息和按钮信息
			nodeMap.put("currentNode",currentNode);
			nodeMap.put("nodeList",nodeList);
			ActionResultWrite.setMapResult(printWriter,nodeMap);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//返回缓存里的所有节点信息，和当前节点信息
	@RequestMapping("findNodeInfo")
	public void findNodeInfo(HttpServletRequest request, PrintWriter printWriter,String appId,Integer upgflowNodeSeq) {
		try {
			Map<String,Object> nodeMap = new HashMap<String,Object>();
			EcmUpgflowNode currentNode  = ecmAppUpgforService.findCurrentUpgNodeSeq(Integer.parseInt(appId));//查找当前节点
			GuideNodeInfo selectNode = upgNodeCache.getGuideNodeInfo(upgflowNodeSeq);// 获取指定节点序号对应的节点对象
			nodeMap.put("currentNode",currentNode);
			nodeMap.put("selectNode",selectNode);
			log.info("EcmUpgflowNode:"+nodeMap);
			ActionResultWrite.setMapResult(printWriter,nodeMap);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//判断知否存在当前节点
	@RequestMapping("findCurrentNode")
	public void isExistCurrentNode(HttpServletRequest request, PrintWriter printWriter,Integer appId,Integer appUpgNewverno) {
		try {
			Map<String,Object> newNode = new HashMap<String,Object>();
			newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
			newNode.put("appId",appId);
			newNode.put("appUpgNewverno", appUpgNewverno);
			List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
			int flag;//判断标志
			if(infoList.size()>0){
				flag = 1;
			}else{
				flag = 0;
			}
			ActionResultWrite.setReadResult(printWriter,flag);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

    }
	 //判断按钮是否置灰
	@RequestMapping("isExitIntant")
	public void isExitIntant(HttpServletRequest request, PrintWriter printWriter,int appId){
		try {
			Map<String,Object> newNode = new HashMap<String,Object>();
			newNode.put("appId",appId);
			newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
			List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo(), "findByAppId", newNode);
			String flag ="1";
			if(infoList.size()==0){//
				flag = "0";
			}
			ActionResultWrite.setReadResult(printWriter,flag);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
}
