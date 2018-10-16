package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import com.dcits.ensemble.om.oms.module.app.EcmUpgndBtn;
import com.dcits.ensemble.om.oms.module.app.GuideNodeInfo;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


/**
 * 缓存不间断升级流程节点类* 
 * @author tangxlf
 * @date 2016-03-01 
 */
@Component
public class UpgNodeCache {
	
   @Resource
   IService omsBaseService;
	
   private static TreeMap<Integer,GuideNodeInfo> guideNodeMap = new TreeMap<Integer,GuideNodeInfo>();//K节点序号  V节点对象
   
   /**	
	 * @return 为一个Map，里面为所有的节点对象组成的Map
	 */
   public synchronized TreeMap<Integer,GuideNodeInfo> getGuideNodeMap() {
		if(guideNodeMap.isEmpty()){
			initGuideNodeMap();
		}
		return guideNodeMap;
	}
	//初始化节点Map
	private void initGuideNodeMap(){
		List<EcmUpgflowNode> nodeList = omsBaseService.findListByCond(new EcmUpgflowNode(),new HashMap<String,Object>() );
		Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("upgndBtnIsview", SysConfigConstants.IS_DEFAULT);
		List<EcmUpgndBtn> btnList = omsBaseService.findListByCond(new EcmUpgndBtn(),newNode );
		System.out.println("HELLO123"+btnList);
		Map<Integer,EcmUpgflowNode> nodeMap = new HashMap<Integer ,EcmUpgflowNode>();
		for(EcmUpgflowNode node :nodeList) {
			nodeMap.put(node.getUpgflowNodeId(), node);
			Integer key = node.getUpgflowNodeSeq();
			Integer nodeId = node.getUpgflowNodeId();
			List<EcmUpgndBtn> btnTem =  new ArrayList<EcmUpgndBtn>();
			for(EcmUpgndBtn btn:btnList ) {
				btn.setDirectNode(nodeMap.get(btn.getUpgndBtnDirnodeid()));
				if(btn.getUpgflowNodeId()==nodeId) {
					btnTem.add(btn);
				}
			}
			GuideNodeInfo value =  new GuideNodeInfo();
			value.setUpgflowNode(node);
			value.setBtnList(btnTem);
			guideNodeMap.put(key, value);	
		}
	}
   /**
	 * 获取按照节点序号排序的升级流程节点列表	 * 
	 * @return List<EcmUpgflowNode>
	 */
	public List<EcmUpgflowNode>  getUpgFlowNodeList(){
		List<EcmUpgflowNode> upgflowNode = new ArrayList<EcmUpgflowNode>();
		Collection<GuideNodeInfo> con = getGuideNodeMap().values();
		Iterator<GuideNodeInfo> ite = con.iterator();
		while(ite.hasNext()) {  
			GuideNodeInfo nodeInfo = ite.next();
			upgflowNode.add(nodeInfo.getUpgflowNode());
		}  
		return upgflowNode;
	}
   /**
	 * 获取指定节点序号对应的节点对象	 * 
	 * @param Integer nodeSeq  节点序号
	 * @return GuideNodeInfo
	 */
	public GuideNodeInfo getGuideNodeInfo(Integer nodeSeq){
		return getGuideNodeMap().get(nodeSeq);
	}
	
	/**
	 *当节点序号发生修改时，清空缓存。
	 */
	public void removeNodeMap() {
		guideNodeMap.clear();
	}
	
	 /**
		 * 获取流程节点列表最小的节点	 * 
		 * @return EcmUpgflowNode
		 */
     public EcmUpgflowNode getCurrentUpgNode(){
        TreeMap<Integer,GuideNodeInfo> tempMap =  getGuideNodeMap();
        return tempMap.get(tempMap.firstKey()).getUpgflowNode();
	 }
	
	
}
