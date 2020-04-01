package com.exampl.zxq.dubbo.proxytool.cglib;

import com.exampl.zxq.dubbo.proxytool.ModelTest;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class CGLibProxy {

    public static void cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ModelTest.class);

        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before cglib proxy...");
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("after cglib proxy!");
                return result;
            }
        });

        ModelTest modelTest = (ModelTest) enhancer.create();
        modelTest.method1();
    }

    public static void cglibProxyFillter(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ModelTest.class);
        CallbackHelper callbackHelper = new CallbackHelper(ModelTest.class,new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return new FixedValue() {
                        public Object loadObject() throws Exception {
                            return "cglib callback";
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        ModelTest modelTest =(ModelTest)enhancer.create();
        System.out.println(modelTest.getUserName());
    }


}
