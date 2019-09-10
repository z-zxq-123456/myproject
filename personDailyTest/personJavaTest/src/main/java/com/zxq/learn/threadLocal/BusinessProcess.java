package com.zxq.learn.threadLocal;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/7/16
 */
public class BusinessProcess {

    public static void main(String[]args){

        Context context = Context.getInstance();
        context.setChanges(1);
        context.setPhase("business process");

        new Thread(){
            @Override
            public void run() {

                Context context = Context.getInstance();
                System.out.println(context.getChanges());
                System.out.println(context.getPhase());
                context.setChanges(2);
                context.setPhase("business process - thead1");
                System.out.println(context.getChanges());
                System.out.println(context.getPhase());
            }
        }.start();

        System.out.println(context.getChanges());
        System.out.println(context.getPhase());
    }
}
