package com.exampl.zxq.dubbo.proxytool.javasist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavasistProxy {

    public static void javasistMake(){
        try {

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("com.exampl.zxq.dubbo.proxytool.ModelTest");
/*
            byte[] bytes = cc.toBytecode();
*/
            System.out.println(cc.getSimpleName());
            CtConstructor constructor = new CtConstructor(null,cc);
            constructor.setBody("{}");
            cc.addConstructor(constructor);


           /* ClassPool pool2 = ClassPool.getDefault();
            CtClass cc2 = pool2.get("com.exampl.zxq.dubbo.proxytool.ModelTest");*/

            CtMethod cm = new CtMethod(CtClass.voidType,"method4",new CtClass[0],cc);
            cm.setModifiers(Modifier.PUBLIC);
            cm.setBody("System.out.println(\"method4  is started!\");");
            cc.addMethod(cm);

            Class clz = cc.toClass();
            Object obj = clz.newInstance();
            Method m = clz.getDeclaredMethod("method4",new Class[0]);
            m.invoke(obj,null);

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
