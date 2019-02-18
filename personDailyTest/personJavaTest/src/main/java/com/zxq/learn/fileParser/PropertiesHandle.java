package com.zxq.learn.fileParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * 属性文件加载
 * Created{ by zhouxqh} on 2018/1/8.
 */
public class PropertiesHandle {

    private String path;

    private Properties properties;

    public PropertiesHandle(String path) {
        this.path = path;
    }

    private Properties createProperties(String path)throws Exception{

        this.properties = new Properties();
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        return properties;
    }
    public void parseProperties()throws Exception{

        Properties properties = createProperties(this.path);
        System.out.println(properties.getProperty("test.source.string"));
    }

    public static void main(String[]args){

        String path = "/source.properties";
        String rootPath = Class.class.getClass().getResource("/").getPath();
        try {
            String convertPath = java.net.URLDecoder.decode(rootPath, "utf-8");
            PropertiesHandle propertiesHandle = new PropertiesHandle(convertPath+path);
            propertiesHandle.parseProperties();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
