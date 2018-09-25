package com.dcits.galaxy.dal.mybatis.table.router.config;

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
import com.dcits.galaxy.dal.mybatis.router.simple.expr.Expression;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.MVELExpression;
import com.dcits.galaxy.dal.mybatis.table.router.config.vo.InternalRule;
import com.dcits.galaxy.dal.mybatis.table.router.config.vo.InternalRules;
import com.dcits.galaxy.dal.mybatis.table.router.rule.TableRoute;
import com.thoughtworks.xstream.XStream;

/**
 * 从XML文件中读取配置的默认实现
 * @author huang.zhe
 */
public class SimpleTableRouterXmlFactoryBean extends AbstractSimpleTableRouterConfigurationFactoryBean {

	@Override
	protected Set<TableRoute> loadRulesFromExternal(Resource configLocation) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("rules", InternalRules.class);
		xstream.alias("rule", InternalRule.class);
		xstream.alias("sqlmap", String.class);
		xstream.addImplicitCollection(InternalRules.class, "rules");
		InternalRules internalRules;
		try{
			internalRules = (InternalRules) xstream.fromXML(configLocation.getInputStream());
	
		} catch(RuntimeException e) {
			
			StringBuilder info = new StringBuilder("\nParse ");
			info.append(configLocation.getDescription());
			info.append(" has error. Error occurred near the ");
			
			String str = e.getMessage();
			int start = str.indexOf("line number");
			int end = str.indexOf("\n", start);
			str = str.substring(start, end);
		
			info.append(str.replaceAll("  +", " "));
			throw new ParseFileException(info.toString());
		}
		
		List<InternalRule> rules = internalRules.getRules();
		if (CollectionUtils.isEmpty(rules)) {
			return null;
		}
		Set<TableRoute> tableRoutes = new HashSet<TableRoute>();
		for (InternalRule rule : rules) {
			List<String> sqlmaps = rule.getSqlmaps();
			String namespace = StringUtils.trimToEmpty(rule.getNamespace());
			String sqlAction = StringUtils.trimToEmpty(rule.getSqlmap());
			String shardingExpression = StringUtils.trimToEmpty(rule.getShardingExpression());
			String destinations = StringUtils.trimToEmpty(rule.getTableShards());

			Validate.notEmpty(destinations, "destination tables must be given explicitly.");

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
			String[] tablenames = destinations.split(",");
			Set<String> tableNameSet = new HashSet<String>();
			for(String tablename:tablenames){
				tableNameSet.add(StringUtils.trimToEmpty(tablename));
			}
			
			if (StringUtils.isNotEmpty(shardingExpression))
				expression = new MVELExpression(shardingExpression, getFunctionsMap());
			for(String sqlmap : sqlmaps){
				sqlmap = StringUtils.trimToEmpty(sqlmap);
				tableRoutes.add(new TableRoute(sqlmap, expression, tableNameSet));
			}
		}
		return tableRoutes;
	}

}
