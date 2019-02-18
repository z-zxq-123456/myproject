package com.zxq.learn.effectJava.singleton;

/**
 * 单例模式2：
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class Evis2 {

    private  Evis2(){};
    private static final Evis2 Instance = new Evis2();
    public static Evis2 getInstance(){
        return Instance;
    }
}
