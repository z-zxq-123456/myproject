package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.mapping.MyMappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {

    private MyConfiguration myConfiguration;


    public MyDefaultSqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
        loadMappersInfo(MyConfiguration.variables.getProperty("mapper.location").replaceAll("\\.","/"));
    }

    @Override
    public MySqlSession openSqlSession() {

        MySqlSession session = new MyDefaultSqlSession(this.myConfiguration);
        return session;
    }

    private void loadMappersInfo(String dirName){
        URL resources = MyDefaultSqlSessionFactory.class.getClassLoader().getResource(dirName);
        File mapperDir = new File(resources.getFile());
        if (mapperDir.isDirectory()){

            File[] mappers = mapperDir.listFiles();
            if(mappers != null){
                for (File file:mappers){
                    if (file.isDirectory()){
                        loadMappersInfo(dirName+"/"+file.getName());
                    }else{
                       // XmlUtil.readMapperXml(file, this.configuration);
                        SAXReader saxReader = new SAXReader();
                        saxReader.setEncoding("UTF-8");
                        try {
                            Document document = saxReader.read(file);
                            Element element = document.getRootElement();

                            if (!"mapper".equals(element.getName())){
                                return;
                            }

                            String namespace = element.attributeValue("namespace");
                            List<MyMappedStatement> myMappedStatements = new ArrayList<>();

                            for (Iterator iterator=element.elementIterator();iterator.hasNext();){

                                Element els = (Element)iterator.next();
                                String elsname = els.getName();
                                MyMappedStatement mappedStatement = new MyMappedStatement();
                                if ("select".equals(elsname)){
                                    String resultType = els.attributeValue("resultType");
                                    mappedStatement.setResultType(resultType);
                                    mappedStatement.setSqlCommandType("select");
                                }else if ("insert".equals(elsname)){
                                    mappedStatement.setSqlCommandType("insert");
                                }else if ("delete".equals(elsname)){
                                    mappedStatement.setSqlCommandType("delete");
                                }else if ("update".equals(elsname)){
                                    mappedStatement.setSqlCommandType("update");
                                }

                                String sqlId = namespace+"."+els.attributeValue("id");
                                mappedStatement.setSqlId(sqlId);
                                mappedStatement.setNamespace(namespace);
                                mappedStatement.setSql(els.getStringValue());
                                myMappedStatements.add(mappedStatement);

                                myConfiguration.addMappedStatement(mappedStatement);

                                myConfiguration.addMapper(Class.forName(namespace));
                            }

                        } catch (DocumentException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
