package com.dcits.galaxy.dal.mybatis.router.index.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;
import com.dcits.galaxy.dal.mybatis.router.index.config.vo.InternalRule;
import com.dcits.galaxy.dal.mybatis.router.index.config.vo.InternalRules;
import com.dcits.galaxy.dal.mybatis.router.index.expr.IndexExpression;
import com.dcits.galaxy.dal.mybatis.router.index.expr.IndexMVELExpression;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRoute;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 从XML文件中读取配置的默认实现
 * @author huang.zhe
 *
 */
public class IndexRouterXmlFactoryBean extends AbstractIndexRouterConfigurationFactoryBean {

	@Override
	protected Set<IndexRoute> loadRulesFromExternal(Resource configLocation) throws IOException {
		//XXX
		XStream xstream = new XStream(new DomDriver());
//		XStream xstream = new XStream(new StaxDriver());
//		XStream xstream = new XStream();
		xstream.alias("rules", InternalRules.class);
		xstream.alias("rule", InternalRule.class);
		xstream.alias("sqlmap", String.class);
		xstream.alias("field", String.class);
		xstream.addImplicitCollection(InternalRules.class, "rules");
		xstream.addImplicitCollection(InternalRule.class, "field");
		xstream.useAttributeFor(InternalRules.class, "expression");
		xstream.useAttributeFor(InternalRules.class, "shards");

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
		
		String defaultExpression=StringUtils.trimToEmpty(internalRules.getExpression());
		
		String ds=internalRules.getShards();
		List<String> defaultShardList=null;
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
			List<String> fields= rule.getField();
			String destinations=rule.getShards();
			
			if (StringUtils.isEmpty(namespace) && StringUtils.isEmpty(sqlAction) && CollectionUtils.isEmpty(sqlmaps)) {
				throw new IllegalArgumentException("at least one of 'namespace' , 'sqlmap' or 'sqlmaps' must be given.");
			}
			
			if(sqlmaps == null)
				sqlmaps = new ArrayList<String>();
			if(StringUtils.isNotEmpty(namespace))
				sqlmaps.add(namespace);
			if(StringUtils.isNotEmpty(sqlAction))
				sqlmaps.add(sqlAction);
			
			List <IndexExpression> expressions=null;
			if(fields!=null){
				String se = null;
				if (StringUtils.isNotEmpty(shardingExpression)) {
					se = shardingExpression;
				}else if(StringUtils.isNotEmpty(defaultExpression)){
					se=defaultExpression;
				}else{
					throw new IllegalArgumentException("at least one of 'Expression' , 'shardingExpression'  must be given.");
				}
				expressions= new ArrayList<IndexExpression>();
				for (String f : fields) {
					String[] fs = f.split(",");
					String sex=se;
					for (String s : fs) {
						sex = sex.replaceFirst("#", StringUtils.trimToEmpty(s));
					}
					IndexExpression expression = new IndexMVELExpression(sex, getFunctionsMap());
					expressions.add(expression);
				}
			}
			
			List<String> shardList=null;
			if (destinations != null) {
				String shards = StringUtils.trimToEmpty(rule.getShards());
				String[] Shardids = shards.split(",");
				shardList = new ArrayList<String>();
				for (String id : Shardids) {
					shardList.add(StringUtils.trimToEmpty(id));
				}
			}else if(defaultShardList !=null){
				shardList =defaultShardList;
			}else{
				throw new IllegalArgumentException("shards must be given.");
			}

			if (expressions != null) {
				for (IndexExpression e : expressions) {
					for (String sqlmap : sqlmaps) {
						sqlmap = StringUtils.trimToEmpty(sqlmap);
						routes.add(new IndexRoute(sqlmap, e, shardList));
					}
				}
			}else{
				for (String sqlmap : sqlmaps) {
					sqlmap = StringUtils.trimToEmpty(sqlmap);
					routes.add(new IndexRoute(sqlmap, null, shardList));
				}
			}
		}
		return routes;
	}
	
}
