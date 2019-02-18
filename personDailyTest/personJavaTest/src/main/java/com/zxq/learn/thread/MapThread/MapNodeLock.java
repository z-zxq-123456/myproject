package com.zxq.learn.thread.MapThread;


/**
 * @Description : 基于散列的Map锁分段
 * @Author :zhouxqh
 * @Date : Create on 2018/3/22
 */
public class MapNodeLock {

    private static final int N_LOCKS = 16;
    private final Node[] buckets ;
    private final Object[] locks;

    public MapNodeLock(int num) {
        buckets = new Node[num];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++){
            locks[i] = new Object();
        }
    }

    private final int hash(Object key){
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key){
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]){
            for (Node m = buckets[hash]; m != null; m = m.getNextNode()){
                if (m.key.equals(key)){
                    return m.value;
                }
            }
            return null;
        }
    }

    public void clear(){
        for (int i = 0; i < buckets.length; i++){
            synchronized (locks[i % N_LOCKS]){
                buckets[i] = null;
            }
        }
    }
}