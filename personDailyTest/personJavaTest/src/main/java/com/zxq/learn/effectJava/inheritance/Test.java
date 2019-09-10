package com.zxq.learn.effectJava.inheritance;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created{ by zhouxqh} on 2017/10/21.
 */
public class Test {

    public  static void main(String[] args){
//        InstrumentHashSet<String> s = new InstrumentHashSet<String>();
//        s.addAll(Arrays.asList("a","b","c"));
//        System.out.println(s.getCount());//6 != 3;
        InstrumentHashSet2 set2 = new InstrumentHashSet2(new Set() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] a) {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {

            }
        });
        set2.addAll(Arrays.asList("a","b","c"));
        System.out.println(set2.getCount());
    }
}
