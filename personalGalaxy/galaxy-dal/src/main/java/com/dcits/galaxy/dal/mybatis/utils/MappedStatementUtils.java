package com.dcits.galaxy.dal.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

import com.dcits.galaxy.dal.mybatis.parser.SQLParser;
import com.dcits.galaxy.dal.mybatis.parser.SelectStatement;


public abstract class MappedStatementUtils {

	/**
	 * 动态Sql及ProviderSqlSource无法生成静态sql，返回空字符串
	 */
	private final static String EMPTY_SQL = "";

	/**
	 * DynamicSqlSource类中包含的SqlNode实例对应的属性名
	 */
	private final static String CONST_DYNAMIC_CONTENTS = "rootSqlNode.contents";

	/**
	 * RawSqlSource类中包含的SqlSource实例对应的属性名
	 */
	private final static String CONST_RAW_SQLSOURCE = "sqlSource";

	/**
	 * StaticSqlSource类中包含的sql语句对应的属性名
	 */
	private final static String CONST_STATIC_SQL = "sql";

	/**
	 * StaticSqlSource类中包含的ParameterMappings对应的属性名
	 */
	private final static String CONST_STATIC_MAPPINGS = "parameterMappings";

	/**
	 * TextSqlNode、StaticTextSqlNode类中包含的sql语句对应的属性名
	 */
	private final static String CONST_TEXT_SQL = "text";

	/**
	 * IfSqlNode类中包含的条件表达式对应的属性名
	 */
	private final static String CONST_TEST_EXPR = "test";

	/**
	 * MappedStatement类中mapId对应的后缀名
	 */
	private final static String CONST_INLINE_SUFFIX = "-Inline";

	public static List<SqlNode> getNodes(SqlSource sqlSource) {
		if (!(sqlSource instanceof DynamicSqlSource))
			return null;

		MetaObject metaObject = MetaUtils.forObject(sqlSource);

		@SuppressWarnings("unchecked")
		List<SqlNode> sqlNodes = (List<SqlNode>) metaObject.getValue(CONST_DYNAMIC_CONTENTS);

		return sqlNodes;
	}

	public static String getSqlText(TextSqlNode node) {
		MetaObject metaObject = MetaUtils.forObject(node);
		return (String) metaObject.getValue(CONST_TEXT_SQL);
	}

	@SuppressWarnings("unchecked")
	public static List<ParameterMapping> getParameterMappings(SqlSource sqlSource) {
		MetaObject metaObject = MetaUtils.forObject(sqlSource);
		if (sqlSource instanceof RawSqlSource) {
			return getParameterMappings((SqlSource) metaObject.getValue(CONST_RAW_SQLSOURCE));
		}

		if (sqlSource instanceof StaticSqlSource) {
			return (List<ParameterMapping>) metaObject.getValue(CONST_STATIC_MAPPINGS);
		}
		return null;
	}

	public static SqlSource makeSqlSource(MappedStatement ms, String sql, Class<?> patampterClass) {
		SqlSource sqlSource = ms.getSqlSource();
		if (sqlSource instanceof DynamicSqlSource) {
			List<SqlNode> newNodes = new ArrayList<SqlNode>();
			newNodes.add(new TextSqlNode(sql));
			SqlNode sqlNode = new MixedSqlNode(newNodes);
			return new DynamicSqlSource(ms.getConfiguration(), sqlNode);
		}

		if (sqlSource instanceof RawSqlSource) {
			SqlSource newSqlSource = new RawSqlSource(ms.getConfiguration(), sql, patampterClass);
			List<ParameterMapping> oldParameterMappings = getParameterMappings(sqlSource);
			List<ParameterMapping> newParameterMappings = getParameterMappings(newSqlSource);
			newParameterMappings.addAll(oldParameterMappings);
			return newSqlSource;
		}

		return null;
	}

	/**
	 * 获取非动态SQL语句
	 * 
	 * @param sqlSource
	 * @return 静态SQL语句返回原始语句，动态SQL返回空字符串
	 */
	public static String getStaticSql(SqlSource sqlSource) {
		MetaObject metaObject = MetaUtils.forObject(sqlSource);
		if (sqlSource instanceof RawSqlSource) {
			return getStaticSql((SqlSource) metaObject.getValue(CONST_RAW_SQLSOURCE));
		}
		if (sqlSource instanceof StaticSqlSource) {
			return (String) metaObject.getValue(CONST_STATIC_SQL);
		}
		return EMPTY_SQL;
	}

	@SuppressWarnings("unused")
	private static String getStaticSql(List<SqlNode> sqlNodes) {
		StringBuilder sb = new StringBuilder();
		for (SqlNode sqlNode : sqlNodes) {
			if (sqlNode instanceof TextSqlNode || sqlNode instanceof StaticTextSqlNode) {
				MetaObject metaObject = MetaUtils.forObject(sqlNode);
				sb.append(metaObject.getValue(CONST_TEXT_SQL)).append(SQLParser.Const_WhiteSpace);
			} else {
				return EMPTY_SQL;
			}
		}
		return sb.toString();
	}
	
	public static List<SqlNode> mergeSqlNodes(List<SqlNode> sqlNodes) {
		List<SqlNode> sqlNodeList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (SqlNode sqlNode : sqlNodes) {
			if (sqlNode instanceof TextSqlNode || sqlNode instanceof StaticTextSqlNode) {
				MetaObject metaObject = MetaUtils.forObject(sqlNode);
				sb.append(metaObject.getValue(CONST_TEXT_SQL)).append(SQLParser.Const_WhiteSpace);
			} else if(sqlNode instanceof IfSqlNode){
				sqlNodeList.add(new TextSqlNode(sb.toString()));
				sb = new StringBuilder();
				sqlNodeList.add(sqlNode);
			} else if(sqlNode instanceof MixedSqlNode) {
				if(sb.length() != 0){
					sqlNodeList.add(new TextSqlNode(sb.toString()));
					sb = new StringBuilder();
				}
				sqlNodeList = mergeMixedSqlNode(sqlNodeList, (MixedSqlNode) sqlNode);
				if(sqlNodeList == null){
					return null;
				}
			} else {
				return null;
			}
		}
		if(sb.length() != 0){
			sqlNodeList.add(new TextSqlNode(sb.toString()));
			sb = new StringBuilder();
		}
		
		return sqlNodeList;
	}
	
	@SuppressWarnings("unchecked")
	private static List<SqlNode> mergeMixedSqlNode(List<SqlNode> sqlNodes,MixedSqlNode mixed) {
		MetaObject meta = MetaUtils.forObject(mixed);
		List<SqlNode> mixeds = (List<SqlNode>) meta.getValue("contents");
		sqlNodes.addAll(mixeds);
		return mergeSqlNodes(sqlNodes);
	}
	
	/*
	 * 获取可以解析的动态SQL
	 * 不能解析的返回空
	 */
	
	public static SelectStatement getDynamicSql(List<SqlNode> list) {
		StringBuilder sb = new StringBuilder();
		SelectStatement stm = null;
		List<SqlNode> iflist = new ArrayList<SqlNode>();
		String sql = null;
		int fg = 0;
		for (SqlNode sqlNode : list) {
			if (sqlNode instanceof TextSqlNode || sqlNode instanceof StaticTextSqlNode) {
				MetaObject metaObject = MetaUtils.forObject(sqlNode);
				String current = metaObject.getValue(CONST_TEXT_SQL).toString();
				String conditions = "";
				String context = "";
				String o =current.trim().replaceAll(SQLParser.Regex_Lt_Ln, SQLParser.Const_WhiteSpace);
				String currentSql = o.toUpperCase()+" ";
				String cutSql = o+" ";
				if (currentSql.contains(" WHERE ") && !currentSql.contains("ORDER BY ")) {
					if(!sb.toString().toUpperCase().contains(" WHERE ")){
					int index_where = currentSql.indexOf(" WHERE "); 
					conditions = cutSql.substring(index_where + 7, currentSql.length());
					}
				}
				if (currentSql.contains("ORDER BY ") && !currentSql.contains(" WHERE ")) {
					sb.append(cutSql).append(SQLParser.Const_WhiteSpace);
				}
				if (fg != 0 && (!currentSql.contains("ORDER BY ")) && !currentSql.contains(" WHERE ")) {
					iflist.add(sqlNode);
				}
				if (!conditions.equals("")) {
					SqlNode sql1 = new TextSqlNode(conditions);
					iflist.add(sql1);
				}
				context = cutSql.replace(conditions, "");
				if(!context.equals("")&&!sb.toString().toUpperCase().contains(" WHERE ")){
					sb.append(context).append(SQLParser.Const_WhiteSpace);
				}
				
			}
			fg += 1;
			if (sqlNode instanceof IfSqlNode) {
				if (sb.toString().toUpperCase().contains( "WHERE ")) {
					if (!sb.toString().contains("ORDER BY ")) {
						iflist.add(sqlNode);
					}
				} else {
					return null;
				}
			} 
		}
		sql = sb.toString();
		stm = SQLParser.parser(sql, iflist);
		return stm;
	}

	/**
	 * 获取if标签的表达式
	 * 
	 * @param node
	 * @return
	 */
	public static String getTestText(IfSqlNode node) {
		MetaObject metaObject = MetaUtils.forObject(node);
		return (String) metaObject.getValue(CONST_TEST_EXPR);
	}

	/**
	 * 生成MappedStatement
	 * 
	 * @param config
	 * @param sqlId
	 * @param sqlSource
	 * @param type
	 * @param parameterType
	 * @param resultType
	 * @return
	 */
	public static MappedStatement buildMappedStatement(Configuration config, String sqlId, SqlSource sqlSource,
			SqlCommandType type, Class<?> parameterType, Class<?> resultType) {
		MappedStatement.Builder builder = new MappedStatement.Builder(config, sqlId, sqlSource, type);

		String mapId = sqlId + CONST_INLINE_SUFFIX;

		// 构造ParameterMap
		ParameterMap.Builder pmBuilder = new ParameterMap.Builder(config, mapId, parameterType,
				new ArrayList<ParameterMapping>());
		builder.parameterMap(pmBuilder.build());

		// 构造resultMap
		ResultMap.Builder rmBuilder = new ResultMap.Builder(config, mapId, resultType, new ArrayList<ResultMapping>());
		List<ResultMap> rm = new ArrayList<>();
		rm.add(rmBuilder.build());
		builder.resultMaps(rm);

		return builder.build();
	}

	/**
	 * 以已有MappedStatement模版生成新的Statement
	 * 
	 * @param ms
	 *            模版MappedStatement
	 * @param sqlId
	 * @param sqlSource
	 * @param type
	 * @param parameterType
	 * @param resultType
	 * @return
	 */
	public static MappedStatement buildMappedStatement(MappedStatement ms, String sqlId, SqlSource sqlSource,
			Class<?> resultType) {
		Configuration config = ms.getConfiguration();

		SqlCommandType type = ms.getSqlCommandType();
		Class<?> parameterType = ms.getParameterMap().getType();

		return buildMappedStatement(config, sqlId, sqlSource, type, parameterType, resultType);
	}

	/**
	 * 以已有MappedStatement模版生成新的Statement
	 * 
	 * @param ms
	 *            模版MappedStatement
	 * @param sqlId
	 * @param sql
	 * @param type
	 * @param parameterType
	 * @param resultType
	 * @return
	 */
	public static MappedStatement buildMappedStatement(MappedStatement ms, String sqlId, String sql,
			Class<?> resultType) {
		SqlSource sqlSource = makeSqlSource(ms, sql, ms.getParameterMap().getType());
		return buildMappedStatement(ms, sqlId, sqlSource, resultType);
	}
	
	/**
	 * 向Configuration中添加MappedStatement
	 * 
	 * @param ms
	 */
	public static void addMappedStatement(MappedStatement ms) {
		Configuration configuration = ms.getConfiguration();
		synchronized (configuration) {
			configuration.addMappedStatement(ms);
		}
	}
}
