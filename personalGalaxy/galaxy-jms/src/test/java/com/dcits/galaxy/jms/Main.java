package com.dcits.galaxy.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/server.xml")){
			
		}

		synchronized (Main.class) {
			while (true) {
				try {
					Main.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
