package com.dcits.orion.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dcits.galaxy.dal.mybatis.extension.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.Configuration;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.merger.MergeExecutor;
import com.dcits.galaxy.dal.mybatis.merger.config.MergeRulesFactoryBean;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergerSetting;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.utils.MappedStatementUtils;

public class PageHandler implements StatementHandler {

//	private static final Logger logger = LoggerFactory.getLogger(PageHandler.class);
	private static final TextSqlNode start = new TextSqlNode("select count(*) as count from (");
	private static final TextSqlNode end = new TextSqlNode(") temp");
	
	private static final String startSql = "select count(*) as count from (";
	private static final String endSql = ") temp";
	
	public static final String Count_Suffix = "$count";
	
	@Override
	public void handle(ShardSqlSessionTemplate sqlSessionTemplate, MappedStatement ms) {
		Configuration config = ms.getConfiguration();
		
		if(isOffsetSql(ms.getId())) {
			return;
		}
		
		String sqlId = ms.getId() + Count_Suffix;
		
		if (config.hasStatement(sqlId)) {
			return;
		}
		
		SqlSource newSqlSource = makeSqlSource(ms, config);
		
		if(newSqlSource == null) {
			return;
		}
		
		MappedStatement countStatement = MappedStatementUtils.buildMappedStatement(config, sqlId, newSqlSource, 
				SqlCommandType.SELECT, Map.class, Integer.class);
				
		MappedStatementUtils.addMappedStatement(countStatement);
		
		MergeExecutor mergeExecutor = sqlSessionTemplate.getMergeExecutor();
		if(mergeExecutor != null) {
			MergeRule mergeRule = buildMergeRule(sqlId);
			mergeExecutor.addMergeRule(mergeRule);
		}
		
		IRouter router = sqlSessionTemplate.getRouter();
		if(router != null) {
			try {
				router.alias(ms.getId(), sqlId);
			} catch (DALException e) {
				// no-do
			}
		}
	}
	
	private boolean isOffsetSql(String sqlid){
		return sqlid.endsWith(MergeRulesFactoryBean.Offset_Suffix);
	}
	
	private SqlSource makeSqlSource(MappedStatement ms, Configuration config) {
		SqlSource sqlSource = ms.getSqlSource();
		if(sqlSource instanceof DynamicSqlSource ) {
			List<SqlNode> oldNodes =  MappedStatementUtils.getNodes(sqlSource);
			List<SqlNode> newNodes = new ArrayList<SqlNode>();
			newNodes.add(start);
			newNodes.addAll(oldNodes);
			newNodes.add(end);
			
			SqlNode sqlNode = new MixedSqlNode(newNodes);
			
			return new DynamicSqlSource(config, sqlNode);
		} 

		if(sqlSource instanceof RawSqlSource) {
			StringBuilder sb = new StringBuilder();
			sb.append(startSql);
			sb.append(MappedStatementUtils.getStaticSql(sqlSource));
			sb.append(endSql);
			return MappedStatementUtils.makeSqlSource(ms, sb.toString(), ParameterWrapper.class);
		}
		
		return null;
	}
	
	protected MergeRule buildMergeRule(String sqlId) {
		List<RuleColumn> cloumns = new ArrayList<RuleColumn>();
		cloumns.add(new RuleColumn("count(*)", "count", "count"));
		
		MergerSetting mergerSetting = new MergerSetting();
		mergerSetting.setBeanId("aggregateMerger");
		mergerSetting.setColumns(cloumns);
		
		List<MergerSetting> mergers = new ArrayList<MergerSetting>();
		mergers.add(mergerSetting);
		
		MergeRule mergeRule = new MergeRule();
		mergeRule.setSqlMap(sqlId);
		mergeRule.setMergers(mergers);
		return mergeRule;
	}
}
