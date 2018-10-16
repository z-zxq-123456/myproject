package com.dcits.ensemble.om.oms.action.utils;


import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.module.sys.MenuNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单action辅助类* 
 * @author tangxlf
 * @date 2015-09-09
 */
public class MenuActionUtil {
	    private static final String LEAF_MENU_ICON ="icon-mini-add";

	    //生成叶子节点菜单
		public static Map<String, Object> packOpenMenu(String id,String name){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id",id);
			map.put("text",name);
			map.put("state", "open");
			map.put("iconCls",LEAF_MENU_ICON);
			return map;
		}

		//生成目录节点菜单
		public static Map<String, Object> packCloseMenu(String id,String name){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id",id);
			map.put("text",name);
			map.put("state", "closed");
			return map;
		}


		//生成完整的菜单类
		public static MenuNode createTreeNodes(List<MenuNode>  treeList){
			MenuNode rootNode = new MenuNode();
			rootNode.setId("0");
			rootNode.setText("菜单根节点");
			Map<String,List<MenuNode>> treeMap = createTreeList(treeList);
			crossTreeMap(rootNode, treeMap);
			rootNode.setId("-1");
			return rootNode;
		}
		//生成完整的ZK目录类
		public static MenuNode createZkTreeNodes(List<MenuNode>  treeList){
			MenuNode rootNode = new MenuNode();
			rootNode.setId("0");
			rootNode.setText("ZK根节点 ");
			rootNode.setName("ZK根节点 ");
			rootNode.setFullPath("ZK根节点 ");
			Map<String,List<MenuNode>> treeMap = createTreeList(treeList);
			crossTreeMap(rootNode,treeMap);
			rootNode.setId("-1");
			return rootNode;
		}
	//生成完整的ZK目录类
	public static MenuNode createDubboTreeNodes(List<MenuNode>  treeList,int parentId ,String path){
		MenuNode rootNode = new MenuNode();
		rootNode.setId(""+path.hashCode());
		rootNode.setName(path);
		rootNode.setParentId(""+parentId);
		//treeList.add(rootNode);
		Map<String,List<MenuNode>> treeMap = createTreeList(treeList);
		crossTreeMap(rootNode, treeMap);
		//rootNode.setId("-1");
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
