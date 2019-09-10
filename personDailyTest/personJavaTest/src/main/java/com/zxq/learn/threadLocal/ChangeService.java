package com.zxq.learn.threadLocal;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/7/16
 */
public class ChangeService {
    public static void main(String[]args){

            Context context = Context.getInstance();
            context.setChanges(3);
            context.setPhase("ChangeService process");

            System.out.println(context.getChanges());
            System.out.println(context.getPhase());
    }
}
