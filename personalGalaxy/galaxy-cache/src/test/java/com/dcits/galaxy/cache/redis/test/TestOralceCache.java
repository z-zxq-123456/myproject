package com.dcits.galaxy.cache.redis.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.paratab.DatabaseFactory;
import com.dcits.galaxy.cache.paratab.metadata.Column;
import com.dcits.galaxy.cache.paratab.metadata.Database;
import com.dcits.galaxy.cache.paratab.metadata.Table;
import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;

public class TestOralceCache extends TestBase implements ICacheStore{
	public  void testGetRow() {										
		System.out.println(ParaTabCacheProxy.getSigleKeyRowMap("tellerccy",76));//入口MAP 出口MAP	
		
	}
	
	public  void testJdbc() {										
		 SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate)SpringApplicationContext.getContext().getBean("sqlSessionTemplate");
		 Map<String,Column> colMap = new HashMap<String,Column>();
			Connection conn = null;	
			String tableName = "tellerccy";
			try {
				conn = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				System.out.println("start");
				Database database = DatabaseFactory.newDatabase(conn, null, conn.getMetaData().getUserName());
				System.out.println("end");
				Table table =database.getTable(tableName);
				String[] colNames =table.getColumnNames();			
				for(String dbColName:colNames){
					colMap.put(transColName(dbColName), table.getColumn(dbColName));				
				}
			} catch (SQLException e) {
				throw new CacheException(tableName+"参数表缓存初始化出错"+e.getMessage());
			}finally{
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						throw new CacheException(tableName+"参数表缓存初始化出错"+e.getMessage());
					}
				}
			}		
			System.out.println("colMap="+colMap);
		
	}
	
	public  void testColumn() {										
		 SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate)SpringApplicationContext.getContext().getBean("sqlSessionTemplate");		
		 Connection conn = null;
		 Date startTime1 = new Date();
			try {
				conn = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				Database database = new Database(null, conn.getMetaData().getUserName());
				System.out.println("start");
				DatabaseMetaData dbmd = conn.getMetaData();
				ResultSet rs = null;
				
			      try
			      {
			        rs = dbmd.getColumns(null, conn.getMetaData().getUserName(), null, null);
			        System.out.println(" diff="+(new Date().getTime()-startTime1.getTime()));
			        
			        while (rs.next())
			        {
			          String catalogName = rs.getString("TABLE_CAT");
			          String schemaName = rs.getString("TABLE_SCHEM");
			          String tableName = rs.getString("TABLE_NAME");
			          String columnName = rs.getString("COLUMN_NAME");
			          int dataType = Integer.parseInt(rs.getString("DATA_TYPE"));
			          Table table = database.getTable(tableName);
			          if (table == null)
			          {
			            table = new Table(tableName);
			            table.setCatalog(catalogName);
			            table.setSchema(schemaName);
			            database.addTable(table);
			          }
			          table.addColumn(new Column(columnName, dataType));
			        }
			      }
			      finally
			      {
			        if (rs != null) {
			          rs.close();
			        }
			      }
			      System.out.println(" diff="+(new Date().getTime()-startTime1.getTime()));
			      try
			      {
			        String[] tableNames = database.getTableNames();
			        System.out.println(" tableNames.len="+tableNames.length);
			        for (int i = 0; i < tableNames.length; i++)
			        {
			          Table table = database.getTable(tableNames[i]);
			          System.out.println(" i="+i+" tableName="+tableNames[i]);
			         
			          try{
			        	 rs = dbmd.getPrimaryKeys(null,  conn.getMetaData().getUserName(), table.getName());
			          
			          if (rs.next())
			          {
			            String columnName = rs.getString("COLUMN_NAME");
			            table.setPrimaryKey(table.getColumn(columnName));
			          }
			          }finally{
			        	  rs.close(); 
			          }
			          
			        }
			      }
			      finally
			      {
			        if (rs != null) {
			          rs.close();
			        }
			      }
			} catch (SQLException e) {
				throw new CacheException(e.getMessage());
			}finally{
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						throw new CacheException(e.getMessage());
					}
				}
			}
			System.out.println(" diff="+(new Date().getTime()-startTime1.getTime()));
	}
	

	private String transColName(String dbColName){
		return JsonUtils.convertHumpCase(dbColName);
	}
	
	public void testGetValue(){		
		System.out.println(ParaTabCacheProxy.getSigleKeyValue("tabUser","userName",2));//入口MAP
	}
	
	
	public void testDeleteRow(){	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		ParaTabCacheProxy.deleteRow("tabProd", map);
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd", map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		ParaTabCacheProxy.deleteRow("tabProd", prod);
		System.out.println(ParaTabCacheProxy.getRowBean("tabProd", prod, prod));//入口MAP 出口MAP
	}
	
	public void testColType(){
		Map<String,Object> comMap = ParaTabCacheProxy.getSigleKeyRowMap("tabUser",2);		
		for(Map.Entry<String,Object> entry : comMap.entrySet()){			
			System.out.println("字段名"+entry.getKey()+"字段类型"+entry.getValue().getClass());
		}
	}
	
	public void testInsertRow(){
		//clearCache();	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 17:30:11");
		ParaTabCacheProxy.insertRow("tabProd",map);//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		prod.setPrdComment("14comment");
		prod.setPrdStatus("2");		
		prod.setPrdDatetime("2015-01-09 17:30:11");
		
		ParaTabCacheProxy.insertRow("tabProd",prod);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",prod));//入口BEAN 出口MAP
		System.out.println(ParaTabCacheProxy.getRowBean("tabProd",prod,prod).getPrdDatetime());//入口BEAN 出口BEAN
		//viewCacheDetail();
	}
	
	public void testUpdateRow(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdId","103");
		map.put("prdType",113);
		map.put("prdComment","update103");
		map.put("prdStatus","1");
		map.put("prdDatetime","2015-01-09 18:30:11");
		ParaTabCacheProxy.updateRow("tabProd",map);//入口MAP 
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",map));//入口MAP 出口MAP
		TabProd prod = new TabProd();
		prod.setPrdId("104");
		prod.setPrdType("14");
		prod.setPrdComment("update14");
		prod.setPrdStatus("3");		
		prod.setPrdDatetime("2015-01-09 17:30:11");
		ParaTabCacheProxy.updateRow("tabProd",prod);//入口BEAN 		
		System.out.println(ParaTabCacheProxy.getRowMap("tabProd",prod));//入口BEAN 出口MAP
	}
	
	
}
