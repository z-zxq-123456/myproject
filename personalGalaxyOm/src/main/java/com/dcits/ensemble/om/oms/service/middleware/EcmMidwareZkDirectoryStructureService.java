package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.action.utils.MenuActionUtil;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.module.sys.MenuNode;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
     * ZK action辅助类*
     *
     * @author WANGBINAF
     * @date 2016-05-19
     */
@Service
public class EcmMidwareZkDirectoryStructureService {
    public static  ZkClient zkc ;

    private ExecutorService executorService ;

    private final static int  THREAD_NUM = 200;//并发线程数量

    private  static final Logger log = LoggerFactory.getLogger(DubboMinitorService.class);
    /**
     *初始化zookeeper链接
     *
     */
    public void  zkInit(String zookeeprUrl){
        try {
            zkc = new ZkClient(zookeeprUrl, 5000);
        }catch (Exception e){
            log.info("错误日志："+e.getMessage());
            throw new GalaxyException("Unable to connect to zookeeper server!");
        }
    }
    /**
     * 生成zookeeper节点树
     *
     * @return List<MenuNode>  树形节点列表
     */
    public   MenuNode getTree(String zookeeprUrl) {
        try {
            MenuNode resultTree = new MenuNode();//返回的所有菜单
            resultTree.setId("0");
            resultTree.setName("ZK根节点 ");
            resultTree.setFullPath("ZK根节点 ");
            zkInit(zookeeprUrl);//链接ZK
            List<String> list = zkc.getChildren("/");
            if (list.size() > 0) {
                for (String name : list) {
                    String path = "/" + name;
                    if (path.startsWith("/dubbo")) {
                        MenuNode resultTreeDubbo =joinChildNode(name,"/" + name,resultTree);
                        getDubboChildThread(name, resultTreeDubbo);//如果是dubb开始的，用线程
                    }else {
                        MenuNode tree =joinChildNode(name,path,resultTree);
                        List<String> pList = zkc.getChildren(path);
                        recurringTree (path,pList,tree);
                    }
                }
            }
          //  zkc.close();//待查询完节点菜单之后关闭zk的链接
            return resultTree;
        } catch (Exception e) {
            e.printStackTrace();
            throw e ;
        }
    }

    /**
     * 递归生成dubbo节点下的子节点
     *
     * @param zkc         zookeeper客户端
     * @param path           节点路径
     * @param List<MenuNode> resultTree    节点列表
     * @param List<String>   pList         子路径
     * @return
     */
    public void getDubboChildThread(String path, MenuNode resultTreeDubbo){
        List<String>  list = zkc.getChildren("/"+path);//查找dubbo的子节点
        CountDownLatch threadSignal = new CountDownLatch(list.size());//初始化countDown
        executorService = Executors.newFixedThreadPool(THREAD_NUM);//初始化固定大小的线程
        for(String serviceName:list) {
            MenuNode tree = joinChildNode(serviceName,path,resultTreeDubbo);
            executorService.execute(new EcmMidwareZkDirectoryStructureActionThread("/"+path,serviceName, tree,threadSignal));
        }
        try {
            threadSignal.await();//等待所有子线程执行完
            executorService.shutdown();//关闭线程池
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    /**
     * 加入子node
     *
     * @param String name         node名字
     * @param String path         node ZK路径
     * @param String parentId     node 父ID
     * @param MenuNode parentTree   父node
     * @return 子node
     */
    private MenuNode joinChildNode(String name,String path,MenuNode parentNode){
        MenuNode tree = new MenuNode();
        tree.setId("" + path.hashCode());
        tree.setParentId(parentNode.getId());
        tree.setName(DubboUtil.handleDubboStr(name));
        tree.setFullPath(DubboUtil.handleDubboStr(path));
        if(parentNode.getChildren()==null){
            parentNode.setChildren( new ArrayList());
        }
        parentNode.getChildren().add(tree);
        return tree;
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
    public   void   recurringTree(String path, List<String> pList,   MenuNode parentNode) {
        for (String name : pList) {
            String newPath = path+"/"+name;
            try {
                MenuNode tree =joinChildNode(name, newPath, parentNode);
                List<String> list = zkc.getChildren(newPath);
                if (list.size() != 0)
                    recurringTree(newPath, list,tree);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
    //获取dubbo下的节点的数据
    public class EcmMidwareZkDirectoryStructureActionThread implements Runnable{
        String path;
        String serviceName;
        MenuNode parentNode;
        CountDownLatch threadsSignal;

        EcmMidwareZkDirectoryStructureActionThread(String path,String serviceName, MenuNode parentNode, CountDownLatch threadsSignal){
            this.path = path;
            this.serviceName = serviceName;
            this.parentNode = parentNode;
            this.threadsSignal = threadsSignal;
        }
        @Override
        public void run() {
            String newPath = path +"/"+serviceName;
            List<String>  list = zkc.getChildren(newPath);//查找dubbo的子节点
          //  long startTime =  System.currentTimeMillis();
            for(String name:list) {
                MenuNode tree = joinChildNode(name,newPath,parentNode);
                List<String> pList = zkc.getChildren(newPath+"/"+name);
                recurringTree(newPath+"/"+name, pList, tree);
            }
          //  long endTime =  System.currentTimeMillis();
            //System.out.println(Thread.currentThread().getName() +" 后台执行时间："+(endTime-startTime));
            threadsSignal.countDown();//线程结束时计数器减1
            }
        }



    /**
     * 获取固定路径下的及节点的数据
     *
     * @param path           节点路径
     * @return  String       节点数据
     */
    public  String getNodData(String path) {
        try {
            if (zkc.exists(path)) {
                String cxnStr = zkc.readData(path);
                if(null!=cxnStr||!cxnStr.equals("")) {
                    return cxnStr;
                }
            }
        }catch (Exception e){
            log.info("亲：该路径下的节点数据不存在");
        }
        return "" ;
    }
}


