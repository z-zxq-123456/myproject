package com.company.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConfigUtils {

    public static int dataNum;
    public static int pkSize;
    public static String env;
    public static long lastModify;
    public static List<Listener> listeners = new ArrayList<>();
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    static {

        executorService.scheduleAtFixedRate(()->{

            File file = new File("src/config/application.properties");
            long lastTime = file.lastModified();

            if (lastTime == lastModify ){
                return;
            }
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            Properties properties = new Properties();
            try {
                properties.load(bufferedReader);
            } catch (IOException e) {
                e.printStackTrace();
            }

            pkSize = Integer.parseInt(properties.getProperty("pkSize"));
            dataNum = Integer.parseInt(properties.getProperty("dataNum"));
            env = properties.getProperty("env");
            lastModify = lastTime;

            for (Listener listener:listeners){
                listener.onChange();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        },10,5, TimeUnit.SECONDS);
    }

    public static interface Listener{
        public Properties onChange();
    }

    public static String getEnv(){
        if (lastModify==0){
            throw new RuntimeException("system init! try later");
        }else {
            return env;
        }
    }
    public static int getDataNum(){
        if (lastModify==0){
            throw new RuntimeException("system init! try later");
        }else {
            return dataNum;
        }
    }
    public static int getPkSize(){
        if (lastModify==0){
           throw new RuntimeException("system init! try later");
        }else {
            return pkSize;
        }

    }

    public static Properties getDbProperties(){
        if (lastModify == 0){
            throw new RuntimeException("system init! try later");
        }
        File file = new File("src/config/"+env+".db.properties");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Properties properties = new Properties();
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
