package com.dcits.orion.stria.test.service;


import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.stria.test.TestStriaBase;

public class TestSpring extends TestStriaBase {
	
	public void testService() throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Test1 t111 = SpringApplicationContext.getContext().getBean(Test1.class);
				t111.setName(".....t111");
				for (int i=0;i<100;i++){
					System.out.println(Thread.currentThread().getName()+":"+t111.getName()+","+t111.getAge());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Test1 t11 = SpringApplicationContext.getContext().getBean(Test1.class);
				for (int i=0;i<1000;i++){
					t11.setName(".....t11");
					t11.setAge(""+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+t11.getName()+","+t11.getAge());
				}
			}
		});
		
		t1.start();
		t2.start();
		
		Thread.sleep(100000);
	}

}
