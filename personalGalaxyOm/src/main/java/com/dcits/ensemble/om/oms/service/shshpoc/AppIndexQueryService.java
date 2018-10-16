package com.dcits.ensemble.om.oms.service.shshpoc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndex;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndexRec;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.CommonServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;
/**
 * 应用监控指标查询* 
 * @author tangxlf
 * @date 2016-07-27
 */
@Service
public class AppIndexQueryService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private int max_req_no = 0;
	
    private static final int APP_INDEX_MONITOR = 0; //查询类型 -- 0指标监控
	
	private static final String TIME_COLS = "END_TIME";
	
	private static final String FLOW_TAB_SQLID = "com.dcits.ensemble.om.oms.dao.app.FwTranInfoDao";
	
	private static final String DIME_VAL_COLNAME = "appIndexrecDimeval";
	
	private static final String DIME_TYPE_COLNAME = "appIndexrecDime";
	
	private  int appMoniPeriod = 1;//查询周期 单位分钟
	
	private static boolean isStartThread = false;//是否开启了查指标轮询线程
	
	private static final ReadWriteLock readWirteLock =new ReentrantReadWriteLock();
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	IService  omsBaseService;
	@Resource
	ParamComboxService  paramComboxService;
	@Resource
	private ShardSqlSessionTemplate sessionTemplate;
//    @Resource
//    CommonServiceUtil  serviceUtil;
	@Resource
	PkServiceUtil serviceUtil;
	
	
	/**
	 * 查询应用监控指标记录
	 * @param   EcmAppmoniIndexRec  appmoniIndexRec 应用监控记录对象
	 * @param   int                 pageNo          页码
	 * @param   int                 pageSize        页面尺寸
	 * @param   int                 mark            查询类型  0指标监控  1指标查询
	 * @return  AppIndexResultInfo  封装监控记录的对象
     */	
	public AppIndexResultInfo findAppIndex(EcmAppmoniIndexRec  appmoniIndexRec,int pageNo,int pageSize,int mark) {
		if(!DataUtil.isNull(appmoniIndexRec.getAppMoniPeriod())){
			this.appMoniPeriod = Integer.parseInt(paramComboxService.getParaName(appmoniIndexRec.getAppMoniPeriod()));
		}
		AppIndexResultInfo  appIndexInfo =new AppIndexResultInfo();
		createColName(appIndexInfo,mark);
		if(DataUtil.isNull(appmoniIndexRec.getAppIndexrecDime())){
			appmoniIndexRec.setAppIndexrecDime(SysConfigConstants.APPMONI_DIME_NODIME);
		}
		if(!isStartThread){
			startThread();
	   }
//		if(DataUtil.isNull(appmoniIndexRec.getStartTime())){
//			appmoniIndexRec.setStartTime(null);;
//		}
//		if(DataUtil.isNull(appmoniIndexRec.getEndTime())){
//			appmoniIndexRec.setEndTime(null);
//		}
		log.info("start excute query");
		if(!DataUtil.isNull(appmoniIndexRec.getStartTime())) {				
			appmoniIndexRec.setStartTime(rmSomething(appmoniIndexRec.getStartTime()));			
		}
		if(!DataUtil.isNull(appmoniIndexRec.getEndTime())) {				
			appmoniIndexRec.setEndTime(rmSomething(appmoniIndexRec.getEndTime()));
		}
		PageData<EcmAppmoniIndexRec> pageData =omsBaseService.findPageByCond(appmoniIndexRec,pageNo,pageSize);
		appIndexInfo.setRows(magicList(pageData.getList()));
		appIndexInfo.setTotal(pageData.getTotalNum());
		return appIndexInfo;
	}
	
	/**
     * 去除日期中的"-"字符
     * @parm String 字符串
     */
	private String rmSomething(String str) {
	    return str.replaceAll("-", "");
	}
	//启动线程开始查询流水表指标写入应用监控记录表
	private void startThread(){
		isStartThread = true;
		new CirceExcuteThread().start();
	}
	//生成SQL字段名
	private  void createColName(AppIndexResultInfo  appIndexInfo,int mark) {		
		EcmAppmoniIndex moniIndex = new EcmAppmoniIndex();
		moniIndex.setAppIndexIsview(SysConfigConstants.IS_DEFAULT);
		List<EcmAppmoniIndex> dataList =omsBaseService.findListByCond(moniIndex);
		String length = paramComboxService.getParaRemark1(SysConfigConstants.APPMONI_COL_LEN);
		if(mark!=APP_INDEX_MONITOR){
			appIndexInfo.addCol("维度类型", "appIndexrecDimeName",length);
			appIndexInfo.addCol("纬度值", "appIndexrecDimeval",length);
		}
		appIndexInfo.addCol("记录日期", "appIndexrecDate",length);
		appIndexInfo.addCol("起始时间", "appIndexrecSttime",length);
		appIndexInfo.addCol("结束时间", "appIndexrecEdtime",length);
		for(EcmAppmoniIndex appmoniIndex:dataList){
			appIndexInfo.addCol(appmoniIndex.getAppIndexName(), appmoniIndex.getAppIndexDesc(),length);
		}
	}
	
	private List<EcmAppmoniIndexRec> magicList(List<EcmAppmoniIndexRec> list){
		for(EcmAppmoniIndexRec appmoniIndexRec:list){		
			appmoniIndexRec.setAppIndexrecDimeName(paramComboxService.getParaName(appmoniIndexRec.getAppIndexrecDime()));
		}
		return list;
	}
	//查询流水表指标写入应用监控记录表线程
	private class CirceExcuteThread extends Thread {
		 private int threadAppMoniPeriod;		
		 
		 public CirceExcuteThread(){}
		  
		 public void run(){
			 while(true){
				 Lock lock = readWirteLock.writeLock();
				 threadAppMoniPeriod = appMoniPeriod;
			     if (lock.tryLock()) {
			          try {		        	  
			      		  List<EcmParam> paramList =paramComboxService.getParaColls(SysConfigConstants.APPMONI_DIME_TYPE,"false");
			      		  List<EcmAppmoniIndex> dataList =omsBaseService.findListByCond(new EcmAppmoniIndex());
			      		  //保证线程安全 后期若用时间可考虑其他方式在不影响效率的前提下解决线程安全问题
			      		  List<Map<String,Object>> indexList = Collections.synchronizedList(new ArrayList<Map<String,Object>>());//声明一个线程安全的list
			      		  String[] timeArray = createStTimeAndEnTime(threadAppMoniPeriod);
			      		  String whereSql = createWhereCond(timeArray[0],timeArray[1]);
			      		  CountDownLatch latch = new CountDownLatch(paramList.size()*dataList.size());
			      		  for(EcmParam param:paramList){
			      			String groupSql = createGroupCond(param.getParaCode());
			      			for(EcmAppmoniIndex moniIndex:dataList){
			      				AppIndexThreadInfo threadInfo = new AppIndexThreadInfo(); 
			      				threadInfo.setIndexList(indexList);
			      				threadInfo.setWhereSql(whereSql);
			      				threadInfo.setGroupSql(groupSql);
			      				threadInfo.setThreadAppIndexrecDime(param.getParaCode());
			      				new AppIndexQueryThread(moniIndex,threadInfo,latch).start();
				      		}
			      		  }
			      		 latch.await();
			    		 omsBaseDao.insertList(new EcmAppmoniIndexRec(),combinResult(indexList,timeArray));
			          }catch(InterruptedException e) {			
			    			 log.error("轮询应用监控指标 出错:"+DataUtil.printErrorStack(e));
			    	  }catch(Exception e){
			        	  log.error("轮询应用监控指标 出错:"+DataUtil.printErrorStack(e));
			          }		
			          finally {
			              lock.unlock();
			          }
			     }
				 try {
					Thread.sleep(appMoniPeriod*60*1000);
				 } catch (InterruptedException e) {			
					log.error("轮询应用监控指标 出错:"+DataUtil.printErrorStack(e));
				 }
			 }
			 
		 }
	}
	
	//合并查询结果
	private List<Map<String,Object>> combinResult(List<Map<String,Object>> indexList,String[] timeArray){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Map<String,Object>> midMap = new HashMap<String,Map<String,Object>>();
		for(Map<String,Object> rowMap:indexList){
			String mapKey = createMidMapKey(rowMap); 
			if(midMap.containsKey(mapKey)){
				midMap.get(mapKey).putAll(rowMap);
			}else{
				midMap.put(mapKey, rowMap);
			}
		}
		for(Map<String,Object> rowMap:midMap.values()){			
			max_req_no=serviceUtil.getMaxReqID(max_req_no,"ECM_APPMONI_INDEX_REC","APP_INDEXREC_ID");				
			rowMap.put("appIndexrecId",new Integer(max_req_no));
			rowMap.put("appIndexrecDate",DateUtils.getDate());
			rowMap.put("appIndexrecSttime",timeHandle(timeArray[0]));
			rowMap.put("appIndexrecEdtime",timeHandle(timeArray[1]));
			resultList.add(rowMap);
		}
		log.info("resultList="+resultList);		
		return resultList;
	}
	
	private String   timeHandle(String fullDate){
		return fullDate.substring(11,16);
	}
	private String createMidMapKey(Map<String,Object> rowMap){
		log.info("rowMap="+rowMap);
		return rowMap.get(DIME_VAL_COLNAME)+"-"+rowMap.get(DIME_TYPE_COLNAME);
	}
	//应用指标查询线程
	private class AppIndexQueryThread extends Thread {
		 private EcmAppmoniIndex moniIndex;
		 private CountDownLatch latch;
		 private AppIndexThreadInfo threadInfo;
		 
		 public AppIndexQueryThread(EcmAppmoniIndex moniIndex,AppIndexThreadInfo threadInfo,CountDownLatch latch){
			  this.moniIndex = moniIndex;
			  this.threadInfo = threadInfo;
			  this.latch = latch;
		 }
		  
		 public void run(){
			  queryAppFlowInfo(createIndexQuerySql(moniIndex,threadInfo),moniIndex,threadInfo);
			  latch.countDown();
		 }
	}
	//生成sql
	private String createIndexQuerySql(EcmAppmoniIndex moniIndex,AppIndexThreadInfo threadInfo){
		String facSql = moniIndex.getAppIndexFac().toUpperCase();
		StringBuilder facSqlSb = new StringBuilder();
		if(!threadInfo.getThreadAppIndexrecDime().equals(SysConfigConstants.APPMONI_DIME_NODIME)){
			String dimeType = paramComboxService.getParaRemark1(threadInfo.getThreadAppIndexrecDime());
			facSqlSb.append(facSql.replaceFirst("SELECT ","SELECT "+dimeType+" AS "+DIME_VAL_COLNAME+", "));
		}else{
			facSqlSb.append(facSql.replaceFirst("SELECT ","SELECT '' AS "+DIME_VAL_COLNAME+", "));
		}		
		if(facSql.indexOf(" WHERE ")>0){
			facSqlSb.append(" AND "+threadInfo.getWhereSql());
		}else{
			facSqlSb.append(" WHERE "+threadInfo.getWhereSql());
		}
		facSqlSb.append(threadInfo.getGroupSql());
		return facSqlSb.toString();
	}
	//生成where条件
	private String createWhereCond(String stTime,String edTime){		
		return TIME_COLS+">= '"+stTime + "' AND " + TIME_COLS + "<='"+edTime+"' ";
	}
	//生成gourp条件
	private String createGroupCond(String threadAppIndexrecDime){
        if(!threadAppIndexrecDime.equals(SysConfigConstants.APPMONI_DIME_NODIME)){
			return " GROUP BY "+paramComboxService.getParaRemark1(threadAppIndexrecDime);
		}
		return "";
	}
	//生成开始时间和结束时间
	private  String[] createStTimeAndEnTime(int threadAppMoniPeriod){
		String[]  timeArray = new String[2];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		timeArray[1] =df.format(cal.getTime());		
		cal.add(12,-threadAppMoniPeriod);
		timeArray[0] =df.format(cal.getTime());	
		log.info("threadAppMoniPeriod="+threadAppMoniPeriod+" timeArray[0]="+timeArray[0]+" timeArray[1]"+timeArray[1]);
		return timeArray;
	}
	
	//查询应用流水信息
	private void queryAppFlowInfo(String sql,EcmAppmoniIndex moniIndex,AppIndexThreadInfo threadInfo){
		List<Map<String,Object>> indexList = threadInfo.getIndexList();
		//IRouter router=sessionTemplate.getRouter();
		//Set<Shard> shards=router.routeShard(FLOW_TAB_SQLID, null);
		List<Shard> shards =sessionTemplate.lookupDataSourcesByRouter(FLOW_TAB_SQLID, null);
		Shard shard=(Shard) shards.toArray()[0];
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		Connection conn = null;
	    try {
	    	conn=shard.getDataSource().getConnection();	
	    	log.info("queryAppIndex Sql ="+sql);
			pstmt = conn.prepareStatement(sql);			
			rs= pstmt.executeQuery();
			while(rs.next()){
				 Map<String,Object> map = new HashMap<String,Object>();
				 map.put(DIME_VAL_COLNAME,rs.getString(1));
				 map.put(moniIndex.getAppIndexDesc(),handleStatData(rs.getString(2)));
				 map.put(DIME_TYPE_COLNAME,threadInfo.getThreadAppIndexrecDime());
				 indexList.add(map);
			}
		} catch (SQLException e) {
			log.error("查询应用流水信息出错，错误信息为："+DataUtil.printErrorStack(e));
		}finally{
			 try {
			      if(pstmt!=null)pstmt.close();						
			      if(rs!=null) rs.close();
			      if(conn!=null) conn.close();
			 }catch (SQLException e) {
				 log.error("查询应用流水信息出错，错误信息为："+DataUtil.printErrorStack(e));
			 }
		}
	}

	public static  String handleStatData(String dataValue){
		if(DataUtil.isNull(dataValue)){
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(Double.parseDouble(dataValue));
	}
	public static void main(String[] args){
//		String[] times =createStTimeAndEnTime(10);
//		System.out.println(times[0]);
//		System.out.println(times[1]);
		System.out.println(handleStatData("null"));
	}
	
}
