package com.dcits.galaxy.dal.mybatis.router.simple.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;
import com.dcits.galaxy.dal.mybatis.router.simple.config.vo.InternalRule;
import com.dcits.galaxy.dal.mybatis.router.simple.config.vo.InternalRules;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.Expression;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.MVELExpression;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.Route;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 从XML文件中读取配置的默认实现
 * @author fan.kaijie
 *
 */
public class SimpleRouterXmlFactoryBean extends AbstractSimpleRouterConfigurationFactoryBean {

	@Override
	protected Set<Route> loadRulesFromExternal(Resource configLocation) throws IOException {
		//XXX
		XStream xstream = new XStream(new DomDriver());
//		XStream xstream = new XStream(new StaxDriver());
//		XStream xstream = new XStream();
		xstream.alias("rules", InternalRules.class);
		xstream.alias("rule", InternalRule.class);
		xstream.alias("sqlmap", String.class);
		xstream.addImplicitCollection(InternalRules.class, "rules");

		InternalRules internalRules;
		try{
			internalRules = (InternalRules) xstream.fromXML(configLocation.getInputStream());
		} catch(RuntimeException e) {
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
			
			if(sqlmaps == null)
				sqlmaps = new ArrayList<String>();
			if(StringUtils.isNotEmpty(namespace))
				sqlmaps.add(namespace);
			if(StringUtils.isNotEmpty(sqlAction))
				sqlmaps.add(sqlAction);

			Expression expression = null;
			String[] shardids = destinations.split(",");
			Set<String> shardIdSet = new HashSet<String>();
			for(String shardid:shardids){
				shardIdSet.add(StringUtils.trimToEmpty(shardid));
			}
			
			if (StringUtils.isNotEmpty(shardingExpression))
				expression = new MVELExpression(shardingExpression, getFunctionsMap());
			for(String sqlmap : sqlmaps){
				sqlmap = StringUtils.trimToEmpty(sqlmap);
				routes.add(new Route(sqlmap, expression, shardIdSet));
			}
		}
		return routes;
	}

}
