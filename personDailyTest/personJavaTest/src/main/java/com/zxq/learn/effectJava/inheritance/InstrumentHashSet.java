package com.zxq.learn.effectJava.inheritance;

import java.util.Collection;
import java.util.HashSet;

/**
 * 复合优于继承
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class InstrumentHashSet<T> extends HashSet<T> {
    private int count = 0;

    public InstrumentHashSet() {

    }

    public InstrumentHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(T t) {
        count++;
        return super.add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        count += c.size();
        return super.addAll(c);
    }

    public int getCount() {
        return count;
    }
}
