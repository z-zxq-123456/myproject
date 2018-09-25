package com.dcits.galaxy.dal.mybatis.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.utils.MappedStatementUtils;
import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;

public class SQLParser {

	public final static String Const_Select = "SELECT";
	public final static String Const_From = "FROM";
	public final static String Const_Where = "WHERE";
	public final static String Const_OrderBy = "ORDER BY";
	public final static String Const_OrderBy_Asc = "ASC";
	public final static String Const_OrderBy_Desc = "DESC";
	public final static String Const_Page_Offset = "count(*)";
	public final static String Const_Page_Offset_Alias = "shard_page_offset";
	public final static String Const_WhiteSpace = " ";
	public final static String Const_As = "AS";
	public final static String Const_Or = "OR";
	public final static String Const_And = "AND";
	public final static String Const_Lt = "\t";
	public final static String Const_Ln = "\n";

	public final static String Regex_Lt_Ln = "(\\s|\\t|\\n)+";

	public final static String Regex_As = " AS ";
	public final static String Regex_Select = "SELECT ";
	public final static String Regex_From = " FROM ";
	public final static String Regex_Where = " WHERE ";
	public final static String Regex_OrderBy = " ORDER BY ";
	public final static String Regex_OrderBy_Asc = " ASC";
	public final static String Regex_OrderBy_Desc = " DESC";

	public final static String Regex_Comma = "[,]";
	public final static String Regex_WhiteSpace = "[ ]+?";
	public final static String Regex_Aggregate_Max = "^MAX[ ]*[(].*$";
	public final static String Regex_Aggregate_Min = "^MIN[ ]*[(].*$";
	public final static String Regex_Aggregate_Sum = "^SUM[ ]*[(].*$";
	public final static String Regex_Aggregate_Count = "^COUNT[ ]*[(].*$";
	public final static String Regex_Aggregate = "(MAX[ ]*[(])|(MIN[ ]*[(])|(SUM[ ]*[(])|(COUNT[ ]*[(])";

	public final static Pattern Pattern_Aggregate = Pattern.compile(Regex_Aggregate, Pattern.CASE_INSENSITIVE);

	
	/*
	 * 静态。动态SQL解析分派
	 */
	public static SelectStatement parser(SqlSource sqlSource) {
		if (sqlSource instanceof DynamicSqlSource) {
			return MappedStatementUtils.getDynamicSql(MappedStatementUtils.getNodes(sqlSource));
		} else if (sqlSource instanceof RawSqlSource || sqlSource instanceof StaticSqlSource) {
			return parser(MappedStatementUtils.getStaticSql(sqlSource));
		} else {
			return null;
		}
	}

	public static SelectStatement parser(String sql) {
		SelectStatement stmt = null;
		sql = sql.trim().replaceAll(SQLParser.Regex_Lt_Ln, Const_WhiteSpace);
		String tempSql = sql.trim().toUpperCase();
		int index_select = tempSql.indexOf(Regex_Select);
		int index_from = tempSql.indexOf(Regex_From);
		int index_where = tempSql.indexOf(Regex_Where);
		int index_orderby = tempSql.indexOf(Regex_OrderBy);

		if (index_select > -1 && index_from > (index_select + 7)) {
			String selectColumns = sql.substring(index_select + 7, index_from);
			stmt = new SelectStatement(selectColumns);
			stmt.setSql(sql);
			if(index_where!=-1){
				if (index_where > -1 && index_where > (index_from + 6)) {
					String tables = sql.substring(index_from + 6, index_where);
					stmt.setFromSql(tables);
				}
				if (index_orderby > -1 && index_orderby > (index_where + 7)) {
					String conditions = sql.substring(index_where + 7, index_orderby);
					stmt.setWhereSql(conditions);
					String orderby = sql.substring(index_orderby + 10);
					stmt.setOrderBySql(orderby);
				} else {
					// TODO
				}
			}else{
				String tables=sql.substring(index_from + 6,sql.length());
				int index_white =tables.indexOf(Const_WhiteSpace);
				String table=tables.substring(0,index_white);
				stmt.setFromSql(table);
				String orderby = sql.substring( index_orderby + 10);
				stmt.setOrderBySql( orderby ); 
			}
		}

		return stmt;
	}

	public static SelectStatement parser(String sql, List<SqlNode> list) {
		SelectStatement stmt = null;
		sql = sql.trim().replaceAll(SQLParser.Regex_Lt_Ln, Const_WhiteSpace);
		String tempSql = sql.trim().toUpperCase();
		int index_select = tempSql.indexOf(Regex_Select);
		int index_from = tempSql.indexOf(Regex_From);
		int index_where = tempSql.indexOf(Regex_Where);
		int index_orderby = tempSql.indexOf(Regex_OrderBy);
		if (index_select > -1 && index_from > (index_select + 7)) {
			String selectColumns = sql.substring(index_select + 7, index_from);
			stmt = new SelectStatement(selectColumns);
			stmt.setSql(sql);
			if (index_where > -1 && index_where > (index_from + 6)) {
				String tables = sql.substring(index_from + 6, index_where);
				stmt.setFromSql(tables);
			}
			if (index_orderby > -1 && index_orderby >= (index_where + 7)) {
				MixedSqlNode mixsql = new MixedSqlNode(list);
				stmt.setWhereNode(mixsql);
				String orderby = sql.substring(index_orderby + 10);
				stmt.setOrderBySql(orderby);
			}
		}
		return stmt;
	}

	/**
	 * 获取聚合列
	 * 
	 * @param stmt
	 * @return
	 */
	public static List<RuleColumn> getAggregateColumns(SelectStatement stmt) {
		List<RuleColumn> columnList = new ArrayList<RuleColumn>();

		String originalColumns = stmt.getSelectSql();
		Matcher matcher = Pattern_Aggregate.matcher(originalColumns);
		if (matcher.find()) {
			Map<String, SelectColumn> columns = stmt.getSelectColumns();
			Set<Entry<String, SelectColumn>> entrySet = columns.entrySet();
			for (Entry<String, SelectColumn> entry : entrySet) {
				SelectColumn column = entry.getValue();
				String columnName = column.getColumnName();
				String upperCaseColumn = columnName.toUpperCase();
				String aggregate = "";
				if (upperCaseColumn.matches(Regex_Aggregate_Max)) {
					aggregate = "max";
				} else if (upperCaseColumn.matches(Regex_Aggregate_Min)) {
					aggregate = "min";
				} else if (upperCaseColumn.matches(Regex_Aggregate_Count)) {
					aggregate = "count";
				} else if (upperCaseColumn.matches(Regex_Aggregate_Sum)) {
					aggregate = "sum";
				} else {
					// TODO
				}
				if (aggregate.isEmpty()) {
					continue;
				}
				String alias = column.getColumnLabel();
				RuleColumn ruleColumn = new RuleColumn();
				ruleColumn.setName(columnName);
				ruleColumn.setAlias(alias);
				ruleColumn.setAggregate(aggregate);
				columnList.add(ruleColumn);
			}

			// String[] columnArray = originalColumns.split(Regex_Comma);
			// for (String column : columnArray) {
			// column = column.trim();
			// String upperCaseColumn = column.toUpperCase();
			// if( !column.isEmpty() ){
			// String aggregate = "";
			// if( upperCaseColumn.matches( Regex_Aggregate_Max) ){
			// aggregate = "max";
			// }else if( upperCaseColumn.matches( Regex_Aggregate_Min ) ){
			// aggregate = "min";
			// }else if( upperCaseColumn.matches( Regex_Aggregate_Count ) ){
			// aggregate = "count";
			// }else if( upperCaseColumn.matches( Regex_Aggregate_Sum ) ){
			// aggregate = "sum";
			// }else{
			// //TODO
			// }
			// if( aggregate.isEmpty() ){
			// continue;
			// }
			// int index_as = upperCaseColumn.indexOf(Regex_As);
			// if( index_as > 0 ){
			// String name = column.substring(0, index_as);
			// name = name.replaceAll(Regex_WhiteSpace, "");
			// String alias = column.substring(index_as + Regex_As.length());
			// alias = alias.replaceAll(Regex_WhiteSpace, "");
			// RuleColumn ruleColumn = new RuleColumn();
			// ruleColumn.setName(name);
			// ruleColumn.setAlias(alias);
			// ruleColumn.setAggregate(aggregate);
			// columnList.add( ruleColumn );
			//
			// }
			// }
			// }
		}

		return columnList;
	}

	/**
	 * 获取排序列
	 * 
	 * @param stmt
	 * @return
	 */
	public static List<RuleColumn> getOrderByColumns(SelectStatement stmt, ResultMap resultMap,
			boolean isMapUnderscoreToCamelCase) {
		List<RuleColumn> columnList = new ArrayList<RuleColumn>();
		String columns = stmt.getOrderBySql();

		if (columns != null && !columns.isEmpty()) {
			Class<?> type = resultMap.getType();
			MetaClass metaClass = MetaUtils.forClass(type);
			String[] columnArray = columns.split(Regex_Comma);
			for (String column : columnArray) {
				column = column.trim();
				String upperCaseColumn = column.toUpperCase();
				if (!column.isEmpty()) {
					RuleColumn ruleColumn = new RuleColumn();

					int index_desc = upperCaseColumn.indexOf(Regex_OrderBy_Desc);
					if (index_desc > 0) {
						ruleColumn.setDesc(true);
						column = column.substring(0, index_desc);
					} else {
						ruleColumn.setDesc(false);
						int index_asc = upperCaseColumn.indexOf(Regex_OrderBy_Asc);
						if (index_asc > 0) {
							column = column.substring(0, index_asc);
						}
					}
					String name = column.trim();
					ruleColumn.setName(name);
					String alias = stmt.getColumnLabel(name);
					if (alias == null || alias.isEmpty()) {
						alias = metaClass.findProperty(name, isMapUnderscoreToCamelCase);
					}
					ruleColumn.setAlias(alias);
					columnList.add(ruleColumn);
				}
			}
		}
		return columnList;
	}
}
