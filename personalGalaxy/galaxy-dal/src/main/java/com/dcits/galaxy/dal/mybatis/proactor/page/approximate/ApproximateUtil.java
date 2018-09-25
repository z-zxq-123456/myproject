package com.dcits.galaxy.dal.mybatis.proactor.page.approximate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.parser.SQLParser;
import com.dcits.galaxy.dal.mybatis.parser.SelectStatement;
import com.dcits.galaxy.dal.mybatis.utils.MappedStatementUtils;

public class ApproximateUtil {


	public final static String Const_Offset_Column = "count(*)";
	public final static String Const_Offset_Column_Alias = "shard_page_total";
	public final static String Const_offset_column_Key = "shard_page_offset_column";
	public final static String Const_offset_statement_Key = "shard_page_offset_statement";
	
	public final static String Const_Select = SQLParser.Const_Select;
	public final static String Const_From = SQLParser.Const_From;
	public final static String Const_Where = SQLParser.Const_Where;
	public final static String Const_OrderBy = SQLParser.Const_OrderBy;
	public final static String Const_OrderBy_Asc = SQLParser.Const_OrderBy_Asc;
	public final static String Const_OrderBy_Desc = SQLParser.Const_OrderBy_Desc;
	public final static String Const_WhiteSpace = SQLParser.Const_WhiteSpace;
	public final static String Const_As = SQLParser.Const_As;
	
	/**
	 * 生成逼近查找分页中所需的查询偏移量的查询语句
	 * @param stmt
	 * @param orderByColumnList
	 * @param configuration 
	 * @return
	 */
	public static SqlSource getOffsetSql(SelectStatement stmt, List<RuleColumn> orderByColumnList, Configuration configuration){
		
		List<SqlNode> sqlNodes = new ArrayList<>();
		
		StringBuilder builder = new StringBuilder();
		builder.append( Const_Select ).append(Const_WhiteSpace);
		builder.append(Const_Offset_Column).append(Const_WhiteSpace);
		builder.append(Const_As).append(Const_WhiteSpace).append( Const_Offset_Column_Alias ).append(Const_WhiteSpace);
		builder.append(Const_From).append(Const_WhiteSpace).append( stmt.getFromSql()).append(Const_WhiteSpace);
		builder.append(Const_Where).append(Const_WhiteSpace);
		
		sqlNodes.add(new StaticTextSqlNode(builder.toString()));
		
		String conditions = stmt.getWhereSql();
		
		if(conditions != null && !conditions.isEmpty()){
			builder =  new StringBuilder();
			builder.append( "(" ).append( conditions ).append( ")" );
			sqlNodes.add(new TextSqlNode(builder.toString()));
		} else if(stmt.getWhereNode() != null){
			sqlNodes.add(new StaticTextSqlNode("("));
			sqlNodes.add(stmt.getWhereNode());
			sqlNodes.add(new StaticTextSqlNode(")"));
		}
		
		if( orderByColumnList.size() > 0){
			builder =  new StringBuilder();
			String extConditions = toConditions(orderByColumnList);
			builder.append(" and (").append( extConditions ).append( ")" );
			sqlNodes.add(new TextSqlNode(builder.toString()));
		}
		
		sqlNodes = MappedStatementUtils.mergeSqlNodes(sqlNodes);
		
		if(sqlNodes == null){
			return null;
		}
		
		SqlNode sql = null;
		if(sqlNodes.size() == 1){
			sql = sqlNodes.get(0);
		} else {
			sql = new MixedSqlNode(sqlNodes);
		}
		
		return new DynamicSqlSource(configuration, sql);
	}
	
	
	/**
	 * 生成逼近查找分页中所需的查询偏移量的MappedStatement
	 * @param configuration
	 * @param sql
	 * @param sqlMap
	 * @param orderByColumnList
	 * @param mappedStatement 
	 * @return
	 */
	public static MappedStatement createOffsetMappedStatement(Configuration configuration,String sql,
			String sqlMap, List<RuleColumn> orderByColumnList, MappedStatement mappedStatement){
		Class<?> resultClass = HashMap.class;
		return  MappedStatementUtils.buildMappedStatement(mappedStatement, sqlMap, sql, resultClass);
	}
	
	/**
	 * 将多个排序列转换成查询条件
	 * @param orderByColumnList
	 * @return
	 */
	public static String toConditions( List<RuleColumn> orderByColumnList){
		StringBuffer extConditions = new StringBuffer();
		int size = orderByColumnList.size();
		RuleColumn ruleColumn = orderByColumnList.get( 0 );
		extConditions.append( toCondition(ruleColumn, false)).append(Const_WhiteSpace);
		for( int i = 1; i < size; i++){
			RuleColumn ruleColumn_i = orderByColumnList.get(i);
			extConditions.append( "or (");
			for( int j = 0; j < i; j++){
				RuleColumn ruleColumn_j = orderByColumnList.get(j);
				if( j == 0){
					extConditions.append( toCondition(ruleColumn_j, true)).append(Const_WhiteSpace);;
				}else{
					extConditions.append( "and ").append( toCondition(ruleColumn_j, true)).append(Const_WhiteSpace);;
				}
			}
			extConditions.append( "and ").append( toCondition(ruleColumn_i, false));
			extConditions.append(" ) ");
		}
		return extConditions.toString();
	}
	
	/**
	 * 将单个排序列转换成条件语句
	 * @param ruleColumn
	 * @param equal
	 * @return
	 */
	public static StringBuffer toCondition( RuleColumn ruleColumn, boolean equal){
		StringBuffer extConditions = new StringBuffer();
		String name = ruleColumn.getName();
		String alias = ruleColumn.getAlias();
		extConditions.append( name );
		boolean isAsc = ruleColumn.isAsc();
		if( equal ){
			extConditions.append("=");
		}else{
			if( isAsc ){
				extConditions.append("<");
			}else{
				extConditions.append(">");
			}
		}
		extConditions.append("#{extParam.").append(alias).append("}");
		return extConditions;
	}
}
