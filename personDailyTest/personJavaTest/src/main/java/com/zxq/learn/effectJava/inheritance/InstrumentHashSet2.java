package com.zxq.learn.effectJava.inheritance;

import java.util.Collection;
import java.util.Set;

/**
 * 复合优于继承<转发>forwarding-class</>
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class InstrumentHashSet2<T> extends ForwardingSet<T> {
    private int count = 0;

    public InstrumentHashSet2(Set<T> s) {
        super(s);
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
