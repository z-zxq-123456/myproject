package com.zxq.learn.handwrite.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 22:55
 **/
public class ClassUtils {

    public static Set<Class<?>> getClasses(String basePackage){

        Set<Class<?>> classes = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(basePackage.replace(".","/"));
            while (urls.hasMoreElements()){

                URL url = urls.nextElement();
                if (url != null){
                    String protocal = url.getProtocol();
                    if (protocal.equals("file")){
                        String pckth = url.getPath();
                        addClass(classes,pckth,basePackage);
                    }else if (protocal.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection!= null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile!=null){
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while (jarEntryEnumeration.hasMoreElements()){
                                    JarEntry entry = jarEntryEnumeration.nextElement();
                                    String entryName = entry.getName();
                                    if (entryName.endsWith(".class")){
                                        String className = entryName.substring(0,entryName.lastIndexOf("."))
                                                .replaceAll("/",".");
                                        doAddClass(classes,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void addClass(Set<Class<?>> classes,String packPth,String packName){

        File[] files = new File(packPth).listFiles(file -> (file.isFile()
                && file.getName().endsWith(".class")) || file.isDirectory());

        for (File file:files){

            String fileName = file.getName();
            if (file.isFile()){
                    String className = fileName.substring(0,fileName.lastIndexOf("."));
                    String className2 = packName+"."+className;
                doAddClass(classes,className2);
            }else {
                String subPackpath = fileName;
                String subPth = packPth+"/"+subPackpath;
                String subPackageName = packName + "." + fileName;
                addClass(classes, subPth, subPackageName);
            }
        }
    }


    private static void doAddClass(Set<Class<?>> classes,String classname){
        classes.add(loadClass(classname,false));
    }

    public static Class<?> loadClass(String className,boolean initialized){
        Class cls = null;
        try {
             cls = Class.forName(className,initialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }
}
