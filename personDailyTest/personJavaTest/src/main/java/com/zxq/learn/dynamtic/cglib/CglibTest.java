package com.zxq.learn.dynamtic.cglib;

import com.zxq.learn.dynamtic.MyOperation;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2019/2/18
 */
public class CglibTest {

    public static void main(String[]a){

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(MyOperation.class);
        enhancer.setCallbacks(new Callback[]{new CglibProxy(),new CglibProxy2() , NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CglibFilter());
        enhancer.setInterceptDuringConstruction(false);/*构造方法中的函数不拦截*/

        MyOperation myOperation = (MyOperation)enhancer.create();

        myOperation.add(1,2);

    }
}
