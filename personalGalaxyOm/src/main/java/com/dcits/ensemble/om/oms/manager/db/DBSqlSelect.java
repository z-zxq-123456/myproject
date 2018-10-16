package com.dcits.ensemble.om.oms.manager.db;

import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库实例查询* 
 * @author tangxlf
 * @date 2016-05-03
 */
@Component
public class DBSqlSelect {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());		
	
	private static final String DBINT_SELECT ="数据库实例查询";
	@Resource
	ParamComboxService paramComboxService;
	
	private static final String COMMON_DATACOL_DBINTNAME = "DB_INT_NAME";//公共数据字段--实例名称
	

	/**
	 * 按照SQL查询指定数据库实例
	 * @param EcmMidwareDbint         intant          db实例对象	 
	 * @param String                  sql             查询SQL  
	 * @param DBSqlResultInfo         rsInfo          数据库查询结果   
	 * 
	 */ 	
	public  void selectDBint(EcmMidwareDbint intant,String sql,DBSqlResultInfo rsInfo) {
		String classDriverName = paramComboxService.getParaRemark1(intant.getDbType());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {		
			Class.forName(classDriverName);
			String pwd  = OMSPassWordBase64.decodeToString(intant.getDbintUserPwd());
			System.out.println(pwd+DbIntHelpUtil.createDbUrl(intant)+intant.getDbintUserName());
			conn = DriverManager.getConnection(DbIntHelpUtil.createDbUrl(intant),intant.getDbintUserName(),pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rsInfo.getColsName() == null){
				rsInfo.setColsName(createColName(rs));
			}
			while(rs.next()){
				Map<String,String> rowMap = new HashMap<String,String>();
				setCommonCol(intant, rowMap);
				for(String colName : rsInfo.getColsName()){
					if(!isCommonCol(colName))rowMap.put(colName,rs.getString(colName)==null?"":rs.getString(colName));
				}
				rsInfo.getResultList().add(rowMap);
			}
		}catch (ClassNotFoundException e) {
			log.error(DBINT_SELECT+"出错,错误信息为："+e.getMessage());			
		}catch (SQLException e) {
			log.error(DBINT_SELECT+"出错,错误信息为："+e.getMessage());			
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {					
					log.error(e.getMessage());	
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {					
					log.error(e.getMessage());	
				}
			}
			if(conn!=null){
				try {
					conn.close();
				}catch (SQLException e) {
					log.error(e.getMessage());					
				}
			}
		}
    }
	//生成SQL字段名
	private  String[] createColName(ResultSet rs) throws SQLException{
		ResultSetMetaData meta = rs.getMetaData();
		String[] colsName  = null;
		if(meta.getColumnCount()>0){
			colsName  = new String [meta.getColumnCount() + 1];
			colsName[0] = COMMON_DATACOL_DBINTNAME;
		}
		for(int i=1;i<meta.getColumnCount()+1;i++){
			colsName[i] = meta.getColumnName(i);
		}
		return colsName;
	}
	//公共字段赋值
	private void setCommonCol(EcmMidwareDbint intant,Map<String,String> rowMap){
		rowMap.put(COMMON_DATACOL_DBINTNAME, "<a type=\"text\" style=\"color:blue\"  title=" + "\'" + getDbintInfo(intant) + "\'" + " >" + intant.getDbintName()+"</a>");
	}
	private String getDbintInfo(EcmMidwareDbint intant) {
		String dbInfoInfo="服务器IP："+intant.getSerIp() +
				"\n端口号："+intant.getDbintPort()+
				"\n服务名:"+intant.getDbintServiceName()+
				"\n用户名:"+intant.getDbintUserName();
		return dbInfoInfo;
	}
	private boolean isCommonCol(String colName){
		if(colName.equals(COMMON_DATACOL_DBINTNAME)){
			return true;
		}
		return false;
	}
	
}
