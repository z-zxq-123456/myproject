package com.dcits.galaxy.store.dao;

import java.util.List;

import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;


/**
 * 日志平台日志插入语句生成
 * <p>Created on 2016/11/15.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
public class InsertSqlProvider {
	/**
	 * 调用链开始批量插入语句生成
	 * @param List<EcmTcechainStart> recordList 调用链开始对象列表
	 * @return 调用链开始插入语句	 
	 */
	public static String createChainStartSql(List<EcmTcechainStart> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECHAIN_START (");
		inserSql.append("TRACE_ID,");
		inserSql.append("TRACE_STTIME,");
		inserSql.append("TRACE_IN_SER,");
		inserSql.append("TRACE_IN_MTD");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getTraceSttime()+"','");
			inserSql.append(recordList.get(i).getTraceInSer()+"','");
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getTraceInMtd()+"'),");
			}else{
				inserSql.append(recordList.get(i).getTraceInMtd()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用链业务字段批量插入语句生成
	 * @param List<EcmTcecinBus> recordList 调用链业务字段对象列表
	 * @return 调用链业务字段插入语句	 
	 */
	public static String createChainBusSql(List<EcmTcecinBus> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIN_BUS (");
		inserSql.append("TRACE_ID,");
		inserSql.append("MESSAGE_CODE,");
		inserSql.append("MESSAGE_TYPE,");
		inserSql.append("SERVICE_CODE,");
		inserSql.append("ACCT_NO,");
		inserSql.append("CARD_NO,");
		inserSql.append("BRANCH_ID,");
		inserSql.append("TELLER_ID");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getMessageCode()+"','");
			inserSql.append(recordList.get(i).getMessageType()+"','");
			inserSql.append(recordList.get(i).getServiceCode()+"','");
			inserSql.append(recordList.get(i).getAcctNo()+"','");
			inserSql.append(recordList.get(i).getCardNo()+"','");
			inserSql.append(recordList.get(i).getBranchId()+"','");
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getTellerId()+"'),");
			}else{
				inserSql.append(recordList.get(i).getTellerId()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用链结束批量插入语句生成
	 * @param List<EcmTcecinEnd> recordList 调用链结束对象列表
	 * @return 调用链结束插入语句	 
	 */
	public static String createChainEndSql(List<EcmTcecinEnd> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIN_END (");
		inserSql.append("TRACE_ID,");
		inserSql.append("TRACE_ENTIME,");		
		inserSql.append("TRACE_STATUS");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getTraceEntime()+"','");			
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getTraceStatus()+"'),");
			}else{
				inserSql.append(recordList.get(i).getTraceStatus()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用环客户端开始批量插入语句生成
	 * @param List<EcmTcecirCs> recordList 调用环客户端开始对象列表
	 * @return 调用环客户端开始插入语句	 
	 */
	public static String createTceCsSql(List<EcmTcecirCs> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIR_CS (");
		inserSql.append("CIR_ID,");
		inserSql.append("TRACE_ID,");
		inserSql.append("CIR_PARENT_ID,");
		inserSql.append("CIR_TYPE,");
		inserSql.append("CIR_CLIENT_IPPORT,");
		inserSql.append("CIR_CLIENT_STTM,");
		inserSql.append("CIR_IN_MSG,");
		inserSql.append("CIR_CLIENT_THDNUM,");
		inserSql.append("CIR_SERVER_IPPORT,");
		inserSql.append("CIR_SERVER_SER,");
		inserSql.append("CIR_SERVER_MTD");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getCirId()+"','");
			inserSql.append(recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getCirParentId()+"','");
			inserSql.append(recordList.get(i).getCirType()+"','");
			inserSql.append(recordList.get(i).getCirClientIpport()+"','");
			inserSql.append(recordList.get(i).getCirClientSttm()+"','");
			inserSql.append(recordList.get(i).getCirInMsg()+"','");
			inserSql.append(recordList.get(i).getCirClientThdnum()+"','");
			inserSql.append(recordList.get(i).getCirServerIpport()+"','");
			inserSql.append(recordList.get(i).getCirServerSer()+"','");			
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getCirServerMtd()+"'),");
			}else{
				inserSql.append(recordList.get(i).getCirServerMtd()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用环服务端开始批量插入语句生成
	 * @param List<EcmTcecirSr> recordList 调用环服务端开始对象列表
	 * @return 调用环服务端开始插入语句	 
	 */
	public static String createTceSrSql(List<EcmTcecirSr> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIR_SR (");
		inserSql.append("CIR_ID,");
		inserSql.append("TRACE_ID,");
		inserSql.append("CIR_SERVER_STTM,");	
		inserSql.append("CIR_SERVER_THDNUM");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getCirId()+"','");
			inserSql.append(recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getCirServerSttm()+"','");					
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getCirServerThdnum()+"'),");
			}else{
				inserSql.append(recordList.get(i).getCirServerThdnum()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用环服务端结束批量插入语句生成
	 * @param List<EcmTcecirSs> recordList 调用环服务端结束对象列表
	 * @return 调用环服务端结束插入语句	 
	 */
	public static String createTceSsSql(List<EcmTcecirSs> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIR_SS (");
		inserSql.append("CIR_ID,");
		inserSql.append("TRACE_ID,");
		inserSql.append("CIR_SERVER_STATUS,");	
		inserSql.append("CIR_SERVER_ENTM");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getCirId()+"','");
			inserSql.append(recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getCirServerStatus()+"','");					
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getCirServerEntm()+"'),");
			}else{
				inserSql.append(recordList.get(i).getCirServerEntm()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 调用环客户端结束批量插入语句生成
	 * @param List<EcmTcecirCr> recordList 调用环客户端结束对象列表
	 * @return 调用环客户端结束插入语句	 
	 */
	public static String createTceCrSql(List<EcmTcecirCr> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TCECIR_CR (");
		inserSql.append("CIR_ID,");
		inserSql.append("TRACE_ID,");
		inserSql.append("CIR_CLIENT_STATUS,");	
		inserSql.append("CIR_CLIENT_ENTM,");
		inserSql.append("CIR_OUT_MSG");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getCirId()+"','");
			inserSql.append(recordList.get(i).getTraceId()+"','");
			inserSql.append(recordList.get(i).getCirClientStatus()+"','");
			inserSql.append(recordList.get(i).getCirClientEntm()+"','");
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getCirOutMsg()+"'),");
			}else{
				inserSql.append(recordList.get(i).getCirOutMsg()+"')");
			}
		}
		return inserSql.toString();
	}
	/**
	 * 日志标注批量插入语句生成
	 * @param List<EcmTraceAnnot> recordList 日志标注对象列表
	 * @return 日志标注插入语句	 
	 */
	public static String createAnnotSql(List<EcmTraceAnnot> recordList){
		StringBuilder  inserSql = new StringBuilder(" INSERT INTO ECM_TRACE_ANNOT (");
		inserSql.append("CIR_ID,");
		inserSql.append("ANNOT_TIME,");
		inserSql.append("ANNOT_LEV,");
		inserSql.append("ANNOT_TYPE,");
		inserSql.append("ANNOT_CLS,");
		inserSql.append("ANNOT_MTD,");
		inserSql.append("ANNOT_ROWNM,");
		inserSql.append("ANNOT_TEXT,");		
		inserSql.append("ANNOT_THREAD_NUM");
		inserSql.append(") VALUES ");
		for(int i=0;i<recordList.size();i++){
			inserSql.append("('"+recordList.get(i).getCirId()+"','");
			inserSql.append(recordList.get(i).getAnnotTime()+"','");
			inserSql.append(recordList.get(i).getAnnotLev()+"','");
			inserSql.append(recordList.get(i).getAnnotType()+"','");
			inserSql.append(recordList.get(i).getAnnotCls()+"','");
			inserSql.append(recordList.get(i).getAnnotMtd()+"','");
			inserSql.append(recordList.get(i).getAnnotRownm()+"','");
			inserSql.append(recordList.get(i).getAnnotText()+"','");			
			if(i!=recordList.size()-1){
				inserSql.append(recordList.get(i).getAnnotThreadNum()+"'),");
			}else{
				inserSql.append(recordList.get(i).getAnnotThreadNum()+"')");
			}
		}
		return inserSql.toString();
	}
}
