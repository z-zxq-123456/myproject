package com.dcits.galaxy.dal.mybatis.merger.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRules;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergerSetting;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;
import com.dcits.galaxy.dal.mybatis.parser.SQLParser;
import com.dcits.galaxy.dal.mybatis.parser.SelectColumnParser;
import com.dcits.galaxy.dal.mybatis.parser.SelectStatement;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.proactor.page.approximate.ApproximateUtil;
import com.dcits.galaxy.dal.mybatis.utils.MappedStatementUtils;
import com.thoughtworks.xstream.XStream;

public class MergeRulesFactoryBean implements InitializingBean {

	private Resource[] locations;

	private Map<String, MergeRule> rulesMap = new ConcurrentHashMap<>();

	private static final MergeRule NULL_MERGERULE = new MergeRule();
	public static final MergeRule DYNAMIC_SQL_MERGERULE = new MergeRule();

	public static final String Offset_Suffix = "$offset";

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	public MergeRule getMergeRule(String sqlMap) {
		return rulesMap.get(sqlMap);
	}

	public boolean hasMergeRule(String sqlMap) {
		return rulesMap.containsKey(sqlMap);
	}

	public MergeRule getMergeRule(String sqlMap, Object parameter, SqlSessionFactory sqlSessionFactory)
			throws Exception {

		MergeRule mergeRule = rulesMap.get(sqlMap);
		if (mergeRule != null) {
			return mergeRule;
		}

		Configuration configuration = sqlSessionFactory.getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(sqlMap);
		ResultMap resultMap = ms.getResultMaps().get(0);
		boolean mapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();
		SelectStatement stmt = SQLParser.parser(ms.getSqlSource());
		if (stmt == null) {
			addMergeRule(sqlMap, DYNAMIC_SQL_MERGERULE);
			throw new Exception("无法根据SQL生成MergeRule, 需手动配置规则MergeRule");
		}
		SelectColumnParser.parser(stmt, resultMap);

		List<RuleColumn> aggregateColumns = SQLParser.getAggregateColumns(stmt);
		if (aggregateColumns.size() > 0) {
			mergeRule = createMergeRule(sqlMap, "aggregateMerger", aggregateColumns);
		} else {
			List<RuleColumn> orderByColumns = SQLParser.getOrderByColumns(stmt, resultMap, mapUnderscoreToCamelCase);
			if (orderByColumns.size() > 0) {
				if (parameter instanceof ParameterWrapper) {
					mergeRule = createPageRule(sqlMap, configuration, mapUnderscoreToCamelCase, orderByColumns, stmt);
				} else {
					mergeRule = createMergeRule(sqlMap, "orderByMerger", orderByColumns);
				}
			} else {
				mergeRule = NULL_MERGERULE;
			}
		}
		addMergeRule(sqlMap, mergeRule);
		return mergeRule;
	}

	private MergeRule createMergeRule(String sqlMap, String beanId, List<RuleColumn> columns) {
		MergeRule mergeRule = new MergeRule();
		mergeRule.setSqlMap(sqlMap);
		MergerSetting mergerSetting = new MergerSetting();
		mergerSetting.setBeanId(beanId);
		mergerSetting.setColumns(columns);
		List<MergerSetting> mergers = new ArrayList<MergerSetting>();
		mergers.add(mergerSetting);
		mergeRule.setMergers(mergers);
		return mergeRule;
	}

	private MergeRule createPageRule(String sqlMap, Configuration configuration, boolean mapUnderscoreToCamelCase,
			List<RuleColumn> columns, SelectStatement stmt) {
		String offsetId = sqlMap + Offset_Suffix;
		MergeRule mergeRule = new MergeRule();
		mergeRule.setSqlMap(sqlMap);

		if (!configuration.hasStatement(offsetId)) {
			SqlSource sqlSource = ApproximateUtil.getOffsetSql(stmt, columns, configuration);
			MappedStatement offsetMappedStatement = null;
			if(sqlSource != null){
				offsetMappedStatement = MappedStatementUtils.buildMappedStatement(configuration, offsetId, sqlSource, SqlCommandType.SELECT, ParameterWrapper.class, HashMap.class);
			}
			if (offsetMappedStatement != null) {
				MappedStatementUtils.addMappedStatement(offsetMappedStatement);
			}
		}

		ProactorSetting proactorSetting = new ProactorSetting();
		proactorSetting.setBeanId("pageProactor");
		proactorSetting.setColumns(columns);

		Properties props = new Properties();
		props.setProperty(ApproximateUtil.Const_offset_column_Key, ApproximateUtil.Const_Offset_Column_Alias);
		props.setProperty(ApproximateUtil.Const_offset_statement_Key, offsetId);
		proactorSetting.setProperties(props);

		mergeRule.setProactorSetting(proactorSetting);

		return mergeRule;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadMergeRules();
	}

	protected void loadMergeRules() throws Exception {

		XStream xstream = initXStream();

		if (getLocations() != null && getLocations().length > 0) {
			for (Resource resource : getLocations()) {
				MergeRules mergeRules = (MergeRules) xstream.fromXML(resource.getInputStream());
				List<MergeRule> rules = mergeRules.getMergeRules();
				if (!CollectionUtils.isEmpty(rules) && rules.size() > 0) {
					for (MergeRule rule : rules) {
						rulesMap.put(rule.getSqlMap(), rule);
					}
				}
			}
		}
	}

	public void addMergeRule(MergeRule rule) {
		addMergeRule(rule.getSqlMap(), rule);
	}

	public void addMergeRule(String sqlMap, MergeRule rule) {
		rulesMap.put(sqlMap, rule);
	}

	private XStream initXStream() {
		XStream xstream = new XStream();
		xstream.alias("merge-rules", MergeRules.class);
		xstream.addImplicitCollection(MergeRules.class, "mergeRules");

		xstream.alias("merge-rule", MergeRule.class);
		xstream.addImplicitCollection(MergeRule.class, "mergers");
		xstream.aliasField("proactor", MergeRule.class, "proactorSetting");

		xstream.aliasField("bean", RuleSetting.class, "beanId");
		xstream.useAttributeFor(RuleSetting.class, "beanId");

		xstream.alias("column", RuleColumn.class);
		xstream.useAttributeFor(RuleColumn.class, "name");
		xstream.useAttributeFor(RuleColumn.class, "alias");
		xstream.useAttributeFor(RuleColumn.class, "aggregate");
		xstream.useAttributeFor(RuleColumn.class, "desc");

		xstream.alias("proactor", ProactorSetting.class);

		xstream.alias("merger", MergerSetting.class);

		return xstream;
	}

}
