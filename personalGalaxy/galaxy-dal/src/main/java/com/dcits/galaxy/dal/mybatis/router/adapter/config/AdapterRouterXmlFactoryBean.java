package com.dcits.galaxy.dal.mybatis.router.adapter.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;
import com.dcits.galaxy.dal.mybatis.router.adapter.config.vo.InternalRule;
import com.dcits.galaxy.dal.mybatis.router.adapter.config.vo.InternalRules;
import com.dcits.galaxy.dal.mybatis.router.adapter.config.vo.InternalRulesSetting;
import com.dcits.galaxy.dal.mybatis.router.index.expr.IndexExpression;
import com.dcits.galaxy.dal.mybatis.router.index.expr.IndexMVELExpression;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRoute;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.Expression;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.MVELExpression;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.Route;
import com.thoughtworks.xstream.XStream;

/**
 * 从XML文件中读取配置的默认实现
 * @author huang.zhe
 */
public class AdapterRouterXmlFactoryBean extends AbstractAdapterRouterConfigurationFactoryBean {

	@Override
	protected Map<InternalRules.RulesType, Object> loadRulesFromExternal(Resource configLocation) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("rules-setting", InternalRulesSetting.class);
		xstream.alias("rules", InternalRules.class);
		xstream.alias("rule", InternalRule.class);
		xstream.alias("sqlmap", String.class);
		xstream.alias("field", String.class);
		xstream.alias("type", InternalRules.RulesType.class);

		xstream.addImplicitCollection(InternalRulesSetting.class, "rulesList");
		xstream.addImplicitCollection(InternalRules.class, "rules");
		xstream.addImplicitCollection(InternalRule.class, "field");
		xstream.useAttributeFor(InternalRules.class, "expression");
		xstream.useAttributeFor(InternalRules.class, "shards");
		xstream.useAttributeFor(InternalRules.class, "type");

		InternalRulesSetting internalRulesSetting;
		try {
			internalRulesSetting = (InternalRulesSetting) xstream.fromXML(configLocation.getInputStream());
		} catch (RuntimeException e) {
			try {
				StringBuilder info = new StringBuilder("\nParse ");
				info.append(configLocation.getDescription());
				info.append(" has error. Error occurred near the ");
				
				String str = e.getMessage();
				int start = str.indexOf("line number");
				int end = str.indexOf("\n", start);
				str = str.substring(start, end);
				
				info.append(str.replaceAll("  +", " "));
				throw new ParseFileException(info.toString());
			} catch (Exception e2) {
				throw new ParseFileException(configLocation, e);
			}
		}

		Map<InternalRules.RulesType, Object> routeMap = new HashMap<InternalRules.RulesType, Object>();

		Set<Route> routeSet = new HashSet<Route>();
		Set<IndexRoute> fieldRouteSet = new HashSet<IndexRoute>();
		for (InternalRules internalRules : internalRulesSetting.getRulesSetting()) {
			if (internalRules.getType() == InternalRules.RulesType.SIMPLE_ROUTER || internalRules.getType() == null) {
				routeSet.addAll(getDefaultRoutes(internalRules));
			} else if (internalRules.getType() == InternalRules.RulesType.INDEX_ROUTER) {
				fieldRouteSet.addAll(getIndexRoutes(internalRules));
			}
		}
		routeMap.put(InternalRules.RulesType.SIMPLE_ROUTER, routeSet);
		routeMap.put(InternalRules.RulesType.INDEX_ROUTER, fieldRouteSet);

		return routeMap;
	}

	private Set<Route> getDefaultRoutes(InternalRules internalRules) {
		List<InternalRule> rules = internalRules.getRules();
		if (CollectionUtils.isEmpty(rules)) {
			return null;
		}
		Set<Route> routes = new HashSet<Route>();
		for (InternalRule rule : rules) {
			List<String> sqlmaps = rule.getSqlmaps();
			String namespace = StringUtils.trimToEmpty(rule.getNamespace());
			String sqlAction = StringUtils.trimToEmpty(rule.getSqlmap());
			String shardingExpression = StringUtils.trimToEmpty(rule.getShardingExpression());
			String destinations = StringUtils.trimToEmpty(rule.getShards());

			Validate.notEmpty(destinations, "destination shards must be given explicitly.");

			if (StringUtils.isEmpty(namespace) && StringUtils.isEmpty(sqlAction) && CollectionUtils.isEmpty(sqlmaps)) {
				throw new IllegalArgumentException("at least one of 'namespace' , 'sqlmap' or 'sqlmaps' must be given.");
			}

			if (sqlmaps == null)
				sqlmaps = new ArrayList<String>();
			if (StringUtils.isNotEmpty(namespace))
				sqlmaps.add(namespace);
			if (StringUtils.isNotEmpty(sqlAction))
				sqlmaps.add(sqlAction);

			Expression expression = null;
			String[] shardids = destinations.split(",");
			Set<String> shardIdSet = new HashSet<String>();
			for (String shardid : shardids) {
				shardIdSet.add(StringUtils.trimToEmpty(shardid));
			}

			if (StringUtils.isNotEmpty(shardingExpression))
				expression = new MVELExpression(shardingExpression, getFunctionsMap());
			for (String sqlmap : sqlmaps) {
				sqlmap = StringUtils.trimToEmpty(sqlmap);
				routes.add(new Route(sqlmap, expression, shardIdSet));
			}
		}
		return routes;
	}

	private Set<IndexRoute> getIndexRoutes(InternalRules internalRules) {
		String defaultExp = StringUtils.trimToEmpty(internalRules.getExpression());

		String ds = internalRules.getShards();
		List<String> defaultShardList = null;
		if (StringUtils.isNotEmpty(ds)) {
			ds = StringUtils.trimToEmpty(ds);
			String[] defaultShardids = ds.split(",");
			defaultShardList = new ArrayList<String>();
			for (String id : defaultShardids) {
				defaultShardList.add(StringUtils.trimToEmpty(id));
			}
		}

		List<InternalRule> rules = internalRules.getRules();
		if (CollectionUtils.isEmpty(rules)) {
			return null;
		}
		Set<IndexRoute> routes = new HashSet<IndexRoute>();
		for (InternalRule rule : rules) {
			List<String> sqlmaps = rule.getSqlmaps();
			String namespace = StringUtils.trimToEmpty(rule.getNamespace());
			String sqlAction = StringUtils.trimToEmpty(rule.getSqlmap());
			String shardingExpression = StringUtils.trimToEmpty(rule.getShardingExpression());
			List<String> fields = rule.getField();
			String destinations = rule.getShards();

			if (StringUtils.isEmpty(namespace) && StringUtils.isEmpty(sqlAction) && CollectionUtils.isEmpty(sqlmaps)) {
				throw new IllegalArgumentException("at least one of 'namespace' , 'sqlmap' or 'sqlmaps' must be given.");
			}

			if (sqlmaps == null)
				sqlmaps = new ArrayList<String>();
			if (StringUtils.isNotEmpty(namespace))
				sqlmaps.add(namespace);
			if (StringUtils.isNotEmpty(sqlAction))
				sqlmaps.add(sqlAction);

			List<IndexExpression> expressions = null;
			if (fields != null) {
				String se = null;
				if (StringUtils.isNotEmpty(shardingExpression)) {
					se = shardingExpression;
				} else if (StringUtils.isNotEmpty(defaultExp)) {
					se = defaultExp;
				} else {
					throw new IllegalArgumentException("at least one of 'Expression' , 'shardingExpression'  must be given.");
				}
				expressions = new ArrayList<IndexExpression>();
				for (String f : fields) {
					String[] fs = f.split(",");
					String sex = se;
					for (String s : fs) {
						sex = sex.replaceFirst("#", StringUtils.trimToEmpty(s));
					}
					IndexExpression expression = new IndexMVELExpression(sex, getFunctionsMap());
					expressions.add(expression);
				}
			}

			List<String> shardList = null;
			if (destinations != null) {
				String shards = StringUtils.trimToEmpty(rule.getShards());
				String[] Shardids = shards.split(",");
				shardList = new ArrayList<String>();
				for (String id : Shardids) {
					shardList.add(StringUtils.trimToEmpty(id));
				}
			} else if (defaultShardList != null) {
				shardList = defaultShardList;
			} else {
				throw new IllegalArgumentException("shards must be given.");
			}

			if (expressions != null) {
				for (IndexExpression e : expressions) {
					for (String sqlmap : sqlmaps) {
						sqlmap = StringUtils.trimToEmpty(sqlmap);
						routes.add(new IndexRoute(sqlmap, e, shardList));
					}
				}
			} else {
				for (String sqlmap : sqlmaps) {
					sqlmap = StringUtils.trimToEmpty(sqlmap);
					routes.add(new IndexRoute(sqlmap, null, shardList));
				}
			}
		}
		return routes;
	}

}
