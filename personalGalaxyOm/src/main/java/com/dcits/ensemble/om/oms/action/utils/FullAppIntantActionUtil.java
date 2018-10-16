package com.dcits.ensemble.om.oms.action.utils;

import com.dcits.ensemble.om.oms.module.sys.MenuNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全量部署action辅助类* 
 * @author tangxlf
 * @date 2015-10-29
 */
public class FullAppIntantActionUtil {	    
	 
		//生成完整的菜单类
		public static MenuNode createTreeNodes(List<MenuNode>  treeList){
			MenuNode rootNode = new MenuNode();
			rootNode.setId("0");
			rootNode.setText("菜单根节点");			
			Map<String,List<MenuNode>> treeMap = createTreeList(treeList);
			crossTreeMap(rootNode,treeMap);
			rootNode.setId("-1");
			return rootNode;
		}
		//创建父节点与子节点列表对应关系
		private static Map<String,List<MenuNode>> createTreeList(List<MenuNode>  treeList){
			//System.out.println("treeList="+treeList);
			Map<String,List<MenuNode>> treeMap = new HashMap<String,List<MenuNode>>();
            for(MenuNode node:treeList){
				String parentId =node.getParentId();
				if(node.getRoleId()==null){
					node.setChecked(false);
				}else{
					node.setChecked(true);
				}
				node.setRoleId(null);
				if(treeMap.containsKey(parentId)){
					List<MenuNode> childrenNodes = treeMap.get(parentId);
					childrenNodes.add(node);
				}else{
					List<MenuNode> childrenNodes = new ArrayList<MenuNode>();
					childrenNodes.add(node);
					treeMap.put(parentId,childrenNodes);
				}
			}
            //System.out.println("treeMap="+JSON.toJSONString(treeMap));
			return treeMap;
		}
		
		//递归生成菜单节点
		public static void crossTreeMap(MenuNode node,Map<String,List<MenuNode>> treeMap){
			String id = node.getId();
			if(treeMap.containsKey(id)){
				List<MenuNode> children = treeMap.get(id);
				node.setChildren(treeMap.get(id));
				node.setChecked(false);
				treeMap.remove(id);
				//if(!treeMap.isEmpty()) {
				  boolean isClose = true;
				  for(MenuNode childNode:children){
					//if(childNode.getChecked()!=null&&childNode.getChecked().equals("true")){
					if(childNode.isChecked()){
						isClose =false;
					}
					if(!treeMap.isEmpty()) {//如果还存在子节点递归调用
					 crossTreeMap(childNode,treeMap);
					}
				  }
				  if(isClose){//如果子节点没有被选中的，父节点不展开
						node.setState("closed");
				  }
				//}
			}			
		}
}
