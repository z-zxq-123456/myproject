package com.zxq.learn.jmm;

public class TestJMM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation1 = null;
        allocation2 = new byte[2 * _1MB];
        allocation2 = null;
        allocation3 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation3 = new byte[3 * _1MB];
        allocation4 = new byte[5 * _1MB];

    }
}
