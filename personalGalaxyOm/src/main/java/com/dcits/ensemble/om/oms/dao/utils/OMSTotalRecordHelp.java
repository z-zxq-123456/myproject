package com.dcits.ensemble.om.oms.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.dal.mybatis.transaction.DataSourceUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.mybatis.spring.SqlSessionTemplate;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import java.util.Set;
/**
 * 分页辅助类
 * @author tangxlf
 * @date 2015-08-17
 */
public class OMSTotalRecordHelp {
	public static  int  findTotalRecoudNum(ShardSqlSessionTemplate sessionTemplate,String sqlId,Object paraObj){
		//SqlSessionTemplate session =sessionTemplate.getSqlSessionTemplate();
		MappedStatement mappedStat = sessionTemplate.getConfiguration().getMappedStatement(sqlId);
		//IRouter router=sessionTemplate.getRouter();
		List<Shard> shards =sessionTemplate.lookupDataSourcesByRouter(sqlId, paraObj);
//		Set<Shard> shards=router.routeShard(sqlId, paraObj);
		Shard s=(Shard) shards.toArray()[0];
		paraObj=createParaWrapper(paraObj);
		String soursql =mappedStat.getBoundSql(paraObj).getSql();
		List<ParameterMapping> list = mappedStat.getBoundSql(paraObj).getParameterMappings();
		String countSql = getCountSql(soursql);
		System.out.println("txl  txl countSql = "+countSql);
		BoundSql countBoundSql = new BoundSql(mappedStat.getConfiguration(),countSql,list,paraObj);
		ParameterHandler handler = new DefaultParameterHandler(mappedStat,paraObj,countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int totalRecord = 0;
	    try {
	    	conn =s.getDataSource().getConnection();
			pstmt = conn.prepareStatement(countSql);
			handler.setParameters(pstmt);
			rs= pstmt.executeQuery();
			if(rs.next()){
				 totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new GalaxyException(e.getMessage());
		}finally{
			 try {
			      if(pstmt!=null)pstmt.close();
			      if(rs!=null) rs.close();
			      if(conn!=null) conn.close();
			 }catch (SQLException e) {
					throw new GalaxyException(e.getMessage());
			 }
		}
	    return totalRecord;
	}

	private static String getCountSql(String sql){
		String[] sliceSqls = sql.toUpperCase().split("UNION  ALL");
		StringBuilder sbSql = new StringBuilder();
		for(int i = 0 ; i < sliceSqls.length ; i++){
			if(sliceSqls.length == 1){
				sbSql.append(replaceSliceCountSql(sliceSqls[i]));
			}else if(i != (sliceSqls.length-1) && sliceSqls.length > 1){
				sbSql.append(replaceSliceCountSql(sliceSqls[i]) + " UNION  ALL ");
			}else if(i == (sliceSqls.length-1) && sliceSqls.length > 1){
				sbSql.append(replaceSliceCountSql(sliceSqls[i].toUpperCase().split(" ORDER BY ")[0]));
			}
		}
		return sbSql.toString();
	}

	private static String replaceSliceCountSql(String slicSql){
		int indexfrom = slicSql.toUpperCase().indexOf("FROM");
		return "select count(*) "+slicSql.substring(indexfrom);
	}


	private static ParameterWrapper createParaWrapper(Object queryObj){
    	ParameterWrapper paraWrapper = new ParameterWrapper();
		paraWrapper.setBaseParam(queryObj);
		return paraWrapper;
    }
}
