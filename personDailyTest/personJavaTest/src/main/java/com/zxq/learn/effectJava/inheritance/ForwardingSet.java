package com.zxq.learn.effectJava.inheritance;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class ForwardingSet<T> implements Set<T> {
    private final Set<T> s;

    public ForwardingSet(Set<T> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return s.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    @Override
    public void clear() {
        s.clear();
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return s.equals(obj);
    }

    @Override
    public String toString() {
        return s.toString();
    }
}
