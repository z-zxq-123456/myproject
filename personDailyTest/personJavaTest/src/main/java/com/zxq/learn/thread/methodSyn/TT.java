package com.zxq.learn.thread.methodSyn;
/**
 * ����������
 * ĳ��ʱ��Σ�ֻ��һ���߳̽��뵽��������
 * m1(),m2()����������ͬʱִ��
 * �ĵĵ��߳�ͬʱ����������ʱ�����һ��������
 * @author acer
 */

public class TT implements Runnable {
	int b = 100;
	
	public synchronized void m1() throws Exception{
		//Thread.sleep(500);
		b = 1000;
		Thread.sleep(1100);
		System.out.println("m1b = " + b+Thread.currentThread().getName());
	}
	
	public synchronized void m2() throws Exception {
		
		//Thread.sleep(2500);
		b = 2000;	
		System.out.println("m2b = " + b+Thread.currentThread().getName());
	}
	
	public void run() {
		try {
			m1();
			System.out.println(Thread.currentThread().getName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		TT tt = new TT();
	
		Thread t = new Thread(tt);
		Thread t2 = new Thread(tt);
		t.start();
		t2.start();
		
		System.out.println("tt1:b = "+ tt.b);
		Thread.sleep(1000);
		tt.m2();
		Thread.sleep(1000);
		System.out.println("tt2:b = "+ tt.b);
	
	}
}

