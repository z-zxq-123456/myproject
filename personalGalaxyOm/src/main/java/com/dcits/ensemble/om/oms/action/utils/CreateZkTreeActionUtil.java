package com.dcits.ensemble.om.oms.action.utils;


import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.module.sys.MenuNode;
import org.I0Itec.zkclient.ZkClient;

import java.util.ArrayList;
import java.util.List;

/**
     * ZK action辅助类*
     *
     * @author WANGBINAF
     * @date 2016-05-19
     */
    public class CreateZkTreeActionUtil {
        public static  ZkClient zkc ;
        /**
         * 生成zookeeper节点树
         *
         * @return List<MenuNode>  树形节点列表
         */
        public static List<MenuNode> getTree(String zookeeprUrl) {
            String path;
            path = "/";
            List<MenuNode> treeList = new ArrayList<MenuNode>();
            try {
                zkc = new ZkClient(zookeeprUrl, 5000);
                List<String> list = zkc.getChildren(path);
                recurringTree("", list, treeList, 0, zkc);
            //    zkc.close();//加载完zk节点之后，方便以后读取节点数据时不再链接(节点数据加载方式发生变化，故恢复原来设计，每次连接完之后关闭连接)
                return treeList;
            } catch (Exception e) {
                e.printStackTrace();
                throw e ;
            }
        }

        /**
         * 递归生成ZOOKEEPER节点
         *
         * @param client         zookeeper客户端
         * @param path           节点路径
         * @param List<MenuNode> treeList    节点列表
         * @param List<String>   pList          子路径
         * @param parentId       父类节点ID
         * @return
         */
        private static void recurringTree(String path, List<String> pList, List<MenuNode> treeList, int parentId, ZkClient zkc) {
            for (String string : pList) {
                String newPath = path + "/" + string;
              // System.out.println("re newPath..." + newPath);
                try {
                    MenuNode tree = new MenuNode();
                    tree.setId("" + newPath.hashCode());
                    tree.setParentId("" + parentId);
                    String data;//变量  保存节点数据
                    if(!newPath.startsWith("/zookeeper")) {
                        if (null == zkc.readData(newPath)) {
                            data = "";
                        } else {
                            data = zkc.readData(newPath);
                        }
                        tree.setText(data);//把该节点的数据放在text中，方便在js中把text作为title属性显示出来
                    }else{
                        tree.setText("");//如果是zookeeper开头的节点，节点数据赋值""
                    }
                    tree.setName(DubboUtil.handleDubboStr(string));
                    tree.setFullPath(newPath);
                    //   List<String> list = getChildren(client, newPath);
                    List<String> list = zkc.getChildren(newPath);
                    if (list == null) {
                        return;
                    }
                    //   System.out.println("re list===>" + list);
                    int pid = Integer.valueOf(tree.getId()).intValue();
                    //   int pid = Integer.parseInt(tree.getId());
                    treeList.add(tree);
                    recurringTree(newPath, list, treeList, pid, zkc);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        public static String  getNodDate(String path) {
            try {
                if (zkc.exists(path)) {
                    String cxnStr = zkc.readData(path);
                    if(null!=cxnStr||!cxnStr.equals("")) {
                        return cxnStr;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "" ;
        }

    }


