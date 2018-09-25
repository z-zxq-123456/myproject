package com.dcits.galaxy.dal.mybatis.plugins;

public class PageSqlFactory {
	
	public final static String DatabaseProductName_MySQL = "MySQL";
	
	public final static String DatabaseProductName_Oracle = "Oracle";
	
	public final static String DatabaseProductName_SQLite = "SQLite";

	/**
	 * 根据已有的SQL生成分页SQL
	 * @param sql 已有SQL
	 * @param rowArgs 行参数
	 * @param databaseProductName 数据库产品名
	 * @return
	 */
	public static String getPageSql(String sql,RowArgs rowArgs,String databaseProductName){
		String pageSql = sql;
		long offset = rowArgs.getOffset();
		long limit = rowArgs.getLimit();
		if( DatabaseProductName_MySQL.equalsIgnoreCase( databaseProductName ) ){
			pageSql = createMySQLPageSql(sql, offset, limit);
		}else if( DatabaseProductName_Oracle.equalsIgnoreCase( databaseProductName ) ){
			pageSql = createOraclePageSql(sql, offset, limit);
		}else if( DatabaseProductName_SQLite.equalsIgnoreCase( databaseProductName ) ){
			pageSql = createSQLitePageSql(sql, offset, limit);
		}else{
			//TODO
		}
		return pageSql;
	}
	
	/**
	 *根据原SQL语句构建查询总记录数的SQL语句
	 * @param sql 原始查询语句
	 * @return String 统计语句
	 */
	public static String getTotalSql(String sql) {
		sql = sql.toLowerCase();
		int index = sql.indexOf("from");
		return "select count(*) " + sql.substring(index);
	}
	
	private static String createMySQLPageSql(String sql, long offset, long limit){
		StringBuilder sb = new StringBuilder(sql);
		sb.append(" limit ").append(offset).append(",").append(limit);
		return sb.toString();
	}
	
	private static String createOraclePageSql(String sql, long offset, long limit ){
		StringBuffer pageSql = new StringBuffer();
		pageSql.append("select * from ( select u.*, rownum r from( ");
		pageSql.append( sql );
		pageSql.append(" ) u where rownum <= " + ( offset + limit ));
		pageSql.append(" ) where r > " + ( offset ));
		return pageSql.toString();
	}
	
	private static String createSQLitePageSql(String sql, long offset, long limit ){
		StringBuilder sb = new StringBuilder(sql);
		sb.append(" limit ").append(offset).append(",").append(limit);
		return sb.toString();
	}
}
