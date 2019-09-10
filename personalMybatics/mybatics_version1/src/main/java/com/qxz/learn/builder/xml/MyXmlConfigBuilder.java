package com.qxz.learn.builder.xml;


import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.executor.MyErrorContext;
import com.qxz.learn.io.ResourcesUtils;
import com.qxz.learn.parsing.MyXNode;
import com.qxz.learn.parsing.MyXpathParser;
import com.qxz.learn.reflaction.MyDefaultReflectorFactory;
import com.qxz.learn.reflaction.MyMetaClass;
import com.qxz.learn.reflaction.MyReflectorFactory;

import java.io.Reader;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/8
 */
public class MyXmlConfigBuilder extends MyBaseBuilder {

   private boolean parsed;
   private MyXpathParser parser;
   private String environment;
   private MyReflectorFactory localReflectorFactory = new MyDefaultReflectorFactory();


   public MyXmlConfigBuilder(Reader reader){
      this(reader,null,null);
   }

   public MyXmlConfigBuilder(Reader reader,String environment){
      this(reader,environment,null);
   }

   public MyXmlConfigBuilder(Reader reader,String environment,Properties props){
      this(new MyXpathParser(reader, true, props, new MyXMLMapperEntityResolver()),environment,props);
   }

   public MyXmlConfigBuilder(MyXpathParser parser, String environment, Properties properties){
      super(new MyConfiguration());
      MyErrorContext.instance().source("sql Mapper Configuration");
      this.configuration.setVariables(properties);
      this.parsed = false;
      this.environment = environment;
      this.parser = parser;
   }

   public MyConfiguration parse()throws Exception{

      if (parsed){
         throw new RuntimeException("mulitpile xml config file");
      }
      parsed = true;
      parseConfiguration(parser.evalNode("/configuration"));
      return configuration;
   }

   private void parseConfiguration(MyXNode root)throws Exception{
      Properties settings = settingsAsProperties(root.evalNode("settings"));
//      loadCustomVfs(settings); TODO ignored vfs in xmlConfiguration
      typeAliasesElement(root.evalNode("typeAliases"));
      pluginElement(root.evalNode("plugins"));
      objectFactoryElement(root.evalNode("objectFactory"));
      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
      reflectorFactoryElement(root.evalNode("reflectorFactory"));
      settingsElement(settings);
      // read it after objectFactory and objectWrapperFactory issue #631
      environmentsElement(root.evalNode("environments"));
      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
      typeHandlerElement(root.evalNode("typeHandlers"));
      mapperElement(root.evalNode("mappers"));
   }

   private Properties settingsAsProperties(MyXNode context){
      if (context == null){
         return  new Properties();
      }
      Properties props = context.getChildrenAsProperties();
      MyMetaClass metaConfig = MyMetaClass.forClass(MyConfiguration.class,localReflectorFactory);
      for (Object key : props.keySet()) {
         if (!metaConfig.hasSetter(String.valueOf(key))) {
            throw new RuntimeException("The setting " + key + " is not known.  Make sure you spelled it correctly (case sensitive).");
         }
      }
      return props;
   }

   private void typeAliasesElement(MyXNode parent) {
      if (parent != null) {
         for (MyXNode child : parent.getChildren()) {
            if ("package".equals(child.getName())) {
               String typeAliasPackage = child.getStringAttr("name");
               configuration.getTypeAliasRegistry().registerAliases(typeAliasPackage);
            } else {
               String alias = child.getStringAttr("alias");
               String type = child.getStringAttr("type");
               try {
                  Class<?> clazz = ResourcesUtils.classForName(type);
                  if (alias == null) {
                     typeAliasRegistry.registerAlias(clazz);
                  } else {
                     typeAliasRegistry.registerAlias(alias, clazz);
                  }
               } catch (ClassNotFoundException e) {
                  throw new RuntimeException("Error registering typeAlias for '" + alias + "'. Cause: " + e, e);
               }
            }
         }
      }
   }

   private void pluginElement(MyXNode parent) throws Exception {
      if (parent != null) {
         for (MyXNode child : parent.getChildren()) {
            String interceptor = child.getStringAttr("interceptor");
            Properties properties = child.getChildrenAsProperties();
            Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).newInstance();
            interceptorInstance.setProperties(properties);
            configuration.addInterceptor(interceptorInstance);
         }
      }
   }
}
