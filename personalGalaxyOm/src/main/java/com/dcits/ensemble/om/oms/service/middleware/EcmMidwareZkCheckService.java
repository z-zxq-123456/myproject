package com.dcits.ensemble.om.oms.service.middleware;




import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.apache.commons.lang.StringUtils;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboServiceHandler;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * EcmMidwareZkCheckService* 
 * @author wangbinaf
 * @createdate 2016-6-7
 */
@Service
public class EcmMidwareZkCheckService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String DIV_SIGN="/";//隔离符号 '/'
	@Resource
	DubboServiceStatusCheck dubboServiceStatusCheck;
	@Resource
	DubboServiceHandler ubboServiceHandler;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	UpdateZkConf updateZkConf;

	private final static int  THREAD_NUM = 200;//并发线程数量

	private ExecutorService executorService ;
    /**
     * 启动选中的ZK服务
     * @param EcmMidwareZkSer mwareZkSer    zk服务对象
     */
   @Transactional
   public void enableAllZkSers(EcmMidwareZkSer mwareZkSer) {
      List<EcmMidwareZkSer>  zkList = changeData(mwareZkSer);
	  ubboServiceHandler.enableDubboService(zkList, Integer.valueOf(mwareZkSer.getMidwareId()));
   }
  
  /**
   * 禁止选中的ZK服务
   * @paramEcmMidwareZkSer mwareZkSer   zk服务对象
   */
  public void disableAllZkSers(EcmMidwareZkSer mwareZkSer) {
	  List<EcmMidwareZkSer>  zkList = changeData(mwareZkSer);
	  ubboServiceHandler.disableDubboService(zkList, Integer.valueOf(mwareZkSer.getMidwareId()));
  }
  /**
   * 转换数据格式
   * @param EcmMidwareZkSer mwareZkSer    zk服务对象
   * @return  List<EcmMidwareZkSer>  ZK服务实例信息列表	     
   */
 @Transactional
 public List<EcmMidwareZkSer>  changeData(EcmMidwareZkSer mwareZkSer) {
	  List<EcmMidwareZkSer>  zkList = new ArrayList<EcmMidwareZkSer>();
	  String[] zkSerGrop =subString (mwareZkSer.getZkSerGrop());
	  String[] zkSerName = subString(mwareZkSer.getZkSerName());
	  String[] zkSerType = subString(mwareZkSer.getZkSerType());
	  String[] zkVersion = subString(mwareZkSer.getZkVersion());
	  String[] zkIpPort = subString(mwareZkSer.getZkIpPort());
	  for(int i = 0;i<zkSerName.length; i++  ) {
		   EcmMidwareZkSer  temp  =new EcmMidwareZkSer(); 
		   temp.setZkSerGrop(zkSerGrop[i].replaceAll("\"", ""));
		   temp.setZkSerType(zkSerType[i].replaceAll("\"", ""));
		   temp.setZkVersion(zkVersion[i].replaceAll("\"", ""));
		   temp.setZkSerName(zkSerName[i].replaceAll("\"", ""));
		   temp.setZkIpPort(zkIpPort[i].replaceAll("\"", ""));
		   zkList.add(temp);
	  }
	  return zkList;
 }
  /**
   * 查找ZK服务信息
   * @param EcmMidwareZkSer   midwareZkSer  ZK实例
   * @param String            zookeeprUrl  ZK地址
   * @return  List<EcmMidwareZkSer>  ZK服务实例信息列表
   */
  public List<EcmMidwareZkSer> findZkSers(EcmMidwareZkSer midwareZkSer) {
	  if (midwareZkSer.getMidwareId().equals("")){
		  return  null;
	  }else {
		  List<EcmMidwareZkint> zkList = zookeeperInfoService.findByMid(Integer.valueOf(midwareZkSer.getMidwareId()));//通过中间件查找zk实例列表
		  String cxnStr = updateZkConf.zkUrl(zkList);//生成zk地址串
		  String path;
		  path = DIV_SIGN + paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROOT);//获取路径"/dubbo"
		 List<EcmMidwareZkSer> treeList = new ArrayList<EcmMidwareZkSer>();
		  try {
			  ZkClient zkc = new ZkClient(cxnStr, 5000);//创建ZK链接
			  if(zkc.exists(path) ){
				  treeList = findSers(midwareZkSer, zkc, path); //如果有服务名则通过页面输入的服务名，模糊匹配出对应结果 信息，反之，返回/dubbo/ 所有的服务名
				  zkc.close();//关闭链接
				  return treeList;
			  }else{
				  throw new GalaxyException("亲，当前zookeeper没有注册任何dubbo服务请检查");
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
			  throw e;
		  }
	  }
  }
	//如果有服务名则通过页面输入的服务名，模糊匹配出对应结果 信息，反之，返回/dubbo/ 所有的服务名
	public  List<EcmMidwareZkSer> findSers(EcmMidwareZkSer midwareZkSer,ZkClient zkc,String path){
		List<EcmMidwareZkSer> treeList = Collections.synchronizedList(new ArrayList<EcmMidwareZkSer>());
		List<String> list =zkc.getChildren(path); //获取路径为"/dubbo/"下的子节点信息
		CountDownLatch threadSignal = new CountDownLatch(list.size());//初始化countDown
		executorService = Executors.newFixedThreadPool(THREAD_NUM);//初始化固定大小的线程池
		if( !StringUtils.isEmpty(midwareZkSer.getZkSerName())){
			for (String serString : list) {
				if(serString.indexOf(midwareZkSer.getZkSerName().trim())!=-1){
					findByPath(path, serString, treeList, zkc,midwareZkSer);
				}
			}
			dubboServiceStatusCheck.removeServiceRouterLis();//清除缓存
		}else{
			for (String serString : list) {
				executorService.execute(new EcmMidwareZkCheckService.EcmMidwareZkCheckServiceActionThread(path, serString, treeList, zkc, midwareZkSer, threadSignal));
				//    findByPath(path, serString, treeList, zkc,midwareZkSer);
			}
			try {
				threadSignal.await();//等待所有子线程执行完
				executorService.shutdown();//关闭线程池
				dubboServiceStatusCheck.removeServiceRouterLis();//清除缓存
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		return treeList;
	}
	//获取dubbo下的节点的数据
	public class EcmMidwareZkCheckServiceActionThread implements Runnable{
		String path;
		String serString;
		List<EcmMidwareZkSer>  treeList;
		ZkClient zkc;
		EcmMidwareZkSer midwareZkSer;
		CountDownLatch threadsSignal;
		EcmMidwareZkCheckServiceActionThread(String path,String serString,List<EcmMidwareZkSer> treeList,ZkClient zkc,EcmMidwareZkSer midwareZkSer, CountDownLatch threadsSignal){
			this.path = path;
			this.serString = serString;
			this.treeList = treeList;
			this.zkc = zkc;
			this.midwareZkSer = midwareZkSer;
			this.threadsSignal = threadsSignal;
		}
		@Override
		public void run() {
			List<EcmMidwareZkSer> list = new ArrayList<EcmMidwareZkSer>();
			findByPath(path, serString, list, zkc, midwareZkSer);;
			treeList.addAll(list);
			threadsSignal.countDown();//线程结束时计数器减1
		}
	}
	/**
	 * 获取dubbo下的所有子节点
	 * @param String  str  dubbo节点的具体服务名
	 * @param ZkClient  zkc   zk的客户端
	 * @return  List<Map<String,Object>>  子节点列表，以Map封装
	 */
	 public   List<Map<String,String>>  getDubboServicesThread(String dirName,ZkClient  zkc) {
		List<Map<String,String>>  list =new ArrayList<Map<String, String>>();
		String provider_path ="/"+ DubboUtil.DUBBO_DIR_NAME+"/"+dirName+"/"+ DubboUtil.PROVIDER_DIR_NAME;
		String consumer_path ="/"+ DubboUtil.DUBBO_DIR_NAME+"/"+dirName+"/"+ DubboUtil.CONSUMER_DIR_NAME;
		if(zkc.exists(consumer_path)&&zkc.countChildren(consumer_path)>0){
			if(!zkc.exists(provider_path)||zkc.countChildren(provider_path)==0){
				List<String> consumerList =zkc.getChildren(consumer_path);
				for(String consumer:consumerList){
					Map<String,String> cousumerMap = new HashMap<String,String>();
					String formatConsumer= DubboUtil.handleDubboStr(consumer);
					String[] firstResult =formatConsumer.split("[?]")[0].split("//");//?是正则表达式中的特殊字符，需要[?]来处理
					cousumerMap.put("dubboSide",firstResult[0].replace(":",""));
					String[] secondResult = firstResult[1].split("/");
					cousumerMap.put("cosumerIP",secondResult[0]);
					cousumerMap.put("dubboService",secondResult[1]);
					list.add(cousumerMap);
				}
			}
		}
		return list;
	}
  /**
	 * 获取ZOOKEEPER服务信息
	 * @param ZkClient   zkc  ZK客户端
	 * @param path     节点路径
	 * @param  List<EcmMidwareZkSer> treeList   ZK服务实例列表信息
	 * @param List<String> pList          子路径
	 * @param EcmMidwareZkSer   midwareZkSer  ZK服务实例
	 * @return
	 */
 public   void findByPath(String path, String serString, List<EcmMidwareZkSer> treeList,ZkClient zkc,EcmMidwareZkSer midwareZkSer) {
	      String newPath = path + "/" + serString;
	    	if(zkc.exists(newPath)){//判断是否存在该路径   true：  存在
	        	if(null!=midwareZkSer.getZkSerType()&&!midwareZkSer.getZkSerType().equals("")){//以服务类型作为查询条件
	        		String pathList = newPath+"/"+paramComboxService.getParaName(midwareZkSer.getZkSerType());
	        		if(zkc.exists(pathList)){//判断是否存在该路径   true：  存在 
			        	List<String> tempList =zkc.getChildren(pathList);
			        	if (tempList.size()>0){
			            	isHaveZkIp(midwareZkSer,tempList,treeList,serString,zkc,"");//如果IP不为空，以IP为查询条件进行查询
			            }
	        		}
	        	}else{
		        	String pathProvider = newPath+"/"+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS);
		        	if(zkc.exists(pathProvider)){//判断是否存在该路径   true：  存在 
			        	List<String> providerList =zkc.getChildren(pathProvider);//得到提供者下的子节点信息
			        	if (providerList.size()>0) {
			            	 isHaveZkIp(midwareZkSer,providerList,treeList,serString,zkc,SysConfigConstants.DUBBO_TYPE_PROVIDERS);
			            }
		        	}
		        	String pathConsumers = newPath+"/"+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS);
		        	if(zkc.exists(pathConsumers)){//判断是否存在该路径   true：  存在 
			        	List<String> consumersList =zkc.getChildren(pathConsumers);
			        	if (consumersList.size()>0) {
			            	 isHaveZkIp(midwareZkSer,consumersList,treeList,serString,zkc, SysConfigConstants.DUBBO_TYPE_CONSUMERS);
			            }
		        	}
		        }
	        }
	}
   /**
	 * 同时查询消费者和提供者信息，如果IP不为空，以IP为查询条件进行查询
	 * @param EcmMidwareZkSer midwareZkSer ZK服务实例
	 * @param List<String> tempList  该路径下的子节点列表
	 * @param  List<EcmMidwareZkSer> treeList   ZK服务实例列表信息
	 * @param String tempPath     服务名
	 * @param  ZkClient zkc     ZK客户端
	 * @return
	 */
 public void  isHaveZkIp(EcmMidwareZkSer midwareZkSer,List<String> tempList,List<EcmMidwareZkSer> treeList,String tempPath,ZkClient zkc,String type){
    if(null!=midwareZkSer.getZkIp()&&!midwareZkSer.getZkIp().equals("")){
	  	for (String pro : tempList) {
	  		if(pro.indexOf(midwareZkSer.getZkIp())!=-1){
	  			setType(midwareZkSer,treeList,tempPath,pro,zkc,type);
	  		}
  	    }
  	}else{
  		for (String pro : tempList) {
  			setType(midwareZkSer,treeList,tempPath,pro,zkc,type);
      	}
  	}  
  }
//给实例赋类型,返回   List<EcmMidwareZkSer>  list 表信息
public   void  setType(EcmMidwareZkSer midwareZkSer,List<EcmMidwareZkSer> treeList,String tempPath,String pro,ZkClient zkc,String type){
	if(null!=midwareZkSer.getZkSerType()&&!midwareZkSer.getZkSerType().equals("")){
		findConsOrPros(tempPath, pro, treeList, midwareZkSer.getZkSerType(), zkc);
	}else{
		findConsOrPros(tempPath, pro, treeList, type, zkc);
	}
}


 /**
  * 返回消费者或者提供者信息
  * @param	  String tempPath  服务名   
  * @param	  String conOrPro  提供者或者消费者路径下的具体信息 "/dubbo/服务名/提供者或者消费者/"
  * @param	  List<EcmMidwareZkSer> treeList    服务实例列表
  * @param    ZkClient zkc     ZK客户端
  * @param	  String  zkSerType 服务类型    "providers/consumers"
  */ 
	 public void findConsOrPros(String tempPath,String conOrPro,List<EcmMidwareZkSer> treeList,String zkSerType,ZkClient zkc){
		    EcmMidwareZkSer tree = new EcmMidwareZkSer();
			tree.setZkSerName(tempPath);
		    tree.setZkSerId(""+conOrPro.hashCode());
		    tree.setZkSerTypeName(paramComboxService.getParaName(zkSerType));
		    tree.setZkSerType(zkSerType);
		    String handleConOrPro = DubboUtil.handleDubboStr(conOrPro);
		    tree.setZkSerStatusName(paramComboxService.getParaName(dubboServiceStatusCheck.checkServeicStatus(zkc,"/"+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROOT)+"/"+tempPath,handleConOrPro,zkSerType)));
		    tree.setZkIpPort( DubboUtil.findZkIpPort(handleConOrPro));
	     	tree.setZkVersion(DubboUtil.findZkVersion(handleConOrPro));
	     	tree.setZkReversion(DubboUtil.findZkRevision(handleConOrPro));
	  	    tree.setZkSerGrop(DubboUtil.findZkSerGroup(handleConOrPro));
	  	    treeList.add(tree);
	  }
	 //把字符串中以","隔开
	 private  String[] subString( String temp){
		 return temp.substring(1, temp.length()-1).split(",");
	  }
}