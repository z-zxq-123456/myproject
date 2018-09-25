package com.dcits.galaxy.base;

/**
 * Created by Tim on 2016/10/31.
 */
public class Holder<T> {

    private volatile T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

}
