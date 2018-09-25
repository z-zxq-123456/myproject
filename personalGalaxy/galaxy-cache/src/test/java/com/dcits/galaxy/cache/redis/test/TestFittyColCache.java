package com.dcits.galaxy.cache.redis.test;

import java.util.Date;

import com.dcits.galaxy.cache.api.ICacheStore;
import com.dcits.galaxy.junit.TestBase;

public class TestFittyColCache extends TestBase implements ICacheStore{
	public  void testGetRow() {										
		Date startTime1 = new Date();
		for(int i=0;i<3500;i++){
			 Date startTime = new Date();
			 Date endTime = new Date();
			 if((endTime.getTime()-startTime.getTime())>1){
				 System.out.println("i="+i+" diff="+(endTime.getTime()-startTime.getTime()));
			 }
			
		}
		 System.out.println(" diff="+(new Date().getTime()-startTime1.getTime()));	
		//System.out.println(user);	
	}
	
	
	
}
