package com.dcits.galaxy.cache.redis.test;

import com.dcits.galaxy.cache.utils.JedisUtils;
import com.dcits.galaxy.junit.TestBase;

public class TestJedisUtils extends TestBase {
	
	public void testInsertRow(){
		for(int i=1;i<450;i++){
		 JedisUtils.setResource("txl:test:"+i,"name"+i,"txl"+i);
		}
		for(int i=1;i<450;i++){
			System.out.println(JedisUtils.getResource("txl:test:"+i,"name"+i));
		}
	}

}