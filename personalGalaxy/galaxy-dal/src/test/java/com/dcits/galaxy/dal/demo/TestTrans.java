package com.dcits.galaxy.dal.demo;

import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.ITransService;
import com.dcits.galaxy.junit.TestBase;

public class TestTrans extends TestBase {

	private ITransService service = (ITransService) context.getBean("transService");
	
	//测试事务嵌套
	public void test1(){
		User user = new User();
		user.setSid(SeqUtils.getNumberSeq().longValue());
		user.setUserName("TransactionTest");
		service.testTrans( user );
	}
}
