package com.dcits.galaxy.cache.template;

import junit.framework.TestCase;

import com.dcits.galaxy.core.serializer.SerializationUtils;

public class SerializableTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testHesssion(){
		try {
//			byte[] bytes = "456".getBytes("UTF-8");
			byte[] bytes = new byte[]{Integer.valueOf(456).byteValue()};
			System.out.println(SerializationUtils.deserialize(bytes));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
