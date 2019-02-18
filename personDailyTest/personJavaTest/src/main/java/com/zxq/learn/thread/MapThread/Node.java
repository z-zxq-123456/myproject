package com.zxq.learn.thread.MapThread;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/3/22
 */
public class Node {

    public Node nextNode;

    public  Object key;

    public Object value;

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
