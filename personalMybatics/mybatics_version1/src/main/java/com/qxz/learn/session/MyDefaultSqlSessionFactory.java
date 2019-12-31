package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;
import java.io.File;
import java.net.URL;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {

    private MyConfiguration myConfiguration;


    public MyDefaultSqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
        loadMappersInfo(MyConfiguration.variables.getProperty("MAPPER_LOCATION").replaceAll("\\.","/"));
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
                    }else if (file.getName().endsWith("Mapper")){

                    }
                }
            }

        }

    }


}
