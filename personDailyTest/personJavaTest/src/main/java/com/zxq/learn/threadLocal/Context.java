package com.zxq.learn.threadLocal;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/7/16
 */
public class Context {

public static Context getInstance(){

     if (ThreadLoacalManager.getTranContext() == null) {
         ThreadLoacalManager.setTranContext(new Context());
     }
     return (Context) ThreadLoacalManager.getTranContext();
 }

    private String phase;

    private int changes;

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }
}
