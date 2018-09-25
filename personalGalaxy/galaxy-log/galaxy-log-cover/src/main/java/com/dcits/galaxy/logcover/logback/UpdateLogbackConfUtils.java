package com.dcits.galaxy.logcover.logback;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.qos.logback.core.util.Loader;

import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.EcmLogconfInfo;
/**
 * 修改logback.xml工具类* 
 * @author wangbinaf
 * @date 2016-10-14 
 */
public class UpdateLogbackConfUtils {
	
private  static final String CONFIGURATION_SCAN = "scan";//节点的属性名
	
	private  static final String CONFIGURATION = "configuration";//节点的属性名
	
	private  static final String CONFIGURATION_SCANPERIOD = "scanPeriod";//节点的属性名
	
	private  static final String APPENDER_CLASS = "class";//节点的属性名
	
	private  static final String CONFIGURATION_LEVEL  = "level";//节点的属性名

	private  static final String CONFIGURATION_LOGGER  = "logger";//节点名字为logger
	 
    private  static final String CONFIGURATION_NAME  = "name";  //节点的属性名
    
    private  static final String CONFIGURATION_ADDITI  = "additivity";//节点的属性名
    
    private  static final String MAXHISTORY = "maxHistory";//最大历史记录
    
    private  static final String MAXFILESIZE = "maxFileSize";//最大存储
    
    private  static final String MAXHISTORY_VALUE = "30";//最大历史记录
    
    private  static final String MAXFILESIZE_VALUE= "10MB";//最大存储
    
    private  static final String PATTERN_VALUE ="%date %level [%thread] %logger.%class{0}#%method[%file:%line] %msg%n";//日志存储格式
    
    private  static final String FILE_NAME_PATTERN_VALUE="logs/logPlatForm-%d{yyyy-MM-dd}.%i.log";//日志存储格式
    
    private  static final String CONFIGURATION_APPENDER_REF  = "appender-ref";//节点名字为appender-ref
    		
    private  static final String GALAXY_LOG_COLLECTION  = "com.dcits.galaxy.logcoll";//节//添加logger节点  naem=galaxy-log-collection
    
    private  static final String GALAXY_LOG_COVER = "com.dcits.galaxy.logcover";////添加logger节点  naem=galaxy-log-cover 
    
    private  static final String GALAXY_LOG_STORE  = "com.dcits.galaxy.store";////添加logger节点  naem=galaxy-log-store
   
    private  static final String GALAXY_LOG_CLIENT  = "com.dcits.galaxy.logclient";//添加logger节点  naem=galaxy-log-logclient
    
    private  static final Logger log = LoggerFactory.getLogger(UpdateLogbackConfUtils.class);
    
    private static  final String  CLASS_NAME = "com.dcits.galaxy.logclient.trace.TraceWriter";//类的全名 以此来判断有没有包含日志平台的jar包
	
	 /**
	  * 修改logback.xml 
	  @param EcmLogconfInfo   updateInfo  日志配置信息 
	  */
	 public   static  void editLogbackConf( EcmLogconfInfo info ){
		 String  xmlPath = getXmlPath();
		 DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
         dbf.setIgnoringElementContentWhitespace(true);
         DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		    Document xmldoc=db.parse(xmlPath);//xmlPath  文件的路径
		    editConfiguration( info.getIsScanName(), info.getScanPeriod(),xmldoc);//修改configuration节点的属性
			modifyLevel(info.getLogLevelName(),CONFIGURATION_LOGGER ,xmldoc);//修改日志平台的日志级别 
		//	modifyLevel(info.getLogLevelName(),CONFIGURATION_ROOT ,xmldoc);//修改root的日志级别
			String className = info.getAppenderClass();
			 if(!DataUtil.isNull(className)){
				 if(isExistClass(CLASS_NAME)){  //类的全名 以此来判断有没有包含日志平台的jar包,为了防止死循环，如果有日志平台的client则修改，如果没有没有则不修改
					 modifyAppender(info.getAppenderClass(),xmldoc);//修改naem=ROLLING 的 appender节点的class的属性值
				 }
			}
			saveConfiguration(xmldoc);//文档修改后保存文档
		 }catch(Exception e){
			 log.error("修改logback.xml :"+DataUtil.printErrorStack(e));
         }
	 }
	 /**
	  * 修改logback.xml 增加logger节点
	  @param EcmLogconfInfo   updateInfo  日志配置信息 
	  */
	 public  static  void addNode( EcmLogconfInfo info ){
		 String  xmlPath = getXmlPath();
		 log.info("xmlPath:"+xmlPath);
		 DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
         dbf.setIgnoringElementContentWhitespace(true);
         DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		    Document xmldoc=db.parse(xmlPath);//xmlPath  文件的路径
		    addNodeOfLogger(info.getLogLevelName(),GALAXY_LOG_COLLECTION,xmldoc);//添加logger节点  naem=galaxy-log-collection,收集端
		    addNodeOfLogger(info.getLogLevelName(),GALAXY_LOG_COVER,xmldoc);//添加logger节点  naem=galaxy-log-cover，公用端
		    addNodeOfLogger(info.getLogLevelName(),GALAXY_LOG_STORE,xmldoc);//添加logger节点  naem=galaxy-log-store  存储端
		    addNodeOfLogger(info.getLogLevelName(),GALAXY_LOG_CLIENT,xmldoc);//添加logger节点  naem=galaxy-log-logclient  客户端
		    addNodeOfAppender(xmldoc);//添加apperder节点
			saveConfiguration(xmldoc);//文档修改后保存文档
		 }catch(Exception e){
			 log.error("增加logger:"+DataUtil.printErrorStack(e));
         }
	 }
	 /**
	  * 修改   logback 节点configuration 的属性  <configuration scan="true" scanPeriod="8 seconds">  
	  * @param String        isScan 是否扫描	
	  * @param String        newScanPeriod 扫描的时间周期 
	  * @param Document     xmldoc	 修改的文件 	 
	  */
	 public  static  void editConfiguration( String  newScan, String newScanPeriod ,Document xmldoc){
         try{
	       	  String scanPeriod = newScanPeriod+" seconds";
	       	  String isScan=newScan;
	          NodeList confList = xmldoc.getElementsByTagName(CONFIGURATION); //configuration  作为常量引入  可定义为参数
	       	  Element confTemp = (Element)confList.item(0);
	       	  //先取出原文件的属性  如果相等则不修改
	       	  String oldScan = confTemp.getAttribute(CONFIGURATION_SCAN);
	       	  String oldScanPeriod = confTemp.getAttribute(CONFIGURATION_SCANPERIOD);
	       	  //如果  isScan相等,并且scanPeriod不等 ，以才修改 
	       	  if(((oldScan.equals(isScan))==true)&&!oldScanPeriod.equals(scanPeriod)){
	       		  confTemp.setAttribute(CONFIGURATION_SCANPERIOD, scanPeriod);  //scanPeriod  作为常量引入  可定义为参数
	       	  }
	       	  //如果  isScan不相等,isScan等于 true，  isScan scanPeriod 都修改 
	       	  if(!oldScan.equals(isScan)&&isScan.equals("true")){
	       		  confTemp.setAttribute(CONFIGURATION_SCAN, isScan);  //scan  作为常量引入  可定义为参数
	       		  confTemp.setAttribute(CONFIGURATION_SCANPERIOD, scanPeriod);  //scanPeriod  作为常量引入  可定义为参数
	       	  }
	       	  //如果  isScan不相等,isScan等于 false，  isScan修改 
	       	  if(!oldScan.equals(isScan)&&isScan.equals("false")){
	       		  confTemp.setAttribute(CONFIGURATION_SCAN, isScan);  //scan  作为常量引入  可定义为参数
	       	  }
            
         }catch(Exception e){
        	 log.error("logback 节点configuration 的属性:"+DataUtil.printErrorStack(e));
         }
 	}
	 /**                                             
	  * 修改   logback 节点appender 的属性 <appender class="ch.qos.logback.core.rolling.RollingFileAppender" name="ROLLING">
	  * @param Document     xmldoc	 修改的文件 	 
	  * @param String      newClass	 新的class属性的值	 
	  */
	 public  static void modifyAppender(String newClass,Document xmldoc ){
          try{
	          Element root = xmldoc.getDocumentElement();
	          Object obj = selectSingleNode("/configuration/appender[@name='ROLLING']", root);
	          if(null!=obj){
		          Element per =(Element) obj;
		          if(!per.getAttribute(APPENDER_CLASS).equals(newClass)){
		        	  per.setAttribute(APPENDER_CLASS, newClass);  //scanPeriod  作为常量引入  可定义为参数
		          }
	          }
          }catch(Exception e){
        	  log.error("修改   logback 节点appender 的属性:"+DataUtil.printErrorStack(e));
	      }  
	 } 

	 /**
	  * 修改节点的日志级别可以修改  logger   root 的  级别   （此处对所有的logger节点全部修改）
	  * @param String        newLevel 新的日志级别	
	  * @param String       nodeName 节点名称  比如 root logger	
	  * @param Document     xmldoc	 修改的文件
	  */
	 public  static void modifyLevel(String  newLevel,String nodeName,Document xmldoc){
          try{   
	        	 Element root = xmldoc.getDocumentElement();
		         Object  objCollect =selectSingleNode("/configuration/logger[@name='"+GALAXY_LOG_COLLECTION+"']", root);
		         Object  objCover =selectSingleNode("/configuration/logger[@name='"+GALAXY_LOG_COVER+"']", root);
		         Object  objStore =selectSingleNode("/configuration/logger[@name='"+GALAXY_LOG_STORE+"']", root);
		         Object  objClient =selectSingleNode("/configuration/logger[@name='"+GALAXY_LOG_CLIENT+"']", root);
		         Element perCollect =(Element) objCollect;
		         Element perCover =(Element) objCover;
		         Element perStore =(Element) objStore;
		         Element perClient =(Element) objClient;
		         perCollect.setAttribute(CONFIGURATION_LEVEL, newLevel); 
		         perCover.setAttribute(CONFIGURATION_LEVEL, newLevel); 
		         perStore.setAttribute(CONFIGURATION_LEVEL, newLevel); 
		         perClient.setAttribute(CONFIGURATION_LEVEL, newLevel); 
//    	         Node logStore = xmldoc.getElementById(" ");
//    	         Node logCover = xmldoc.getElementById("");
//    	         Node logCollect= xmldoc.getElementById("");                
//	             NodeList nodelist = xmldoc.getElementsByTagName(nodeName); //查询节点 为节点的所有的节点集合
//	             
//	             if (nodelist.getLength()>0){//当该节点存在，进行修改
//		             for (int i = 0; i < nodelist.getLength(); i++) { // 循环处理对象
//		            	  Element tempRoot = (Element)nodelist.item(i);
//		            	  if(!tempRoot.getAttribute(CONFIGURATION_LEVEL).equals(newLevel)){//当日志级别发生改变时，修改日志级别
//		            		  tempRoot.setAttribute(CONFIGURATION_LEVEL, newLevel); //设置 新的日志级别
//		    	          }
//		             }
//	             }
          }catch(Exception e){
        	  log.error("修改节点的日志级:"+DataUtil.printErrorStack(e));
	      }  
      }
	 
	 /**
	  * 增加logger节点   
	  * @param String        newLevel 新的日志级别	
	  * @param String       name 节点名称  比如 root logger	
	  * @param Document     xmldoc	 修改的文件
	  */
	 public static  void addNodeOfLogger(String  newLevel,String name,Document xmldoc){
          try{
        	  Element root = xmldoc.getDocumentElement();
        	   Object  per =selectSingleNode("/configuration/logger[@name='"+name+"']", root);
	          if(null==per){//不存在则增加该节点
	        	 Element appender =xmldoc.createElement(CONFIGURATION_LOGGER);
	 			 appender.setAttribute(CONFIGURATION_NAME, name);
	 			 appender.setAttribute(CONFIGURATION_LEVEL,newLevel);
	 			 appender.setAttribute(CONFIGURATION_ADDITI,"false");
	 			 Element ref =xmldoc.createElement(CONFIGURATION_APPENDER_REF);
	 			 ref.setAttribute("ref", "LOGPLATFORM");
	 			 appender.appendChild(ref);
	 			 root.appendChild(appender);
	          }
          }catch(Exception e){
        	  log.error("增加logger节点  :"+DataUtil.printErrorStack(e));
	      }  
      }
	 /**
	  * 增加appender节点   
	  * @param Document     xmldoc	 修改的文件
	  */
	 public static  void addNodeOfAppender(Document xmldoc){
          try{
        	  Element root = xmldoc.getDocumentElement();
	          Object  per =selectSingleNode("/configuration/appender[@name='LOGPLATFORM']", root);
	          if(null==per){//不存在则增加该节点
	        	 Element appender =xmldoc.createElement("appender");
	        	 appender.setAttribute(CONFIGURATION_NAME, "LOGPLATFORM");
				 appender.setAttribute(APPENDER_CLASS,ConfigConstants.APPENDER_CLASS_ORIGNAL);
	 			 Element prudent =xmldoc.createElement("prudent");
	 			 prudent.setTextContent("true");
	 			 Element rollingPolicy =xmldoc.createElement("rollingPolicy");
	 			 rollingPolicy.setAttribute(APPENDER_CLASS,"ch.qos.logback.core.rolling.TimeBasedRollingPolicy");
	 			 Element fileNamePattern =xmldoc.createElement("fileNamePattern");
	 			 fileNamePattern.setTextContent(FILE_NAME_PATTERN_VALUE);
	 			 Element maxHistory =xmldoc.createElement(MAXHISTORY);
	 			 maxHistory.setTextContent(MAXHISTORY_VALUE);
	 			 Element timeBased =xmldoc.createElement("timeBasedFileNamingAndTriggeringPolicy");
	 			 timeBased.setAttribute(APPENDER_CLASS,"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP");
	 			 Element maxFileSize =xmldoc.createElement(MAXFILESIZE);
	 			 maxFileSize.setTextContent(MAXFILESIZE_VALUE);
	 			 Element encoder =xmldoc.createElement("encoder");
	 			 Element pattern =xmldoc.createElement("pattern");
	 			 pattern.setTextContent(PATTERN_VALUE);
	 			 encoder.appendChild(pattern);
	 			 timeBased.appendChild(maxFileSize);
	 			 rollingPolicy.appendChild(fileNamePattern);
	 			 rollingPolicy.appendChild(maxHistory);
	 			 rollingPolicy.appendChild(timeBased);
	 			 appender.appendChild(rollingPolicy);
	 			 appender.appendChild(prudent);
	 			 appender.appendChild(encoder);
	 			 NodeList childs = root.getChildNodes();
				 Node secondnode = childs.item(1);
		         root.insertBefore(appender, secondnode);//插入到固定节点之前
	 		//	 root.appendChild(appender);
	          }
          }catch(Exception e){
        	  log.error("增加appender节点 :"+DataUtil.printErrorStack(e));
	      }  
      }
	//文档修改后保存文档
	 private  static void saveConfiguration( Document xmldoc){
		 String  xmlPath = getXmlPath();
		  TransformerFactory factory = TransformerFactory.newInstance();
          Transformer former = null;
		  try {
			former = factory.newTransformer();
			former.setOutputProperty(OutputKeys.INDENT, "yes");  //换行
		 	former.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");//缩进2格
			former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
		  }catch(Exception e){
			  log.error("文档修改后保存文档:"+DataUtil.printErrorStack(e));
         }
	 }
	//扫描满足条件的节点
	private  static Object selectSingleNode(String express, Element source) {
		  Object result=null;
          XPathFactory xpathFactory=XPathFactory.newInstance();
          XPath xpath=xpathFactory.newXPath();
          try {
        	  result  =  xpath.evaluate(express, source, XPathConstants.NODE);
          } catch (XPathExpressionException e) {
        	  log.error("扫描满足条件的节点:"+DataUtil.printErrorStack(e));
          }
          return result;
	}

    //获取logback.xml文件
	 private  static String    getXmlPath(){      
		   ClassLoader myClassLoader=Loader.getClassLoaderOfObject(new UpdateLogbackConfUtils());
		   log.info("文件名字："+myClassLoader.getResource("logback.xml").getFile());
		   return myClassLoader.getResource("logback.xml").getFile();
	 }
	 
	 //判断该类是否存在,存在返回true，不存在返回false
    private  static  boolean   isExistClass( String  name){
    	try{
    		Class.forName(name);
    		return  true;
    	 } catch (Exception e) {
    		 log.error("查看是否是客户端:"+DataUtil.printErrorStack(e));
    	 }
    	return false;
    }
}