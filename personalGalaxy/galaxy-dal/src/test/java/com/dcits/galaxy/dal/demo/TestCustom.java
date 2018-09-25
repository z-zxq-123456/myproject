package com.dcits.galaxy.dal.demo;

import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.demo.service.ICustomService;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.junit.TestBase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestCustom extends TestBase {

	private ICustomService service = (ICustomService) context.getBean("customService");

	public void testInsertBatch() {

		List<Custom> list = new ArrayList<Custom>();
		for (int i = 11; i <= 100; i++) {
			Custom custom = new Custom();
			custom.setCustomId(new BigInteger(i+""));
			custom.setCustomName("customName"+i);
			custom.setCustomType("P");
			custom.setAge(30+i);
			custom.setAmt(new BigDecimal("987654321.09"));
			
			list.add(custom);
		}
		
		service.insertBatch(list);
	}
	
	public void testCount(){
		String statement = "findTotal";
		Custom custom = new Custom();
		custom.setCustomType("P");
		List<Object> resultList = service.query(statement, custom);
		System.out.println("FindCount result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testConverge(){
		String statement = "findConverge";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindCount result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testOrder(){
		String statement = "findOrderBy";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindCount result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindPage() {
		String statement = "findPage";
		Custom custom = new Custom();
		custom.setCustomId(new BigInteger("1"));
		RowArgs rowArgs = new RowArgs(50, 10);
		ParameterWrapper paraWrapper = new ParameterWrapper();
		paraWrapper.setBaseParam(custom);
		paraWrapper.setRowArgs(rowArgs);
		
		List<Object> resultList = service.query(statement, paraWrapper);
		System.out.println("FindPage result: ");
		for( Object result : resultList ){
			System.out.println( result );
		}
	}
}
