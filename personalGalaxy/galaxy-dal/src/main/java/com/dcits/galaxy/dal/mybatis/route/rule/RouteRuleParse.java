package com.dcits.galaxy.dal.mybatis.route.rule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;
import com.dcits.galaxy.dal.mybatis.route.RouteException;
import com.dcits.galaxy.dal.mybatis.route.rule.exp.Expression;
import com.dcits.galaxy.dal.mybatis.route.rule.exp.FormalParam;
import com.dcits.galaxy.dal.mybatis.route.rule.exp.MvelExpression;

/**
 * 从XML文件中读取配置的默认实现
 * 
 * @author chen.hong
 *
 */
public class RouteRuleParse {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RouteRuleParse.class);

	public static final String RULE = "rule";
	
	public static final String EXPRESSION = "expression";
	
	public static final String FORMALPARAMS = "formalParams";
	
	public static final String FORMALPARAM = "formalParam";
	
	public static final String RESULTOPTIONS = "resultOptions";
	
	public static final String ITEM = "item";

	public static final String NAMESPACE = "namespace";

	public static final String SQLMAP = "sqlmap";
	
	public static final String ACTUALPARAM = "actualParam";
	
	public static final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
	
	public static Set<RouteRule> loadRules(Resource configLocation,Map<String,Object> functionMap){
		
		InputStream in = null;
		XmlBuilder xmlBuilder;
		Set<RouteRule> routes = new HashSet<RouteRule>();
		Map<String,Expression> expressionMap = new HashMap<String,Expression>();
		Map<String,RouteResult> RouteResultMap = new HashMap<String,RouteResult>();
		try {
			in = configLocation.getInputStream();
			xmlBuilder = new XmlBuilder(in);
			Document doc = xmlBuilder.getDoc();
			//解析表达式和结果集
			NodeList expressions = doc.getElementsByTagName(EXPRESSION);
			for(int i=0;i<expressions.getLength();i++){
				Map<String,FormalParam> defaultParams = null;
				Element expressionEle = (Element) expressions.item(i);
				String expId = expressionEle.getAttribute("id");
				//判断表达式是否已经存在
				if(expressionMap.containsKey(expId)){
					logger.error("at Most one of 'Expression'  must be given:"+expId);
					throw new RouteException("at Most one of 'Expression'  must be given:"+expId);
				}
				//判断结果集是否已经存在
				if(RouteResultMap.containsKey(expId)){
					logger.error("at Most one of 'RouteResult'  must be given:"+expId);
					throw new RouteException("at Most one of 'RouteResult'  must be given:"+expId);
				}
				//解析表达式
				if(expressionEle.hasAttribute("exp")){//有表达式
					String expStr = expressionEle.getAttribute("exp");
					defaultParams = parseExpression(expressionEle);
					expressionMap.put(expId, new MvelExpression(expStr,defaultParams,functionMap));
				}
				//解析结果集
				NodeList shards = expressionEle.getElementsByTagName(RESULTOPTIONS);
				if(shards.getLength()<1){
					logger.error("at least one of 'RouteResult'  must be given:"+expId);
					throw new RouteException("at least one of 'RouteResult'  must be given:"+expId);
				}else if(shards.getLength()>1){
					logger.error("at Most one of 'RouteResult'  must be given:"+expId);
					throw new RouteException("at Most one of 'RouteResult'  must be given:"+expId);
				}
				Element shardEle = (Element) shards.item(0);
				RouteResult routeResult = parseShardResult(shardEle);
				RouteResultMap.put(expId, routeResult);
			}
			//解析路由规则
			NodeList rules = doc.getElementsByTagName(RULE);
			for(int i=0;i<rules.getLength();i++){
				List<RouteBasis> namespaces = null;
				List<RouteBasis> sqlmaps = null;
				List<RouteBasis> sqlMapList = new ArrayList<RouteBasis>();
				Expression expression = null;
				RouteResult routeResult = null;
				Element rule = (Element) rules.item(i);
				if(!rule.hasAttribute("expression")){
					logger.error("the attribute expression must be given");
					throw new RouteException("the attribute expression must be given");
				}
				String expId = rule.getAttribute("expression");
				if(expressionMap.containsKey(expId)){
					expression = expressionMap.get(expId);
				}
				if(!RouteResultMap.containsKey(expId)){
					logger.error("at least one of 'RouteResult'  must be given:"+expId);
					throw new RouteException("at least one of 'RouteResult'  must be given:"+expId);
				}else{
					routeResult = RouteResultMap.get(expId);
				}
				NodeList namespaceList = rule.getElementsByTagName(NAMESPACE);
				NodeList sqlmapList = rule.getElementsByTagName(SQLMAP);
				//解析namespace
				if (namespaceList.getLength()>0) {
					namespaces = parseNamespacesOrSqlmaps(namespaceList);
				}
				//解析sqlmap
				if (sqlmapList.getLength()>0) {
					sqlmaps = parseNamespacesOrSqlmaps(sqlmapList);
				}
				
				if(null!=namespaces&&namespaces.size()>0){
					sqlMapList.addAll(namespaces);
				}
				if(null!=sqlmaps&&sqlmaps.size()>0){
					sqlMapList.addAll(sqlmaps);
				}
				routes.add(new RouteRule(expression,routeResult,sqlMapList));
			}
			}catch (Exception e) {
				logger.error("Parse routeRule xml file error!!!"+e.getMessage());
				throw new ParseFileException("Parse routeRule xml file error!!!");
			}finally{
				try {
					if(null!=in){
						in.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		return routes;
	}
	
	
	/**
	 * 解析路由表达式Expression
	 * @param expression 表达式节点
	 * @param functionsMap 路由函数
	 * @return 
	 */
	private static Map<String,FormalParam> parseExpression(Element expression) {
		NodeList parameters = expression.getElementsByTagName(FORMALPARAMS);
		if(parameters.getLength()<1){
			return null;
		}else if(parameters.getLength()>1){
			logger.error("at most one of 'parameters'  must be given.");
			throw new RouteException("at most one of 'parameters'  must be given.");
		}
		Element parameterEle =  (Element) parameters.item(0);
		NodeList expressionpros = parameterEle.getElementsByTagName(FORMALPARAM);
		Map<String,FormalParam> defaultValues = new HashMap<String,FormalParam>();
		for(int i=0;i<expressionpros.getLength();i++){
			Element pro = (Element) expressionpros.item(i);
			String name = pro.getAttribute("name");
			String value = pro.getAttribute("default");
			String type = pro.getAttribute("type");
			Class<?> typeClass = typeAliasRegistry.resolveAlias(type);
			if(null==typeClass){
				logger.error(" The ClassType +"+type +"not allow!");
			}
			Object o = typeAliasRegistry.stringConvertToClassType(value, typeClass);
			defaultValues.put(name, new FormalParam(name,typeClass,o));
		}
		return defaultValues;
	}
	/**
	 * 解析路由结果集shardResult
	 * @param result
	 * @return RouteResult
	 */
	private static RouteResult parseShardResult(Element result){
		String separator = null;
		NodeList shards = result.getElementsByTagName(ITEM);
		if(shards.getLength()<1){
			logger.error("at least one of 'shards'  must be given.");
			throw new RouteException("at least one of 'shards'  must be given.");
		}
		String type = result.getAttribute("type");
		List<String> resultList = null;
		Map<String, Set<String>> resultMap = null;
		if(RouteRule.RULE_RESULT_LIST.equals(type)){
			resultList = parseShardsByList(shards);
			separator = RouteRule.RULE_RESULT_LIST_DEFAULT_SPARATOR;
			if(result.hasAttribute("separator")){
				separator = result.getAttribute("separator");
			}
		}else if(RouteRule.RULE_RESULT_MAP.equals(type)){
			resultMap = parseShardsByMap(shards);
		}else{
			logger.error("shardResult's type must be 'map' or 'list'");
			throw new RouteException("shardResult's type must be 'map' or 'list'");
		}
		return new RouteResult(type,separator,resultList,resultMap);
	}
	/**
	 * 解析类型为 list的结果集Shards
	 * @param list
	 * @return List
	 */
	private static List<String> parseShardsByList(NodeList list){
		List<String> shards = new ArrayList<String>();
		Element el = (Element) list.item(0);
		String value = el.getTextContent().trim();
		String[] shardids = value.split(",");
		for(int i =0 ; i<shardids.length;i++){
			shards.add(shardids[i]);
		}
		return shards;
	}
	
	/**
	 * 解析类型为 Map的结果集Shards
	 * @param list 结果集节点 
	 * @return Map
	 */
	private static Map<String, Set<String>> parseShardsByMap(NodeList list) {
		Map<String, Set<String>> shardMap = new HashMap<String, Set<String>>();
		for(int i=0;i<list.getLength();i++){
			Element shards = (Element) list.item(i);
			String result =  RouteRule.ROUTE_SHARD_DEFAULT_NAME;
			if(shards.hasAttribute("result")){
				result = shards.getAttribute("result");
			}
			String value = shards.getTextContent().trim();
			String[] shardids = value.split(",");
			Set<String> shardIdSet = new HashSet<String>();
			for (String shardid : shardids) {
				shardIdSet.add(StringUtils.trimToEmpty(shardid));
			}
			shardMap.put(result, shardIdSet);
		}
		return shardMap;
	}
	/**
	 * 解析路由路径Namespace或者Sqlmaps
	 * @param list Namespace或者Sqlmaps节点
	 * @return
	 */
	private static List<RouteBasis> parseNamespacesOrSqlmaps(NodeList list) {
		List<RouteBasis> targetList = new ArrayList<RouteBasis>();
		for(int i=0;i<list.getLength();i++){
			Element namespace = (Element) list.item(i);
			String name = namespace.getAttribute("name");
			NodeList namespacepros = namespace.getElementsByTagName(ACTUALPARAM);
			Map<String, String> propertyMap = null;
			if(namespacepros.getLength()>0){
				propertyMap = new HashMap<String, String>();
				for(int j=0;j<namespacepros.getLength();j++){
					Element pro = (Element) namespacepros.item(j);
					String pname = pro.getAttribute("name");
					String alias = pro.getAttribute("formalParam"); 
					propertyMap.put(pname, alias);
				}
			}
			targetList.add(new RouteBasis(name,propertyMap));
		}
		return targetList;
	}
}

class XmlBuilder{

	/**
	 * 
	 * 构造函数说明：
	 * <p>
	 * 
	 * 参数说明：@param path
	 * <p>
	 * 
	 **/
	public XmlBuilder(InputStream inputStream){
		this.inputStream = inputStream;
		init();
	}

	/**
	 * 
	 * 方法名称：init
	 * <p>
	 * 
	 * 方法功能：初始化函数
	 * <p>
	 * 
	 * 参数说明：
	 * <p>
	 * 
	 * 返回：void
	 * <p>
	 * 
	 * 
	 **/

	public void init(){
		buildDocument();
		buildRoot();
	}

	/**
	 * 
	 * 方法名称：buildDocument
	 * <p>
	 * 
	 * 方法功能：将XML文件生成Document
	 * <p>
	 * 
	 * 参数说明：
	 * <p>
	 * 
	 * 返回：void
	 * <p>
	 * 
	 **/

	private void buildDocument(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			logger.debug("Construct document builder success.");
			doc = builder.parse(inputStream);
			logger.debug("Build xml document success.");
		} catch (ParserConfigurationException e){
			logger.error("Construct document builder error:" + e);
		} catch (SAXException e){
			logger.error("Parse xml file error:" + e);
		} catch (IOException e){
			logger.error("Read xml file error:" + e);
		}
	}

	/**
	 * 
	 * 方法名称：buildRoot
	 * <p>
	 * 
	 * 方法功能：生成XML的根结点
	 * <p>
	 * 
	 * 参数说明：
	 * <p>
	 * 
	 * 返回：void
	 * <p>
	 * 
	 * 
	 **/

	private void buildRoot(){
		root = doc.getDocumentElement();
	}

	/**
	 * 
	 * @return 返回 doc。
	 * 
	 */

	public Document getDoc(){
		return doc;
	}

	/**
	 * 
	 * @param doc
	 *            要设置的 doc。
	 * 
	 */

	public void setDoc(Document doc){
		this.doc = doc;
	}

	/**
	 * 
	 * @return 返回 path。
	 * 
	 */

	public String getPath(){
		return path;
	}

	/**
	 * 
	 * @param path
	 *            要设置的 path。
	 * 
	 */

	public void setPath(String path){
		this.path = path;
	}

	/**
	 * 
	 * @return 返回 root。
	 * 
	 */

	public Element getRoot(){
		return root;
	}

	/**
	 * 
	 * @param root
	 *            要设置的 root。
	 * 
	 */

	public void setRoot(Element root){
		this.root = root;
	}

	/* 全局变量 */

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private InputStream inputStream = null;// xml文件流
	
	private String path = null;// xml文件路径

	private Document doc = null;// xml文件对应的document

	private Element root = null;// xml文件的根结点

	private Logger logger = LoggerFactory.getLogger(XmlBuilder.class);

}

class TypeAliasRegistry{
	
	private static final Logger logger = LoggerFactory
			.getLogger(TypeAliasRegistry.class);
	
	private static final Map<String, Class<?>> TYPE_ALIASES = new HashMap<String, Class<?>>();
	
	public TypeAliasRegistry() {
		TYPE_ALIASES.put("string", String.class);
		TYPE_ALIASES.put("byte", byte.class);
		TYPE_ALIASES.put("long", long.class);
		TYPE_ALIASES.put("short", short.class);
	    TYPE_ALIASES.put("int", int.class);
	    TYPE_ALIASES.put("integer", int.class);
	    TYPE_ALIASES.put("double", double.class);
	    TYPE_ALIASES.put("float", float.class);
	    TYPE_ALIASES.put("boolean", boolean.class);
	    TYPE_ALIASES.put("char", char.class);
	}
	
	public static <T> Class<T> resolveAlias(String string) {
	      if (string == null) return null;
	      String key = string.toLowerCase(Locale.ENGLISH); // issue #748
	      if (TYPE_ALIASES.containsKey(key)) {
	        return (Class<T>) TYPE_ALIASES.get(key);
	      } 
	      return null;
	 }
	
	public static Object stringConvertToClassType(String value,Class targetType){
        Object converted = value;
        try{
        	if (targetType == String.class) {
            	converted =  value;
            }else if(targetType == byte.class ){
            	converted = Byte.parseByte(value);
            }else if(targetType == long.class ){
            	converted = Long.parseLong(value);
            }else if(targetType == short.class ){
            	converted = Short.parseShort(value);
            }else if(targetType == int.class){
            	converted = Integer.parseInt(value);
            }else if(targetType == double.class){
            	converted = Double.parseDouble(value);
            }else if(targetType == float.class){
            	Float.parseFloat(value);
            }else if(targetType == boolean.class){
            	converted = Boolean.parseBoolean(value);
            }else if(targetType == char.class){
            	converted = value.charAt(0);
            }else{
            	converted = null;
            }
        }catch(Exception e){
        	logger.error("File convert To ClassType :"+targetType +e.getMessage());
        	converted = null;
        }
        return converted;
	}
	
}
