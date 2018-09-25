package com.dcits.galaxy.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;

/**
 * 日志平台DAO* 
 * @author tangxlf
 * @date 2016-10-17 
 */
@Repository
public class LogPlatDao {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ShardSqlSessionTemplate logPlatShardSqlSessionTemplate;
	
	private static final String NAME_SPACE ="com.dcits.galaxy.store.dao.LogPlatDao";
	
	private static final String SQLID_CHAINSTART ="insertChainStart";//调用链开始表--ECM_TCECHAIN_START  SQLID
	
	private static final String SQLID_CHAINBUS ="insertChainBus";    //调用链业务表--ECM_TCECIN_BUS      SQLID
	
	private static final String SQLID_CHAINEND ="insertChainEnd";    //调用链结束表--ECM_TCECIN_END      SQLID
	
	private static final String SQLID_TCE_CS ="insertTceCs";         //调用环客户端开始表--ECM_TCECIR_CS   SQLID
	
	private static final String SQLID_TCE_SR ="insertTceSr";         //调用环服务端开始表--ECM_TCECIR_SR   SQLID
	
	private static final String SQLID_TCE_SS ="insertTceSs";         //调用环服务端结束表--ECM_TCECIR_SS   SQLID
	
	private static final String SQLID_TCE_CR ="insertTceCr";         //调用环客户端结束表--ECM_TCECIR_CR   SQLID
	
	private static final String SQLID_ANNOT ="insertAnnot";          //日志标注表--ECM_TRACE_ANNOT      SQLID
	/**
	 * 调用链开始批量插入
	 * @param List<EcmTcechainStart> recordList 调用链开始对象列表	 
	 */
	public  void insertChainStartList(List<EcmTcechainStart> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_CHAINSTART,recordList);
		 //insertRows(InsertSqlProvider.createChainStartSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcechainStart total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用链业务字段批量插入
	 * @param List<EcmTcecinBus> recordList 调用链业务字段对象列表	 
	 */
	public  void insertChainBusList(List<EcmTcecinBus> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_CHAINBUS,recordList);
		 //insertRows(InsertSqlProvider.createChainBusSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecinBus total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用链结束批量插入
	 * @param List<EcmTcecinEnd> recordList 调用链结束对象列表	 
	 */
	public  void insertChainEndList(List<EcmTcecinEnd> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_CHAINEND,recordList);
		 //insertRows(InsertSqlProvider.createChainEndSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecinEnd total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用环客户端开始批量插入
	 * @param List<EcmTcecirCs> recordList 调用环客户端开始对象列表	 
	 */
	public  void insertTceCsList(List<EcmTcecirCs> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_TCE_CS,recordList);
		 //insertRows(InsertSqlProvider.createTceCsSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecirCs total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用环服务端开始批量插入
	 * @param List<EcmTcecirSr> recordList 调用环服务端开始对象列表	 
	 */
	public  void insertTceSrList(List<EcmTcecirSr> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_TCE_SR,recordList);
		 //insertRows(InsertSqlProvider.createTceSrSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecirSr total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用环服务端结束批量插入
	 * @param List<EcmTcecirSs> recordList 调用环服务端结束对象列表	 
	 */
	public  void insertTceSsList(List<EcmTcecirSs> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_TCE_SS,recordList);
		 //insertRows(InsertSqlProvider.createTceSsSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecirSs total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 调用环客户端结束批量插入
	 * @param List<EcmTcecirCr> recordList 调用环客户端结束对象列表	 
	 */
	public  void insertTceCrList(List<EcmTcecirCr> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_TCE_CR,recordList);
		 //insertRows(InsertSqlProvider.createTceCrSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTcecirCr total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	/**
	 * 日志标注批量插入
	 * @param List<EcmTraceAnnot> recordList 日志标注对象列表	 
	 */
	public  void insertAnnotList(List<EcmTraceAnnot> recordList) {
		 long startTime = System.currentTimeMillis();
		 logPlatShardSqlSessionTemplate.insertAddBatch(NAME_SPACE,SQLID_ANNOT,recordList);
		 //insertRows(InsertSqlProvider.createAnnotSql(recordList));
		 long endTime = System.currentTimeMillis();
		 log.warn("EcmTraceAnnot total time："+(endTime-startTime)+" rows num:"+recordList.size());
	}
	
	public void insertRows(String rowsSql){
		Connection conn = null;
		Statement stmt = null;
		 try {
	            conn = logPlatShardSqlSessionTemplate.getShardManager().getShard("partition_log").getDataSource().getConnection();
	            conn.setAutoCommit(false);
	            stmt = conn.createStatement();
	            stmt.execute(rowsSql);
	            conn.commit();
				conn.setAutoCommit(true);
	        } catch (Exception e) {
	        	log.error(" rowsSql: "+rowsSql+" batch insert failed: "+DataUtil.printErrorStack(e));
	        } finally {
	        	try {
		        	if (stmt != null) {	                
		                stmt.close();
		            }
		            if (conn != null) {	               
		                conn.close();	               
		            }
	        	 } catch (SQLException e) {
	                	log.error(" batch insert failed:"+DataUtil.printErrorStack(e));
	             }
	        }
	}
}
