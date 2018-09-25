package com.dcits.galaxy.example.exp1;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.galaxy.example.exp1.api.DemoService;
import com.dcits.galaxy.example.exp1.api.User;

public class MyDemoServiceTest extends TestCase {

	private ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml");
	private DemoService service = (DemoService) context.getBean("myDemoService");
	
	public void myDemoServiceTest1() {
		try {
			
			String str = service.sayHello("xcy");
			System.out.println("str = " + str);
			assertEquals("Hello xcy", str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void myDemoServiceTest2() {
		User user = service.getUser("wyq");
		System.out.println(user);
	}
}
